package org.winterframework.dashboard.security.permission;

public enum UserRole {
    ADMIN(1, "admin"),
    ;

    private final int id;
    private final String code;

    public int id() {
        return id;
    }

    public String code() {
        return code;
    }

    UserRole(int id, String code) {
        this.id = id;
        this.code = code;
    }
}
