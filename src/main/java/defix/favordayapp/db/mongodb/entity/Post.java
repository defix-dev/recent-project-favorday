package defix.favordayapp.db.mongodb.entity;

import defix.favordayapp.services.post.PostSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "post_document")
public class Post {
    @Id
    private long id;
    private String title;
    private String shortPart;
    private String content;
    private String author;
    private LocalDate publishDate;
    private String[] tags;
    private PostSettings postSettings;
}
