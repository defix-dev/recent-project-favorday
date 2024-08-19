package defix.favordayapp.postFormPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.db.mongodb.entity.Post;
import defix.favordayapp.db.mongodb.repositories.PostRepository;
import defix.favordayapp.services.post.PostDTO;
import defix.favordayapp.services.post.PostFormService;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;

@Controller
@RequestMapping("/post_form")
public class PostFormController {
    private final PostFormService postFormService;

    @Autowired
    public PostFormController(PostFormService postFormService) {
        this.postFormService = postFormService;
    }

    @GetMapping
    public String postForm() {
        return RedirectAdapter.changePage(PageConstants.POST_FORM);
    }

    @PostMapping("/create_post")
    public ResponseEntity<Void> createPost(@RequestBody PostDTO postDTO, @RequestParam(value = "confirm") boolean confirm) {
        postFormService.createPost(postDTO, confirm);
        return ResponseEntity.ok().build();
    }
}
