package cn.wintersoft.dashboard.mybatis;

import cn.wintersoft.dashboard.security.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateFieldMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        if (SecurityUtils.isAuthenticated()) {
            this.strictInsertFill(metaObject, "createBy", SecurityUtils::getUserId, Long.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        if (SecurityUtils.isAuthenticated()) {
            this.strictInsertFill(metaObject, "updateBy", SecurityUtils::getUserId, Long.class);
        }
    }

}
