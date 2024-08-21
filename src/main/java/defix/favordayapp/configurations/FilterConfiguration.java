package defix.favordayapp.configurations;

import defix.favordayapp.services.localization.configuration.LocalizationSessionConfiguration;
import defix.favordayapp.services.localization.utils.LocalizationPrefixRemover;
import defix.favordayapp.services.localization.utils.init.LocalizationSessionInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    private final LocalizationSessionConfiguration sessionConfiguration;

    @Autowired
    public FilterConfiguration(LocalizationSessionConfiguration sessionConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
    }

    @Bean
    public FilterRegistrationBean<LocalizationPrefixRemover> prefixRemoveFilter() {
        FilterRegistrationBean<LocalizationPrefixRemover> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LocalizationPrefixRemover(sessionConfiguration));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<LocalizationSessionInit> sessionListener() {
        return new ServletListenerRegistrationBean<>(new LocalizationSessionInit(sessionConfiguration));
    }
}
