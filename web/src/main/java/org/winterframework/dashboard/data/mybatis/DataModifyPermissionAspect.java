package org.winterframework.dashboard.data.mybatis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Aspect
public class DataModifyPermissionAspect {

    @Pointcut("@annotation(org.winterframework.dashboard.data.mybatis.DataModifyPermission)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        DataModifyPermission filters = method.getAnnotation(DataModifyPermission.class);
        DataModifyPermissionHolder.set(filters.field());
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        DataModifyPermissionHolder.clear();
    }

}
