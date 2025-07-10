package com.atguigu.daijia.common.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//自定义注解
//登录判断
@Target(ElementType.METHOD)//注解的位置
@Retention(RetentionPolicy.RUNTIME)//注解的范围
public @interface GuiguLogin {

}
