package com.sumavision.tetris.punch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumavision.tetris.attend.AttendDao;
import com.sumavision.tetris.attend.AttendPo;
import com.sumavision.tetris.attend.AttendQuery;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

/**
 * 考勤服务<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年1月3日  
 */
@Service
public class PunchServiceImpl implements PunchService{
	@Autowired
	AttendQuery attendQuery;
	@Autowired
	AttendDao attendDao;
	@Autowired
	PicDao picDao;
	@Autowired 
	UserQuery userQuery;

	/**
	 * 创建打卡工单
	 */
	@Override
	public AttendPo createOrder(String token) {
		// TODO Auto-generated method stub
		try {
			UserVO userVO=userQuery.findByToken(token);
			if (userVO!=null) {
//				userVO.setCode(1+"");
				AttendPo getPo=attendQuery.selectTodayOrderById(userVO.getCode());
				if (getPo!=null) {
					return getPo;
				}else{
					AttendPo sendPo=new AttendPo();
					sendPo.setCode(userVO.getCode());
					sendPo.setAddTime(new Date());
					sendPo.setDutyDay(new Date());
					sendPo.setType(0);
					List<AttendPo> pos=new ArrayList<AttendPo>();
					pos.add(sendPo);
					List<AttendPo> saveAttendPos=attendDao.save(pos);
					if (saveAttendPos!=null&&saveAttendPos.size()>0) {
						return saveAttendPos.get(0);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上班
	 */

	@Override
	public Long start_Order(Long id, int onDutyState) {
		AttendPo sendPo=attendDao.findByid(id);
		if (sendPo!=null) {
			sendPo.setOnDutyState(onDutyState);
			sendPo.setOnDutyTime(new Date());
			sendPo.setType(1);
			List<AttendPo> pos=new ArrayList<AttendPo>();
			pos.add(sendPo);
			List<AttendPo> saveAttendPos=attendDao.save(pos);
			if (saveAttendPos!=null&&saveAttendPos.size()>0) {
				return saveAttendPos.get(0).getId();
			}else{
				return 0L;
			}
		}else{
			return 0L;
		}
		
	}


	/**
	 * 下班
	 */
	@Override
	public Long end_Order(Long id, int offDutyState) {
		AttendPo sendPo=attendDao.findByid(id);
		if (sendPo!=null) {
			sendPo.setOffDutuState(offDutyState);
			sendPo.setOffDutyTime(new Date());
			sendPo.setType(1);
			List<AttendPo> pos=new ArrayList<AttendPo>();
			pos.add(sendPo);
			List<AttendPo> saveAttendPos=attendDao.save(pos);
			if (saveAttendPos!=null&&saveAttendPos.size()>0) {
				return saveAttendPos.get(0).getId();
			}else{
				return 0L;
			}
		}else{
			return 0L;
		}
		
	}
	
	/**
	 * 外勤
	 */
	@Override
	public Long out_Order(Long id, String title, String content, String address, String Strpics) {
		AttendPo sendPo=attendDao.findByid(id);
		if (sendPo!=null) {
			sendPo.setTitle(title);
			sendPo.setContent(content);
			sendPo.setAddress(address);
			sendPo.setOnDutyState(1);
			sendPo.setOnDutyTime(new Date());
			sendPo.setType(2);
			List<PicPo> picList=new ArrayList<PicPo>();
			String[] picPos=Strpics.split(",");
			if (picPos!=null&&picPos.length>0) {
				for (int i = 0; i < picPos.length; i++) {
					PicPo pic=new PicPo();
					pic.setAttendId(id);
					pic.setPicture(picPos[i]);
					picList.add(pic);
				}
				List<PicPo> pics=picDao.save(picList);
				if (pics!=null&&pics.size()>0) {
					List<AttendPo> pos=new ArrayList<AttendPo>();
					pos.add(sendPo);
					List<AttendPo> saveAttendPos=attendDao.save(pos);
					if (saveAttendPos!=null&&saveAttendPos.size()>0) {
						return saveAttendPos.get(0).getId();
					}else{
						return 0L;
					}
				}else{
					return 0L;
				}
			}else{
				return 0L;
			}
		}else{
			return 0L;
		}
	
	}

	/**
	 * 根据ID查询本月工单
	 */
	@Override
	public List<AttendPo> get_monthOrder(int year,int month,String token) {
		UserVO userVO;
		try {
			userVO = userQuery.findByToken(token);
			if (userVO!=null) {
//				userVO.setCode(1+"");
				String code=userVO.getCode();
				List<AttendPo> pos=attendDao.findMonthAttendbyCode(year,month,code);
				if (pos!=null&&pos.size()>0) {
					return pos;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
