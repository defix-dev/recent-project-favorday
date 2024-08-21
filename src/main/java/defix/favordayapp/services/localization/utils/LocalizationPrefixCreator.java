package defix.favordayapp.services.localization.utils;

import defix.favordayapp.services.localization.configuration.LocalizationConfiguration;
import defix.favordayapp.services.localization.configuration.LocalizationSessionConfiguration;
import defix.favordayapp.services.localization.configuration.LocalizationUriConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LocalizationPrefixCreator implements HandlerInterceptor {
    private final LocalizationSessionConfiguration sessionConfiguration;
    private final LocalizationUriConfiguration uriConfiguration;

    public LocalizationPrefixCreator(LocalizationSessionConfiguration sessionConfiguration, LocalizationUriConfiguration uriConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
        this.uriConfiguration = uriConfiguration;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(request.getSession().getAttribute(sessionConfiguration.getPrefixRemoveFlag()) == null
                && uriConfiguration.getDenyPath().stream().noneMatch(uri::startsWith)) {
            response.sendRedirect("/" + request.getSession().getAttribute(sessionConfiguration.getLanguageKey()) + request.getRequestURI());
        }
        else {
            request.getSession().removeAttribute(sessionConfiguration.getPrefixRemoveFlag());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
