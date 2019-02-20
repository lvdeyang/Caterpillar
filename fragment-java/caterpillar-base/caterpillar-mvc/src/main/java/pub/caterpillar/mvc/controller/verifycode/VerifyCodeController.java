package pub.caterpillar.mvc.controller.verifycode;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.mvc.controller.BaseController;

/**
 * 生成验证码
 * lvdeyang 2017年6月12日
 */
@Controller
@RequestMapping("/verify/code")
public class VerifyCodeController extends BaseController{

	@RequestMapping(value = "/get/{width}/{height}", method=RequestMethod.GET)
	public void generate(
			@PathVariable int width, 
			@PathVariable int height, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
           
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //生成图片 
        VerifyCodeUtils.outputImage(width, height, response.getOutputStream(), verifyCode); 
	} 
	
}
