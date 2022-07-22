package cn.wintersoft.dashboard.security.access;

import cn.wintersoft.dashboard.security.exception.DataPermissionException;
import cn.wintersoft.dashboard.security.permission.UserRole;
import cn.wintersoft.dashboard.security.permission.handler.DataPermissionHandler;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import cn.wintersoft.dashboard.security.core.JwtAuthenticationToken;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据权限调度器
 */
public class DataPermissionEvaluator implements PermissionEvaluator {

    private final List<DataPermissionHandler> handlers;
    private volatile Map<String, DataPermissionHandler> handlerMap;

    public DataPermissionEvaluator(List<DataPermissionHandler> handlers) {
        this.handlers = handlers;
        init();
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

            if (handlerMap.containsKey(permission.toString())) {
                DataPermissionHandler handler = handlerMap.get(permission.toString());
                return handler.hasPermission(jwtAuthenticationToken, targetDomainObject);
            }

            throw new DataPermissionException("No permission handler found for permission: " + permission);
        }
        return false;
    }

    public void init() {
        if (handlerMap == null) {
            synchronized (this) {
                if (handlerMap == null) {
                    if (handlers != null) {
                        handlerMap = handlers.stream().collect(
                                Collectors.toMap(DataPermissionHandler::getSupportPermission, h -> h));
                    } else {
                        handlerMap = new HashMap<>();
                    }
                }
            }
        }
    }

}
