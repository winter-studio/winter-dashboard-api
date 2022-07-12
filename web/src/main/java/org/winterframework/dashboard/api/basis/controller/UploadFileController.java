package org.winterframework.dashboard.api.basis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.basis.model.TemporaryFileUpload;
import org.winterframework.dashboard.web.model.ApiRes;

/**
 * @author Kyun
 * @since 2022-06-25
 */
@Tag(name = "文件管理模块")
@RestController
@RequestMapping("/files")
@Slf4j
public class UploadFileController {

    @Operation(summary = "上传临时文件", description = "上传临时文件，返回临时文件ID")
    @PostMapping(path = "/temp",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiRes<String> uploadTemporaryFile(TemporaryFileUpload temporaryFileUpload) {
        log.info("{}", temporaryFileUpload);

        return ApiRes.success("ID");
    }
}
