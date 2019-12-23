package com.sumavision.tetris.business.record.controller.feign;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.business.record.service.RecordService;
import com.sumavision.tetris.business.record.vo.RecordVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/capacity/record/feign")
public class RecordFeignController {
	
	@Autowired
	private RecordService recordService;
	
	/**
	 * 添加收录<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午10:34:01
	 * @param String recordInfo 收录信息
	 * @return String 收录标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String recordInfo,
			HttpServletRequest request) throws Exception{
		
		RecordVO record = JSONObject.parseObject(recordInfo, RecordVO.class);
		
		return recordService.createRecord(record);
	}
	
	/**
	 * 停止收录<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月3日 上午10:35:32
	 * @param String id 收录标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Object delete(
			String id,
			HttpServletRequest request) throws Exception{
		
		recordService.deleteRecord(id);
		
		return null;
	}
	
}
