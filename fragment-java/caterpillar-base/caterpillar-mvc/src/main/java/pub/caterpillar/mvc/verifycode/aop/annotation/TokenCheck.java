package pub.caterpillar.mvc.verifycode.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * 处理验证码
 * lvdeyang 2018年01月10日
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface TokenCheck {

	String value() default "";
	
}
