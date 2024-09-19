package defix.favordayapp.services.localization.utils.init;

import defix.favordayapp.configurations.PageConstants;
import defix.favordayapp.services.localization.Language;
import defix.favordayapp.services.localization.utils.LocalizationDataProvider;
import defix.favordayapp.services.localization.configuration.LocalizationConfiguration;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

@Service
public class LocalizationLoader {
    private final HttpSession session;
    private final LocalizationConfiguration configuration;

    @Autowired
    public LocalizationLoader(HttpSession session, LocalizationConfiguration configuration) {
        this.session = session;
        this.configuration = configuration;
    }

    public void load(PageConstants pageConstant, Model model) {
        String lang = (String) session.getAttribute(configuration
                .getSessionConfiguration().getLanguageKey());
        String pageName = pageConstant.getPageName();
        model.addAttribute(pageName + configuration.getThymeleafConfiguration().getLocalizationDictPrefix(),
                new LocalizationDataProvider(configuration.getPathConfiguration())
                .getWordsDict(lang, pageName));

        model.addAttribute("lang", getFullNameFromShortLang(lang));
    }

    private String getFullNameFromShortLang(String lang) {
        Optional<Language> optionalLanguage = Arrays.stream(Language.values()).filter(v -> v.getShortRecord().equals(lang)).findFirst();
        return optionalLanguage.orElseThrow().getFullName();
    }
}
