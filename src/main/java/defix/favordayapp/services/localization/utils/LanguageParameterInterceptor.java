package defix.favordayapp.services.localization.utils;

import defix.favordayapp.services.localization.PersonalLocale;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LanguageParameterInterceptor implements HandlerInterceptor {
    private final PersonalLocale personalLocale;

    public LanguageParameterInterceptor(final PersonalLocale personalLocale) {
        this.personalLocale = personalLocale;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!validateUri(request.getRequestURI(), personalLocale.getLanguage())) {
            response.sendRedirect("/" + personalLocale.getLanguage() + request.getRequestURI());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean validateUri(String uri, String language) {
        String modifiedUri = uri.substring(1, uri.length() - 1);
        return modifiedUri.substring(0, modifiedUri.indexOf('/')).equals(language) ? true : false;
    }
}
