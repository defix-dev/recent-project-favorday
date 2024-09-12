package defix.favordayapp.services.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardDTO {
    private String author;
    private long voiceCount;
    private long postId;
}
