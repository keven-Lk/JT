package com.jt.explain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) //对方法生效
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache_Find {
	String key() default "";//默认为第一个参数
	int  seconds() default 0;
}
