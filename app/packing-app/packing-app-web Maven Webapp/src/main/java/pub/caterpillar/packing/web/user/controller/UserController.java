package pub.caterpillar.packing.web.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.packing.business.qr.dao.QrCodeDAO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.admin.vo.AdminVO;
import pub.caterpillar.packing.web.user.exception.QrCodeLostException;
import pub.caterpillar.packing.web.user.exception.UserNotFoundException;
import pub.caterpillar.packing.web.user.vo.UserVO;

@Controller
@RequestMapping(value = "/user")
public class UserController{

	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private QrCodeDAO conn_qr_code;
	
	@RequestMapping(value = "/list")
	public ModelAndView List(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		HttpSession session = null;
		
		//检查登录
		session = request.getSession();
		AdminVO admin = (AdminVO)session.getAttribute("admin");
		
		mv = new ModelAndView("front/packing/user/user");
		
		//页面初始化
		List<UserPO> user_entitys = conn_user.queryByAdmin(admin.getId());
		List<UserVO> users = UserVO.getConverter(UserVO.class).convert(user_entitys, UserVO.class);
		
		mv.addObject("users", users);
		
		return mv;
	}
	
	@RequestMapping(value = "/info/{id}")
	public ModelAndView Info(@PathVariable Long id) throws Exception{
		
		ModelAndView mv = null;
		
		//检查登录
		
		mv = new ModelAndView("front/packing/user/user-info");
		
		//获取用户
		UserPO user_entity = conn_user.get(id);
		if(user_entity == null){
			throw new UserNotFoundException(id);
		}
		
		UserVO user = new UserVO().set(user_entity);
		
		//获取用户二维码
		List<QrCodePO> qrCode_entitys = conn_qr_code.findByField("identification", user_entity.getIdentification());
		if(qrCode_entitys==null || qrCode_entitys.size()<=0){
			throw new QrCodeLostException(user_entity.getIdentification());
		}
		
		user.setQrCode(qrCode_entitys.get(0).getPath());
		mv.addObject("user", user);
		return mv;
	}
	
}
