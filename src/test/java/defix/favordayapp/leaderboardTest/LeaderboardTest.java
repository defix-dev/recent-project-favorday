package defix.favordayapp.leaderboardTest;

import defix.favordayapp.db.mongo.entity.Post;
import defix.favordayapp.services.leaderboard.LeaderboardDTO;
import defix.favordayapp.services.leaderboard.LeaderboardService;
import defix.favordayapp.services.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

@SpringBootTest
public class LeaderboardTest {
    private final Logger logger = LoggerFactory.getLogger(LeaderboardTest.class);

    @Autowired
    private PostService postService;

    @Autowired
    private LeaderboardService service;

    @Test
    @DisplayName("GET ALL POSTS TEST")
    public void testAllPostsData() {
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts) {
            System.out.println(post.getAuthor());
        }

        assertTrue(posts.size() > 0);
        assertTrue(posts.size() > 1);
        assertTrue(posts.size() > 2);
        assertTrue(posts.size() > 3);
        assertTrue(posts.size() > 10);
        assertTrue(posts.size() > 15);
    }

    @Test
    @DisplayName("CONVERT TO DTO TEST")
    public void testLeaderboard_convertToDto() {
        List<LeaderboardDTO> dto = service.convertPostToLeaderboardDTO(postService.getAllPosts());

        for (LeaderboardDTO ldto : dto)
            System.out.println(ldto.getAuthor());

        assertTrue(!dto.isEmpty());
    }
}
