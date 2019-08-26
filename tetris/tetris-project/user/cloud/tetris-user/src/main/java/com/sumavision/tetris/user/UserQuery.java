package com.sumavision.tetris.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.constant.HttpConstant;
import com.sumavision.tetris.mvc.ext.context.HttpSessionContext;
import com.sumavision.tetris.organization.CompanyDAO;
import com.sumavision.tetris.organization.CompanyPO;
import com.sumavision.tetris.system.role.SystemRoleDAO;
import com.sumavision.tetris.system.role.SystemRolePO;
import com.sumavision.tetris.system.role.SystemRoleType;
import com.sumavision.tetris.system.theme.SystemThemeDAO;
import com.sumavision.tetris.system.theme.SystemThemePO;
import com.sumavision.tetris.user.exception.TokenTimeoutException;

@Component
public class UserQuery {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserQuery.class);
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private SystemThemeDAO systemThemeDao;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	/**
	 * 用户登录校验<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午2:39:14
	 * @param String token 登录token
	 * @return boolean 判断结果
	 */
	public boolean checkToken(String token) throws Exception{
		UserPO user = userDao.findByToken(token);
		if(user == null){
			LOG.error(new StringBufferWrapper().append("token 无效：").append(token).toString());
			throw new TokenTimeoutException();
		}
		Date now = new Date();
		Date timeScope = DateUtil.addMinute(user.getLastModifyTime(), 30);
		if(!timeScope.after(now)){
			LOG.error(new StringBufferWrapper().append("token 超时：").append(token).toString());
			throw new TokenTimeoutException();
		}
		user.setLastModifyTime(now);
		userDao.save(user);
		return true;
	}
	
	/**
	 * 检查当前用户的token是否可用<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月26日 下午3:26:04
	 * @param UserPO user 用户
	 * @return boolean token有效性
	 */
	public boolean userTokenUseable(UserPO user) throws Exception{
		if(user.getToken() == null) return false;
		Date now = new Date();
		Date timeScope = DateUtil.addMinute(user.getLastModifyTime(), 30);
		if(!timeScope.after(now)){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取当前登录用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月22日 上午10:14:26
	 * @return UserVO 当前用户
	 */
	public UserVO current() throws Exception{

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		HttpSession session = null;
		String sessionId = request.getHeader(HttpConstant.HEADER_SESSION_ID);
		if(sessionId == null){
			//临时session 5秒超时
			session = HttpSessionContext.build(null, HttpConstant.TEMPORARY_SESSION_TIMEOUT);
		}else{
			session = HttpSessionContext.get(sessionId);
			if(session == null){
				//feign调用统一sessionid 超时时间
				session = HttpSessionContext.build(sessionId, HttpConstant.SESSION_TIMEOUT);
			}
		}
		
		String token = request.getHeader(HttpConstant.HEADER_AUTH_TOKEN);
		UserVO user = (UserVO)session.getAttribute(HttpConstant.ATTRIBUTE_USER);
		boolean needQuery = false;
		if(user == null) needQuery = true;
		else if(!user.getToken().equals(token)) needQuery = true;
		if(!needQuery) return user;
		
		//查询用户
		user = findByToken(token);
		session.setAttribute(HttpConstant.ATTRIBUTE_USER, user);
		
		return user;
	}
	
	/**
	 * 清除当前登录用户缓存<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月22日 上午10:14:26
	 */
	public void clearCurrentUser() throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String sessionId = request.getHeader(HttpConstant.HEADER_SESSION_ID);
		if(sessionId != null){
			HttpSession session = HttpSessionContext.get(sessionId);
			if(session != null){
				session.removeAttribute(HttpConstant.ATTRIBUTE_USER);
			}
		}
	}
	
	/**
	 * 根据token查询用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月6日 上午11:12:39
	 * @param String token 用户登录令牌
	 * @return UserPO 用户
	 */
	public UserVO findByToken(String token) throws Exception{
		if(token == null) return null;
		UserPO userEntity = userDao.findByToken(token);
		if(userEntity == null) return null;
		
		UserVO user = new UserVO();
		user.setUuid(userEntity.getId().toString())
			.setNickname(userEntity.getNickname())
			.setClassify(userEntity.getClassify()==null?"":userEntity.getClassify().toString())
			.setIcon(userEntity.getIcon())
			.setToken(userEntity.getToken())
			.setId(userEntity.getId());
		if(userEntity.getTags() != null && !userEntity.getTags().isEmpty()) user.setTags(Arrays.asList(userEntity.getTags().split(UserPO.SEPARATOR_TAG))); else user.setTags(new ArrayList<String>());
		
		List<SystemRolePO> businessRoles = systemRoleDao.findByUserIdAndType(userEntity.getId(), SystemRoleType.BUSINESS.toString());
		
		if(businessRoles!=null && businessRoles.size()>0){
			StringBufferWrapper roleIds = new StringBufferWrapper();
			for(SystemRolePO role:businessRoles){
				roleIds.append(role.getId()).append(",");
			}
			String ids = roleIds.toString();
			ids = ids.substring(0, ids.length()-1);
			user.setBusinessRoles(ids);
		}
		
		//加入组织机构信息
		if(UserClassify.COMPANY.equals(userEntity.getClassify())){
			CompanyPO company = companyDao.findByUserId(userEntity.getId());
			user.setCompanyInfo(company);
			if(company.getThemeId() != null){
				SystemThemePO theme = systemThemeDao.findOne(company.getThemeId());
				if(theme == null){
					user.setThemeUrl(SystemThemePO.DEFAULT_URL);
				}else{
					user.setThemeUrl(theme.getUrl());
				}
			}else{
				user.setThemeUrl(SystemThemePO.DEFAULT_URL);
			}
		}else{
			if(userEntity.isAutoGeneration()){
				user.setGroupId("0");
			}
			user.setCompanyInfo(null);
			user.setThemeUrl(SystemThemePO.DEFAULT_URL);
		}
		
		return user;
	}
	
	/**
	 * 分页查询用户（前端接口）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月18日 下午5:32:06
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserVO> 用户列表
	 */
	public Map<String, Object> list(int currentPage, int pageSize) throws Exception{
		List<UserPO> users = findAllOrderByUpdateTimeDesc(currentPage, pageSize);
		List<UserVO> view_users = UserVO.getConverter(UserVO.class).convert(users, UserVO.class);
		long total = userDao.count();
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", view_users)
												   .getMap();
	}
	
	/**
	 * 分页查询用户（后台接口）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月18日 下午5:32:46
	  * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserPO> 用户列表
	 */
	public List<UserPO> findAllOrderByUpdateTimeDesc(int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserPO> users = userDao.findAllOrderByUpdateTimeDesc(page);
		return users.getContent();
	}
	
	/**
	 * 分页查询用户（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午6:37:06
	 * @param Collection<Long> except 例外用户id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 数据总量
	 * @return List<UserVO> rows 用户列表
	 */
	public Map<String, Object> listWithExcept(Collection<Long> except, int currentPage, int pageSize) throws Exception{
		int total = userDao.countWithExcept(except);
		List<UserPO> users = findWithExceptOrderByUpdateTimeDesc(except, currentPage, pageSize);
		List<UserVO> view_users = UserVO.getConverter(UserVO.class).convert(users, UserVO.class);
		return new HashMapWrapper<String, Object>().put("total", total)
											       .put("rows", view_users)
											       .getMap();
	}
	
	/**
	 * 分页查询用户（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月23日 下午6:31:48
	 * @param Collection<Long> except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserPO> 用户列表
	 */
	public List<UserPO> findWithExceptOrderByUpdateTimeDesc(Collection<Long> except, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserPO> users = userDao.findWithExceptOrderByUpdateTimeDesc(except, page);
		return users.getContent();
	}
	
	/**
	 * 分页查询公司下的用户列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:17:57
	 * @param Long companyId 公司id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 用户总量
	 * @return List<UserVO> rows 用户列表
	 */
	public Map<String, Object> listByCompanyId(Long companyId, int currentPage, int pageSize) throws Exception{
		int total = userDao.countByCompanyId(companyId);
		List<UserPO> users = findByCompanyId(companyId, currentPage, pageSize);
		List<UserVO> view_users = new ArrayList<UserVO>();
		if(users!=null && users.size()>0){
			for(UserPO user:users){
				view_users.add(new UserVO().set(user));
			}
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", view_users)
												   .getMap();
	}
	
	/**
	 * 分页查询公司下的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:12:38
	 * @param Long companyId 公司id
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserPO> 用户列表
	 */
	public List<UserPO> findByCompanyId(Long companyId, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserPO> users = userDao.findByCompanyId(companyId, page);
		return users.getContent();
	}
	
	/**
	 * 分页查询公司下的用户列表（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:17:57
	 * @param Long companyId 公司id
	 * @param Collection<Long> except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 用户总量
	 * @return List<UserVO> rows 用户列表
	 */
	public Map<String, Object> listByCompanyIdWithExcept(Long companyId, Collection<Long> except, int currentPage, int pageSize) throws Exception{
		if(except == null) return listByCompanyId(companyId, currentPage, pageSize);
		int total = userDao.countByCompanyIdWithExcept(companyId, except);
		List<UserPO> users = findByCompanyIdWithExcept(companyId, except, currentPage, pageSize);
		List<UserVO> view_users = new ArrayList<UserVO>();
		if(users!=null && users.size()>0){
			for(UserPO user:users){
				view_users.add(new UserVO().set(user));
			}
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", view_users)
												   .getMap();
	}
	
	/**
	 * 分页查询公司下的用户（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:12:38
	 * @param Long companyId 公司id
	 * @param Collection<Long> except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserPO> 用户列表
	 */
	public List<UserPO> findByCompanyIdWithExcept(Long companyId, Collection<Long> except, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserPO> users = userDao.findByCompanyIdWithExcept(companyId, except, page);
		return users.getContent();
	}
	
	/**
	 * 分页查询用户（前端接口，增加管理员过滤，跟隶属角色无关）<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月28日 下午5:32:06
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @param long userId 用户id
	 * @return List<UserVO> 用户列表
	 */
	public Map<String, Object> list(int currentPage, int pageSize,Long userId) throws Exception{
		CompanyPO companyPO = companyDao.findByUserId(userId);
		List<Long> userList = new ArrayList<Long>();
		userList.add(userId);
		if (companyPO != null) {
			return listByCompanyIdWithExcept(companyPO.getId(), userList, currentPage, pageSize);
		}else {
			return null;
		}
	}
	
	/**
	 * 分页查询公司下的用户列表（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月25日 上午11:17:57
	 * @param Long companyId 公司id
	 * @param Collection<Long> except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 用户总量
	 * @return List<UserVO> rows 用户列表
	 */
	public Map<String, Object> listByRoleIdWithExcept(Long roleId, Collection<Long> except, int currentPage, int pageSize) throws Exception{
		int total = userDao.countByRoleIdWithExcept(roleId, except);
		List<UserPO> users = findByRoleIdWithExcept(roleId, except, currentPage, pageSize);
		List<UserVO> view_users = new ArrayList<UserVO>();
		if(users!=null && users.size()>0){
			view_users = UserVO.getConverter(UserVO.class).convert(users, UserVO.class);
		}
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", view_users)
												   .getMap();
	}
	
	/**
	 * 分页查询公司下的用户（带例外）<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月30日 上午11:12:38
	 * @param Long companyId 公司id
	 * @param Collection<Long> except 例外用户id列表
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<UserPO> 用户列表
	 */
	public List<UserPO> findByRoleIdWithExcept(Long roleId, Collection<Long> except, int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<UserPO> users = userDao.findByRoleIdWithExcept(roleId, except, page);
		return users.getContent();
	}
	
	/**
	 * 根据id查询用户列表<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月11日 下午3:20:13
	 * @param Collection<Long> ids 用户id列表
	 * @return List<UserPO> 用户列表
	 */
	public List<UserVO> findByIdIn(Collection<Long> ids) throws Exception{
		List<UserPO> entities = userDao.findByIdIn(ids);
		return UserVO.getConverter(UserVO.class).convert(entities, UserVO.class);
	}
	
	/**************************************************************************
	 **************************************************************************
	 **************************************************************************/
	
	/**
	 * 获取用户组下所有的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月6日 下午4:53:11
	 * @param String groupId 用户组id
	 * @return List<UserVO> 用户列表
	 */
	@Deprecated
	public List<UserVO> list(String groupId){
		return this.users;
	}
	
	/**
	 * 分页获取用户组下的用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午5:24:19
	 * @param String groupId 用户组id
	 * @param Integer pageSize 每页数量
	 * @param Integer currentSize 当前页码
	 * @oaran Collection<String> except 例外用户
	 * @return List<UserVO> 用户列表
	 */
	@Deprecated
	public List<UserVO> list(String groupId, Integer pageSize, Integer currentPage, Collection<String> except){
		if(except==null || except.size()<=0) return this.users;
		List<UserVO> users = this.users;
		List<UserVO> filtered = new ArrayList<UserVO>();
		for(UserVO user:users){
			if(!except.contains(user.getUuid())){
				filtered.add(user);
			}
		}
		return filtered;
	}
	
	/**
	 * 计算企业内部用户总数（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月10日 下午2:19:03
	 * @param String groupId 企业id
	 * @param Collection<String> except 例外用户id列表
	 * @return Integer 总数
	 */
	@Deprecated
	public Integer count(String groupId, Collection<String> except){
		if(except==null || except.size()<=0) return count(groupId);
		List<UserVO> users = this.users;
		int count = 0;
		for(UserVO user:users){
			if(!except.contains(user.getUuid())){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 获取企业内用户总数<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午5:26:09
	 * @param String groupId 用户组id
	 * @return Integer 用户数量
	 */
	@Deprecated
	public Integer count(String groupId){
		return this.users.size();
	}
	
	/**
	 * 根据userId查找用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午6:20:07
	 * @param Collection<String> userIds 用户id列表
	 * @return List<UserVO> 用户列表
	 */
	@Deprecated
	public List<UserVO> find(Collection<String> userIds){
		List<UserVO> users = this.users;
		List<UserVO> findUsers = new ArrayList<UserVO>();
		for(String userId:userIds){
			for(UserVO user:users){
				if(user.getUuid().equals(userId)){
					findUsers.add(user);
					break;
				}
			}
		}
		return findUsers;
	}
	
	/**
	 * 根据userId分页查询<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月10日 上午10:08:22
	 * @param Collection<String> userIds 用户id列表
	 * @param Integer pageSize 每页数据量
	 * @param Integer currentPage 当前页码
	 * @return List<UserVO> 用户列表
	 */
	@Deprecated
	public List<UserVO> find(Collection<String> userIds, Integer pageSize, Integer currentPage){
		return find(userIds);
	}
	
	/** 测试数据 */
	private List<UserVO> users = new ArrayListWrapper<UserVO>().add(new UserVO().setUuid("1")
																        .setNickname("用户1")
																        .setStatus(UserStatus.ONLINE.getName())
																        .setNumbersOfMessage(0))
														.add(new UserVO().setUuid("2")
															            .setNickname("用户2")
															            .setStatus(UserStatus.OFFLINE.getName())
															            .setNumbersOfMessage(0))
														.add(new UserVO().setUuid("3")
															            .setNickname("用户3")
															            .setStatus(UserStatus.OFFLINE.getName())
															            .setNumbersOfMessage(0))
														.add(new UserVO().setUuid("4")
														                .setNickname("用户4")
															            .setStatus(UserStatus.ONLINE.getName())
															            .setNumbersOfMessage(13))
														.add(new UserVO().setUuid("5")
															            .setNickname("用户5")
															            .setStatus(UserStatus.OFFLINE.getName())
															            .setNumbersOfMessage(0))
														.add(new UserVO().setUuid("6")
														                .setNickname("用户6")
															            .setStatus(UserStatus.OFFLINE.getName())
															            .setNumbersOfMessage(2))
														.getList();
	
}

