package com.todolist.todolist.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class CookieService {
    private final String cookieName = "BASKETBALL_MANAGEMENT";
    @Value("${app.jwtExpirationMs}")
    private int sessionDuration;

    public ResponseCookie createTokenCookie(String token) {
        return ResponseCookie.from(cookieName, token)
                .secure(false)
                .httpOnly(true)
                .path("/")
                .maxAge(sessionDuration / 1000)
                .sameSite("Strict")
                .build();
    }

    public ResponseCookie createRemovalCookie() {
        return ResponseCookie.from(cookieName, "")
                .secure(false)
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
    }

    public void setCookieHeader(HttpServletResponse response, ResponseCookie cookie) {
        response.setHeader("Set-Cookie", cookie.toString());
    }

    public Optional<Cookie> getCookieFromRequest(HttpServletRequest request) {
        var cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (var cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }
}
