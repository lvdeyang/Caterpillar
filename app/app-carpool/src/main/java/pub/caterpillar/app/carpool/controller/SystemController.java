package pub.caterpillar.app.carpool.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pub.caterpillar.app.carpool.dao.SystemPropertiesDAO;
import pub.caterpillar.app.carpool.po.SystemPropertiesPO;
import pub.caterpillar.app.carpool.service.SystemService;
import pub.caterpillar.app.carpool.vo.WeixinConfigVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

	@Autowired
	private SystemPropertiesDAO conn_systemproperties;
	
	@Autowired
	private SystemService systemService;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/weixin/config", method = RequestMethod.GET)
	public Object queryWeixinConfig(HttpServletRequest request) throws Exception{
		return WeixinConfigVO.getInstance();
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/set/weixin/config", method = RequestMethod.POST)
	public Object setWeixinConfig(
			String appid,
			String appsecret,
			String token,
			String mch_id,
			String send_name,
			String key,
			HttpServletRequest request
			) throws Exception{
		
		systemService.setWeixinConfig(appid, appsecret, token, mch_id, send_name, key);
		return WeixinConfigVO.getInstance();
	}
	
}
