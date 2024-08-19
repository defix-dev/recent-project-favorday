package defix.favordayapp.services.account.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Password Do Not Match");
    }
}
