package com.sumavision.tetris.system.theme;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SystemThemeQuery {
	
	@Autowired
	private SystemThemeDAO systemThemeDao;

	/**
	 * 分页查询系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午1:32:07
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return List<SystemThemePO> 主题列表
	 */
	public List<SystemThemePO> load(int currentPage, int pageSize) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<SystemThemePO> pagedEntities = systemThemeDao.findAll(page);
		if(pagedEntities == null) return null;
		return pagedEntities.getContent();
	}
	
	/**
	 * 分页查询系统主题（带例外）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午4:58:53
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @oaram Collection<Long> exceptIds 例外主题id
	 * @return List<SystemThemePO> 主题列表
	 */
	public List<SystemThemePO> loadWithExcept(int currentPage, int pageSize, Collection<Long> exceptIds) throws Exception{
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<SystemThemePO> pagedEntities = systemThemeDao.findByIdNotIn(exceptIds, page);
		if(pagedEntities == null) return null;
		return pagedEntities.getContent();
	}
	
}
