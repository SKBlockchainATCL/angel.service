package angel.service.entrypost;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple8;
import angel.service.jsa.EntryPostContract;
import reactor.core.publisher.Flux;

@Service
public class EntryPostService implements IEntryPostService{

  @Autowired
  private EntryPostContract entryPostContract;

  @Autowired
  private IEntryPostRepository entryPostRepository;

  //string : 20190717skyang
  //hashTo : e8ebe9fb4ec36fd8edeafb4d7db1fe978d3e507bfb55eded13d5cce6f78cb214
  //string : 20190717skyang001
  //hashTo : 38375179a4d41165106b38eb404202c0dc7364fb31f9bb132b9881b1cacc2ef2
  public Flux<EntryPost> retrieveEntryPost(String userId, String from, String to, int pageSize, int pageNo, String sortFields){

    Flux<EntryPost> entryPost = null;
    RemoteCall<Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]>> rpcCall = null;

    // lamda식 내부의 변수범위 : 참고(https://xxxelppa.tistory.com/49)
    // lamda 식이 final인이뉴는 함수형 interface기 때문인데 관련변수는 static final로 취급된다.
    try{
      String hashId = "";//Sha256
      entryPost = entryPostRepository.findAllByHashId(hashId, Sort.by(Sort.Direction.ASC, "_id"))
          .map(e -> new EntryPost(e.getId(), e.getContents(), e.getPhoto1(), e.getPhoto2(), e.getPhoto3() 
                                 ,e.getPostedAt(),e.getLikes(), e.getServiceProgramId(), e.getUserId()));
      
      rpcCall = entryPostContract.getEntryPost(hashId.getBytes());
      Tuple8<byte[], byte[], byte[], byte[], byte[], BigInteger, byte[], byte[]> RpcResult = rpcCall.send();
      entryPost = entryPostRepository.findAllByUserId(userId).map(s -> new EntryPost(s.getId(), null, null, null, null, null, 0, 0, 0));

    } catch(Exception e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return entryPost;
  }
}
