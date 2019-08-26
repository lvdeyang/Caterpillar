package com.sumavision.tetris.system.role;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = UserSystemRolePermissionPO.class, idClass = Long.class)
public interface UserSystemRolePermissionDAO extends BaseDAO<UserSystemRolePermissionPO>{

	/**
	 * 获取用户的系统角色权限绑定<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:34:45
	 * @param Long userId 用户id
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByUserId(Long userId);
	
	/**
	 * 根据用户id和系统角色列表获取权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午2:14:21
	 * @param Long userId 用户id
	 * @param Collection<Long> roleIds 系统角色id列表
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByUserIdAndRoleIdIn(Long userId, Collection<Long> roleIds);
	
	/**
	 * 分页获取用户的系统角色权限绑定<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:34:45
	 * @param Long userId 用户id
	 * @param Pageable page 分页信息
	 * @return Page<UserSystemRolePermissionPO> 权限列表
	 */
	public Page<UserSystemRolePermissionPO> findByUserId(Long userId, Pageable page);
	
	/**
	 * 根据用户和角色类型分页查询授权情况
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午3:17:10
	 * @param Long userId 用户id
	 * @param SystemRoleType roleType 角色类型
	 * @param Pageable page 分页信息
	 * @return Page<UserSystemRolePermissionPO> 权限列表
	 */
	public Page<UserSystemRolePermissionPO> findByUserIdAndRoleType(Long userId, SystemRoleType roleType, Pageable page);
	
	/**
	 * 统计用户绑定的系统角色数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月21日 下午12:30:40
	 * @param Long userId 用户id
	 * @return int 系统角色数量
	 */
	public int countByUserId(Long userId);
	
	/**
	 * 根据用户和角色类型统计授权数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午3:14:45
	 * @param Long userId 用户id
	 * @param SystemRoleType roleType 角色类型
	 * @return 授权数量
	 */
	public long countByUserIdAndRoleType(Long userId, SystemRoleType roleType);
	
	/**
	 * 根据角色（批量）获取角色权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午2:13:45
	 * @param Collection<Long> roleIds 角色id列表
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByRoleIdIn(Collection<Long> roleIds);
	
	/**
	 * 分页查询系统角色绑定的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午4:59:50
	 * @param Long roleId 角色id
	 * @param Pageable page 分页信息
	 * @return Page<UserSystemRolePermissionPO> 权限列表
	 */
	public Page<UserSystemRolePermissionPO> findByRoleId(Long roleId, Pageable page);
	
	/**
	 * 统计系统角色绑定的用户数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午4:56:13
	 * @param Long roleId 系统角色id
	 * @return int 用户数量
	 */
	public int countByRoleId(Long roleId);
	
	/**
	 * 根据系统角色id和用户列表获取权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午8:03:12
	 * @param Long roleId 系统角色id
	 * @param Collection<Long> userIds 用户id列表 
	 * @return List<UserSystemRolePermissionPO> 权限列表
	 */
	public List<UserSystemRolePermissionPO> findByRoleIdAndUserIdIn(Long roleId, Collection<Long> userIds);
	
	/**
	 * 根据用户id,权限id以及生成类型查询权限<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午1:29:05
	 * @param Long userId 用户id
	 * @param Long roleId 权限id
	 * @param boolean autoGeneration 是否自动生成
	 * @return UserSystemRolePermissionPO 权限
	 */
	public UserSystemRolePermissionPO findByUserIdAndRoleIdAndAutoGeneration(Long userId, Long roleId, boolean autoGeneration);
	
}
