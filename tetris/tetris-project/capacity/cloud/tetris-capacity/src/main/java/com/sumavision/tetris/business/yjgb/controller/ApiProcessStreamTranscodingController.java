package com.sumavision.tetris.business.yjgb.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.business.yjgb.service.TransformService;
import com.sumavision.tetris.business.yjgb.vo.MimsVO;
import com.sumavision.tetris.business.yjgb.vo.RecordVO;
import com.sumavision.tetris.business.yjgb.vo.StreamTranscodingVO;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/api/process/stream/transcoding")
public class ApiProcessStreamTranscodingController {
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private TransformService transformService;

	/**
	 * 添加流转码任务<br/>
	 * <b>作者:</b>xh<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午2:43:01
	 * @param String assetPath 录制地址
	 * @param String transcode_streamTranscodingInfo 流转码信息
	 * @param String transcode_recordInfo 录制信息
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add/task")
	public Object addTask(
			String __processInstanceId__, 
			String assetPath, 
			String transcode_streamTranscodingInfo, 
			String transcode_recordInfo, 
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		StreamTranscodingVO info = JSON.parseObject(transcode_streamTranscodingInfo, StreamTranscodingVO.class);
		
		RecordVO recordInfo = null;
		if (transcode_recordInfo != null && !transcode_recordInfo.isEmpty()) {
			recordInfo = JSON.parseObject(transcode_recordInfo, RecordVO.class);
		}
		
		if (assetPath != null && !assetPath.isEmpty()) {
			info.setAssetUrl(assetPath);
		}
		
		String messageId = null;
		if (info.isTranscoding()) {
			messageId = transformService.addStreamTask(user, __processInstanceId__, info, recordInfo);
		}
		
		return new HashMapWrapper<String, Object>().put("record_recordInfo", transcode_recordInfo).put("record_messageId", messageId).getMap();
	}
	
	/**
	 * 删除流转码任务<br/>
	 * <b>作者:</b>xh<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午3:55:01
	 * @param String messageId 任务标识
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/task")
	public Object deleteTask(String messageId, HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		
		//能力对接
		MimsVO _mims = transformService.deleteStreamTask(user, messageId);
		
		return new HashMapWrapper<String, Object>().put("mimsIfAdd", _mims.isNeedAdd())
												   .put("mimsName", _mims.getName())
												   .put("mimsType", _mims.getType())
												   .put("mimsHttpUrl", _mims.getHttpUrl())
												   .put("mimsFtpUrl", _mims.getFtpUrl())
												   .getMap();
	}
	
	/**
	 * 录制回调节点<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月25日 上午10:05:51
	 * @param String messageId 任务标识
	 * @param String mimsId 媒资id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/record/callback")
	public Object recordCallback(String messageId, String mimsId, HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		
		transformService.recordCallback(messageId, mimsId);
		
		return null;
	}
	
}
