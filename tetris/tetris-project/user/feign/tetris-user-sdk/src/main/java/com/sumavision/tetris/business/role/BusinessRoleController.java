package com.sumavision.tetris.business.role;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/business/role")
public class BusinessRoleController {
	
	@Autowired
	private BusinessRoleQuery businessRoleQuery;
	
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
	
}
