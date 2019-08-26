package com.sumavision.tetris.init;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sumavision.tetris.commons.context.SystemInitialization;
import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.encoder.MessageEncoder.Sha256Encoder;
import com.sumavision.tetris.system.role.SystemRoleDAO;
import com.sumavision.tetris.system.role.SystemRoleGroupDAO;
import com.sumavision.tetris.system.role.SystemRoleGroupPO;
import com.sumavision.tetris.system.role.SystemRolePO;
import com.sumavision.tetris.system.role.SystemRoleType;
import com.sumavision.tetris.system.role.UserSystemRolePermissionDAO;
import com.sumavision.tetris.system.role.UserSystemRolePermissionPO;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserInitialization implements SystemInitialization{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserInitialization.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private SystemRoleDAO systemRoleDao;
	
	@Autowired
	private SystemRoleGroupDAO systemRoleGroupDao;
	
	@Autowired
	private UserSystemRolePermissionDAO userSystemRolePermissionDao;
	
	@Autowired
	private Sha256Encoder sha256Encoder;
	
	@Override
	public int index() {
		return 0;
	}

	@Override
	public void init() {
		Date now = new Date();
		UserPO internalAdmin = userDao.findByUsernameAndAutoGeneration("admin", true);
		if(internalAdmin == null){
			String password = "sumavisionrd";
			try{
				password = sha256Encoder.encode(password);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error(DateUtil.now());
				LOG.error("初始化用户时密码编码失败！");
				System.exit(0);
			}
			internalAdmin = new UserPO();
			internalAdmin.setUsername("admin");
			internalAdmin.setNickname("系统管理员");
			internalAdmin.setPassword(password);
			internalAdmin.setClassify(UserClassify.INTERNAL);
			internalAdmin.setAutoGeneration(true);
			internalAdmin.setUpdateTime(now);
			userDao.save(internalAdmin);
			
			SystemRoleGroupPO internalRoleGroup = systemRoleGroupDao.findByAutoGeneration(true);
			if(internalRoleGroup == null){
				internalRoleGroup = new SystemRoleGroupPO();
				internalRoleGroup.setAutoGeneration(true);
				internalRoleGroup.setName("系统运维");
				internalRoleGroup.setUpdateTime(now);
				systemRoleGroupDao.save(internalRoleGroup);
			}
			
			SystemRolePO internalRole = systemRoleDao.findByAutoGeneration(true);
			if(internalRole == null){
				internalRole = new SystemRolePO();
				internalRole.setName("管理员");
				internalRole.setAutoGeneration(true);
				internalRole.setType(SystemRoleType.SYSTEM);
				internalRole.setUpdateTime(now);
				systemRoleDao.save(internalRole);
			}
			
			if(!internalRoleGroup.getId().equals(internalRole.getSystemRoleGroupId())){
				internalRole.setSystemRoleGroupId(internalRoleGroup.getId());
				systemRoleDao.save(internalRole);
			}
			
			UserSystemRolePermissionPO permission = userSystemRolePermissionDao.findByUserIdAndRoleIdAndAutoGeneration(internalAdmin.getId(), internalRole.getId(), true);
			if(permission == null){
				permission = new UserSystemRolePermissionPO();
				permission.setUserId(internalAdmin.getId());
				permission.setRoleId(internalRole.getId());
				permission.setRoleType(internalRole.getType());
				permission.setAutoGeneration(true);
				permission.setUpdateTime(now);
				userSystemRolePermissionDao.save(permission);
			}
		}
	}

}
