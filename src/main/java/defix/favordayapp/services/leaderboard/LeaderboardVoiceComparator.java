package defix.favordayapp.services.leaderboard;

import defix.favordayapp.db.mongo.entity.Post;

import java.util.Comparator;

public class LeaderboardVoiceComparator implements Comparator<LeaderboardDTO> {
    @Override
    public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
        if(o1.getVoiceCount() == o2.getVoiceCount())
            return 0;

        return (o1.getVoiceCount() - o2.getVoiceCount()) > 0 ? 1 : -1;
    }
}
