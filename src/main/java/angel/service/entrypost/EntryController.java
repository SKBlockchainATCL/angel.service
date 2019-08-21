package angel.service.entrypost;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

  // service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
  // service/entryPosts/belongToMe
  @GetMapping(value = "/entryPosts/belongTo/{userId}")
  public Flux<EntryPost> getEntryPosts(@PathVariable String userId, @RequestParam(value = "from", required = false) String from,
      @RequestParam(value = "to", required = false) String to, @RequestParam(value = "pageSize", required = false) int pageSize,
      @RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "sortFields", required = false) String sortFields,
      @RequestParam(value = "photoHashId1", required = false) byte[] photoHashId1) throws Exception{
    // get parameters
    // long boardId = Long.valueOf(request.pathVariable("id"));
    // Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    // code entity를정의하여 return예

    // 데이터가 많지 않을 것으로 예상되어 일단은 전체 조회로 진행
    // from, to 데이터를무한으로 설정( 예 : 1~10000 )으로 서버로 전
    // from : lastest 라고 전송시 분기로 최근 100개 전송
    // 서버에서 ipfs 진행 시 multipart 로 전송받아 처리하ㄱㅣ
    //
    Flux<EntryPost> entryPostMono = null;
    try{
      entryPostMono = entryPostService.retrieveEntryPost(userId, from, to, pageSize, pageNo, sortFields);
    } catch(Exception e){
      return null;
    }

    return entryPostMono;

  }
  // 전체 불러오는 api 가 필요하긴하다.

  // send multipart
  // https://github.com/ipfs/java-ipfs-http-client
  @PostMapping(value = "/entryPosts/belongTo/{userId}/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String createEntryPostsImages(@PathVariable String userId, @RequestPart MultipartFile imageFile) throws Exception{
    //List<MultipartFile> f = imageFile;
    //String photoHashId = entryPostService.uploadImages(f.get(0).getBytes());

    return "";

  }
  
  @PostMapping(value = "/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Flux<DataBuffer> requestBodyFlux(@RequestBody Flux<Part> parts) throws IOException {
    // create an async file channel to store the file on disk
    
 // pointer to the end of file offset
    AtomicInteger fileWriteOffset = new AtomicInteger(0);
    // error signal
    AtomicBoolean errorFlag = new AtomicBoolean(false);
    Flux<FilePart> fp = parts.ofType(FilePart.class);
    
    //final Mono<String> filename = partFluxDescription(fp);
    //String name = filename.map(map -> map.toString());
    //String str = filename.block();
    File file = new File("../t.jpg");
    System.out.println("file.getAbsolutePath()" + file.getAbsolutePath());
    try{
      file.createNewFile();
    } catch(IOException e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE);
   
    return fp.flatMap(fa -> fa.content()).doOnEach(fm -> {
          // read data from the incoming data buffer into a file array
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

//                     if (closeCondition.onTaskCompleted())
//                         try {
//                             LOGGER.info("closing after last part");
//                             fileChannel.close();
//                         } catch (IOException ignored) {
//                             ignored.printStackTrace();
//                         }
                 }

                 @Override
                 public void failed(Throwable exc, ByteBuffer attachment) {
                     // there as an error while writing to disk, set an error flag
                     errorFlag.set(true);
                     LOGGER.info("error saving file part {}", filePartOffset);
                 }
             });
           });
      
      //return partFluxDescription(parts);
  }

  private static String partMapDescription(MultiValueMap<String, Part> partsMap) {
      return partsMap.keySet().stream().sorted()
          .map(key -> partListDescription(partsMap.get(key)))
          .collect(Collectors.joining(",", "Map[", "]"));
  }

  private static Mono<String> partFluxDescription(Flux<? extends Part> partsFlux) {
      return partsFlux.log().collectList().map(EntryController::partListDescription);
  }

  private static String partListDescription(List<? extends Part> parts) {
      return parts.stream().map(EntryController::partDescription)
          .collect(Collectors.joining(",", "[", "]"));
  }

  private static String partDescription(Part part) {
      return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name() + part.content();
  }
  
  // service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
  // service/entryPosts/belongToMe
  @PostMapping(value = "/entryPosts/belongTo/{userId}")
  public Flux<EntryPost> createEntryPosts(@PathVariable String userId
  // , @RequestParam(value = "contentsid" , required=false) String contentsid
      , @RequestParam(value = "photoHashId1", required = false) String photoHashId1
      // , @RequestParam(value = "photoHashId2", required=false) String
      // photoHashId2
      // , @RequestParam(value = "photoHashId3", required=false) String
      // photoHashId3
      // , @RequestParam(value = "postedAt", required=false) String postedAt
      // , @RequestParam(value = "likes", required=false) BigInteger likes
      , @RequestParam(value = "description", required = true) String desc, @RequestParam(value = "serviceProgramId", required = false) String serviceProgramId)
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

    Flux<EntryPost> entryPostMono = entryPostService.createEntryPost(userId, photoHashId1, "", "", serviceProgramId);

    return entryPostMono;

  }
}
