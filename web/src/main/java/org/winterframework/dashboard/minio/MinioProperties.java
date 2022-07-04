package org.winterframework.dashboard.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String endpoint;
    private String preSignedEndpoint;
    private String accessKey;
    private String secretKey;
}
