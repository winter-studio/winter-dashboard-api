package org.winterframework.dashboard.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class DynamicWhiteListRequestMatcher extends PermitAllRequestMatcher {

    private final Set<String> whiteList = new HashSet<>();


    public DynamicWhiteListRequestMatcher() {
        //TODO dynamic config with database
        whiteList.add("/");
        whiteList.add("/favicon.ico");
        whiteList.add("/auth/**");
    }


    @Override
    protected Set<String> getPaths() {
        return whiteList;
    }
}
