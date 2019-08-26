package com.sumavision.tetris.user;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开发者基本配置增删改操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年4月22日 上午9:47:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicDevelopmentService {
	
	@Autowired
	private BasicDevelopmentDAO basicDevelopmentDao;
	
	/**
	 * 添加用户基础配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 上午11:15:43
	 * @param UserVO user 当前用户
	 * @return BasicDevelopmentPO 用户基础配置
	 */
	public BasicDevelopmentPO add(UserVO user) throws Exception{
		BasicDevelopmentPO basicDevelopment = new BasicDevelopmentPO();
		basicDevelopment.setAppId(UUID.randomUUID().toString().replaceAll("-", "0"));
		basicDevelopment.setUpdateTime(new Date());
		basicDevelopment.setUserId(Long.valueOf(user.getUuid()));
		basicDevelopment.setAppSecret("");
		basicDevelopmentDao.save(basicDevelopment);
		return basicDevelopment;
	}
	
	/**
	 * 修改开发者密码（AppSecret）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午1:11:28
	 * @param UserVO user 用户
	 * @param String appSecret 开发者密码
	 * @return BasicDevelopmentPO 用户基础配置
	 */
	public BasicDevelopmentPO update(UserVO user, String appSecret) throws Exception{
		BasicDevelopmentPO basicDevelopment = basicDevelopmentDao.findByUserId(Long.valueOf(user.getUuid()));
		basicDevelopment.setAppSecret(appSecret);
		basicDevelopment.setUpdateTime(new Date());
		basicDevelopmentDao.save(basicDevelopment);
		return basicDevelopment;
	}
	
}
