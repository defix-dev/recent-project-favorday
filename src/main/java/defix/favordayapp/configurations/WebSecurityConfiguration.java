package defix.favordayapp.configurations;

import defix.favordayapp.services.account.AccountService;
import defix.favordayapp.services.localization.Language;
import defix.favordayapp.services.localization.utils.LocalizationPrefixRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    private final AccountService accountService;
    private final FilterRegistrationBean<LocalizationPrefixRemover> prefixRemoveFilter;

    @Autowired
    public WebSecurityConfiguration(AccountService accountService, FilterRegistrationBean<LocalizationPrefixRemover> prefixRemoveFilter) {
        this.accountService = accountService;
        this.prefixRemoveFilter = prefixRemoveFilter;
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
        http.addFilterBefore(prefixRemoveFilter.getFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpRequests ->
                        httpRequests.requestMatchers("/localization/**", "/favicon.ico", "/authorization/**", "/home", "/recommendations/**", "/", "/js/**", "/images/**", "/css/**")
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
