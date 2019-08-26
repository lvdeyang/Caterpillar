package com.sumavision.tetris.system.role;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = SystemRolePO.class, idClass = Long.class)
public interface SystemRoleDAO extends BaseDAO<SystemRolePO>{

	/**
	 * 根据类型查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午11:27:24
	 * @param SystemRoleType type 角色类型
	 * @return List<SystemRolePO> 系统角色列表
	 */
	public List<SystemRolePO> findByType(SystemRoleType type);
	
	/**
	 * 根据类型查询系统角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月17日 上午11:27:24
	 * @param SystemRoleType type 角色类型
	 * @param Collection<Long> ids 例外角色id列表
	 * @return List<SystemRolePO> 系统角色列表
	 */
	public List<SystemRolePO> findByTypeAndIdNotIn(SystemRoleType type, Collection<Long> ids);
	
	/**
	 * 查询用户的系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:46:52
	 * @param Long userId 用户id
	 * @return List<SystemRolePO> 系统角色列表
	 */
	@Query(value = "SELECT role.* FROM tetris_user_system_role role LEFT JOIN tetris_user_system_role_permission permission ON role.id=permission.role_id WHERE permission.user_id=?1", nativeQuery = true)
	public List<SystemRolePO> findByUserId(Long userId);
	
	/**
	 * 根据类型查询用户的角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:46:52
	 * @param Long userId 用户id
	 * @return List<SystemRolePO> 角色列表
	 */
	@Query(value = "SELECT role.* FROM tetris_user_system_role role LEFT JOIN tetris_user_system_role_permission permission ON role.id=permission.role_id WHERE permission.user_id=?1 AND role.type=?2", nativeQuery = true)
	public List<SystemRolePO> findByUserIdAndType(Long userId, String type);
	
	/**
	 * 根据分组和类型查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午2:06:38
	 * @param Long systemRoleGroupId 角色组id
	 * @param SystemRoleType type 角色类型
	 * @param Pageable page 分页信息
	 * @return Page<SystemRolePO> 角色列表
	 */
	public Page<SystemRolePO> findBySystemRoleGroupIdAndTypeOrderByUpdateTimeDesc(Long systemRoleGroupId, SystemRoleType type, Pageable page);
	
	/**
	 * 根据系统角色组查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午1:50:21
	 * @param Long systemRoleGroupId 系统角色组id
	 * @return List<SystemRolePO> 系统角色列表
	 */
	public List<SystemRolePO> findBySystemRoleGroupId(Long systemRoleGroupId);
	
	/**
	 * 根据类型查询系统角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午1:21:09
	 * @param boolean autoGeneration 是否自动生成
	 * @return SystemRolePO 系统角色
	 */
	public SystemRolePO findByAutoGeneration(boolean autoGeneration);
	
	/***************
	 * 业务角色相关方法
	 ***************/
	
	/**
	 * 根据公司和角色类型统计角色数量<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:05
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param boolean autoGeneration 是否自动生成
	 * @return long 角色数量
	 */
	public long countByCompanyIdAndTypeAndAutoGeneration(Long companyId, SystemRoleType type, boolean autoGeneration);
	
	/**
	 * 根据公司和角色类型查询角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:59
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param boolean autoGeneration 是否自动生成
	 * @param Pageable page 分页信息
	 * @return Page<SystemRolePO> 角色列表
	 */
	public Page<SystemRolePO> findByCompanyIdAndTypeAndAutoGeneration(Long companyId, SystemRoleType type, boolean autoGeneration, Pageable page);
	
	/**
	 * 根据公司和角色类型查询角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:59
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param boolean autoGeneration 是否自动生成
	 * @param Pageable page 分页信息
	 * @return Page<SystemRolePO> 角色列表
	 */
	public long countByCompanyIdAndTypeAndIdNotInAndAutoGeneration(Long companyId, SystemRoleType type, Collection<Long> exceptIds, boolean autoGeneration);
	
	/**
	 * 根据公司和角色类型查询角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:59
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param boolean autoGeneration 是否自动生成
	 * @param Pageable page 分页信息
	 * @return Page<SystemRolePO> 角色列表
	 */
	public Page<SystemRolePO> findByCompanyIdAndTypeAndIdNotInAndAutoGeneration(Long companyId, SystemRoleType type, Collection<Long> exceptIds, boolean autoGeneration, Pageable page);
	
	/**
	 * 查询企业管理员业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月7日 下午4:50:07
	 * @param Long companyId 公司id
	 * @return SystemRolePO 管理员业务角色
	 */
	@Query(value = "SELECT * FROM TETRIS_USER_SYSTEM_ROLE WHERE COMPANY_ID=?1 AND AUTO_GENERATION=1", nativeQuery = true)
	public SystemRolePO findCompanyAdminRole(Long companyId);
	
}
