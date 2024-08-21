package defix.favordayapp.homePage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.localization.utils.init.LocalizationLoader;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final LocalizationLoader loader;

    @Autowired
    public HomeController(LocalizationLoader loader) {
        this.loader = loader;
    }

    @GetMapping
    public String toHome(HttpSession session, Model model) {
        return RedirectAdapter.defaultRedirect("/home");
    }

    @GetMapping("/home")
    public String home(Model model) {
        loader.load(PageConstants.HOME, model);
        loader.load(PageConstants.NAVIGATION, model);
        return RedirectAdapter.changePage(PageConstants.HOME);
    }
}
