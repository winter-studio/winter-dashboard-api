package cn.wintersoft.dashboard.minio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DownloadFile {
    private Long id;
    private String fileName;
    private String accessUrl;
}
