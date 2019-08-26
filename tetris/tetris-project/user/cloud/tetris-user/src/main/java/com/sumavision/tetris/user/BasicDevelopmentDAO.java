package com.sumavision.tetris.user;

import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = BasicDevelopmentPO.class, idClass = Long.class)
public interface BasicDevelopmentDAO extends BaseDAO<BasicDevelopmentPO>{

	/**
	 * 根据appId查询用户基本配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午1:50:15
	 * @param String appId 开发者id
	 * @return BasicDevelopmentPO 用户基本配置
	 */
	public BasicDevelopmentPO findByAppId(String appId);
	
	/**
	 * 查询用户的开发者基本配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 上午11:08:31
	 * @param Long userId 用户id
	 * @return DevelopmentPO 开发者基本配置
	 */
	public BasicDevelopmentPO findByUserId(Long userId);
	
}
