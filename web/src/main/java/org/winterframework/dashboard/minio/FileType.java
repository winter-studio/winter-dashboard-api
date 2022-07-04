package org.winterframework.dashboard.minio;

public enum FileType {
    BASIS("basis");

    private final String name;

    FileType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
