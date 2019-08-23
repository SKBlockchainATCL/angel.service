package angel.service.entrypost;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple8;
import angel.service.jsa.EntryPostContract;
import reactor.core.publisher.Flux;

//retrieve - select - find
//modity - update - 
//remove - delete - 
//create - insert -
@Service
public class EntryPostServiceImpl implements EntryPostService{

  @Autowired
  private EntryPostContract entryPostContract;

  @Autowired
  private EntryPostRepository entryPostRepository;

  // string : 20190717skyang
  // hashTo : e8ebe9fb4ec36fd8edeafb4d7db1fe978d3e507bfb55eded13d5cce6f78cb214
  // string : 20190717skyang001
  // hashTo : 38375179a4d41165106b38eb404202c0dc7364fb31f9bb132b9881b1cacc2ef2
  /**
   * 
   */
  public Flux<ArrayList<EntryPost>> retrieveEntryPost(@NotNull String userId, String from, String to, int pageSize, int pageNo, String sortFields){

    ArrayList<EntryPost> entryArr = new ArrayList<EntryPost>();
    EntryPost entryPost = null;
    RemoteCall<BigInteger> rpcCall1 = null;
    RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>> rpcCall2 = null;
    Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]> RpcResult = null;
    // lamda식 내부의 변수범위 : 참고(https://xxxelppa.tistory.com/49)
    // lamda 식이 final인이뉴는 함수형 interface기 때문인데 관련변수는 static final로 취급된다.
    try{

      // 01. ipfs 에서 파일 조회
      //IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

      if(from.equals("lastest")){
        // indexing
        rpcCall1 = entryPostContract.getEntryPostLengthByUser(stringToBytes32(userId));
        BigInteger bi = rpcCall1.send();
        for(int i = 0; BigInteger.valueOf(i).compareTo(bi) < 0; i++) {
          rpcCall2 = entryPostContract.getEntryPost(stringToBytes32(userId), BigInteger.valueOf(i));
          //rpcCall2.send();
          RpcResult = rpcCall2.send();
//          return ( entry.contentsId
//              ,entry.photoId1
//              ,entry.photoId2
//              ,entry.photoId3
//              ,entry.postedAt
//              ,entry.likes
//              ,entry.serviceProgramId
//              ,entry.userId
          
          //entrypost object 
//          public String id;
//          public String contents;
//          public String photo1;
//          public String photo2;
//          public String photo3;
//          public String postedAt;
//          public BigInteger likes;
//          public String serviceProgramId;
//          public String userId;
          new String(RpcResult.getValue1());
          entryPost = new EntryPost("", 
              new String(RpcResult.getValue1()), 
              new String(RpcResult.getValue2()), 
              new String(RpcResult.getValue3()),
              new String(RpcResult.getValue4()), 
              new String(RpcResult.getValue5()), 
              RpcResult.getValue6(), 
              new String(RpcResult.getValue7()),
              new String(RpcResult.getValue8()));
          
          entryArr.add(entryPost);
        }
        


      } else{

      }

      // searching hash index
      // String hashId =
      // "38375179a4d41165106b38eb404202c0dc7364fb31f9bb132b9881b1cacc2ef2";//Sha256
      //
      //rpcCall = entryPostContract.getEntryPost(userId.getBytes());

      //Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]> RpcResult = rpcCall.send();



      //Multihash filePointer = Multihash.fromBase58("QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB");
      //byte[] fileContents = ipfs.cat(filePointer);

    } catch(Exception e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return Flux.just(entryArr);
  }

  /**
   * 
   * @param userId
   * @param from
   * @param to
   * @param pageSize
   * @param pageNo
   * @param sortFields
   * @return
   */
  // bytes32 contentsId,
  // bytes32 photoId1,
  // bytes32 photoId2,
  // bytes32 photoId3,
  // bytes32 postedAt,
  // uint256 likes,
  // bytes32 serviceProgramId,
  // bytes32 userId
  public Flux<EntryPost> createEntryPost(String id, String userId, String filepath, String photoHashId2, String photoHashId3, String serviceProgramId){

    Flux<EntryPost> entryPost = null;
    // RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger,
    // byte[], byte[]>> rpcCall = null;

    // lamda식 내부의 변수범위 : 참고(https://xxxelppa.tistory.com/49)
    // lamda 식이 final인이뉴는 함수형 interface기 때문인데 관련변수는 static final로 취급된다.
    try{
      String contentsId = UUID.randomUUID().toString();// 서버에 generation

      SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
      String postedAt = f.format(new Date());// 서버시간으로 generation

      BigInteger likes = BigInteger.valueOf(0); // 0으로 세팅

      // IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
      //IPFS ipfs = new IPFS("/ip4/104.236.176.52/tcp/4001/ipfs/qmsolnsgccfuzqjzradhn95w2crsfmzutddwp8hxahca9z");
      //ipfs.refs.local();
      
      File path = new File(".");
      System.out.println(path.getAbsolutePath());
      
      //NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("hello.txt", "G'day world! IPFS rocks!".getBytes());
      //MerkleNode addResult = ipfs.add(file).get(0);
      // 파일명가져오
      //addResult.name.get();
      // hash 값 - 조회시 전달필
      //addResult.hash.toBase58();
      // create redis Data
      // String hashId = "";
      // create smart contract data
      //Image path
      RemoteCall<TransactionReceipt> rpcCall = entryPostContract.setEntryPost(
          stringToBytes32(contentsId),
          stringToBytes32(filepath),
          //addResult.name.get().getBytes(),
          stringToBytes32(""),
          stringToBytes32(""),
          stringToBytes32(postedAt),
          likes, 
          stringToBytes32(serviceProgramId), 
          stringToBytes32(userId));

      TransactionReceipt tR = rpcCall.send();
      List<Log> log = tR.getLogs();
      // log object 선언
    } catch(Exception e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return entryPost;
  }
  
  public static byte[] stringToBytes32(String string) {
    byte[] byteValue = string.getBytes();
    byte[] byteValueLen32 = new byte[32];
    System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
    return new Bytes32(byteValueLen32).getValue();
}

  public String uploadImages(byte[] bytes){
    // TODO Auto-generated method stub
    File f = new File("/");// need filename + url
    System.out.println(f.getAbsolutePath());
    FileOutputStream fos = null;
    try{
      fos = new FileOutputStream(f);
      fos.write(bytes);
    } catch(IOException e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try{
        fos.close();
      } catch(IOException e){
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    return null;
  }
}
