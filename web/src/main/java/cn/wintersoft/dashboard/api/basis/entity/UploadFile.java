package cn.wintersoft.dashboard.api.basis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author Kyun
 * @since 2022-06-26
 */
@Getter
@Setter
@TableName("upload_file")
@Schema(name = "UploadFile", description = "文件上传")
public class UploadFile {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联ID")
    private Long relatedId;

    @Schema(description = "关联类型(Minio Bucket)")
    private String relatedType;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件原始名称")
    private String filename;

    @Schema(description = "文件扩展名")
    private String suffix;

    @Schema(description = "文件大小")
    private Long contentLength;

    @Schema(description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "删除标识")
    @TableLogic
    private Boolean deleted;


}
