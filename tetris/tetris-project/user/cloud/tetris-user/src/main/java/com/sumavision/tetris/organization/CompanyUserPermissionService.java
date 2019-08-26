package com.sumavision.tetris.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sumavision.tetris.user.UserPO;

/**
 * 公司用户权限操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午10:07:16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyUserPermissionService {

	@Autowired
	private CompanyUserPermissionDAO companyUserPermissionDao;
	
	/**
	 * 添加用户公司关联<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 上午10:07:47
	 * @param CompanyPO company 公司
	 * @param UserPO user 用户
	 */
	public void add(CompanyPO company, UserPO user) throws Exception{
		CompanyUserPermissionPO permission = new CompanyUserPermissionPO();
		permission.setUserId(user.getId().toString());
		permission.setCompanyId(company.getId());
		companyUserPermissionDao.save(permission);
	}
	
}
