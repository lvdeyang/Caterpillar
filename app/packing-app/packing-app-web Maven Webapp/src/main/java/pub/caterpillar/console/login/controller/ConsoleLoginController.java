package pub.caterpillar.console.login.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.console.login.exception.AdminNotFoundException;
import pub.caterpillar.console.login.exception.ErrorPasswordException;
import pub.caterpillar.console.login.vo.AdminVO;
import pub.caterpillar.mvc.verifycode.aop.annotation.TokenCheck;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;

@Controller
@RequestMapping(value = "/console/login")
public class ConsoleLoginController{

	private static final Logger LOG = LoggerFactory.getLogger(ConsoleLoginController.class);
	
	@Autowired
	private AdminDAO conn_admin;
	
	//主页支持GET和POST
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		
		ModelAndView mv = null;
		
		mv = new ModelAndView("web/login/login");
		
		return mv;
	}
	
	@TokenCheck(value = "redirect:/console/login/index")
	@RequestMapping(value = "/do/login", method = RequestMethod.POST)
	public ModelAndView doLogin(
			String username,
			String password,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		AdminPO admin = conn_admin.queryByUsername(username);
		
		if(admin == null){
			throw new AdminNotFoundException(admin.getUsername());
		}
		
		//密码编码
		byte[] md5Bytes = Md5Utils.encodeMD5(password);
        String base64Str = Md5Utils.encodeBase64(md5Bytes);
        String hexStr = "{md5}".concat(base64Str);
		
        if(!admin.getPassword().equals(hexStr)){
        	throw new ErrorPasswordException();
        }
        
        //登录标记
        AdminVO v_admin = new AdminVO().set(admin)
        							   .setTimestamp(new Date())
        							   .setKey(UUID.randomUUID().toString());
        HttpSession session = request.getSession(true);
        session.setAttribute(AdminVO.CATCHNAME, v_admin);
        
        //登录成功
        mv = new ModelAndView("redirect:/console/index");
        
		return mv;
	}
	
}
