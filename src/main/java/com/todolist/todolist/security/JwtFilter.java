package com.todolist.todolist.security;

import com.todolist.todolist.service.LocalTimeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.temporal.ChronoUnit;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String loginPath = "/api/auth/login";
    private static final String heartbeatPath = "/api/heartbeat";

    @Autowired
    private LocalTimeProvider localTimeProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var requestUri = request.getRequestURI();
            var jwt = jwtTokenProvider.getTokenFromRequest(request);

            if (loginPath.equals(requestUri)) {
                jwt = null;
            }

            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                var userId = jwtTokenProvider.getUserIdFromToken(jwt);
                UserDetails userDetails = userDetailsServiceImplementation.loadUserById(userId);
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                if (!requestUri.equals(heartbeatPath)) {
                    jwt = jwtTokenProvider.refreshToken(jwt);
                    cookieService.setCookieHeader(response, cookieService.createTokenCookie(jwt));
                }
                var tokenExpiryTime = jwtTokenProvider.getTokenExpirationTime(jwt);
                response.addHeader("X-SESSION-TTL", Long.toString(ChronoUnit.SECONDS.between(localTimeProvider.localDateTimeNow(), tokenExpiryTime)));
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication", ex);
        }
        filterChain.doFilter(request, response);
    }
}
