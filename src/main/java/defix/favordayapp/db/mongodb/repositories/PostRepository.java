package defix.favordayapp.db.mongodb.repositories;

import defix.favordayapp.db.mongodb.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, Long> {
}
