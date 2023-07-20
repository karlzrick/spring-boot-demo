package com.xkcoding.desensitized.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xkcoding.desensitized.databind.DesensitizationSerialize;
import com.xkcoding.desensitized.enums.DesensitizationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏注解
 *
 * @author xuyuan
 * @date 2023-07-20 14:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {

    /**
     * 脱敏数据类型，只要在CUSTOMER的时候，startInclude和endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.CUSTOMER;

    /**
     * 开始位置（包含）
     */
    int startInclude() default 0;

    /**
     * 结束位置（不包含）
     */
    int endExclude() default 0;
}
