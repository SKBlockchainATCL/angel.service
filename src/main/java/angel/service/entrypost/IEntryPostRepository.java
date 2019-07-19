package angel.service.entrypost;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IEntryPostRepository extends ReactiveMongoRepository<EntryPost, Integer>{

  Flux<EntryPost> findById(final String name);

  Flux<EntryPost> findByContents(final String name);

  Flux<EntryPost> findAllById(final String name);

  Flux<EntryPost> findByPhoto1(final String photo1);

  Flux<EntryPost> findByUserId(final String userId);

  Flux<EntryPost> findAllByUserId(final String userId);
  
  Flux<EntryPost> findAllByHashId(final String hashId, Sort sort);

}

