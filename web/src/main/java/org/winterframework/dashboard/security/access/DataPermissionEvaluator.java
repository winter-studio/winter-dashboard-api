package org.winterframework.dashboard.security.access;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.winterframework.dashboard.security.core.JwtAuthenticationToken;
import org.winterframework.dashboard.security.exception.DataPermissionException;
import org.winterframework.dashboard.security.permission.UserRole;
import org.winterframework.dashboard.security.permission.handler.DataPermissionHandler;

import java.io.Serializable;
import java.util.List;

public class DataPermissionEvaluator implements PermissionEvaluator {

    private final List<DataPermissionHandler> handlers;

    public DataPermissionEvaluator(List<DataPermissionHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return handle(authentication, targetDomainObject, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return handle(authentication, targetId, permission);
    }

    private boolean handle(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            if (jwtAuthenticationToken.hasRole(UserRole.ADMIN)) {
                return true;
            }
            if (permission instanceof String permissionStr) {
                for (DataPermissionHandler handler : handlers) {
                    if (handler.supports(permissionStr)) {
                        return handler.hasPermission(jwtAuthenticationToken, targetDomainObject);
                    }
                }
            }
            throw new DataPermissionException("No permission handler found for permission: " + permission);
        }
        return false;
    }

}
