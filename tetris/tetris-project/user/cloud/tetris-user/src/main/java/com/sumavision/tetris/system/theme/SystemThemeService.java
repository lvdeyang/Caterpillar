package com.sumavision.tetris.system.theme;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.system.theme.exception.SystemThemeNotFoundException;

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemThemeService {

	@Autowired
	private SystemThemeDAO systemThemeDao;
	
	/**
	 * 添加系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午2:00:08
	 * @param String name 主题名称
	 * @param String url 主题url
	 * @return SystemThemePO 系统主题
	 */
	public SystemThemePO add(
			String name,
			String url) throws Exception{
		
		SystemThemePO theme = new SystemThemePO();
		theme.setName(name);
		theme.setUrl(url);
		theme.setUpdateTime(new Date());
		systemThemeDao.save(theme);
		
		return theme;
	}
	
	/**
	 * 修改系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午2:06:12
	 * @param Long id 主题id
	 * @param String name 主题名称
	 * @param String url 主题url
	 * @return SystemThemePO 系统主题
	 */
	public SystemThemePO edit(
			Long id,
			String name,
			String url) throws Exception{
		
		SystemThemePO theme = systemThemeDao.findOne(id);
		if(theme == null){
			throw new SystemThemeNotFoundException(id);
		}
		
		theme.setName(name);
		theme.setUrl(url);
		theme.setUpdateTime(new Date());
		systemThemeDao.save(theme);
		
		return theme;
	}
	
	/**
	 * 删除系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午2:07:52
	 * @param Long id 主题id
	 */
	public void remove(Long id) throws Exception{
		SystemThemePO theme = systemThemeDao.findOne(id);
		if(theme == null) return;
		systemThemeDao.delete(theme);
	}
	
}
