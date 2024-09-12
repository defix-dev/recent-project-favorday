package defix.favordayapp.services.post;

import defix.favordayapp.db.mongo.entity.Post;
import defix.favordayapp.db.mongo.repositories.PostRepository;
import defix.favordayapp.mongoUtils.MongoRepoService;
import defix.favordayapp.services.post.exceptions.PostDoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService extends MongoRepoService<Post, Long> {
    @Autowired
    public PostService(PostRepository repository) {
        super(repository);
    }

    public Post loadPostById(long id) throws PostDoNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(PostDoNotFoundException::new);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public void savePostByDTO(PostDTO postDTO, String author) {
        Post post = new Post();
        post.setId(generateNextId());
        post.setPostSettings(postDTO.getSettings());
        post.setTitle(postDTO.getTitle());
        post.setAuthor(author);
        post.setContent(postDTO.getContent());
        post.setShortPart(postDTO.getShortPart());
        post.setPublishDate(LocalDate.now());
        post.setTags(postDTO.getTags());
        post.setVoiceCount(0);

        repository.save(post);
    }
}
