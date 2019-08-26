package com.sumavision.tetris.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.config.server.ServerProps;
import com.sumavision.tetris.config.server.UserServerPropsQuery;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.exception.UserNotExistException;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServerPropsQuery userServerPropsQuery;
	
	/**
	 * 查询枚举类型<br/>
	 * <p>
	 *   查询用户分类<br/>
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 上午9:26:40
	 * @return Set<String> classifies 用户分类
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query/types")
	public Object queryTypes(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		Set<String> values = new HashSet<String>();
		UserClassify[] classifies = UserClassify.values();
		for(UserClassify classify:classifies){
			if(classify.isShow()){
				values.add(classify.getName());
			}
		}
		
		return values;
	}
	
	/**
	 * 分页查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 上午8:29:31
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserVO> 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			int currentPage,
			int pageSize,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		return userQuery.list(currentPage, pageSize);
	}
	
	/**
	 * 分页查询用户（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午5:33:31
	 * @param JSONString except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return List<UserVO> rows 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/with/except")
	public Object listWithExceptIds(
			String except,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		if(except == null){
			return userQuery.list(currentPage, pageSize);
		}else{
			List<Long> exceptIds = JSON.parseArray(except, Long.class);
			return userQuery.listWithExcept(exceptIds, currentPage, pageSize);
		}
		
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
	@RequestMapping(value = "/list/company/user/with/except")
	public Object listCompanyUserWithExceptIds(
			String except,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		Long companyId = Long.valueOf(user.getGroupId());
		List<Long> exceptIds = null;
		if(except != null){
			exceptIds = JSON.parseArray(except, Long.class);
		}
		return userQuery.listByCompanyIdWithExcept(companyId, exceptIds, currentPage, pageSize);
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
	 * 添加一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午3:55:30
	 * @param String nickname 昵称
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String repeat 密码确认
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param Long companyId 公司id
	 * @param String companyName 公司名称
	 * @return UserVO 用户数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String nickname,
            String username,
            String password,
            String repeat,
            String mobile,
            String mail,
            String classify,
            Long companyId,
            String companyName) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		if(classify.equals(UserClassify.NORMAL.getName())){
			return userService.add(nickname, username, password, repeat, mobile, mail, classify, true);
		}else if(classify.equals(UserClassify.COMPANY.getName())){
			if(companyId!=null && companyName==null){
				return userService.add(nickname, username, password, repeat, mobile, mail, classify, companyId);
			}else if(companyName!=null && companyId==null){
				return userService.add(nickname, username, password, repeat, mobile, mail, classify, companyName);
			}
		}
		return null;
	}
	
	/**
	 * 删除一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:39:20
	 * @param @PathVariable id 用户id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Object delete(
			@PathVariable Long id, 
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		userService.delete(id);
		
		return null;
	}
	
	/**
	 * 修改一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:54:11
	 * @param @PathVariable Long id 用户id
	 * @param String nickname 昵称
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param boolean editPassword 是否修改密码
	 * @param String oldPassword 旧密码
	 * @param String newPassword 新密码
	 * @param String repeat 重复新密码
	 * @return UserVO 修改后的数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String nickname,
            String mobile,
            String mail,
            String tags,
            boolean editPassword,
            String oldPassword,
            String newPassword,
            String repeat) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		return userService.edit(id, nickname, mobile, mail, tags, editPassword, oldPassword, newPassword, repeat);
	}
	
	/**
	 * 分页查询用户<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 上午8:29:31
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserVO> 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/subordinate/list")
	public Object listBySubordinate(
			int currentPage,
			int pageSize,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		if(user.getId() != null){
			return userQuery.list(currentPage, pageSize,user.getId());
		}else if(user.getUuid() != null){
			return userQuery.list(currentPage, pageSize,Long.parseLong(user.getUuid()));
		}else {
			return null;
		}
	}
	
	/**
	 * 查询当前用户详细信息<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 上午8:29:31
	 * @return UserVO 用户
	 * @throws Exception 
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query")
	public Object query(HttpServletRequest request) throws Exception{
		UserVO user = userQuery.current();
		
		List<UserVO> userInfo = userQuery.findByIdIn(new ArrayListWrapper<Long>().add(user.getId()).getList());
		
		if (userInfo == null || userInfo.isEmpty()) throw new UserNotExistException(user.getId()); 
		
		return userInfo.get(0);
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
