package defix.favordayapp.services.localization.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "localization.path")
@Getter
@Setter
public class LocalizationPathConfiguration {
    private String localizationsPath;
    private String localizationFilePrefix;
}
