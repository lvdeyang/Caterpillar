package com.guolaiwan.app.web.chapman.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import com.guolaiwan.app.web.user.exception.ChapmanAlreadyExistException;
import com.guolaiwan.app.web.user.exception.LoginTimeoutException;
import com.guolaiwan.app.web.user.exception.VerifyCodeErrorException;
import com.guolaiwan.app.web.user.vo.UserVO;
import com.guolaiwan.bussiness.chapman.dao.ChapmanDAO;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceFolderType;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.user.dao.UserDAO;
import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/chapman")
public class ChapmanController extends BaseController{

	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private ChapmanDAO conn_chapman;
	
	@Autowired
	private SourceFolderDAO conn_source_folder;
	
	@RequestMapping("/home")
	public ModelAndView home(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		HttpSession session = request.getSession(true);
		
		Object user = session.getAttribute(LoginCacheName.USER.getName());
		
		Object chapman = session.getAttribute(LoginCacheName.CHAPMAN.getName());
		
		if(user==null || chapman==null){
			throw new LoginTimeoutException();
		}
		
		ModelAndView mv = new ModelAndView("chapman/home");
		
		return mv;
	}
	
	//申请商户
	@ResponseBody
	@RequestMapping(value = "/regist/{userId}", method = RequestMethod.POST)
	public Map<String, Object> regist(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String chapmanname = request.getParameter("chapmanname");	
		String code = request.getParameter("code").toLowerCase();
		String key = request.getParameter(LoginCacheName.KEY.getName());
		
		HttpSession session = request.getSession(true);
		
		String _code = (String) session.getAttribute("verCode");
		
		if(!code.equals(_code)){
			//验证码错误
			throw new VerifyCodeErrorException();
		}
		
		Object _key = session.getAttribute(LoginCacheName.KEY.getName());
		
		//登录超时验证
		if(key==null || _key==null || !key.equals((String)_key)){
			throw new LoginTimeoutException();
		}else{
			Object user = session.getAttribute(LoginCacheName.USER.getName());
			if(user==null || !((UserVO)user).getId().equals(userId)){
				throw new LoginTimeoutException();
			}
		}
		
		UserPO user = conn_user.get(userId);
		
		if(user.getChapman() != null){
			throw new ChapmanAlreadyExistException();
		}
		
		ChapmanPO chapman = new ChapmanPO();
		chapman.setChapmanname(chapmanname);
		chapman.setUpdateTime(new Date());
		chapman.setUser(user);
		conn_chapman.save(chapman);
		
		user.setChapman(chapman);
		conn_user.saveOrUpdate(user);
		
		//创建商户默认目录
		SourceFolderPO root = new SourceFolderPO();
		root.setFoldername("根目录");
		root.setType(SourceFolderType.DEFAULT);
		root.setUpdateTime(new Date());
		conn_source_folder.save(root);
		
		root.setChapman(chapman);
		if(chapman.getSourceFolders() == null){
			chapman.setSourceFolders(new HashSet<SourceFolderPO>());
		}
		chapman.getSourceFolders().add(root);
		conn_chapman.saveOrUpdate(chapman);
		
		ChapmanVO _chapman = new ChapmanVO().set(chapman);
		session.setAttribute(LoginCacheName.CHAPMAN.getName(), _chapman);
		
		SourceFolderVO _root = new SourceFolderVO().set(root);
		session.setAttribute(LoginCacheName.ROOT.getName(), _root);
		
		return success(result);
	}
	
}
