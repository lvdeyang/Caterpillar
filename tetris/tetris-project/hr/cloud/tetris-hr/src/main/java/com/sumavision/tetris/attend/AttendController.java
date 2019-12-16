package com.sumavision.tetris.attend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.org.OrgVO;
import com.sumavision.tetris.user.UserVO;


@Controller
@RequestMapping(value = "/hr/attendRec")
public class AttendController {
	
	@Autowired
	private AttendService service;

	/**
	 * 获取全部考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月10日  
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/pageExcel")
	public Object getAllAttend(HttpServletRequest request) throws Exception {

		List<AttendVo> attendVos= service.getAllAttend();
		return attendVos;
	}
	
	/**
	 * 获取分页考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月10日  
	 */
	@ResponseBody
	@RequestMapping(value = "/page")
	public Object getAttendbyPage(int pageSize,  int pageIndex,HttpServletRequest request) throws Exception {

		List<AttendVo> attendVos= service.getAttendByPage(pageSize,pageIndex);
		return attendVos;
	}
	
	/**
	 * 根据员工信息查询考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月10日  
	 */
	@ResponseBody
	@RequestMapping(value = "/sel")
	public Object getAttendbyWorker(int workerId,  int deviceId,HttpServletRequest request) throws Exception {
		
		List<AttendVo> attendVos= service.getAttendbyWorker(workerId,deviceId);
		return attendVos;
	}
}
