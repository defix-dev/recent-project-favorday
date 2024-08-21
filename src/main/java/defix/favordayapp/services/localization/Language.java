package defix.favordayapp.services.localization;

import lombok.Getter;

@Getter
public enum Language {
    DEFAULT("ru"),

    ENGLISH("en"),
    RUSSIAN("ru");

    private final String shortRecord;

    Language(String shortRecord) {
        this.shortRecord = shortRecord;
    }
}
