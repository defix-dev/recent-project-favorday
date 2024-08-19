package defix.favordayapp.services.localization;

import lombok.Getter;

@Getter
public class PersonalLocale {
    public static final String DEFAULT_LANGUAGE = Language.RUSSIAN.getShortRecord();
    private final String language;

    public PersonalLocale(String language) {
        this.language = language;
    }
}
