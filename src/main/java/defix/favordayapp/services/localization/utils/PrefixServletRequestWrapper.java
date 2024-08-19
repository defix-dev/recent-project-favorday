package defix.favordayapp.services.localization.utils;

import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class PrefixServletRequestWrapper extends HttpServletRequestWrapper {
    private final String newRequestUri;
    private final String newRequestServletPath;

    public PrefixServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String requestUri = request.getRequestURI().substring(1);
        requestUri = requestUri.substring(0, requestUri.indexOf('/'));
        newRequestUri = requestUri;
        String requestServletPath = request.getServletPath();
        newRequestServletPath = requestServletPath.isEmpty() ? requestUri : requestUri + requestServletPath;
    }

    @Override
    public String getRequestURI() {
        return newRequestUri;
    }

    @Override
    public String getServletPath() {
        return newRequestServletPath;
    }
}
