package com.sumavision.tetris.punch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.attend.AttendPo;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.device.DevicePO;
import com.sumavision.tetris.device.DeviceVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.org.OrgVO;
import com.sumavision.tetris.user.UserVO;


@Controller
@RequestMapping(value = "/api/android")
public class PunchController {
	
	@Autowired
	private PunchService service;
	
	/**
	 * 
		 * 创建打卡工单<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @return
		 * @throws Exception
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/create_order")
	public Object createOrder(HttpServletRequest request) throws Exception{
		String token=request.getHeader("tetris-001");
		AttendPo po=service.createOrder(token);
		if (po!=null) {
			boolean isOn=false;
			boolean isOff=false;
			if (po.getOnDutyTime()!=null) {
				isOn=true;
			}
			if (po.getOffDutyTime()!=null) {
				isOff=true;
			}
			return new HashMapWrapper<String, Object>().put("code", 200)
					   .put("result", "success")
					   .put("id", po.getId())
					   .put("isOn",isOn)
					   .put("isOff",isOff)
					   .getMap();
		}else{
			return new HashMapWrapper<String, Object>().put("code", 404)
					   .put("result", "faile")
					   .put("id", 0)
					   .getMap();
		}
	}
	/**
	 * 
		 * 上班打卡<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param request
		 * @return
		 * @throws Exception
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/start_order")
	public Object start_Order(Long id,int onDutyState,HttpServletRequest request) throws Exception{
		Long returnId=service.start_Order(id,onDutyState);
		if (returnId!=0) {
			return new HashMapWrapper<String, Object>().put("code", 200)
					   .put("result", "success")
					   .put("data", "")
					   .getMap();
		}else{
			return new HashMapWrapper<String, Object>().put("code", 404)
					   .put("result", "faile")
					   .put("data", "")
					   .getMap();
		}
	}
	/**
	 * 
		 * 下班打卡<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param request
		 * @return
		 * @throws Exception
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/end_order")
	public Object end_Order(Long id,int offDutyState,HttpServletRequest request) throws Exception{
		Long returnId=service.end_Order(id,offDutyState);
		if (returnId!=0) {
			return new HashMapWrapper<String, Object>().put("code", 200)
					   .put("result", "success")
					   .put("data", "")
					   .getMap();
		}else{
			return new HashMapWrapper<String, Object>().put("code", 404)
					   .put("result", "faile")
					   .put("data", "")
					   .getMap();
		}
	}
	/**
	 * 
		 * 外勤打卡<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param request
		 * @return
		 * @throws Exception
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/out_order")
	public Object end_Order(Long id,String title,String content,String address,String pics,HttpServletRequest request) throws Exception{
		Long returnId=service.out_Order(id,title,content,address,pics);
		if (returnId!=0) {
			return new HashMapWrapper<String, Object>().put("code", 200)
					   .put("result", "success")
					   .put("data", "")
					   .getMap();
		}else{
			return new HashMapWrapper<String, Object>().put("code", 404)
					   .put("result", "faile")
					   .put("data", "")
					   .getMap();
		}
	}
	/**
	 * 
		 * 获取本月考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param request
		 * @return
		 * @throws Exception
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getorder_month")
	public Object get_monteOrder(int year,int month,HttpServletRequest request) throws Exception{
		String token=request.getHeader("tetris-001");
		List<AttendPo> pos=service.get_monthOrder(year,month,token);
		if (pos!=null&&pos.size()>0) {
			return new HashMapWrapper<String, Object>().put("code", 200)
					   .put("result", "success")
					   .put("data", pos)
					   .getMap();
		}else{
			return new HashMapWrapper<String, Object>().put("code", 404)
					   .put("result", "faile")
					   .put("data", "")
					   .getMap();
		}
	}
}
