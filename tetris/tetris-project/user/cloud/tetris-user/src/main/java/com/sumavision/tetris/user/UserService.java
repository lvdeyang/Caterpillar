package com.sumavision.tetris.user;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.business.role.BusinessRoleService;
import com.sumavision.tetris.commons.util.encoder.MessageEncoder.Sha256Encoder;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.organization.CompanyDAO;
import com.sumavision.tetris.organization.CompanyPO;
import com.sumavision.tetris.organization.CompanyService;
import com.sumavision.tetris.organization.CompanyUserPermissionDAO;
import com.sumavision.tetris.organization.CompanyUserPermissionPO;
import com.sumavision.tetris.organization.CompanyUserPermissionService;
import com.sumavision.tetris.organization.CompanyVO;
import com.sumavision.tetris.organization.OrganizationDAO;
import com.sumavision.tetris.organization.OrganizationPO;
import com.sumavision.tetris.organization.OrganizationUserPermissionDAO;
import com.sumavision.tetris.organization.OrganizationUserPermissionPO;
import com.sumavision.tetris.organization.exception.CompanyNotExistException;
import com.sumavision.tetris.system.role.SystemRoleVO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionDAO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionPO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionService;
import com.sumavision.tetris.user.event.UserRegisteredEvent;
import com.sumavision.tetris.user.exception.MailAlreadyExistException;
import com.sumavision.tetris.user.exception.MobileAlreadyExistException;
import com.sumavision.tetris.user.exception.MobileNotExistException;
import com.sumavision.tetris.user.exception.PasswordCannotBeNullException;
import com.sumavision.tetris.user.exception.PasswordErrorException;
import com.sumavision.tetris.user.exception.RepeatNotMatchPasswordException;
import com.sumavision.tetris.user.exception.UserNotExistException;
import com.sumavision.tetris.user.exception.UsernameAlreadyExistException;
import com.sumavision.tetris.user.exception.UsernameCannotBeNullException;

/**
 * 用户操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月19日 下午3:51:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService{

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	@Autowired
	private UserSystemRolePermissionService userSystemRolePermissionService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyUserPermissionService companyUserPermissionService;
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private OrganizationDAO organizationDao;
	
	@Autowired
	private OrganizationUserPermissionDAO organizationUserPermissionDao;
	
	@Autowired
	private CompanyUserPermissionDAO companyUserPermissionDao;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private Sha256Encoder sha256Encoder;
	
	@Autowired
	private BusinessRoleService businessRoleService;
	
	/**
	 * 添加一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午1:23:01
	 * @param String nickname 昵称
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String repeat 密码确认
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param String classify 用户类型
	 * @param boolean emit 是否要发射事件
	 * @return UserVO 用户
	 */
	public UserVO add(
			String nickname,
            String username,
            String password,
            String repeat,
            String mobile,
            String mail,
            String classify,
            boolean emit) throws Exception{
		
		UserPO user = addUser(nickname, username, password, repeat, mobile, mail, classify);
		
		if(emit){
			//发布用户注册事件
			UserRegisteredEvent event = new UserRegisteredEvent(applicationEventPublisher, user.getId().toString(), user.getNickname());
			applicationEventPublisher.publishEvent(event);
		}
		
		return new UserVO().set(user);
	}
	
	/**
	 * 添加一个用户，并创建公司<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午3:51:08
	 * @param String nickname 昵称
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String repeat 确认密码
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param String companyName 公司名称
	 * @return UserVO 新建的用户
	 */
	public UserVO add(
			String nickname,
            String username,
            String password,
            String repeat,
            String mobile,
            String mail,
            String classify,
            String companyName) throws Exception{
		
		UserPO user = addUser(nickname, username, password, repeat, mobile, mail, UserClassify.COMPANY.getName());
		
		CompanyVO company = null;
		SystemRoleVO adminRole = null;
		if(user.getClassify().equals(UserClassify.COMPANY)){
			//创建公司
			company = companyService.add(companyName, user);
			
			//处理公司管理员
			adminRole = businessRoleService.add(company.getId(), "企业管理员", true);
			
			//用户授权
			//1：授权默认的系统角色
			//2：授权公司管理员业务角色
			userSystemRolePermissionService.bindSystemRole(user.getId(), new ArrayListWrapper<Long>().add(2l).add(Long.valueOf(adminRole.getId())).getList());
		}
		
		//发布用户注册事件
		UserRegisteredEvent event = null;
		if(company == null){
			event = new UserRegisteredEvent(applicationEventPublisher, user.getId().toString(), user.getNickname());
		}else{
			event = new UserRegisteredEvent(applicationEventPublisher, user.getId().toString(), user.getNickname(), company.getId().toString(), company.getName(), adminRole.getId().toString(), adminRole.getName());
		}
		applicationEventPublisher.publishEvent(event);
		
		return new UserVO().set(user);
	}
	
	/**
	 * 添加一个用户，并加入公司<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午3:51:08
	 * @param String nickname 昵称
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String repeat 确认密码
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param String companyId 公司id
	 * @return UserVO 新建的用户
	 */
	public UserVO add(
			String nickname,
            String username,
            String password,
            String repeat,
            String mobile,
            String mail,
            String classify,
            Long companyId) throws Exception{
		
		CompanyPO company = companyDao.findOne(companyId);
		
		if(company == null){
			throw new CompanyNotExistException(companyId);
		}
		
		UserPO user = addUser(nickname, username, password, repeat, mobile, mail, classify);
		
		if(user.getClassify().equals(UserClassify.COMPANY)){
			//加入公司
			companyUserPermissionService.add(company, user);
			//绑定用户和系统角色
			userSystemRolePermissionService.bindSystemRole(user.getId(), new ArrayListWrapper<Long>().add(3l).getList());
		}
		
		//发布用户注册事件
		UserRegisteredEvent event = new UserRegisteredEvent(applicationEventPublisher, user.getId().toString(), user.getNickname(), company.getId().toString(), company.getName());
		applicationEventPublisher.publishEvent(event);
		
		return new UserVO().set(user);
	}
	
	/**
	 * 添加一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午1:23:01
	 * @param String nickname 昵称
	 * @param String username 用户名
	 * @param String password 密码
	 * @param String repeat 密码确认
	 * @param String mobile 手机号
	 * @param String mail 邮箱
	 * @param String classify 用户类型
	 * @return UserPO 用户
	 */
	private UserPO addUser(
			String nickname,
            String username,
            String password,
            String repeat,
            String mobile,
            String mail,
            String classify) throws Exception{
		
		if(username == null) throw new UsernameCannotBeNullException();
		
		if(nickname == null) nickname = username;
		
		if(password == null) throw new PasswordCannotBeNullException();
		
		if(!password.equals(repeat)) throw new RepeatNotMatchPasswordException();
		
		UserPO user = userDao.findByUsername(username);
		if(user != null){
			throw new UsernameAlreadyExistException(username);
		}
		
		if(mobile != null){
			user = userDao.findByMobile(mobile);
			if(user != null){
				throw new MobileAlreadyExistException(mobile);
			}
		}
		
		if(mail != null){
			user = userDao.findByMail(mail);
			if(user != null){
				throw new MailAlreadyExistException(mail);
			}
		}
		
		user = new UserPO();
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(sha256Encoder.encode(password));
		user.setMobile(mobile);
		user.setMail(mail);
		user.setStatus(UserStatus.OFFLINE);
		user.setAutoGeneration(false);
		user.setClassify(UserClassify.fromName(classify));
		user.setUpdateTime(new Date());
		userDao.save(user);
		
		return user;
	}
	
	/**
	 * 删除一个用户<br/>
	 * <p>
	 *  删除用户角色映射
	 * 	-如果用户是企业用户<br/>
	 * 	     如果用户创建了公司<br/>
	 *      删除创建的公司<br/>
	 *      删除公司下的所有部门<br/>
	 *      公司内的所有用户变为个人用户<br/>
	 *      删除公司内公司用户的映射<br/>
	 *      删除公司内部门用户映射<br/>
	 *    如果用户加入了公司<br/>
	 *    	删除公司内公司用户的映射<br/>
	 *      删除公司内部门用户映射<br/>
	 * 	-如果用户是个人用户直接删除<br/>
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:37:10
	 * @param Long id 用户id
	 */
	public void delete(Long id) throws Exception{
		
		UserPO user = userDao.findOne(id);
		
		if(user == null) return;
		
		if(UserClassify.COMPANY.equals(user.getClassify())){
			
			CompanyPO company = companyDao.findByUserId(user.getId());
			
			if(company.getUserId().equals(user.getId().toString())){
				//修改公司内用户为普通用户
				List<UserPO> users = userDao.findByCompanyIdAndExcept(company.getId(), new ArrayListWrapper<Long>().add(user.getId()).getList());
				if(users!=null && users.size()>0){
					for(UserPO scope:users){
						scope.setClassify(UserClassify.NORMAL);
					}
				}
				userDao.save(users);
				
				//删除部门
				List<OrganizationPO> organizations = organizationDao.findByCompanyIdOrderBySerialAsc(company.getId());
				if(organizations!=null && organizations.size()>0){
					Set<Long> organizationIds = new HashSet<Long>();
					for(OrganizationPO organization:organizations){
						organizationIds.add(organization.getId());
					}
					List<OrganizationUserPermissionPO> permissions = organizationUserPermissionDao.findByOrganizationIdIn(organizationIds);
					organizationUserPermissionDao.deleteInBatch(permissions);
					organizationDao.deleteInBatch(organizations);
				}
				
				//删除公司
				List<CompanyUserPermissionPO> permissions = companyUserPermissionDao.findByCompanyId(company.getId());
				companyUserPermissionDao.deleteInBatch(permissions);
				companyDao.delete(company);
			}else{
				List<OrganizationUserPermissionPO> permissions0 = organizationUserPermissionDao.findByUserIdIn(new ArrayListWrapper<String>().add(user.getId().toString()).getList());
				if(permissions0!=null && permissions0.size()>0){
					organizationUserPermissionDao.deleteInBatch(permissions0);
				}
				List<CompanyUserPermissionPO> permissions1 = companyUserPermissionDao.findByUserId(user.getId().toString());
				if(permissions1!=null && permissions1.size()>0){
					companyUserPermissionDao.deleteInBatch(permissions1);
				}
			}
			
		}
		
		userDao.delete(user);
		
		List<UserSystemRolePermissionPO> permissions = userSystemRolePermissionDao.findByUserId(id);
		
		userSystemRolePermissionDao.deleteInBatch(permissions);
		
	}
	
	/**
	 * 修改一个用户<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月19日 下午4:51:17
	 * @param Long id 用户id
	 * @param String nickname 用户昵称
	 * @param String mobile 用户手机号
	 * @param String mail 用户邮箱
	 * @param boolean editPassword 是否修改密码
	 * @param String oldPassword 旧密码
	 * @param String newPassword 新密码
	 * @param String repeat 新密码确认
	 * @return UserVO 修改后的用户
	 */
	public UserVO edit(
			Long id, 
			String nickname,
            String mobile,
            String mail,
            String tags,
            boolean editPassword,
            String oldPassword,
            String newPassword,
            String repeat) throws Exception{
		
		UserPO user = userDao.findOne(id);
		
		if(user == null) throw new UserNotExistException(id);
		
		if(editPassword){
			oldPassword = sha256Encoder.encode(oldPassword);
			if(!user.getPassword().equals(oldPassword)) throw new PasswordErrorException();
			
			if(newPassword == null) throw new PasswordCannotBeNullException();
			
			if(!newPassword.equals(repeat)) throw new RepeatNotMatchPasswordException();
			
			user.setPassword(sha256Encoder.encode(newPassword));
		}
		
		if(mobile != null){
			UserPO userExist = userDao.findByMobileWithExcept(mobile, user.getId());
			if(userExist != null){
				throw new MobileAlreadyExistException(mobile);
			}
		}
		
		if(mail != null){
			UserPO userExist = userDao.findByMailWithExcept(mail, user.getId());
			if(userExist != null){
				throw new MailAlreadyExistException(mail);
			}
		}
		
		user.setNickname(nickname);
		user.setMobile(mobile);
		user.setMail(mail);
		user.setUpdateTime(new Date());
		if(tags != null) user.setTags(tags);
		userDao.save(user);
		
		return new UserVO().set(user);
	}
	
	/**
	 * 修改密码<br/>
	 * <b>作者:</b>ldy<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月16日 上午11:01:23
	 * @param username 用户民
	 * @param mobile 手机号
	 * @param newPassword 新密码
	 * @param repeat 重复新密码
	 * @return UserVO 修改后的用户
	 */
	public UserVO modifyPassword(
			String username,
			String mobile,
            String newPassword,
            String repeat) throws Exception{
		
		UserPO user = null;
		
		if(mobile != null){
			user = userDao.findByMobile(mobile);
			if(user == null){
				throw new MobileNotExistException(mobile);
			}
			
			if(!newPassword.equals(repeat)) throw new RepeatNotMatchPasswordException();
			
			user.setPassword(sha256Encoder.encode(newPassword));
		}
		
		userDao.save(user);
		
		return new UserVO().set(user);
	}
}
