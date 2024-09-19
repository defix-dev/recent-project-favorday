package defix.favordayapp.leaderboardPage;

import defix.favordayapp.services.leaderboard.LeaderboardDTO;
import defix.favordayapp.services.leaderboard.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {
    private final LeaderboardService service;

    @Autowired
    public LeaderboardRestController(LeaderboardService service) {
        this.service = service;
    }

    @GetMapping("/users_data")
    public LinkedList<LeaderboardDTO> getLeaderboardData() {
        return service.getLeaderboardData();
    }
}
