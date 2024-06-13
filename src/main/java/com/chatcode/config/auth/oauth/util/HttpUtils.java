package com.chatcode.config.auth.oauth.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpUtils {

    public static void setCookie(HttpServletResponse response, String key, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static String getCookieValue(Cookie[] cookies, String key) {
        return Arrays.stream(cookies)
            .filter(cookie -> key.equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);
    }

    public static String getQueryValue(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    public static String buildUri(String url, Map<String, Object> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        queryParams.forEach(builder::queryParam);
        return builder.build().toUriString();
    }

    public static String getDataFromHeader(HttpServletRequest request, String key) {
        return request.getHeader(key);
    }
}
