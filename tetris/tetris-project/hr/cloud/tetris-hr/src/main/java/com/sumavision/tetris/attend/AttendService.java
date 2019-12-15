package com.sumavision.tetris.attend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.org.OrgDAO;
import com.sumavision.tetris.org.OrgQuery;
import com.sumavision.tetris.org.OrgUserDAO;
import com.sumavision.tetris.user.UserQuery;

/**
 * 考勤记录获取<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月10日  
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttendService {

	@Autowired
	private AttendQuery query;
	
	/**
	 * 查询全部考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b> 2019年12月10日  
	 * @return
	 */

public List<AttendVo> getAllAttend() throws Exception {
	// TODO Auto-generated method stub
	List<AttendPo> attends =query.getAllAttend();
	List<AttendVo> rootAttends = generateRootattends(attends);
	return rootAttends;
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
	public List<AttendVo> getAttendByPage(int pageSize, int pageIndex) throws Exception {
		// TODO Auto-generated method stub
		int pageStart=(pageIndex-1)*pageSize;
		List<AttendPo> attends =query.getAttendByPage(pageStart, pageSize);
		List<AttendVo> rootAttends = generateRootattends(attends);
		return rootAttends;
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
	public List<AttendVo> getAttendbyWorker(int workerId, int deviceId) throws Exception{
		// TODO Auto-generated method stub
		List<AttendPo> attends =query.getAttendbyWorker(workerId, deviceId);
		List<AttendVo> rootAttends = generateRootattends(attends);
		return rootAttends;
	}
	
	
	/**
	 * VO--PO 转换<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b> 2019年12月10日  
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	private List<AttendVo> generateRootattends(Collection<AttendPo> attends) throws Exception {
		if (attends == null || attends.size() <= 0)
			return null;
		List<AttendVo> rootAttends = new ArrayList<AttendVo>();
		for (AttendPo attend : attends) {
			if (attend.getAddTime()!= null) {
				rootAttends.add(new AttendVo().set(attend));
			}
		}
		
		return rootAttends;
	}
}
