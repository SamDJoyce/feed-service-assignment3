package org.ac.cst8277.Joyce.Samuel.feed_service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeedService {
	private final WebClient userClient = WebClient.create("http://localhost:8080");
	private final WebClient subClient  = WebClient.create("http://localhost:8082");
	private final WebClient postClient = WebClient.create("http://localhost:8081");
	
	
	public Flux<Post> getFeed(String token) {
	    return validateToken(token)
	        .flatMapMany(userId ->
	            getSubscriptions(token) // already uses token
	        )
	        .flatMap(producerId ->
	            getAllPostsByProducer(token, producerId)
	        );
	}
	
	private Flux<Post> getAllPostsByProducer(String token, Integer producerId) {
	    return postClient.get()
	        .uri(uriBuilder -> uriBuilder
	            .path("/post/from-user")
	            .queryParam("token", token)
	            .queryParam("producer_id", producerId)
	            .build())
	        .retrieve()
	        .bodyToFlux(Post.class);
	}
	
	private Flux<Integer> getSubscriptions(String token) {
	    return subClient.get()
	        .uri(uriBuilder -> uriBuilder
	            .path("/subscriptions")
	            .queryParam("token", token)
	            .build())
	        .retrieve()
	        .bodyToFlux(Integer.class);
	}
	
	private Mono<Integer> validateToken(String token) {
	    return userClient.get()
	        .uri(uriBuilder -> uriBuilder
	            .path("/users/validate")
	            .queryParam("token", token)
	            .build())
	        .retrieve()
	        .bodyToMono(Integer.class);
	}
}
