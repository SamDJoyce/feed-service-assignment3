package org.ac.cst8277.Joyce.Samuel.feed_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/feed")
public class FeedController {
	private final FeedService service;
 
	public FeedController(FeedService service) {
		this.service = service;
	}
	
	// Retrieve the user's feed
	@GetMapping
    public Flux<Post> getFeed(@RequestParam String token) {
        return service.getFeed(token);
    }
}
