package defix.favordayapp.services.localization.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LocalizationConfiguration {
    private final LocalizationPathConfiguration pathConfiguration;
    private final LocalizationUriConfiguration uriConfiguration;
    private final LocalizationSessionConfiguration sessionConfiguration;
    private final LocalizationThymeleafConfiguration thymeleafConfiguration;

    @Autowired
    public LocalizationConfiguration(LocalizationPathConfiguration pathConfiguration,
                                     LocalizationUriConfiguration uriConfiguration,
                                     LocalizationSessionConfiguration sessionConfiguration,
                                     LocalizationThymeleafConfiguration thymeleafConfiguration) {
        this.pathConfiguration = pathConfiguration;
        this.uriConfiguration = uriConfiguration;
        this.sessionConfiguration = sessionConfiguration;
        this.thymeleafConfiguration = thymeleafConfiguration;
    }
}
