package cn.wintersoft.dashboard.security.permission;

import org.springframework.stereotype.Component;

@Component("Roles")
public class Roles {
    public static final String ADMIN = UserRole.ADMIN.code();
}
