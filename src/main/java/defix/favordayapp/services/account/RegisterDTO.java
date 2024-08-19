package defix.favordayapp.services.account;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "Username is required!")
    private String username;

    private String name;
    private String surname;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm Password is required!")
    private String confirmPassword;
}
