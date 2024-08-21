package defix.favordayapp.services.localization.utils;

import defix.favordayapp.services.localization.Language;

import java.util.Arrays;
import java.util.List;

public class LanguageHelper {
    public static List<String> getShortRecordValues() {
        return Arrays.stream(Language.values()).map(Language::getShortRecord).toList();
    }

    public static boolean isPrefixExists(String uri) {
        int secondSlashIndex = uri.indexOf('/', 1);

        String prefix;
        if (secondSlashIndex != -1) {
            prefix = uri.substring(1, secondSlashIndex);
        } else {
            prefix = uri.substring(1);
        }

        return LanguageHelper.getShortRecordValues().stream().anyMatch(value -> value.equals(prefix));
    }

    public static Language getLangPrefixFromUri(String uri) {
        if (!isPrefixExists(uri)) {
            throw new IllegalArgumentException("Prefix does not exist in the URI: " + uri);
        }

        int secondSlashIndex = uri.indexOf('/', 1);
        String langPrefix;

        if (secondSlashIndex != -1) {
            langPrefix = uri.substring(1, secondSlashIndex);
        } else {
            langPrefix = uri.substring(1);
        }

        return Arrays.stream(Language.values())
                .filter(value -> value.getShortRecord().equals(langPrefix))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching language found for prefix: " + langPrefix));
    }
}
