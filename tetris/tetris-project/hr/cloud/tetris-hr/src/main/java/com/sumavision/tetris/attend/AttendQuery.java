package com.sumavision.tetris.attend;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.device.DevicePO;


@Component
public class AttendQuery {
	@Autowired
	private AttendDao  dao;
	

	/**
		 * 查询全部考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月10日  
		 * @return
		 */
	
	public List<AttendPo> getAllAttend() throws Exception {
		// TODO Auto-generated method stub
		List<AttendPo> attends =dao.findAll();
		if (attends!=null&&attends.size()>0) {
			return attends;
		}
		return null;
	}
	
	/**
	 * 查询分页考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b> 2019年12月10日  
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws Exception 
	 */
	public List<AttendPo> getAttendByPage(int pageSize, int pageIndex) throws Exception {
		// TODO Auto-generated method stub
		int pageStart=(pageIndex-1)*pageSize;
		List<AttendPo> attends =dao.findBypage(pageStart, pageSize);
		if (attends!=null&&attends.size()>0) {
			return attends;
		}
		return null;
	}
	
	/**
	 * 根据员工信息查询考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b> 2019年12月10日  
	 * @param workerId
	 * @param workerId2
	 * @return
	 * @throws Exception 
	 */
	public List<AttendPo> getAttendbyWorker(int workerId, int deviceId) throws Exception{
		// TODO Auto-generated method stub
		List<AttendPo> attends =dao.findByWorker(workerId, deviceId);
		if (attends!=null&&attends.size()>0) {
			return attends;
		}
		return null;
	}

	/**
		 * 功能说明<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月11日  
		 * @param integer
		 * @return
		 */
	public Long getAttendById(Integer attendRecId) {
		// TODO Auto-generated method stub
		AttendPo attend =dao.findByAttendRecId(attendRecId);
		if (attend!=null) {
			return attend.getId();
		}
		return null;
	}
	
	
	public Page<AttendPo> findAll(int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<AttendPo> attends = dao.findAll(page);
		return attends;
	}

	/**
		 * 根据ID查询当日考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2020年1月3日  
		 * @param code
		 * @return
		 */
	public AttendPo selectTodayOrderById(String code) {
		// TODO Auto-generated method stub
		AttendPo attend = dao.findTodayOrderById(code);
		return attend;
	}


}
