package defix.favordayapp.homePage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.db.mongodb.entity.Post;
import defix.favordayapp.db.mongodb.repositories.PostRepository;
import defix.favordayapp.services.localization.PersonalLocale;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    private PersonalLocale personalLocale;

    @GetMapping
    public String toHome() {
        System.out.println(personalLocale.getLanguage());
        return RedirectAdapter.defaultRedirect("/home");
    }

    @GetMapping("/home")
    public String home() {
        return RedirectAdapter.changePage(PageConstants.HOME);
    }
}
