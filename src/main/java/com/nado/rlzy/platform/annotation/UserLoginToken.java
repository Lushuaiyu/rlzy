package com.nado.rlzy.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录才能操作的注解
 *
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 10:25 2019/7/26
 * @Param
 * @return
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
