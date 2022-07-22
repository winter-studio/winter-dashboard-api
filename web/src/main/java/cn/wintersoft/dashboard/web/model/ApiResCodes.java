package cn.wintersoft.dashboard.web.model;

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
        public static final int WECHAT_OAUTH_ERROR = 20006;
        public static final int SMS_SEND_ERROR = 20007;
        public static final int SMS_SEND_TOO_MUCH = 20007;
        public static final int SMS_CODE_INVALID = 20008;
        public static final int WECHAT_REST_ERROR = 20009;
    }

    public static class Error {
        public static final int COMMON = 30000;

        public static final int FILE_NOT_FOUNT = 30001;
        public static final int FILE_DOWNLOAD_ERROR = 30002;
        public static final int PERMISSION_DECLINE = 30003;
    }

}
