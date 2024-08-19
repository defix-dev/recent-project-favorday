package defix.favordayapp.services.account.exceptions;

import defix.favordayapp.services.account.RegisterDTO;
import javax.validation.ConstraintViolation;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class UnCorrectAccountDataException extends RuntimeException {
    private final Set<ConstraintViolation<RegisterDTO>> violations;

    public UnCorrectAccountDataException(Set<ConstraintViolation<RegisterDTO>> violations) {
        super();
        this.violations = violations;
    }
}
