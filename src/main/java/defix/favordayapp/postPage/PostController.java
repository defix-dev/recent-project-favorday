package defix.favordayapp.postPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping
    public String post() {
        return RedirectAdapter.changePage(PageConstants.POST_PAGE);
    }
}
