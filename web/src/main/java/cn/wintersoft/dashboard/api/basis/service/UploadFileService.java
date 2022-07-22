package cn.wintersoft.dashboard.api.basis.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import cn.wintersoft.dashboard.api.basis.entity.UploadFile;
import cn.wintersoft.dashboard.api.basis.mapper.UploadFileMapper;
import cn.wintersoft.dashboard.api.basis.model.MinioObject;
import cn.wintersoft.dashboard.minio.DownloadFile;
import cn.wintersoft.dashboard.minio.FileType;
import cn.wintersoft.dashboard.minio.MinioProperties;
import cn.wintersoft.dashboard.security.utils.SecurityUtils;
import cn.wintersoft.dashboard.web.exception.ApiErrorException;
import cn.wintersoft.dashboard.web.model.ApiResCodes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Kyun
 * @since 2022-06-25
 */
@Service
@RequiredArgsConstructor
public class UploadFileService extends ServiceImpl<UploadFileMapper, UploadFile> implements IService<UploadFile> {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    public MinioObject downloadPublicFile(Long id) {
        // 获取文件信息
        UploadFile uploadFile = this.getById(id);
        if (uploadFile == null) {
            throw new ApiErrorException(ApiResCodes.Error.FILE_NOT_FOUNT, "文件不存在", HttpStatus.NOT_FOUND);
        }
        return getMinioObject(uploadFile);
    }

    public MinioObject downloadProtectedFile(Long id) {
        // 检查权限 获取文件信息
        UploadFile uploadFile = this.getById(id);
        if (uploadFile == null) {
            throw new ApiErrorException(ApiResCodes.Error.FILE_NOT_FOUNT, "文件不存在", HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(uploadFile.getCreateBy(), SecurityUtils.getUserId())) {
            throw new ApiErrorException(ApiResCodes.Error.FILE_NOT_FOUNT, "没有权限", HttpStatus.FORBIDDEN);
        }
        return getMinioObject(uploadFile);
    }

    @NotNull
    private MinioObject getMinioObject(UploadFile uploadFile) {
        MinioObject minioObject = new MinioObject();
        String filename = uploadFile.getFilename();
        minioObject.setFileName(filename);

        String bucket = uploadFile.getRelatedType();
        String path = uploadFile.getPath();

        GetObjectArgs args = GetObjectArgs.builder().bucket(bucket).object(path).build();
        GetObjectResponse response;
        try {
            response = minioClient.getObject(args);
        } catch (Exception e) {
            throw new ApiErrorException(ApiResCodes.Error.FILE_DOWNLOAD_ERROR, "下载文件出错");
        }
        minioObject.setObjectResponse(response);
        return minioObject;
    }

    public List<DownloadFile> getFiles(FileType maintenance, Long id) {
        LambdaQueryWrapper<UploadFile> wrapper =
                Wrappers.lambdaQuery(UploadFile.class).eq(UploadFile::getRelatedType, maintenance.value())
                        .eq(UploadFile::getRelatedId, id);
        List<UploadFile> entities = this.list(wrapper);
        return entities.stream().map(this::getDownloadFile).collect(Collectors.toList());
    }

    private DownloadFile getDownloadFile(UploadFile uploadFile) {
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setId(uploadFile.getId());
        downloadFile.setFileName(uploadFile.getFilename());
        //minio pre sign
        GetPresignedObjectUrlArgs args =
                GetPresignedObjectUrlArgs.builder()
                                         .bucket(uploadFile.getRelatedType())
                                         .object(uploadFile.getPath())
                                         .expiry(60 * 60 * 24)
                                         .method(Method.GET).build();
        try {
            String presignedObjectUrl = minioClient.getPresignedObjectUrl(args);
            presignedObjectUrl =
                    presignedObjectUrl.replace(minioProperties.getEndpoint(), minioProperties.getPreSignedEndpoint());
            downloadFile.setAccessUrl(presignedObjectUrl);
        } catch (Exception e) {
            log.error("获取文件下载地址失败", e);
        }
        return downloadFile;
    }

}
