package defix.favordayapp.services.localization;

import lombok.Getter;

@Getter
public enum Language {
    DEFAULT("ru", "Русский"),

    ENGLISH("en", "English"),
    RUSSIAN("ru", "Русский"),
    JAPANESE("jpn", "日本語");

    private final String shortRecord;
    private final String fullName;

    Language(String shortRecord, String fullName) {
        this.shortRecord = shortRecord;
        this.fullName = fullName;
    }

    Language(Language language) {
        this.shortRecord = language.shortRecord;
        this.fullName = language.fullName;
    }
}
