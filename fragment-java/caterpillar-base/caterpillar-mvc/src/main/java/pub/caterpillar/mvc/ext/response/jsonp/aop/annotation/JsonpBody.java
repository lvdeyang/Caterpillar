package pub.caterpillar.mvc.ext.response.jsonp.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * 处理jsonp返回
 * lvdeyang 2018年01月10日
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface JsonpBody {

}
