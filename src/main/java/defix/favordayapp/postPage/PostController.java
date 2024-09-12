package defix.favordayapp.postPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.feedback.FeedbackDTO;
import defix.favordayapp.services.feedback.FeedbackService;
import defix.favordayapp.services.post.PostService;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final FeedbackService feedbackService;

    @Autowired
    public PostController(PostService postService, FeedbackService feedbackService) {
        this.postService = postService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{id}")
    public String post(@PathVariable long id, Model model) {
        model.addAttribute("postId", id);
        model.addAttribute("postInfo", postService.loadPostById(id));
        model.addAttribute("feedbacksInfo", feedbackService.getFeedbacksByPostId(id));
        return RedirectAdapter.changePage(PageConstants.POST_PAGE);
    }

    @PostMapping("/{id}/publish")
    public String publishFeedback(@PathVariable("id") long postId, @RequestParam("feedback-input") String feedback) {
        feedbackService.publish(new FeedbackDTO(postId, feedback));
        return RedirectAdapter.defaultRedirect("/posts/" + postId);
    }
}
