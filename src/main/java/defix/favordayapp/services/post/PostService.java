package defix.favordayapp.services.post;

import defix.favordayapp.db.mongodb.entity.Post;
import defix.favordayapp.db.mongodb.repositories.PostRepository;
import defix.favordayapp.services.post.exceptions.PostDoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    public static final int FIRST_ID = 1;
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public long getLastId() throws PostDoNotFoundException {
        List<Post> posts = sortPostByIdWithDirection(Sort.Direction.DESC);
        return posts.stream().findFirst().orElseThrow(PostDoNotFoundException::new).getId();
    }

    public long getFirstId() throws PostDoNotFoundException {
        List<Post> posts = sortPostByIdWithDirection(Sort.Direction.ASC);
        return posts.stream().findFirst().orElseThrow(PostDoNotFoundException::new).getId();
    }

    public List<Post> sortPostByIdWithDirection(Sort.Direction direction) {
        Pageable pageable = PageRequest.of(0, 1, direction, "_id");
        return postRepository.findAll(pageable).stream().toList();
    }

    public Post loadPostById(long id) throws PostDoNotFoundException {
        return postRepository
                .findById(id)
                .orElseThrow(PostDoNotFoundException::new);
    }

    public void savePostByDTO(PostDTO postDTO, String author) {
        Post post = new Post();
        post.setId(generateIdForNewPost());
        post.setPostSettings(postDTO.getSettings());
        post.setTitle(postDTO.getTitle());
        post.setAuthor(author);
        post.setContent(postDTO.getContent());
        post.setShortPart(postDTO.getShortPart());
        post.setPublishDate(LocalDate.now());
        post.setTags(postDTO.getTags());

        postRepository.save(post);
    }

    private long generateIdForNewPost() {
        try {
            return getLastId()+1;
        }
        catch (PostDoNotFoundException e) {
            return FIRST_ID;
        }
    }
}
