package defix.favordayapp.authorizationPage;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.account.AccountService;
import defix.favordayapp.services.account.RegisterDTO;
import defix.favordayapp.services.localization.utils.init.LocalizationLoader;
import defix.favordayapp.services.redirect.RedirectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    private final AccountService accountService;
    private final LocalizationLoader loader;

    @Autowired
    public AuthorizationController(AccountService accountService, LocalizationLoader loader) {
        this.accountService = accountService;
        this.loader = loader;
    }

    @GetMapping
    public String authorizationPage(Model model) {
        loader.load(PageConstants.NAVIGATION, model);
        return RedirectAdapter.changePage(PageConstants.AUTHORIZATION);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerAccount(@RequestBody RegisterDTO registerDTO) {
        accountService.registerAccount(registerDTO);
        return ResponseEntity.ok().build();
    }
}
