package org.winterframework.dashboard.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioRunner implements ApplicationRunner {

    private final MinioClient minioClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBuckets();
    }

    public void createBuckets() throws Exception {
        log.info("Creating MinIO buckets...");
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            String bucketName = fileType.value();
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                MakeBucketArgs args = MakeBucketArgs.builder().bucket(bucketName).build();
                minioClient.makeBucket(args);
                log.info("Bucket {} created.", bucketName);
            } else {
                log.info("Bucket {} already exists.", bucketName);
            }
        }

    }
}
