package angel.service.entrypost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import angel.service.jsa.EntryPostContract;
import reactor.core.publisher.Flux;

@Service
public class EntryPostService<V> implements IEntryPostService{

	@Autowired
	private EntryPostContract entryPostContract; 
	
	@Autowired
	private IEntryPostRepository entryPostRepository;
	
	public Flux<EntryPost> retrieveEntryPost(String photo1) {

		Flux<EntryPost> entryPost = entryPostRepository.findByPhoto1(photo1);
		
		RemoteCall<String> t = entryPostContract.getEntryPost(photo1);
		EntryPost entryPost2 = new EntryPost();
		String ta = null;
		try {
			ta = t.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entryPost2.setContents(ta);

//부장님이 말씀하신 5가
//	ㅋ	entryPostRepository.findByPhoto1(photo1)
//				.map((Function<? super EntryPost, ? extends V>) mapper -> {
//					RemoteCall<String> t = entryPostContract.getEntryPost(photo1);
//					mapper.getId();
//					EntryPost entryPost2 = new EntryPost();
//					try {
//						String ta = t.send();
//						entryPost2.setContents(ta);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					return null;
//					
//					
//				});
	//entryPost.map(mapper)
		Flux<EntryPost> mergeObjectWithStream = Flux.merge(entryPost, Flux.just(entryPost2));;
		//Flux<EntryPost> mergeObjectWithStream = null;

		return mergeObjectWithStream;
	}

}
