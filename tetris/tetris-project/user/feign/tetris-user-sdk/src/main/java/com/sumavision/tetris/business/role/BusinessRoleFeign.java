package com.sumavision.tetris.business.role;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.config.feign.FeignConfiguration;

@FeignClient(name = "tetris-user", configuration = FeignConfiguration.class)
public interface BusinessRoleFeign {

	/**
	 * 根据id查询角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月8日 下午1:44:02
	 * @param JSONString ids 角色id列表 
	 * @return List<SystemRoleVO> 角色列表
	 */
	@RequestMapping(value = "/business/role/feign/find/by/id/in")
	public JSONObject findByIdIn(@RequestParam("ids") String ids) throws Exception;
	
	/**
	 * 分页查询企业业务角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:07:34
	 * @param JSONString except 例外角色id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 总数据量
	 * @return List<SystemRoleVO> rows 业务角色列表
	 */
	@RequestMapping(value = "/business/role/feign/list/with/except/ids")
	public JSONObject listWithExceptIds(
			@RequestParam("except") String except,
			@RequestParam("currentPage") int currentPage,
			@RequestParam("pageSize") int pageSize) throws Exception;
	
	/**
	 * 分页查询企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:07:34
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 总数据量
	 * @return List<SystemRoleVO> rows 业务角色列表
	 */
	@RequestMapping(value = "/business/role/feign/list")
	public JSONObject list(
			@RequestParam("currentPage") int currentPage, 
			@RequestParam("pageSize") int pageSize) throws Exception;
	
	/**
	 * 查询企业管理员业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月7日 下午4:54:09
	 * @return SystemRoleVO 企业管理员业务角色
	 */
	@RequestMapping(value = "/business/role/feign/find/company/admin/role")
	public JSONObject findCompanyAdminRole() throws Exception;
	
}
