package angel.service.entrypost;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EntryPostService {
	public Flux<ArrayList<EntryPost>> retrieveEntryPost(@NotNull String userId, String from, String to, int pageSize, int pageNo, String sortFields
     );
}
