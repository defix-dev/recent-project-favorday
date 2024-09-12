package defix.favordayapp.postFormPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.localization.utils.init.LocalizationLoader;
import defix.favordayapp.services.post.PostDTO;
import defix.favordayapp.services.post.PostFormService;
import defix.favordayapp.services.post.PostReqSender;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;

@Controller
@RequestMapping("/post_form")
public class PostFormController {
    private final PostFormService postFormService;
    private final PostReqSender reqSender;
    private final LocalizationLoader loader;

    @Autowired
    public PostFormController(PostFormService postFormService, PostReqSender reqSender, LocalizationLoader loader) {
        this.postFormService = postFormService;
        this.reqSender = reqSender;
        this.loader = loader;
    }

    @GetMapping
    public String postForm(Model model) {
        loader.load(PageConstants.NAVIGATION, model);
        return RedirectAdapter.changePage(PageConstants.POST_FORM);
    }

    @PostMapping("/create_post")
    public ResponseEntity<Void> createPost(@RequestBody PostDTO postDTO, @RequestParam(value = "confirm") boolean confirm) {
        postFormService.createPost(postDTO, confirm);
        reqSender.notify(postDTO.getSettings());
        return ResponseEntity.ok().build();
    }
}
