package com.sumavision.tetris.attend;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

/**
 * 考勤记录输出类<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月10日  
 */
public class AttendVo extends AbstractBaseVO<AttendVo,AttendPo>{
	  //添加时间
    String addTime;
    //主键id
    int attendRecId;
    //企业
    int companyId;
    //企业名称
    String companyName;
    //设备
    int deviceId;
    //考勤日期
    String dutyDay;
    //集团
    int groupId;
    //下班状态 0正常 1早退
    int offDutuState;
    //下班打卡时间
    String offDutyTime;
    //上班状态
    int onDutyState;
    //上班打卡时间
    String onDutyTime;
    //员工
    int workerId;
    //员工名称
    String workerName;
    //员工工号
    String workerNo;
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public AttendVo setAddTime(String addTime) {
		this.addTime = addTime;
		return this;
	}
	/**
	 * @return the attendRecId
	 */
	public int getAttendRecId() {
		return attendRecId;
	}
	/**
	 * @param attendRecId the attendRecId to set
	 */
	public AttendVo setAttendRecId(int attendRecId) {
		this.attendRecId = attendRecId;
		return this;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public AttendVo setCompanyId(int companyId) {
		this.companyId = companyId;
		return this;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public AttendVo setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	/**
	 * @return the deviceId
	 */
	public int getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public AttendVo setDeviceId(int deviceId) {
		this.deviceId = deviceId;
		return this;
	}
	/**
	 * @return the dutyDay
	 */
	public String getDutyDay() {
		return dutyDay;
	}
	/**
	 * @param dutyDay the dutyDay to set
	 */
	public AttendVo setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
		return this;
	}
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public AttendVo setGroupId(int groupId) {
		this.groupId = groupId;
		return this;
	}
	/**
	 * @return the offDutuState
	 */
	public int getOffDutuState() {
		return offDutuState;
	}
	/**
	 * @param offDutuState the offDutuState to set
	 */
	public AttendVo setOffDutuState(int offDutuState) {
		this.offDutuState = offDutuState;
		return this;
	}
	/**
	 * @return the offDutyTime
	 */
	public String getOffDutyTime() {
		return offDutyTime;
	}
	/**
	 * @param offDutyTime the offDutyTime to set
	 */
	public AttendVo setOffDutyTime(String offDutyTime) {
		this.offDutyTime = offDutyTime;
		return this;
	}
	/**
	 * @return the onDutyState
	 */
	public int getOnDutyState() {
		return onDutyState;
	}
	/**
	 * @param onDutyState the onDutyState to set
	 */
	public AttendVo setOnDutyState(int onDutyState) {
		this.onDutyState = onDutyState;
		return this;
	}
	/**
	 * @return the onDutyTime
	 */
	public String getOnDutyTime() {
		return onDutyTime;
	}
	/**
	 * @param onDutyTime the onDutyTime to set
	 */
	public AttendVo setOnDutyTime(String onDutyTime) {
		this.onDutyTime = onDutyTime;
		return this;
	}
	/**
	 * @return the workerId
	 */
	public int getWorkerId() {
		return workerId;
	}
	/**
	 * @param workerId the workerId to set
	 */
	public AttendVo setWorkerId(int workerId) {
		this.workerId = workerId;
		return this;
	}
	/**
	 * @return the workerName
	 */
	public String getWorkerName() {
		return workerName;
	}
	/**
	 * @param workerName the workerName to set
	 */
	public AttendVo setWorkerName(String workerName) {
		this.workerName = workerName;
		return this;
	}
	/**
	 * @return the workerNo
	 */
	public String getWorkerNo() {
		return workerNo;
	}
	/**
	 * @param workerNo the workerNo to set
	 */
	public AttendVo setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
		return this;
	}
	/* (non-Javadoc)
	 * @see com.sumavision.tetris.mvc.converter.AbstractBaseVO#set(java.lang.Object)
	 */
	@Override
	public AttendVo set(AttendPo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setAddTime(DateUtil.format(entity.getAddTime(),"yyyy-MM-dd HH:mm:ss"))
			.setAttendRecId(entity.getAttendRecId())
			.setCompanyId(entity.getCompanyId())
			.setCompanyName(entity.getCompanyName())
			.setDeviceId(entity.getDeviceId())
			.setDutyDay(DateUtil.format(entity.getDutyDay(),"yyyy-MM-dd HH:mm:ss"))
			.setGroupId(entity.getGroupId())
			.setOffDutuState(entity.getOffDutuState())
			.setOffDutyTime(DateUtil.format(entity.getOffDutyTime(),"yyyy-MM-dd HH:mm:ss"))
			.setOnDutyState(entity.getOnDutyState())
			.setOnDutyTime(DateUtil.format(entity.getOnDutyTime(),"yyyy-MM-dd HH:mm:ss"))
			.setWorkerId(entity.getWorkerId())
			.setWorkerName(entity.getWorkerName())
			.setWorkerNo(entity.getWorkerNo());
	return this;
	}
    

}
