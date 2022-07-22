package cn.wintersoft.dashboard.security.utils;

import cn.wintersoft.dashboard.security.core.JwtUserDetails;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import cn.wintersoft.dashboard.security.core.JwtAuthenticationToken;

import java.util.Collection;

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
        JwtUserDetails principal = getAuthentication().getPrincipal();
        return principal.getUserId();
    }

    public static String getUsername() {
        JwtUserDetails principal = getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    public static Claims getClimes() {
        JwtUserDetails principal = getAuthentication().getPrincipal();
        return principal.claims();
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
        JwtUserDetails principal = getAuthentication().getPrincipal();
        return principal.getAuthorities();
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

    public static boolean isAdmin() {
        return isAuthenticated() && getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("admin"));
    }
}
