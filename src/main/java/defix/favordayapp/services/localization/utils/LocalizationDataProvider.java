package defix.favordayapp.services.localization.utils;

import defix.favordayapp.services.localization.configuration.LocalizationPathConfiguration;
import defix.favordayapp.services.localization.utils.LanguageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LocalizationDataProvider {
    private final LocalizationPathConfiguration pathConfiguration;

    public LocalizationDataProvider(LocalizationPathConfiguration pathConfiguration) {
        this.pathConfiguration = pathConfiguration;
    }

    public Map<String, String> getWordsDict(String language, String pageName) {
        Map<String, String> words = new HashMap<>();

        Yaml yaml = new Yaml();
        InputStream stream = getClass().getClassLoader()
                .getResourceAsStream(pathConfiguration.getLocalizationsPath()
                        + pageName + pathConfiguration.getLocalizationFilePrefix() + ".yml");
        Map<String, Object> languages = yaml.load(stream);

        return (Map<String, String>) languages.get(language);
    }
}
