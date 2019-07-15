package angel.service.entrypost;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IEntryPostRepository extends ReactiveMongoRepository<EntryPost, Integer>  {

	Flux<EntryPost> findById(final String name);
	Flux<EntryPost> findByContents(final String name);
	Flux<EntryPost> findAllById(final String name);
	Flux<EntryPost> findByPhoto1(final String name);
}
