package org.winterframework.dashboard.web.model;

public final class ApiResCodes {

    public static class Ok {
        public static final int COMMON = 10000;
    }

    public static class Failure {
        public static final int COMMON = 20000;
        public static final int JWT_EXPIRED = 20001;
        public static final int JWT_INVALID = 20002;
        public static final int JWT_REVOKED = 20003;
        public static final int SECURITY = 20004;
        public static final int JWT_REFRESH_TOKEN_INVALID = 20005;


    }

    public static class Error {
        public static final int COMMON = 30000;

    }

}
