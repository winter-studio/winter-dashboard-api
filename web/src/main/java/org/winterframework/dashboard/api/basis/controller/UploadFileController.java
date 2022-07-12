package org.winterframework.dashboard.api.basis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.winterframework.dashboard.api.basis.service.UploadFileService;
import org.winterframework.dashboard.web.model.ApiRes;

/**
 * @author Kyun
 * @since 2022-06-25
 */
@Tag(name = "文件管理模块")
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class UploadFileController {

    private final UploadFileService uploadFileService;

    @Operation(summary = "上传公开的文件", description = "上传公开的文件，返回访问路径")
    @PostMapping(path = "/public-assets",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiRes<String> uploadPublicFile(@RequestParam("file") MultipartFile file) throws Exception {
        String url = uploadFileService.uploadPublicFile(file);
        return ApiRes.success(url);
    }
}
