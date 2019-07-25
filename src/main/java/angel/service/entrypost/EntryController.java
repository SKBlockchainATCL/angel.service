package angel.service.entrypost;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/service",
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ParametersAreNonnullByDefault
@ThreadSafe
//service/entryPosts?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
//참고1 : https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
//참고2 : 
public class EntryController {
	
	@Autowired
	private EntryPostServiceImpl entryPostService;
	//service/entryPosts/belongTo/{userId}?from={from}&to={to}&pageSize={size}&pageNo={no}&sort={fields}
	//service/entryPosts/belongToMe
	@PostMapping(value = "/entryPosts/belongTo/{userId}")
	public Flux<EntryPost> getEntryPosts(@PathVariable String userId,
	                                     String from, 
	                                     String to,
	                                     int pageSize,
	                                     int pageNo,
	                                     String sortFields) {
		//get parameters 
		//long boardId = Long.valueOf(request.pathVariable("id"));
		//Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		//code entity를정의하여 return예
		Flux<EntryPost> entryPostMono = entryPostService
		                                  .retrieveEntryPost(userId
		                                                    ,from
		                                                    ,to
		                                                    ,pageSize
		                                                    ,pageNo
		                                                    ,sortFields);

		return entryPostMono;
		
	}	 	 	 	 
}
