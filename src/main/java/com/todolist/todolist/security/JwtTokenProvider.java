package com.todolist.todolist.security;

import com.todolist.todolist.service.LocalTimeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


@Component
@Slf4j
public class JwtTokenProvider {
    private final LocalTimeProvider localTimeProvider;
    private final CookieService cookieService;
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationTime;

    private Key signingKey;

    public JwtTokenProvider(LocalTimeProvider localTimeProvider, CookieService cookieService) {
        this.localTimeProvider = localTimeProvider;
        this.cookieService = cookieService;
    }

    @PostConstruct
    public void init() {
        signingKey = new SecretKeySpec(
                DatatypeConverter.parseBase64Binary(jwtSecret),
                SignatureAlgorithm.HS512.getJcaName()
        );
    }

    public String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer("POLSL")
                .setSubject(Long.toString(((UserPrincipal) auth.getPrincipal()).getId()))
                .setIssuedAt(localTimeProvider.localDate())
                .setExpiration(new Date(localTimeProvider.localDate().getTime() + jwtExpirationTime))
                .setId(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return cookieService.getCookieFromRequest(request).map(Cookie::getValue).orElse(null);
    }

    public Integer getUserIdFromToken(String token) {
        return Integer.parseInt(
                Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getSubject()
        );
    }

    public String refreshToken(String originalToken) {
        HashMap<String, Object> claims = new HashMap<>();
        var headerClaims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(originalToken);
        claims.put("sessionId", headerClaims.getBody().get("sessionId"));
        claims.put("authorities", headerClaims.getBody().get("authorities"));
        claims.put("level", headerClaims.getBody().get("level"));
        if (headerClaims.getBody().get("originalSub") != null) {
            claims.put("originalSub", headerClaims.getBody().get("originalSub"));
        }
        return Jwts.builder()
                .setIssuer("POLSL")
                .setClaims(claims)
                .setSubject(headerClaims.getBody().getSubject())
                .setIssuedAt(localTimeProvider.localDate())
                .setExpiration(new Date(localTimeProvider.localDate().getTime() + jwtExpirationTime))
                .setId(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }

    public LocalDateTime getTokenExpirationTime(String token) {
        return localTimeProvider.localDateTime(
                Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getExpiration()
        );
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            log.error("String is empty");
        }
        return false;
    }


}
