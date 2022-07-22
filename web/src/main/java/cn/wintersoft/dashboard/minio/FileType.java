package cn.wintersoft.dashboard.minio;

public enum FileType {
    BASIS("basis"),
    UserProfile("userprofile");

    private final String value;

    FileType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
