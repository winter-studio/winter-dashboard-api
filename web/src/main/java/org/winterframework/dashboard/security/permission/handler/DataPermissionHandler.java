package org.winterframework.dashboard.security.permission.handler;

import org.winterframework.dashboard.security.core.JwtAuthenticationToken;

public interface DataPermissionHandler {
    boolean supports(String permission);

    boolean hasPermission(JwtAuthenticationToken authentication, Object targetDomainObject);
}
