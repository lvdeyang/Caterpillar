package com.guolaiwan.app.web.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.chapman.source.vo.SourceFolderVO;
import com.guolaiwan.app.web.chapman.vo.ChapmanVO;
import com.guolaiwan.app.web.enumeration.LoginCacheName;
import com.guolaiwan.app.web.user.exception.ConflictPasswordException;
import com.guolaiwan.app.web.user.exception.LoginTimeoutException;
import com.guolaiwan.app.web.user.exception.PasswordErrorException;
import com.guolaiwan.app.web.user.exception.UnkonwnUserException;
import com.guolaiwan.app.web.user.exception.UsernameHasAreadyBeUsedException;
import com.guolaiwan.app.web.user.exception.VerifyCodeErrorException;
import com.guolaiwan.app.web.user.vo.UserVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.user.dao.UserDAO;
import com.guolaiwan.bussiness.user.enumeration.UserSexType;
import com.guolaiwan.bussiness.user.po.UserPO;

import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private SourceFolderDAO conn_source_folder;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	//用户页面
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("user/home");
		return mv;
	}
	
	//用户登录
	@ResponseBody
	@RequestMapping(value="/do/login" , method=RequestMethod.POST)
	public Map<String, Object> doLogin(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code").toLowerCase();
		
		HttpSession session = request.getSession(true); 
		String _code = (String) session.getAttribute("verCode");
		
		if(!code.equals(_code)){
			//验证码错误
			throw new VerifyCodeErrorException();
		}
        
		UserPO user = conn_user.getUserByName(username);
		if(user == null){
			throw new UnkonwnUserException();
		}
		
		String s_pas = Sha1Util.getSha1(password);
		if(!s_pas.equals(user.getPassword())){
			throw new PasswordErrorException();
		}
		
		UserVO _user = new UserVO().set(user);
		session.setAttribute(LoginCacheName.USER.getName(), _user);
		
		ChapmanPO chapman = user.getChapman();
		if(chapman != null){
			ChapmanVO _chapman = new ChapmanVO().set(chapman);
			session.setAttribute(LoginCacheName.CHAPMAN.getName(), _chapman);
			
			SourceFolderPO root = dbcheck_chapman.chapmanRootFolderExist(chapman);
			SourceFolderVO _root = new SourceFolderVO().set(root);
			session.setAttribute(LoginCacheName.ROOT.getName(), _root);
		}
		
		//生成一个随机的key--32位uuid+时间戳+MD5加密
		String key = Md5Utils.MD5(new StringBufferWrapper().append(UUID.randomUUID()).append(new Date().getTime()).toString());
		session.setAttribute(LoginCacheName.KEY.getName(), key);
		
		result.put("key", key);
		
		return success(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/do/logout", method = RequestMethod.POST)
	public Map<String, Object> doLogout(
			HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true); 
		
		session.removeAttribute(LoginCacheName.KEY.getName());
		
		session.removeAttribute(LoginCacheName.USER.getName());
		
		return success(result);
	}
	
	//用户注册
	@ResponseBody
	@RequestMapping(value = "/do/regist" , method = RequestMethod.POST)
	public Map<String, Object> doRegist(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{

		Map<String, Object> result = new HashMap<String, Object>();
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirmPass");
		String code = request.getParameter("code").toLowerCase();
		
		HttpSession session = request.getSession(true); 
		String _code = (String) session.getAttribute("verCode");
		
		if(!code.equals(_code)){
			//验证码错误
			throw new VerifyCodeErrorException();
		}
		
		if(!password.equals(confirmPass)){
			//两次密码输入不一致
			throw new ConflictPasswordException();
		}
		
		//用户名已经备注册
		UserPO user = conn_user.getUserByName(username);
		if(user != null){
			throw new UsernameHasAreadyBeUsedException();
		}
		
		user = new UserPO();
		user.setUpdateTime(new Date());
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(Sha1Util.getSha1(password));
		user.setRealName(username);
		user.setSex(UserSexType.MALE);
		conn_user.save(user);
		
		return success(result);
	}
	
}
