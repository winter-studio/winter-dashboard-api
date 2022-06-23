package org.winterframework.dashboard.data.mybatis;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataModifyPermission {

    @AliasFor("field")
    String value() default "user_id";

    @AliasFor("value")
    String field() default "user_id";
}
