package defix.favordayapp.db.mongo.repositories;

import defix.favordayapp.db.mongo.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Long> {
}
