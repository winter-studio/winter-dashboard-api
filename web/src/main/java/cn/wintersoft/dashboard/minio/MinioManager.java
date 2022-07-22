package cn.wintersoft.dashboard.minio;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Snowflake;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import static cn.hutool.core.text.StrPool.DOT;
import static cn.hutool.core.text.StrPool.SLASH;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioManager {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final Snowflake snowflake;

    public String putFileAndGetUrl(InputStream inputStream, long size, String fileName, StorePath storePath)
            throws Exception {
        String suffix = FileNameUtil.getSuffix(fileName);
        String fullPath = storePath.dir() + SLASH + snowflake.nextIdStr() + DOT + suffix;
        String bucket = storePath.bucket().value();

        try (inputStream) {
            PutObjectArgs args = PutObjectArgs.builder()
                                              .bucket(bucket)
                                              .object(fullPath)
                                              .stream(inputStream, size, -1).build();
            minioClient.putObject(args);
        }

        return minioProperties.getPreSignedEndpoint() + SLASH + bucket + SLASH + fullPath;
    }

    public void ensureBucket(String bucketName) throws Exception {
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
