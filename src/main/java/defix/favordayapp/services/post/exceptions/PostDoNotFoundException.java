package defix.favordayapp.services.post.exceptions;

public class PostDoNotFoundException extends RuntimeException {
    public PostDoNotFoundException() {
        super("Post Do Not Found");
    }
}
