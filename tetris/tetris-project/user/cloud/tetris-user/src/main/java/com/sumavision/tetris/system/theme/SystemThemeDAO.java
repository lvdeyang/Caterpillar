package com.sumavision.tetris.system.theme;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = SystemThemePO.class, idClass = Long.class)
public interface SystemThemeDAO extends BaseDAO<SystemThemePO>{

	/**
	 * 分页查询系统主题带例外<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午4:55:52
	 * @param Collection<Long> exceptIds 例外主题id
	 * @param Pageable page 分页信息
	 * @return Page<SystemThemePO> 主题列表
	 */
	public Page<SystemThemePO> findByIdNotIn(Collection<Long> exceptIds, Pageable page);
	
	/**
	 * 统计系统主题数量（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午4:57:49
	 * @param Collection<Long> exceptIds 例外主题id
	 * @return int 主题数量
	 */
	public int countByIdNotIn(Collection<Long> exceptIds);
	
	/**
	 * 根据url查询主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月8日 下午1:19:49
	 * @param String url 主题链接
	 * @return SystemThemePO 主题
	 */
	public SystemThemePO findByUrl(String url);
	
}
