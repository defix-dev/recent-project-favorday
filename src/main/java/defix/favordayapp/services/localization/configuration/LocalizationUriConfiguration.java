package defix.favordayapp.services.localization.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "localization.uri")
@Getter
@Setter
public class LocalizationUriConfiguration {
    private List<String> denyPath;
}
