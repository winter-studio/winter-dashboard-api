package cn.wintersoft.dashboard.minio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioRunner implements ApplicationRunner {

    private final MinioManager minioManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBuckets();
    }

    public void createBuckets() throws Exception {
        log.info("Creating MinIO buckets...");
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            String bucketName = fileType.value();
            minioManager.ensureBucket(bucketName);
        }

    }
}
