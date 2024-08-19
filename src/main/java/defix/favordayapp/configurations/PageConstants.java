package defix.favordayapp.configurations;

import lombok.Getter;

@Getter
public enum PageConstants {
    HOME("home"),
    POST_FORM("post_form"),
    LEADERBOARD("leaderboard"),
    SEARCH("search"),
    RECOMMENDATIONS("recommendations"),
    AUTHORIZATION("authorization"),
    POST_PAGE("post_page");

    private final String pageName;

    PageConstants(String pageName) {
        this.pageName = pageName;
    }
}
