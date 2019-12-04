package com.sumavision.tetris.user;

import java.util.Collection;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.config.feign.FeignConfiguration;

/**
 * 系统用户feign接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月7日 上午10:25:16
 */
@FeignClient(name = "tetris-user", configuration = FeignConfiguration.class)
public interface UserFeign {

	/**
	 * 用户登录校验<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午2:39:14
	 * @param String token 登录token
	 * @return boolean 判断结果
	 */
	@RequestMapping(value = "/user/feign/check/token")
	public JSONObject checkToken(@RequestParam("token") String token);
	
	/**
	 * 获取当前登录用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月22日 上午10:14:26
	 * @return JSONObject 当前用户
	 */
	@RequestMapping(value = "/user/feign/current")
	public JSONObject current();
	
	/**
	 * 根据token查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午6:23:15
	 * @param String token 用户登录token
	 * @return JSONObject 用户数据
	 */
	@RequestMapping(value = "/user/feign/find/by/token")
	public JSONObject findByToken(@RequestParam("token") String token);
	
	/**
	 * 根据id查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月11日 下午3:30:27
	 * @param JSONArray ids 用户id列表
	 * @return JSONObject 用户列表
	 */
	@RequestMapping(value = "/user/feign/find/by/id/in")
	public JSONObject findByIdIn(@RequestParam("ids") String ids);
	
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
	@RequestMapping(value = "/user/feign/list/by/{companyId}/with/except")
	public JSONObject listByCompanyIdWithExcept(
			@PathVariable("companyId") Long companyId,
			@RequestParam("except") String except,
			@RequestParam("currentPage") int currentPage,
			@RequestParam("pageSize") int pageSize);
	
	
	@RequestMapping(value = "/user/feign/add")
	public Object add(
			@RequestParam("nickname") String nickname,
			@RequestParam("companyId") Long companyId,
			@RequestParam("password") String password,
			@RequestParam("mobile") String mobile,
			@RequestParam("mail") String mail,
			@RequestParam("classify")  String classify,
			@RequestParam("code") String code);
	
	
	@RequestMapping(value = "/user/feign/delete")
	public Object delete(
			@RequestParam("nickname") Long id);
	
}
