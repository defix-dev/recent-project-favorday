package defix.favordayapp.services.localization.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.boot.origin.SystemEnvironmentOrigin;

public class PrefixRemoveWrapper extends HttpServletRequestWrapper {
    private final String clippedUri;
    private final String clippedServletPath;

    public PrefixRemoveWrapper(HttpServletRequest request) {
        super(request);

        if(LanguageHelper.isPrefixExists(request.getRequestURI())) {
            clippedUri = getClippedUri(request.getRequestURI());
            clippedServletPath = getClippedServletPath(request.getServletPath());
            return;
        }

        clippedUri = request.getRequestURI();
        clippedServletPath = request.getServletPath();
    }

    private String getClippedUri(String uri) {
        return uri.substring(LanguageHelper.getLangPrefixFromUri(uri).getShortRecord().length()+1);
    }

    private String getClippedServletPath(String servletPath) {
        return getClippedUri(servletPath);
    }

    @Override
    public String getRequestURI() {
        return clippedUri;
    }

    @Override
    public String getServletPath() {
        return clippedServletPath;
    }
}
