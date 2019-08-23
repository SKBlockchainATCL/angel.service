package angel.service.entrypost;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/service")
// service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
// 참고1 : https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
// 참고2 :
public class EntryController{
  Logger LOGGER = LoggerFactory.getLogger(EntryController.class);
  @Autowired
  private EntryPostServiceImpl entryPostService;
  
  private String fileName;
  
  // service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
  // service/entryPosts/belongToMe
  @PostMapping(value = "/entryposts/belongto/{userId}")
  public Flux<EntryPost> createEntryPosts(@PathVariable String userId
  // , @RequestParam(value = "contentsid" , required=false) String contentsid
      , @RequestParam("filepath") String filepath
      // , @RequestParam(value = "photoHashId2", required=false) String
      // photoHashId2
      // , @RequestParam(value = "photoHashId3", required=false) String
      // photoHashId3
      // , @RequestParam(value = "postedAt", required=false) String postedAt
      // , @RequestParam(value = "likes", required=false) BigInteger likes
      , @RequestParam(value = "description", required = false) String desc
      , @RequestParam(value = "serviceProgramId", required = false) String serviceProgramId)
      throws Exception{
    // get parameters
    // long boardId = Long.valueOf(request.pathVariable("id"));
    // Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    // code entity를정의하여 return예
    // 2019-08-19
    // "contentsid" backend 에서
    // photoHashId2, photoHashId3 삭제 사진 1개만 올리는 것으로 변경
    // photoHashId1 에 대해ipfs로 업로드후 back-end로 filehash값만전송( ipfs변경 추후고려 )
    // comment 숫자는 ramdom으로 설정
    // 서버에서 ipfs 진행 시 :byteArray로 전송받아 처리하ㄱㅣ
    String id = UUID.randomUUID().toString();
    Flux<EntryPost> entryPostMono = entryPostService.createEntryPost(id, userId, filepath, "", "", serviceProgramId);

    return entryPostMono;

  }
  // service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
  // service/entryPosts/belongToMe
  @GetMapping(value = "/entryPosts/belongTo/{userId}")
  public Flux<ArrayList<EntryPost>> getEntryPosts(@PathVariable String userId, @RequestParam(value = "from", required = false) String from,
      @RequestParam(value = "to", required = false) String to, @RequestParam(value = "pageSize", required = false) int pageSize,
      @RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "sortFields", required = false) String sortFields
       ) throws Exception{
    // get parameters
    // long boardId = Long.valueOf(request.pathVariable("id"));
    // Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    // code entity를정의하여 return예

    // 데이터가 많지 않을 것으로 예상되어 일단은 전체 조회로 진행
    // from, to 데이터를무한으로 설정( 예 : 1~10000 )으로 서버로 전
    // from : lastest 라고 전송시 분기로 최근 100개 전송
    // 서버에서 ipfs 진행 시 multipart 로 전송받아 처리하ㄱㅣ
    //
    Flux<ArrayList<EntryPost>> entryPostMono = null;
    try{
      entryPostMono = entryPostService.retrieveEntryPost(userId, from, to, pageSize, pageNo, sortFields);
    } catch(Exception e){
      return null;
    }
     //entryPostMono.last().map(e -> e.getContents)
    return entryPostMono;

  }
  // 전체 불러오는 api 가 필요하긴하다.

//  // send multipart
//  // https://github.com/ipfs/java-ipfs-http-client
//  @PostMapping(value = "/entryPosts/belongTo/{userId}/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//  public String createEntryPostsImages(@PathVariable String userId, @RequestPart MultipartFile imageFile) throws Exception{
//    //List<MultipartFile> f = imageFile;
//    //String photoHashId = entryPostService.uploadImages(f.get(0).getBytes());
//
//    return "";
//
//  }
  
  @PostMapping(value = "/entryPosts/belongTo/{userId}/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Flux<Object> requestBodyFlux(@PathVariable String userId, @RequestBody Flux<Part> parts){
    // create an async file channel to store the file on disk
    

    return parts
        .filter(part -> part instanceof FilePart) // only retain file parts
        .ofType(FilePart.class) // convert the flux to FilePart
        .flatMap(this::saveFile);
  }
  
  private Mono<Object> saveFile(FilePart filePart) {
//  fp.log();
    // pointer to the end of file offset
    AtomicInteger fileWriteOffset = new AtomicInteger(0);
    // error signal
    AtomicBoolean errorFlag = new AtomicBoolean(false);
  //final Mono<String> filename = partFluxDescription(fp);
  //String name = filename.map(map -> map.toString());
  //String str = filename.block();
 // Mono<String> filename = partFluxDescription(parts);
//  String contentsId = UUID.randomUUID().toString();// 서버에 generation
  
  //String filename2 = filename.block();
  //String filename2 = partListDescription((List<? extends Part>)parts.log().collectList().cast(String.class));
  //filename.map( new String(f));
  File file = new File("../"+filePart.filename());
  
  System.out.println("file.getAbsolutePath()" + file.getAbsolutePath());
  
  try{
    file.createNewFile();
  } catch(IOException e){
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
  AsynchronousFileChannel fileChannel;
  try{
    fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE);
    
    return filePart.content().doOnEach(fm -> {
      // read data from the incoming data buffer into a file array
      if (fm.hasValue() && !errorFlag.get()) {
         DataBuffer db = fm.get();
         int count = db.readableByteCount();
         byte[] bytes = new byte[count];
         db.read(bytes);
         // create a file channel compatible byte buffer
         final ByteBuffer byteBuffer = ByteBuffer.allocate(count);
         byteBuffer.put(bytes);
         byteBuffer.flip();
         
      // get the current write offset and increment by the buffer size
         final int filePartOffset = fileWriteOffset.getAndAdd(count);
         LOGGER.info("processing file part at offset {}", filePartOffset);
         // write the buffer to disk
         fileChannel.write(byteBuffer, filePartOffset, null, new CompletionHandler<Integer, ByteBuffer>() {
             @Override
             public void completed(Integer result, ByteBuffer attachment) {
                 // file part successfuly written to disk, clean up
                 LOGGER.info("done saving file part {}", filePartOffset);
                 byteBuffer.clear();

//                 if (closeCondition.onTaskCompleted())
//                     try {
//                         LOGGER.info("closing after last part");
//                         fileChannel.close();
//                     } catch (IOException ignored) {
//                         ignored.printStackTrace();
//                     }
             }

             @Override
             public void failed(Throwable exc, ByteBuffer attachment) {
                 // there as an error while writing to disk, set an error flag
                 errorFlag.set(true);
                 LOGGER.info("error saving file part {}", filePartOffset);
             }
         });
      }
       }).doOnComplete(() -> {
         // all done, close the file channel
         LOGGER.info("done processing file parts");
         try{
          fileChannel.close();
        } catch(IOException e){
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

     }).doOnError(t -> {
         // ooops there was an error
         LOGGER.info("error processing file parts");
         try {
             fileChannel.close();
         } catch (IOException ignored) {
         }
         // take last, map to a status string
     }).last().map(dataBuffer -> { 
       JSONObject jo1 = new JSONObject();
       jo1.put("filepath", file.getAbsolutePath());
       return jo1;
       });
  } catch(IOException e){
    // TODO Auto-generated catch block
    e.printStackTrace();
    
    return null;
  }
 
  
  }

  public String getFileName(){
    return fileName;
  }

  public void setFileName(String fileName){
    this.fileName = fileName;
  }
  
//  @RestController
//  @RequestMapping("/files")
//  public class FileController {
//
//      @GetMapping("/{fileName}")
//      public Mono<Resource> getFile(@PathVariable String fileName) {
//          return fileRepository.findByName(fileName)
//                   .map(name -> new FileSystemResource(name));
//      }
}
