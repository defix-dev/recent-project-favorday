package defix.favordayapp.db.mongo.entity;

import defix.favordayapp.mongoUtils.UniqueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "feedback_document")
public class Feedback implements UniqueObject {
    @Id
    private long id;
    private long postId;
    private String publisher;
    private String feedback;
    private LocalDate publishDate;
}
