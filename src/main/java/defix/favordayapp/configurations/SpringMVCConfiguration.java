package defix.favordayapp.configurations;

import defix.favordayapp.services.localization.configuration.LocalizationConfiguration;
import defix.favordayapp.services.localization.configuration.LocalizationSessionConfiguration;
import defix.favordayapp.services.localization.configuration.LocalizationUriConfiguration;
import defix.favordayapp.services.localization.utils.LocalizationPrefixCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {
    private final LocalizationConfiguration configuration;

    @Autowired
    public SpringMVCConfiguration(LocalizationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocalizationPrefixCreator(configuration.getSessionConfiguration(), configuration.getUriConfiguration()));
    }
}
