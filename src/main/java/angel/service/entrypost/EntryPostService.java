package angel.service.entrypost;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EntryPostService {
	public Flux<EntryPost> retrieveEntryPost(@NotNull String userId, String from, String to, int pageSize, int pageNo, String sortFields
     );
}
