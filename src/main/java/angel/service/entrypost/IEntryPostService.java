package angel.service.entrypost;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IEntryPostService {
	public Flux<EntryPost> retrieveEntryPost(String userId, String from, String to, int pageSize, int pageNo, String sortFields);
}
