package com.sumavision.tetris.capacity.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/capacity/management")
public class CapacityManagementController {
	
	@Autowired
	private CapacityManagementService capacityManagementService;

	/**
	 * 添加能力<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月10日 下午1:16:04
	 * @param String ip 能力ip
	 * @param String type 能力类型 封装/转码
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(String ip, String type, HttpServletRequest request) throws Exception{
		
		capacityManagementService.addCapacity(ip, type);
		
		return null;
	}
	
}
