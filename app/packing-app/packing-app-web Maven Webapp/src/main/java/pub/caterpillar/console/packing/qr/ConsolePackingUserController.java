package pub.caterpillar.console.packing.qr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.user.vo.UserVO;

@Controller
@RequestMapping(value = "/console/packing/user")
public class ConsolePackingUserController {

	@Autowired
	private AdminDAO conn_admin;
	
	@Autowired
	private UserDAO conn_user;
	
	//获取商户入口注册的用户
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/by/chapman/{id}", method = RequestMethod.GET)
	public Object queryByChapman(
			@PathVariable Long id, 
			HttpServletRequest request) throws Exception{
		
		AdminPO admin = conn_admin.get(id);
		
		List<UserPO> users = conn_user.queryByAdmin(admin.getUuid());
		
		List<UserVO> trans_users = UserVO.getConverter(UserVO.class).convert(users, UserVO.class);
		
		return trans_users;
	}
	
}
