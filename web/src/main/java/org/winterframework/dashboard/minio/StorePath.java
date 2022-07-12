package org.winterframework.dashboard.minio;

public enum StorePath {
    UserProfile(FileType.UserProfile, "avatar");

    private final FileType fileType;
    private final String dir;

    StorePath(FileType maintenance, String image) {
        this.fileType = maintenance;
        this.dir = image;
    }

    public FileType bucket() {
        return fileType;
    }

    public String dir() {
        return dir;
    }
}
