package pub.caterpillar.packing.web.register.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;
import pub.caterpillar.packing.business.qr.dao.QrCodeDAO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.packing.exception.EntranceInvalidException;
import pub.caterpillar.packing.web.packing.exception.IdentificationInvalidException;
import pub.caterpillar.packing.web.register.exception.IdentificationAlreadyInUsedException;
import pub.caterpillar.packing.web.register.exception.LicenseAlreadyInUsedException;
import pub.caterpillar.packing.web.register.exception.MobileAlreadyInUsedException;
import pub.caterpillar.packing.web.tocken.exception.TokenErrorException;
import pub.caterpillar.packing.web.tocken.exception.TokenTimeoutException;

@Controller
@RequestMapping(value = "/register")
public class RegisterController{

	@Autowired
	private QrCodeDAO conn_qr_code;
	
	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private AdminDAO conn_admin;
	
	@RequestMapping(value = "/index/{entrance}")
	public ModelAndView index(@PathVariable String entrance) throws Exception{
		
		ModelAndView mv = null;
		
		AdminPO admin = conn_admin.get(entrance);
		
		if(admin == null){
			throw new EntranceInvalidException(entrance);
		}
		
		mv = new ModelAndView("front/packing/register/license_register");
		
		mv.addObject("entrance", entrance);
		
		return mv;	
		
	}
	
	@RequestMapping(value = "/do/register/{identification}")
	public ModelAndView doRegister(
			@PathVariable String identification,
			String region,
			String license,
			String type,
			String name,
			String mobile,
			String token,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//接口参数校验
		
		//参数忽略大小写
		region = region.toUpperCase();
		license = license.toUpperCase();
				
		//检验token
		if(token == null){
			throw new TokenErrorException();
		}else{
			token = token.toLowerCase();
			HttpSession session = request.getSession();
			Object verObj = session.getAttribute("verCode");
			if(verObj == null){
				throw new TokenTimeoutException();
			}
			String verCode = verObj.toString();
			if(!verCode.equals(token)){
				throw new TokenErrorException();
			}
			//验证码移除
			session.removeAttribute("verCode");
		}
				
		//检验绑定id是否有效
		List<QrCodePO> codeList = conn_qr_code.findByField("identification", identification);
		
		if(codeList==null || codeList.size()<=0){
			throw new IdentificationInvalidException(identification);
		}
		
		//检验绑定id是否注册
		List<UserPO> users = conn_user.findByField("identification", identification);
		
		if(users!=null && users.size()>0){
			throw new IdentificationAlreadyInUsedException(identification);
		}
		
		//检验license是否被注册
		users = conn_user.queryByLicense(license);
		
		if(users!=null && users.size()>0){
			throw new LicenseAlreadyInUsedException(license);
		}
		
		//检验手机号码是否被注册2
		users = conn_user.queryByMobile(mobile);
		
		if(users!=null && users.size()>0){
			throw new LicenseAlreadyInUsedException(license);
		}
		
		UserPO user = new UserPO();
		user.setIdentification(identification);
		user.setRegion(region);
		user.setLicense(license);
		user.setType(type);
		user.setName(name);
		user.setMobile(mobile);
		conn_user.save(user);
		
		//mv = new ModelAndView("success/success");
		//mv.addObject("message", "感谢使用酷车管家！");
		mv = new ModelAndView("redirect:http://www.51qcgj.com/index.php/wap/lqdz.html");
		
		return mv;
	}
	
	@RequestMapping(value = "/do/license/register/{entrance}")
	public ModelAndView doLisenceRegister(
			@PathVariable String entrance,
			String region,
			String license,
			String type,
			String name,
			String mobile,
			String token,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//接口参数校验
		
		//参数忽略大小写
		region = region.toUpperCase();
		license = license.toUpperCase();
		
		//检验token
		if(token == null){
			throw new TokenErrorException();
		}else{
			token = token.toLowerCase();
			HttpSession session = request.getSession();
			Object verObj = session.getAttribute("verCode");
			if(verObj == null){
				throw new TokenTimeoutException();
			}
			String verCode = verObj.toString();
			if(!verCode.equals(token)){
				throw new TokenErrorException();
			}
			//验证码移除
			session.removeAttribute("verCode");
		}
				
		//入口是否有效
		AdminPO admin = conn_admin.get(entrance);
		
		if(admin == null){
			throw new EntranceInvalidException(entrance);
		}
		
		//检验手机号码是否被注册2
		List<UserPO> users = conn_user.queryByMobile(mobile);
		
		if(users!=null && users.size()>0){
			throw new MobileAlreadyInUsedException(mobile);
		}
		
		UserPO user = new UserPO();
		user.setIdentification(entrance);
		user.setRegion(region);
		user.setLicense(license);
		user.setType(type);
		user.setName(name);
		user.setMobile(mobile);
		conn_user.save(user);
		
		//mv = new ModelAndView("success/success");
		//mv.addObject("message", "感谢使用酷车管家！");
		mv = new ModelAndView("redirect:http://www.51qcgj.com/index.php/wap/lqdz.html");
		
		return mv;
	}
	
}
