package defix.favordayapp.postPage;

import defix.favordayapp.db.mongo.entity.Post;
import defix.favordayapp.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostRestController {
    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    /*@GetMapping("posts/{id}")
    public Post postPage(@PathVariable long id) {
        return postService.loadPostById(id);
    }*/
}
