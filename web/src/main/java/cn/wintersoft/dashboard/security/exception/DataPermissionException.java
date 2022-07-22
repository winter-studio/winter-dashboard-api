package cn.wintersoft.dashboard.security.exception;

public class DataPermissionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataPermissionException(String message) {
        super(message);
    }
}
