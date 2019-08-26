package com.sumavision.tetris.system.role;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.mvc.ext.response.parser.JsonBodyResponseParser;

/**
 * 用户系统角色查询sdk接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月11日 下午3:21:45
 */
@Component
public class SystemRoleQuery {

	@Autowired
	private SystemRoleFeign systemRoleFeign;
	
	/**
	 * 查询用户的所有系统角色，包含组织机构关联角色，取并集<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月11日 下午3:21:59
	 * @param String userId 用户id
	 * @return List<SystemRoleVO> 角色列表
	 */
	public List<SystemRoleVO> queryUserRoles(String userId) throws Exception{
		return JsonBodyResponseParser.parseArray(systemRoleFeign.queryUserRoles(), SystemRoleVO.class);
	}
	
	/**
	 * 分组查询系统角色sdk接口（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:09:53
	 * @param Collection<String> roleIds 例外系统角色id列表
	 * @return List<SystemRoleGroupVO> 分组后的系统角色
	 */
	public List<SystemRoleGroupVO> listWithGroupByExceptIds(Collection<String> roleIds) throws Exception{
		JSONObject response = null;
		if(roleIds==null || roleIds.size()<=0){
			response = systemRoleFeign.listWithGroupByExceptIds(null);
		}else{
			response = systemRoleFeign.listWithGroupByExceptIds(JSON.toJSONString(roleIds));
		}
		return JsonBodyResponseParser.parseArray(response, SystemRoleGroupVO.class);
	}
	
	/**
	 * 根据给定的系统角色id分组查询系统角色sdk接口<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:09:53
	 * @param Collection<String> roleIds 给定的系统角色id列表
	 * @return List<SystemRoleGroupVO> 分组后的系统角色
	 */
	public List<SystemRoleGroupVO> listWithGroupByIds(Collection<String> roleIds) throws Exception{
		JSONObject response = null;
		if(roleIds==null || roleIds.size()<=0){
			response = systemRoleFeign.listWithGroupByIds(null);
		}else{
			response = systemRoleFeign.listWithGroupByIds(JSON.toJSONString(roleIds));
		}
		return JsonBodyResponseParser.parseArray(response, SystemRoleGroupVO.class);
	}
	
	/**
	 * 根据id（批量）查询系统角色列表sdk接口<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 下午1:41:09
	 * @param Collection<String> roleIds 给定的系统角色id列表
	 * @return List<SystemRoleVO> 系统角色列表
	 */
	public List<SystemRoleVO> listByIds(Collection<String> roleIds) throws Exception{
		JSONObject response = null;
		if(roleIds==null || roleIds.size()<=0){
			response = systemRoleFeign.listByIds(null);
		}else{
			response = systemRoleFeign.listByIds(JSON.toJSONString(roleIds));
		}
		return JsonBodyResponseParser.parseArray(response, SystemRoleVO.class);
	}
	
	/**
	 * 查询系统内置角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午1:44:52
	 * @return SystemRoleVO 系统内置角色
	 */
	public SystemRoleVO queryInternalRole() throws Exception{
		return JsonBodyResponseParser.parseObject(systemRoleFeign.queryInternalRole(), SystemRoleVO.class);
	}
	
}
