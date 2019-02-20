package pub.caterpillar.packing.web.login.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.web.admin.vo.AdminVO;
import pub.caterpillar.packing.web.tocken.exception.TokenErrorException;
import pub.caterpillar.packing.web.tocken.exception.TokenTimeoutException;

@Controller
@RequestMapping(value = "/login")
public class LoginController{

	private final String URI = "front/packing/login/login";
	
	@Autowired
	private AdminDAO conn_admin;
	
	@Autowired
	private UserDAO conn_user;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView(URI);
		return mv;
	}
	
	
	@RequestMapping(value = "/do/login")
	public ModelAndView doLogin(
			String username,
			String password,
			String token,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		HttpSession session = null;
		
		//检验token
		if(token == null){
			throw new TokenErrorException(URI);
		}else{
			token = token.toLowerCase();
			session = request.getSession();
			Object verObj = session.getAttribute("verCode");
			if(verObj == null){
				throw new TokenTimeoutException(URI);
			}
			String verCode = verObj.toString();
			if(!verCode.equals(token)){
				throw new TokenErrorException(URI);
			}
			//验证码移除
			session.removeAttribute("verCode");
		}
		
		//获取用户
		List<AdminPO> admins = conn_admin.findByField("username", username);
		if(admins==null || admins.size()<=0){
			//throw new AdminNotFoundException(username);
			mv = new ModelAndView(URI);
			mv.addObject("username", username);
			mv.addObject("password", password);
			mv.addObject("message", new StringBufferWrapper().append("账号“").append(username).append("”不存在!").toString());
			return mv;
		}
		
		//密码编码
		byte[] md5Bytes = Md5Utils.encodeMD5(password);
        String base64Str = Md5Utils.encodeBase64(md5Bytes);
        String hexStr = "{md5}".concat(base64Str);
		
		//比对密码
		AdminPO admin_entity = admins.get(0);
		if(!hexStr.equals(admin_entity.getPassword())){
			//throw new ErrorPasswordException();
			mv = new ModelAndView(URI);
			mv.addObject("username", username);
			mv.addObject("password", password);
			mv.addObject("message", "密码错误！");
			return mv;
		}
		
		//记录登录状态
		AdminVO admin = new AdminVO().set(admin_entity);
		session.setAttribute("admin", admin);
		
		//重定向到绑定用户列表
		mv = new ModelAndView("redirect:/user/list");
		
		return mv;
	}
	
}
