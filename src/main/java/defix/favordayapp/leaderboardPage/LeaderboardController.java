package defix.favordayapp.leaderboardPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.leaderboard.LeaderboardDTO;
import defix.favordayapp.services.leaderboard.LeaderboardService;
import defix.favordayapp.services.localization.utils.init.LocalizationLoader;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.TreeSet;

@Controller
@RequestMapping("/leaderboard")
public class LeaderboardController {
    private final LocalizationLoader loader;

    @Autowired
    public LeaderboardController(LocalizationLoader loader) {
        this.loader = loader;
    }

    @GetMapping
    public String leaderboard(Model model) {
        //loader.load(PageConstants.LEADERBOARD, model);
        loader.load(PageConstants.NAVIGATION, model);
        return RedirectAdapter.changePage(PageConstants.LEADERBOARD);
    }
}
