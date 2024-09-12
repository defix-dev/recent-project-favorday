package defix.favordayapp.db.mongo.entity;

import defix.favordayapp.mongoUtils.UniqueObject;
import defix.favordayapp.services.post.PostSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "post_document")
public class Post implements UniqueObject {
    @Id
    private long id;
    private long voiceCount;
    private String title;
    private String shortPart;
    private String content;
    private String author;
    private LocalDate publishDate;
    private String[] tags;
    private PostSettings postSettings;
}
