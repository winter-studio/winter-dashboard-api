package org.winterframework.dashboard.minio;

public enum StorePath {
    UserProfile(FileType.BASIS, "user_profile");

    private final FileType fileType;
    private final String dir;

    StorePath(FileType maintenance, String image) {
        this.fileType = maintenance;
        this.dir = image;
    }

    public FileType getBucket() {
        return fileType;
    }

    public String getDir() {
        return dir;
    }
}
