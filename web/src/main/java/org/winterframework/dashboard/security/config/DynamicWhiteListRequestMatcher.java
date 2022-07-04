package org.winterframework.dashboard.security.config;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class DynamicWhiteListRequestMatcher extends PermitAllRequestMatcher {

    private final Set<String> whiteList = new HashSet<>();


    public DynamicWhiteListRequestMatcher() {
        //TODO dynamic config with database
        whiteList.add("/");
        whiteList.add("/favicon.ico");
        whiteList.add("/auth/token");
        whiteList.add("/auth/wechat");
        whiteList.add("/files/public-assets/**");
    }


    @Override
    protected Set<String> getPaths() {
        return whiteList;
    }
}
