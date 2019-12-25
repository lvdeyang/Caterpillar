package com.sumavision.tetris.business.live.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.business.live.service.StreamPassbyService;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/api/server/live")
public class ApiServerLiveController {
	
	@Autowired
	private StreamPassbyService streamPassbyService;

	/**
	 * 删除流透传任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月25日 上午9:37:08
	 * @param String id 任务标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/stream/passby")
	public Object deleteStreamPassby(String id, HttpServletRequest request) throws Exception{
		
		streamPassbyService.deleteRtmp2Hls(id);
		
		return null;
	}
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/create/task")
	public Object createTask(HttpServletRequest request) throws Exception{
		
		streamPassbyService.createTask(1l, 
				new ArrayListWrapper<String>().add("test1").add("test2").getList(),
				"test","/home/nginx/live/test");
		
		return null;
	}
	
}
