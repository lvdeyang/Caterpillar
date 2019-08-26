package com.sumavision.tetris.organization;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/organization/user/permission")
public class OrganizationUserPermissionController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private OrganizationUserPermissionDAO organizationUserPermissionDao;
	
	@Autowired
	private OrganizationUserPermissionQuery organizationUserPermissionQuery;
	
	@Autowired
	private OrganizationUserPermissionService organizationUserPermissionService;
	
	/**
	 * 分页查询部门下的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午9:15:51
	 * @param Long organizationId 部门id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return List<OrganizationUserPermissionVO> rows 用户列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Long organizationId,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		return organizationUserPermissionQuery.listByOrganizationId(organizationId, currentPage, pageSize);
	}
	
	/**
	 * 绑定用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午6:09:59
	 * @param Long id 部门id
	 * @param String users 用户id数组[id, id]
	 * @return List<UserVO> 绑定后部门内所有用户
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/bind")
	public Object bind(
			Long organizationId, 
			String userIds, 
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		if(userIds == null) return null;
		
		return organizationUserPermissionService.bind(organizationId, JSON.parseArray(userIds, String.class));
	}
	
	/**
	 * 用户部门解绑<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月8日 上午9:19:29
	 * @param Long id 部门id
	 * @param String userId 用户id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/unbind/{id}")
	public Object unbind(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		OrganizationUserPermissionPO permission = organizationUserPermissionDao.findOne(id);
		
		if(permission != null){
			organizationUserPermissionDao.delete(permission);
		}
		
		return null;
	}
	
}
