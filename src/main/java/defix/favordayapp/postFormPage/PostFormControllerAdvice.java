package defix.favordayapp.postFormPage;

import defix.favordayapp.services.account.exceptions.UnCorrectAccountDataException;
import defix.favordayapp.services.post.exceptions.UnConfirmedStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PostFormControllerAdvice {
    @ExceptionHandler(UnConfirmedStateException.class)
    public ResponseEntity<String> handleUnConfirmedStateException(UnConfirmedStateException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }
}
