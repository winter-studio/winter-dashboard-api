package org.winterframework.dashboard.web.model;

public final class ApiResCodes {

    public static class Ok {
        public static final int COMMON = 10000;
    }

    public static class Failure {
        public static final int COMMON = 20000;
        public static final int JWT_EXPIRED = COMMON + 1;
        public static final int JWT_INVALID = COMMON + 2;
        public static final int JWT_REVOKED = COMMON + 3;
        public static final int SECURITY = COMMON + 4;
        public static final int JWT_REFRESH_TOKEN_INVALID = COMMON + 5;


    }

    public static class Error {
        public static final int COMMON = 30000;

    }

}
