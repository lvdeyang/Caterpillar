package pub.caterpillar.packing.web.packing.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pub.caterpillar.packing.business.qr.dao.QrCodeDAO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.packing.exception.IdentificationInvalidException;

@Controller
@RequestMapping(value = "/base")
public class PackingController{
	
	@Autowired
	private QrCodeDAO conn_qr_code;
	
	@Autowired
	private UserDAO conn_user;
	
	@RequestMapping(value = "/route/{identification}")
	public ModelAndView route(@PathVariable String identification) throws Exception{
		
		ModelAndView mv = null;
		
		//检验绑定id是否有效
		List<QrCodePO> codeList = conn_qr_code.findByField("identification", identification);
		
		if(codeList==null || codeList.size()<=0){
			throw new IdentificationInvalidException(identification);
		}
		
		//检验绑定id是否注册
		List<UserPO> users = conn_user.findByField("identification", identification);
		
		if(users==null || users.size()<=0){
			//返回注册页面
			mv = new ModelAndView("front/packing/register/register");
		}else{
			//返回通知挪车页面
			mv = new ModelAndView("front/packing/notice/notice");
		}
		
		mv.addObject("identification", identification);
		
		return mv;
	}
	
}
