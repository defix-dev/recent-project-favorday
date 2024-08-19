package defix.favordayapp.configurations;

import defix.favordayapp.services.localization.PersonalLocale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class LocalizationConfiguration {
    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
    public PersonalLocale personalLocale() {
        return new PersonalLocale(PersonalLocale.DEFAULT_LANGUAGE);
    }
}
