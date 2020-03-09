package com.muma.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticate {
	/**
	 * 权限列表，以“,”分割的权限编号
	 * 0,买家，1商家，2管理员
	 * @return
	 */
	String permissions() default "";

}
