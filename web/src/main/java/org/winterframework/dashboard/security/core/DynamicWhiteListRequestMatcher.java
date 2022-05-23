package org.winterframework.dashboard.security.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class DynamicWhiteListRequestMatcher implements RequestMatcher {

    private final Set<String> whiteList = new HashSet<>();

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public DynamicWhiteListRequestMatcher() {
        //TODO dynamic config with database
        whiteList.add("/");
        whiteList.add("/favicon.ico");
        whiteList.add("/auth/**");
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        for (String s : whiteList) {
            if (antPathMatcher.match(s, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

}
