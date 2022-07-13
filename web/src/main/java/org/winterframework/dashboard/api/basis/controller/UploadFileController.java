package org.winterframework.dashboard.api.basis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
