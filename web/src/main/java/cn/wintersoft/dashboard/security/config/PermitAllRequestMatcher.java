package cn.wintersoft.dashboard.security.config;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;

public abstract class PermitAllRequestMatcher implements RequestMatcher {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean matches(HttpServletRequest request) {
        Set<String> paths = getPaths();
        for (String s : paths) {
            if (antPathMatcher.match(s, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    protected abstract Set<String> getPaths();

}
