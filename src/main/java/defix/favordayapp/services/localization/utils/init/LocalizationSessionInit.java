package defix.favordayapp.services.localization.utils.init;

import defix.favordayapp.services.localization.Language;

import defix.favordayapp.services.localization.configuration.LocalizationSessionConfiguration;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class LocalizationSessionInit implements HttpSessionListener {
    private final LocalizationSessionConfiguration sessionConfiguration;

    public LocalizationSessionInit(LocalizationSessionConfiguration sessionConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(sessionConfiguration.getLanguageKey(), Language.DEFAULT.getShortRecord());
        HttpSessionListener.super.sessionCreated(se);
    }
}
