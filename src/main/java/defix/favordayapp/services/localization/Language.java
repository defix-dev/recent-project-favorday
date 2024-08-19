package defix.favordayapp.services.localization;

import lombok.Getter;

@Getter
public enum Language {
    ENGLISH("en"),
    RUSSIAN("ru");

    private final String shortRecord;

    Language(String shortRecord) {
        this.shortRecord = shortRecord;
    }
}
