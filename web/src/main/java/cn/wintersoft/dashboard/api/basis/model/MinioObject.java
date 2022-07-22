package cn.wintersoft.dashboard.api.basis.model;

import io.minio.GetObjectResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinioObject {
    private String fileName;
    private GetObjectResponse objectResponse;
}
