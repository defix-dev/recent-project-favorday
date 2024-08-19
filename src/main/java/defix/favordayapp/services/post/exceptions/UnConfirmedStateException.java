package defix.favordayapp.services.post.exceptions;

public class UnConfirmedStateException extends RuntimeException {
    public UnConfirmedStateException() {
        super("Unconfirmed");
    }
}
