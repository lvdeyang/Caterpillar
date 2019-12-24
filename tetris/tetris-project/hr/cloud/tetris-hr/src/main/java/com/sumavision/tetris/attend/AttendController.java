package com.sumavision.tetris.attend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.device.DevicePO;
import com.sumavision.tetris.device.DeviceVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.org.OrgVO;
import com.sumavision.tetris.user.UserVO;


@Controller
@RequestMapping(value = "/hr/attend")
public class AttendController {
	
	@Autowired
	private AttendQuery query;
	
	@Autowired
	private AttendDao dao;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			String workerNo,
			HttpServletRequest request) throws Exception{
		
		//TODO 权限校验		
		List<AttendPo> entities = dao.findBypageAndWorkNo(currentPage-1, pageSize,workerNo);
		
		List<AttendVo> attends = AttendVo.getConverter(AttendVo.class).convert(entities, AttendVo.class);
		
		Long total = dao.countBypageAndWorkNo(workerNo);
			
		return new HashMapWrapper<String, Object>().put("rows", attends)
													   .put("total", total)
													   .getMap();
	}
	
}
