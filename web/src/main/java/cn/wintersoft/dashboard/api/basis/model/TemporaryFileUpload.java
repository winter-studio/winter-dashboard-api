package cn.wintersoft.dashboard.api.basis.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(description = "临时文件上传")
public class TemporaryFileUpload {

    private String type;
    private MultipartFile file;
}
