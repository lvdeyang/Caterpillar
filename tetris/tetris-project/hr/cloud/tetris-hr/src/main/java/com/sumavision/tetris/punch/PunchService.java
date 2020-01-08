package com.sumavision.tetris.punch;

import java.util.List;

import com.sumavision.tetris.attend.AttendPo;

/**
 * 内容说明<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年1月3日  
 */
public interface PunchService {

	/**
		 * 创建考勤工单<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param token
	 * @return 
		 */
	AttendPo createOrder(String token);

	/**
		 * 上班<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param attendId
		 * @param onDutyState
		 * @return
		 */
	Long start_Order(Long attendId, int onDutyState);

	/**
		 * 下班<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param attendId
		 * @param offDutyState
		 * @return
		 */
	Long end_Order(Long attendId, int offDutyState);

	/**
		 * 外勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param attendId
		 * @param title
		 * @param content
		 * @param address
		 * @param pics
		 * @return
		 */
	Long out_Order(Long attendId, String title, String content, String address,String pics);

	/**
		 * 功能说明<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param code
		 * @return
		 */
	List<AttendPo> get_monthOrder(int year,int month,String code);

}
