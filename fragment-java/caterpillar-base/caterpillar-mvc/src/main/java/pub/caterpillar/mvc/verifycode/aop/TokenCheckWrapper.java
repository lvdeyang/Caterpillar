package pub.caterpillar.mvc.verifycode.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.verifycode.aop.annotation.TokenCheck;
import pub.caterpillar.mvc.verifycode.bean.VerifyCode;
import pub.caterpillar.mvc.verifycode.exception.ErrorVerifyCodeException;
import pub.caterpillar.mvc.verifycode.exception.NoRequestFoundForTokenCheckException;
import pub.caterpillar.mvc.verifycode.exception.NoVeriryCodeFoundException;
import pub.caterpillar.mvc.verifycode.exception.VerifyCodeTimeoutException;

/**
 * json 返回包装
 * lvdeyang 2018年01月10日
 */

@Component  
@Aspect	   
public class TokenCheckWrapper {
	
	public static final boolean DEBUG = true;
	
	/*@Pointcut("("
				+ "execution(public java.lang.String *(..)) || "
				+ "execution(public org.springframework.web.servlet.ModelAndView *(..)) || "
				+ "execution(public void *(..)) || "
				+ "execution(public java.lang.Object *(..))"
			+ ") && "
			+ "@annotation(pub.caterpillar.mvc.verifycode.aop.annotation.TokenCheck)")*/
	@Pointcut("execution(public * *(..)) && @annotation(pub.caterpillar.mvc.verifycode.aop.annotation.TokenCheck)")
    public void tokenCheck(){}
	
	@Around("tokenCheck()")
	public Object wrap(ProceedingJoinPoint joinPoint) throws Throwable{
		
		HttpServletRequest request = null;
		
		Object[] args = joinPoint.getArgs();
		
		//获取@TockenChek
		Class<?>[] argTypes = new Class[args.length];
		for(int i=0; i<args.length; i++){
			//处理特殊参数类型
			if(args[i] instanceof HttpServletRequest){
				argTypes[i] = HttpServletRequest.class;
			}else if(args[i] instanceof HttpServletResponse){
				argTypes[i] = HttpServletResponse.class;
			}else{
				argTypes[i] = args[i].getClass();
			}
		}
		Signature s = joinPoint.getSignature();
		Class<?> tClass = s.getDeclaringType();
		Method method = tClass.getMethod(s.getName(), argTypes);
		TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
		
		//跳转页面
		String fPath = tokenCheck.value();
		
		for(Object arg: args){
			if(arg instanceof HttpServletRequest){
				request = (HttpServletRequest)arg;
				break;
			}
		}
		
		if(request == null){
			String target = joinPoint.toString();
			throw new NoRequestFoundForTokenCheckException(target.substring(13, target.length()-1));
		}
		
		HttpSession session = request.getSession();
		
		Object code = session.getAttribute(VerifyCode.CATCHNAME);
	
		if(code == null){
			if(fPath==null || "".equals(fPath)){
				throw new NoVeriryCodeFoundException();
			}else{
				throw new NoVeriryCodeFoundException(fPath);
			}
		}
		
		VerifyCode vercode = (VerifyCode)code;
		if(vercode.isTimeout()){
			if(fPath==null || "".equals(fPath)){
				throw new VerifyCodeTimeoutException();
			}else{
				throw new VerifyCodeTimeoutException(fPath);
			}
		}
		
		String token = request.getParameter("token");
		if(!vercode.compare(token)){
			if(fPath==null || "".equals(fPath)){
				throw new ErrorVerifyCodeException();
			}else{
				throw new ErrorVerifyCodeException(fPath);
			}
		}
		
		session.removeAttribute(VerifyCode.CATCHNAME);
		
		return joinPoint.proceed(args);
		
	}
	
}
