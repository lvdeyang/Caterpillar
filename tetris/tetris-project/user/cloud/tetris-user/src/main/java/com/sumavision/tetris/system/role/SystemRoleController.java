package com.sumavision.tetris.system.role;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.system.role.exception.SystemRoleGroupNotExistException;
import com.sumavision.tetris.system.role.exception.SystemRoleNotExistException;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/system/role")
public class SystemRoleController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private SystemRoleQuery systemRoleQuery;
	
	@Autowired
	private SystemRoleGroupDAO systemRoleGroupDao;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private SystemRoleService systemRoleService;
	
	/**
	 * 分页查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 上午10:25:27
	 * @param Long groupId 系统角色组id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 系统角色数据总量 
	 * @return List<SystemRoleVO> rows 系统角色列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Long groupId,
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		return systemRoleQuery.list(groupId, currentPage, pageSize);
	}
	
	/**
	 * 添加一个系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午4:04:18
	 * @param Long groupId 系统角色组id
	 * @param String name 系统角色名
	 * @return SystemRoleVO 系统角色
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			Long groupId,
			String name,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		SystemRoleGroupPO group = systemRoleGroupDao.findOne(groupId);
		
		if(group == null){
			throw new SystemRoleGroupNotExistException(groupId);
		}
		
		SystemRolePO role = new SystemRolePO();
		role.setName(name);
		role.setUpdateTime(new Date());
		role.setSystemRoleGroupId(group.getId());
		role.setType(SystemRoleType.SYSTEM);
		systemRoleDao.save(role);
		
		return new SystemRoleVO().set(role);
	}
	
	/**
	 * 修改一个系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午4:10:20
	 * @param @PathVariable Long id 系统角色id
	 * @param String name 系统角色名称
	 * @return SystemRoleVO 系统角色
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		SystemRolePO role = systemRoleDao.findOne(id);
		
		if(role == null){
			throw new SystemRoleNotExistException(id);
		}
		
		role.setName(name);
		systemRoleDao.save(role);
		
		return new SystemRoleVO().set(role);
	}
	
	/**
	 * 删除系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午4:13:30
	 * @param @PathVariable Long id 系统角色id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		SystemRolePO role = systemRoleDao.findOne(id);
		
		if(role != null){
			systemRoleService.delete(new ArrayListWrapper<SystemRolePO>().add(role).getList());
		}
		
		return null;
	}
	
	
}
