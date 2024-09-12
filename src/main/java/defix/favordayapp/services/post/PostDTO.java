package defix.favordayapp.services.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO implements Serializable {
    private String title;
    private String shortPart;
    private String content;
    private PostSettings settings;
    private String[] tags = new String[] {
            "TEST-TAG"
    };
}
