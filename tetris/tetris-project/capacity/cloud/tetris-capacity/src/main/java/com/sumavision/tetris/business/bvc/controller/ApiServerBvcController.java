package com.sumavision.tetris.business.bvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.business.bvc.service.BvcService;
import com.sumavision.tetris.business.bvc.vo.BvcVO;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/api/server/bvc")
public class ApiServerBvcController {
	
	@Autowired
	private BvcService bvcService;

	/**
	 * 添加流转码任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午5:31:22
	 * @param String bvcInfo bvc参数
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String bvcInfo,
			HttpServletRequest request) throws Exception{
		
		BvcVO _bvcInfo = JSON.parseObject(bvcInfo, BvcVO.class);
		
		bvcService.addTranscode(_bvcInfo);
		
		return null;
	}
	
	/**
	 * 删除流转码任务<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年12月18日 下午5:35:51
	 * @param String id 流转码任务标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Object delete(
			String id,
			HttpServletRequest request) throws Exception{
		
		bvcService.deleteTranscode(id);
		
		return null;
	}
	
}
