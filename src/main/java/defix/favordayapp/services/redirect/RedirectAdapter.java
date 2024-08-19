package defix.favordayapp.services.redirect;

import defix.favordayapp.configurations.PageConstants;

import java.net.URI;

public class RedirectAdapter {
    public static String defaultRedirect(String targetUrl) {
        return buildRedirectUrl(targetUrl);
    }

    public static String changePage(PageConstants pageName) {
        return pageName.getPageName();
    }

    public static String buildRedirectUrl(String url) {
        return "redirect:" + url;
    }

    public static URI getLocation(String targetUrl) {
        return URI.create(targetUrl);
    }
}
