package defix.favordayapp.configurations;

import defix.favordayapp.services.localization.Language;
import defix.favordayapp.services.localization.PersonalLocale;
import defix.favordayapp.services.localization.utils.LanguageParameterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {
    private final PersonalLocale personalLocale;

    @Autowired
    public SpringMVCConfiguration(PersonalLocale personalLocale) {
        this.personalLocale = personalLocale;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LanguageParameterInterceptor(personalLocale));
    }
}
