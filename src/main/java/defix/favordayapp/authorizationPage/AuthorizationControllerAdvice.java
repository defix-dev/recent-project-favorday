package defix.favordayapp.authorizationPage;

import defix.favordayapp.services.account.RegisterDTO;
import defix.favordayapp.services.account.exceptions.AccountAlreadyExistsException;
import defix.favordayapp.services.account.exceptions.PasswordDoNotMatchException;
import defix.favordayapp.services.account.exceptions.UnCorrectAccountDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.nio.channels.AcceptPendingException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthorizationControllerAdvice {
    @ExceptionHandler(UnCorrectAccountDataException.class)
    public ResponseEntity<Map<String, String>> handleUnCorrectAccountData(UnCorrectAccountDataException e) {
        Map<String, String> body = new HashMap<>();
        for(ConstraintViolation<RegisterDTO> violation : e.getViolations())
            body.put(violation.getPropertyPath().toString(), violation.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(PasswordDoNotMatchException.class)
    public ResponseEntity<String> handlePasswordDoNotMatch(PasswordDoNotMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountAlreadyExists(AccountAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
