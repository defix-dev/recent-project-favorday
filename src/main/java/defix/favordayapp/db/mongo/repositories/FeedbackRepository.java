package defix.favordayapp.db.mongo.repositories;

import defix.favordayapp.db.mongo.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback, Long> {
}
