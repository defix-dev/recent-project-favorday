package defix.favordayapp.services.feedback;

import defix.favordayapp.db.mongo.entity.Feedback;
import defix.favordayapp.db.mongo.repositories.FeedbackRepository;
import defix.favordayapp.mongoUtils.MongoRepoService;
import defix.favordayapp.services.account.AccountService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService extends MongoRepoService<Feedback, Long> {
    private final AccountService accountService;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, AccountService accountService) {
        super(feedbackRepository);
        this.accountService = accountService;
    }

    public void publish(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setPublisher(accountService.getCurrentAccount().getUsername());
        feedback.setId(generateNextId());
        feedback.setPostId(dto.getPostId());
        feedback.setFeedback(dto.getFeedback());
        feedback.setPublishDate(LocalDate.now());

        repository.save(feedback);
    }

    public List<Feedback> getFeedbacksByPostId(long postId) {
        return repository.findAll().stream().filter(v -> v.getPostId() == postId).toList();
    }
}
