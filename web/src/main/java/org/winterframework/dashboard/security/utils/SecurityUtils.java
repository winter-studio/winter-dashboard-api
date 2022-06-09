package org.winterframework.dashboard.security.utils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.winterframework.dashboard.security.core.JwtAuthenticationToken;
import org.winterframework.dashboard.security.core.JwtUserDetails;

@Slf4j
public class SecurityUtils {

    public static final int JWT_TOKEN_EXPIRED = 1;
    public static final int JWT_TOKEN_INVALID = 2;
    public static final int JWT_TOKEN_REVOKED = 3;
    private final static ThreadLocal<Integer> authenticationState = new ThreadLocal<>();

    public static void setAuthenticationState(int state) {
        authenticationState.set(state);
    }

    public static int getAuthenticationState() {
        return authenticationState.get() == null ? 0 : authenticationState.get();
    }

    public static void clearAuthenticationState() {
        authenticationState.remove();
    }

    public static Long getUserId() {
        final JwtAuthenticationToken authentication = getAuthentication();
        JwtUserDetails principal = authentication.getPrincipal();
        return principal.getUserId();
    }

    public static String getUsername() {
        final JwtAuthenticationToken authentication = getAuthentication();
        JwtUserDetails principal = authentication.getPrincipal();
        return principal.getUsername();
    }

    public static Claims getClimes() {
        final JwtAuthenticationToken authentication = getAuthentication();
        JwtUserDetails principal = authentication.getPrincipal();
        return principal.getClaims();
    }

    public static boolean isAuthenticated() {
        return getAuthentication() != null;
    }

    private static JwtAuthenticationToken getAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.debug("no authentication in security context found");
            return null;
        }
        if (!(authentication instanceof JwtAuthenticationToken)) {
            log.debug("authentication in security context is not a JwtAuthenticationToken");
            return null;
        }
        return (JwtAuthenticationToken) authentication;
    }

}
