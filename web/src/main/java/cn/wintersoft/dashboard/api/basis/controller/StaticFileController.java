package cn.wintersoft.dashboard.api.basis.controller;

import cn.wintersoft.dashboard.api.basis.service.UploadFileService;
import io.minio.GetObjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.wintersoft.dashboard.api.basis.model.MinioObject;

import java.util.Objects;

@Tag(name = "文件管理模块")
@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class StaticFileController {

    private final UploadFileService uploadFileService;

    @Operation(summary = "通过附件ID下载文件", description = "此接口用于下载图片等公开的文件，可用nginx缓存")
    @GetMapping("/public-assets/{id}")
    public ResponseEntity<Resource> downloadPublicStaticFile(@PathVariable Long id) {
        MinioObject downloadFile = uploadFileService.downloadPublicFile(id);
        return downloadStaticFile(downloadFile);
    }

    @Operation(summary = "通过附件ID下载文件", description = "此接口用于下载非公开的文件，需要登陆才能下载，不可缓存")
    @GetMapping("/protected-assets/{id}")
    public ResponseEntity<Resource> downloadProtectedStaticFile(@PathVariable Long id) {
        MinioObject downloadFile = uploadFileService.downloadProtectedFile(id);
        return downloadStaticFile(downloadFile);
    }

    @NotNull
    private ResponseEntity<Resource> downloadStaticFile(MinioObject downloadFile) {
        GetObjectResponse objectResponse = downloadFile.getObjectResponse();
        Resource resource = new InputStreamResource(objectResponse);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFile.getFileName());

        return ResponseEntity.ok()
                             .headers(headers)
                             .contentLength(Long.parseLong(
                                     Objects.requireNonNull(objectResponse.headers().get("Content-Length"))))
                             .contentType(MediaType.parseMediaType(
                                     Objects.requireNonNull(objectResponse.headers().get("Content-Type"))))
                             .body(resource);
    }

}
