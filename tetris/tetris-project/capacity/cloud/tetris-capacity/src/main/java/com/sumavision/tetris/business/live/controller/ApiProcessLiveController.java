package com.sumavision.tetris.business.live.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.business.common.dao.TaskInputDAO;
import com.sumavision.tetris.business.common.po.TaskInputPO;
import com.sumavision.tetris.business.common.service.LockService;
import com.sumavision.tetris.business.common.service.RedisLock;
import com.sumavision.tetris.business.live.service.StreamPassbyService;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

import javassist.expr.NewArray;

@Controller
@RequestMapping(value = "/api/process/live")
public class ApiProcessLiveController {
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private StreamPassbyService streamPassbyService;
	
	@Autowired
	private LockService lockService;
	
	@Autowired
	private TaskInputDAO taskInputDao;

	/**
	 * 添加流透传任务 -- rtmp转hls<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午2:49:48
	 * @param String rtmpUrl rtmp地址
	 * @param String name 名称
	 * @param String storageUrl 存储地址
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/stream/passby")
	public Object streamPassby(
			String __processInstanceId__, 
		    String rtmpUrl, 
		    String name, 
		    String storageUrl, 
		    HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//String id = streamPassbyService.createRtmp2hls(__processInstanceId__, rtmpUrl, name, storageUrl);
		
		return null;
	}
	
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
	public Object deleteStreamPassby(String liveId, HttpServletRequest request) throws Exception{
		
		streamPassbyService.deleteRtmp2Hls(liveId);
		
		return null;
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/test")
	public Object test(String name, HttpServletRequest request) throws Exception{
		
		final TaskInputDAO dao = taskInputDao;
		
		for(int i=0; i<2; i++){
			final int ii = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					lockService.test(ii);
				}
			}).start();
		}
//		System.out.println(name + new Date().getTime());
//		lockService.test1(name);
//		System.out.println(name + new Date().getTime());
		
		return null;
	}
	
}
