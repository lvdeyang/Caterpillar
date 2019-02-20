package pub.caterpillar.packing.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;
import pub.caterpillar.packing.web.admin.exception.AdminDoseNotExistException;
import pub.caterpillar.packing.web.admin.vo.AdminVO;
import pub.caterpillar.packing.web.tocken.exception.TokenErrorException;
import pub.caterpillar.packing.web.tocken.exception.TokenTimeoutException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController{

	@Autowired
	private AdminDAO conn_admin;
	
	@RequestMapping(value = "/list")
	public ModelAndView List(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		HttpSession session = null;
		
		//登录校验
		session = request.getSession();
		AdminVO admin = (AdminVO)session.getAttribute("admin");
		
		mv = new ModelAndView("front/packing/admin/admin");
		mv.addObject("admin", admin);
		
		return mv;
	}
	
	@RequestMapping(value = "/change/password/{id}")
	public ModelAndView changePassword(
			@PathVariable long id,
			String oldPass,
			String newPass,
			String repeat,
			String token,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//登录校验
		
		//检验token
		if(token == null){
			throw new TokenErrorException("forward:/admin/list");
		}else{
			token = token.toLowerCase();
			HttpSession session = request.getSession();
			Object verObj = session.getAttribute("verCode");
			if(verObj == null){
				throw new TokenTimeoutException("forward:/admin/list");
			}
			String verCode = verObj.toString();
			if(!verCode.equals(token)){
				throw new TokenErrorException("forward:/admin/list");
			}
			//验证码移除
			session.removeAttribute("verCode");
		}
		
		//获取管理员
		AdminPO admin_entity = conn_admin.get(id);
		if(admin_entity == null){
			throw new AdminDoseNotExistException(id);
		}
		
		//旧密码编码
		byte[] md5Bytes = Md5Utils.encodeMD5(oldPass);
        String base64Str = Md5Utils.encodeBase64(md5Bytes);
        String hexStr = "{md5}".concat(base64Str);
        
        mv = new ModelAndView("forward:/admin/list");
        
        if(!hexStr.equals(admin_entity.getPassword())){
        	mv.addObject("message", "旧密码输入有误！");
        	mv.addObject("oldPass", oldPass);
        	mv.addObject("newPass", newPass);
        	mv.addObject("repeat", repeat);
        	return mv;
        }
        
        //新旧密码不一致
        if(!newPass.equals(repeat)){
        	mv.addObject("message", "新密码两次输入不一致！");
        	mv.addObject("oldPass", oldPass);
        	mv.addObject("newPass", newPass);
        	mv.addObject("repeat", repeat);
        	return mv;
        }
        
        //新密码编码
        md5Bytes = Md5Utils.encodeMD5(newPass);
        base64Str = Md5Utils.encodeBase64(md5Bytes);
        hexStr = "{md5}".concat(base64Str);
        
        admin_entity.setPassword(hexStr);
        conn_admin.saveOrUpdate(admin_entity);
        
        mv.addObject("success", "修改成功！");
        return mv;
	}
	
}
