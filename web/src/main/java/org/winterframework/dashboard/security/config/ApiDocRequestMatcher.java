package org.winterframework.dashboard.security.config;

import java.util.Set;

public class ApiDocRequestMatcher extends PermitAllRequestMatcher {

    private final Set<String> whiteList = Set.of("/v3/api-docs",
            "/swagger-resources/**",
            "/doc.html",
            "/webjars/**");


    @Override
    protected Set<String> getPaths() {
        return whiteList;
    }
}
