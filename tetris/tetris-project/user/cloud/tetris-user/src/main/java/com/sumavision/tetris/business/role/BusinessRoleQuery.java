package com.sumavision.tetris.business.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.system.role.SystemRoleDAO;
import com.sumavision.tetris.system.role.SystemRolePO;
import com.sumavision.tetris.system.role.SystemRoleType;
import com.sumavision.tetris.system.role.SystemRoleVO;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Component
public class BusinessRoleQuery {

	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private UserQuery userQuery;
	 
	@Autowired
	private BusinessRoleQuery businessRoleQuery;
	
	/**
	 * 根据id查询角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月8日 下午1:44:02
	 * @param Collection<Long> ids 角色id列表 
	 * @return List<SystemRoleVO> 角色列表
	 */
	public List<SystemRoleVO> findByIdIn(Collection<Long> ids) throws Exception{
		if(ids==null || ids.size()<=0) return null;
		List<SystemRolePO> entities = systemRoleDao.findAll(ids);
		List<SystemRoleVO> roles = null;
		if(entities!=null && entities.size()>0){
			roles = new ArrayList<SystemRoleVO>();
			for(SystemRolePO entity:entities){
				roles.add(new SystemRoleVO().set(entity));
			}
		}
		return roles;
	}
	
	/**
	 * 查询企业管理员业务角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月7日 下午4:54:09
	 * @return SystemRoleVO 企业管理员业务角色
	 */
	public SystemRoleVO findCompanyAdminRole() throws Exception{
		UserVO user = userQuery.current();
		SystemRolePO entity = systemRoleDao.findCompanyAdminRole(Long.valueOf(user.getGroupId()));
		return new SystemRoleVO().set(entity);
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
	public Map<String, Object> list(
			int currentPage,
			int pageSize) throws Exception{
		UserVO user = userQuery.current();
		Long companyId = Long.valueOf(user.getGroupId());
		long total = systemRoleDao.countByCompanyIdAndTypeAndAutoGeneration(companyId, SystemRoleType.BUSINESS, false);
		List<SystemRolePO> entities = businessRoleQuery.findByCompanyIdAndType(companyId, SystemRoleType.BUSINESS, currentPage, pageSize);
		List<SystemRoleVO> rows = null;
		if(entities!=null && entities.size()>0){
			rows = new ArrayList<SystemRoleVO>();
			for(SystemRolePO entity:entities){
				SystemRoleVO row = new SystemRoleVO().set(entity);
				rows.add(row);
			}
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 分页查询企业业务角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午5:07:34
	 * @param Collection<Long> except 例外角色id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return long total 总数据量
	 * @return List<SystemRoleVO> rows 业务角色列表
	 */
	public Map<String, Object> listWithExceptIds(
			Collection<Long> except,
			int currentPage,
			int pageSize) throws Exception{
		UserVO user = userQuery.current();
		Long companyId = Long.valueOf(user.getGroupId());
		long total = 0l;
		List<SystemRolePO> entities = null;
		if(except==null || except.size()<=0){
			total = systemRoleDao.countByCompanyIdAndTypeAndAutoGeneration(companyId, SystemRoleType.BUSINESS, false);
			entities = businessRoleQuery.findByCompanyIdAndType(companyId, SystemRoleType.BUSINESS, currentPage, pageSize);
		}else{
			total = systemRoleDao.countByCompanyIdAndTypeAndIdNotInAndAutoGeneration(companyId, SystemRoleType.BUSINESS, except, false);
			entities = businessRoleQuery.findByCompanyIdAndTypeWithExceptIds(companyId, SystemRoleType.BUSINESS, except, currentPage, pageSize);
		}
		List<SystemRoleVO> rows = null;
		if(entities!=null && entities.size()>0){
			rows = new ArrayList<SystemRoleVO>();
			for(SystemRolePO entity:entities){
				SystemRoleVO row = new SystemRoleVO().set(entity);
				rows.add(row);
			}
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 根据公司和角色类型查询角色<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:59
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param int currentPage 当前页
	 * @param int pageSize 每页数据量
	 * @return List<SystemRolePO> 角色列表
	 */
	public List<SystemRolePO> findByCompanyIdAndType(
			Long companyId, 
			SystemRoleType type, 
			int currentPage, 
			int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<SystemRolePO> pagedEntities = systemRoleDao.findByCompanyIdAndTypeAndAutoGeneration(companyId, type, false, page);
		return pagedEntities.getContent();
	}
	
	/**
	 * 根据公司和角色类型查询角色（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年8月2日 下午4:53:59
	 * @param Long companyId 公司id
	 * @param SystemRoleType type 角色类型
	 * @param int currentPage 当前页
	 * @param int pageSize 每页数据量
	 * @param Collection<Long> exceptIds 例外角色id列表
	 * @return List<SystemRolePO> 角色列表
	 */
	public List<SystemRolePO> findByCompanyIdAndTypeWithExceptIds(
			Long companyId,
			SystemRoleType type,
			Collection<Long> exceptIds,
			int currentPage,
			int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<SystemRolePO> pagedEntities = systemRoleDao.findByCompanyIdAndTypeAndIdNotInAndAutoGeneration(companyId, type, exceptIds, false, page);
		return pagedEntities.getContent();
	}
	
}
