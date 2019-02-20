package pub.caterpillar.mvc.verifycode.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.mvc.verifycode.bean.VerifyCode;

/**
 * 生成验证码
 * lvdeyang 2017年6月12日
 */
@Controller
@RequestMapping("/newverify/code")
public class NewVerifyCodeController{

	//设置响应头
	private void setResponseHead(HttpServletResponse response){
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
	}
	
	//缓存验证码
	private void setVerifyCode(HttpServletRequest request, VerifyCode code){
		//存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute(VerifyCode.CATCHNAME, code); 
	}
	
	@RequestMapping(value = "/get/{width}/{height}", method=RequestMethod.GET)
	public void generate(
			@PathVariable int width, 
			@PathVariable int height, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		
		//设置响应头
		setResponseHead(response);
		
		//生成二维码对象
		VerifyCode code = new VerifyCode(4);
		
		//缓存验证码
		setVerifyCode(request, code);
		
		//生成图片 
        VerifyCodeUtils.outputImage(width, height, response.getOutputStream(), code.getToken()); 
	}
	
	@RequestMapping(value = "/get/{width}/{height}/{timeout}", method=RequestMethod.GET)
	public void generate(
			@PathVariable int width, 
			@PathVariable int height, 
			@PathVariable int timeout,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		//设置响应头
		setResponseHead(response);
		
		//生成二维码对象
		VerifyCode code = new VerifyCode(4, timeout);
		
		//缓存验证码
		setVerifyCode(request, code);
		
		//生成图片 
        VerifyCodeUtils.outputImage(width, height, response.getOutputStream(), code.getToken());
	} 
	
}
