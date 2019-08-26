package com.sumavision.tetris.commons.context;

import java.util.Comparator;

/**
 * 系统初始化接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月15日 下午12:52:51
 */
public interface SystemInitialization {

	/**
	 * 获取接口执行顺序<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月15日 下午12:52:48
	 * @return int 执行顺序
	 */
	public int index();
	
	/**
	 * 初始化方法<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月15日 下午12:53:38
	 */
	public void init();
	
	/**
	 * 接口排序器（升序）<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月15日 下午12:53:51
	 */
	public class HandlerComparator implements Comparator<SystemInitialization>{

		@Override
		public int compare(SystemInitialization o1, SystemInitialization o2) {
			return o1.index() - o2.index();
		}
		
	}
}
