package org.winterframework.dashboard.minio;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                          .endpoint(minioProperties.getEndpoint())
                          .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                          .build();
    }

}
