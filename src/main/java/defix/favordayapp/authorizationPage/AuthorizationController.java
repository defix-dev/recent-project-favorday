package defix.favordayapp.authorizationPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.account.AccountService;
import defix.favordayapp.services.account.RegisterDTO;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    private final AccountService accountService;

    @Autowired
    public AuthorizationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String authorizationPage() {
        return RedirectAdapter.changePage(PageConstants.AUTHORIZATION);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerAccount(@RequestBody RegisterDTO registerDTO) {
        accountService.registerAccount(registerDTO);
        return ResponseEntity.ok().build();
    }
}
