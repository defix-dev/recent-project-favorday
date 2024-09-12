package defix.favordayapp.leaderboardPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.leaderboard.LeaderboardDTO;
import defix.favordayapp.services.leaderboard.LeaderboardService;
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
    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public String leaderboard(Model model) {
        LinkedList<LeaderboardDTO> data =  leaderboardService.getLeaderboardData();
        model.addAttribute("leaderboardInfo", data);
        return RedirectAdapter.changePage(PageConstants.LEADERBOARD);
    }
}
