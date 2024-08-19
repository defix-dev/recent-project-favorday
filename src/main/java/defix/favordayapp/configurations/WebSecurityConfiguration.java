package defix.favordayapp.configurations;

import defix.favordayapp.services.account.AccountService;
import defix.favordayapp.services.localization.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    private final AccountService accountService;

    @Autowired
    public WebSecurityConfiguration(AccountService accountService) {
        this.accountService = accountService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(accountService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(httpRequests ->
                        httpRequests.requestMatchers("/authorization/**", "/home", "/recommendations/**", "/", "/js/**", "/images/**", "/css/**")
                                .permitAll().anyRequest().authenticated())
                .formLogin(login ->
                        login.loginPage("/authorization")
                                .passwordParameter("log-password")
                                .usernameParameter("log-username")
                                .defaultSuccessUrl("/home"))
                .logout(logout -> logout.logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/success_logout"));
        return http.build();
    }
}
