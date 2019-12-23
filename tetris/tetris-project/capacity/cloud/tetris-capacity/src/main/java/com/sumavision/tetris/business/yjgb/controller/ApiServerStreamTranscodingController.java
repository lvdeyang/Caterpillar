package com.sumavision.tetris.business.yjgb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.business.yjgb.service.TransformService;
import com.sumavision.tetris.business.yjgb.vo.OutParamVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/api/server/stream/transcoding")
public class ApiServerStreamTranscodingController {
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private TransformService transformService;
	
	/**
	 * 删除流转码任务<br/>
	 * <b>作者:</b>xh<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午3:55:01
	 * @param String id 任务标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/task")
	public Object deleteTask(String messageId, HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		
		//能力对接
		transformService.deleteStreamTask(user, messageId);
		
		return null;
	}
	
	/**
	 * 添加任务输出<br/>
	 * <b>作者:</b>xh<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月21日 下午5:00:35
	 * @param String id 任务标识
	 * @param String outputParam 添加的输出
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add/output")
	public Object addOutput(String id, String outputParam, HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		
		List<OutParamVO> outputParams = JSONObject.parseArray(outputParam, OutParamVO.class);
		
		//添加输出
		transformService.addStreamOutput(id, outputParams);
		
		return null;
	}
	
	/**
	 * 删除任务输出<br/>
	 * <b>作者:</b>xh<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月21日 下午5:01:32
	 * @param String id 任务标识
	 * @param String outputParam 删除的输出
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/output")
	public Object deleteOutput(String id, String outputParam, HttpServletRequest request) throws Exception {
		UserVO user = userQuery.current();
		
		List<OutParamVO> outputParams = JSONObject.parseArray(outputParam, OutParamVO.class);

		//删除输出
		transformService.deleteStreamOutput(id, outputParams);
		
		return null;
	}
}
