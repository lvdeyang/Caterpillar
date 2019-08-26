package com.sumavision.tetris.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.config.server.ServerProps;
import com.sumavision.tetris.config.server.UserServerPropsQuery;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private UserServerPropsQuery userServerPropsQuery;
	
	/**
	 * 根据id查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月11日 下午4:51:04
	 * @param JSONArray ids 用户id列表
	 * @return List<UserVO> 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/find/by/id/in")
	public Object findByIdIn(
			String ids,
			HttpServletRequest request) throws Exception{
		List<Long> userIds = JSON.parseArray(ids, Long.class);
		return userQuery.findByIdIn(userIds);
	}
	
	/**
	 * 分页查询公司下的用户列表（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:17:57
	 * @param Long companyId 公司id
	 * @param JSONString except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 用户总量
	 * @return List<UserVO> rows 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/by/{companyId}/with/except")
	public Object listByCompanyIdWithExcept(
			@PathVariable Long companyId,
			String except,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		List<Long> exceptIds = null;
		if(except != null){
			exceptIds = JSON.parseArray(except, Long.class);
		}
		return userQuery.listByCompanyIdWithExcept(companyId, exceptIds, currentPage, pageSize);
	}
	
	/**
	 * 重定向到个人中心<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午3:11:36
	 * @throws Exception 
	 */
	@RequestMapping(value = "/index/personal/{token}")
	public void queryPersonalUrl(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		ServerProps serverProps = userServerPropsQuery.queryProps();
		
		StringBufferWrapper redirectUrl = new StringBufferWrapper().append("http://")
				   .append(serverProps.getIp())
				   .append(":")
				   .append(serverProps.getPort())
				   .append("/")
				   .append("index/")
				   .append(token)
				   .append("#/page-personal");
		
		response.sendRedirect(redirectUrl.toString());
	}
}
