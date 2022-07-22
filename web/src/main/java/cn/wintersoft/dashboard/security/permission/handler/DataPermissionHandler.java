package cn.wintersoft.dashboard.security.permission.handler;

import cn.wintersoft.dashboard.security.core.JwtAuthenticationToken;

public interface DataPermissionHandler {
    String getSupportPermission();

    boolean hasPermission(JwtAuthenticationToken authentication, Object targetDomainObject);
}
