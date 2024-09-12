package defix.favordayapp.services.localization.utils;

import defix.favordayapp.services.localization.configuration.LocalizationSessionConfiguration;
import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class LocalizationPrefixRemover implements Filter {
    private final LocalizationSessionConfiguration sessionConfiguration;

    public LocalizationPrefixRemover(LocalizationSessionConfiguration sessionConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if(LanguageHelper.isPrefixExists(req.getRequestURI()) || req.getMethod().equals("POST")) {
            req.getSession().setAttribute(sessionConfiguration.getPrefixRemoveFlag(),
                    sessionConfiguration.getPrefixRemoveFlag());
        }

        PrefixRemoveWrapper reqWrapper = new PrefixRemoveWrapper(req);
        filterChain.doFilter(reqWrapper, servletResponse);
    }
}
