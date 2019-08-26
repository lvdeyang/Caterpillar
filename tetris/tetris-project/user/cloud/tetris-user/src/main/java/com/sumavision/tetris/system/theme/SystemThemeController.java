package com.sumavision.tetris.system.theme;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.EditorAwareTag;

import com.alibaba.fastjson.JSON;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/system/theme")
public class SystemThemeController {

	@Autowired
	private SystemThemeQuery systemThemeQuery;
	
	@Autowired
	private SystemThemeDAO systemThemeDao;
	
	@Autowired
	private SystemThemeService systemThemeService;
	
	/**
	 * 分页查询系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午1:34:22
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return total long 总数据量
	 * @return rows List<SystemThemeVO> 主题列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/load")
	public Object load(
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		long total = systemThemeDao.count();
		
		List<SystemThemePO> entities = systemThemeQuery.load(currentPage, pageSize);
		
		List<SystemThemeVO> rows = SystemThemeVO.getConverter(SystemThemeVO.class).convert(entities, SystemThemeVO.class);
	
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 分页查询系统主题带例外br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午4:51:44
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @param JSONArray except 例外主题id列表
	 * @return total long 总数据量
	 * @return rows List<SystemThemeVO> 主题列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/load/with/except")
	public Object loadWithExcept(
			int currentPage,
			int pageSize,
			String except,
			HttpServletRequest request) throws Exception{
		
		long total = 0l;
		List<SystemThemePO> entities = null;
			
		if(except == null){
			total = systemThemeDao.count();
			entities = systemThemeQuery.load(currentPage, pageSize);
		}else{
			List<Long> exceptIds = JSON.parseArray(except, Long.class);
			total = systemThemeDao.countByIdNotIn(exceptIds);
			entities = systemThemeQuery.loadWithExcept(currentPage, pageSize, exceptIds);
		}
		
		List<SystemThemeVO> rows = SystemThemeVO.getConverter(SystemThemeVO.class).convert(entities, SystemThemeVO.class);
		
		return new HashMapWrapper<String, Object>().put("total", total)
												   .put("rows", rows)
												   .getMap();
	}
	
	/**
	 * 添加系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午1:56:56
	 * @param String name 主题名称
	 * @param String url css url
	 * @return SystemThemeVO 主题
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name,
			String url,
			HttpServletRequest request) throws Exception{
		
		SystemThemePO entity = systemThemeService.add(name, url);
		
		return new SystemThemeVO().set(entity);
	}
	
	/**
	 * 修改系统主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午2:56:11
	 * @param @PathVariable id 主题id
	 * @param String name 主题名称
	 * @param String url 主题url
	 * @return SystemThemeVO 主题
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name,
			String url,
			HttpServletRequest request) throws Exception{
		
		SystemThemePO entity = systemThemeService.edit(id, name, url);
		
		return new SystemThemeVO().set(entity);
	}
	
	/**
	 * 删除主题<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月4日 下午2:11:05
	 * @param @PathVariable Long id 主题id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		systemThemeService.remove(id);
		
		return null;
	}
	
}
