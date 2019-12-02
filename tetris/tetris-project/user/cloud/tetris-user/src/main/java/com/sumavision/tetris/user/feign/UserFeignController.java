package com.sumavision.tetris.user.feign;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserService;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/user/feign")
public class UserFeignController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录校验<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午2:39:14
	 * @param String token 登录token
	 * @return boolean 判断结果
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/check/token")
	public Object checkToken(
			String token, 
			HttpServletRequest request) throws Exception{
		
		return userQuery.checkToken(token);
	}
	
	/**
	 * 查询当前登录用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 上午10:23:32
	 * @return UserVO 当前登录用户
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/current")
	public Object current(HttpServletRequest request) throws Exception{
		
		return userQuery.current();
	}
	
	/**
	 * 根据token查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午6:23:15
	 * @param String token 用户登录token
	 * @return UserVO 用户数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/find/by/token")
	public Object findByToken(
			String token,
			HttpServletRequest request) throws Exception{
		
		return userQuery.findByToken(token);
	}
	
	/**
	 * 根据用户id查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月11日 下午3:25:59
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
		
		if(except == null){
			return userQuery.listByCompanyId(companyId, currentPage, pageSize);
		}else{
			List<Long> exceptIds = JSON.parseArray(except, Long.class);
			return userQuery.listByCompanyIdWithExcept(companyId, exceptIds, currentPage, pageSize);
		}
	}
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String nickname,
            Long companyId,
            String password,
            String mobile,
            String mail,
            String classify) throws Exception{
		
		userService.add(nickname, nickname, password, password, mobile, mail, classify, companyId);
		return null;
	}
	
}
