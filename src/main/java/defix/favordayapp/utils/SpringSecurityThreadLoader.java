package defix.favordayapp.utils;

import defix.favordayapp.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityThreadLoader {
    private final AccountService accountService;

    @Autowired
    public SpringSecurityThreadLoader(AccountService accountService) {
        this.accountService = accountService;
    }

    public void loadContextByUsername(String username) {
        UserDetails userDetails = accountService.loadUserByUsername(username);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        ));
        SecurityContextHolder.setContext(context);
    }
}
