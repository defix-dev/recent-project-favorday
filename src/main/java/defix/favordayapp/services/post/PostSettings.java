package defix.favordayapp.services.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostSettings {
    private boolean anonymous;
    private boolean disallowComments;
    private boolean disallowRate;
}
