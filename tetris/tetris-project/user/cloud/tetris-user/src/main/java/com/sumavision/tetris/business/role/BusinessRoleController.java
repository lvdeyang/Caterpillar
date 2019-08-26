package com.sumavision.tetris.business.role;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/business/role")
public class BusinessRoleController {

	@Autowired
	private BusinessRoleQuery businessRoleQuery;
	
	@Autowired
	private BusinessRoleService businessRoleService;
	
	/**
	 * 根据id查询角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月8日 下午1:44:02
	 * @param JSONString ids 角色id列表 
	 * @return List<SystemRoleVO> 角色列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/find/by/id/in")
	public Object findByIdIn(
			String ids,
			HttpServletRequest request) throws Exception{
		if(ids == null) return null;
		return businessRoleQuery.findByIdIn(JSON.parseArray(ids, Long.class));
	}
	
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
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/with/except/ids")
	public Object listWithExceptIds(
			String except,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		if(except == null){
			return businessRoleQuery.listWithExceptIds(null, currentPage, pageSize);
		}else{
			return businessRoleQuery.listWithExceptIds(JSON.parseArray(except, Long.class), currentPage, pageSize);
		}
	}
	
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
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		return businessRoleQuery.list(currentPage, pageSize);
	}
	
	/**
	 * 添加企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:28:02
	 * @param String name 角色名称
	 * @return SystemRoleVO 企业业务角色
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name,
			HttpServletRequest request) throws Exception{
		return businessRoleService.add(name);
	}
	
	/**
	 * 修改企业业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:31:49
	 * @param Long id 角色id
	 * @param String name 角色名称
	 * @return SystemRoleVO 角色
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit")
	public Object edit(
			Long id,
			String name,
			HttpServletRequest request) throws Exception{
		return businessRoleService.edit(id, name);
	}
	
	/**
	 * 删除业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月6日 上午10:17:42
	 * @param @PathVariable Long id 业务角色id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		businessRoleService.remove(id);
		return null;
	}
	
}
