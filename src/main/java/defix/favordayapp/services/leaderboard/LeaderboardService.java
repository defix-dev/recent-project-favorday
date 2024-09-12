package defix.favordayapp.services.leaderboard;

import defix.favordayapp.db.mongo.entity.Post;
import defix.favordayapp.services.post.PostService;
import org.antlr.v4.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeaderboardService {
    private final PostService postService;

    @Autowired
    public LeaderboardService(PostService postService) {
        this.postService = postService;
    }

    public LinkedList<LeaderboardDTO> getLeaderboardData() {
        return sortLeaderboardData(convertPostToLeaderboardDTO(postService.getAllPosts()));
    }

    public List<LeaderboardDTO> convertPostToLeaderboardDTO(List<Post> posts) {
        return posts.stream().map(v -> new LeaderboardDTO(v.getAuthor(), v.getVoiceCount(), v.getId())).toList();
    }

    public LinkedList<LeaderboardDTO> sortLeaderboardData(List<LeaderboardDTO> data) {
        LinkedList<LeaderboardDTO> outputData = new LinkedList<>(data);
        outputData.sort(Comparator.comparingLong(LeaderboardDTO::getVoiceCount));
        Collections.reverse(outputData);
        return outputData;
    }
}
