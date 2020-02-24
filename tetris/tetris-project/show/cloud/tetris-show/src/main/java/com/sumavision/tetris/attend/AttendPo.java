package com.sumavision.tetris.attend;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 考勤数据<br/>
 * <p>详细描述</p>
 * <b>作者:</b>sjj<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年12月10日 
 */

@Entity
@Table(name="TETRIS_ATTEND")
public class AttendPo extends AbstractBasePO{
	private static final long serialVersionUID = 1L;
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
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
	public void setAttendRecId(int attendRecId) {
		this.attendRecId = attendRecId;
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
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
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
	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
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
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	public void setOffDutuState(int offDutuState) {
		this.offDutuState = offDutuState;
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
	public void setOffDutyTime(String offDutyTime) {
		this.offDutyTime = offDutyTime;
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
	public void setOnDutyState(int onDutyState) {
		this.onDutyState = onDutyState;
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
	public void setOnDutyTime(String onDutyTime) {
		this.onDutyTime = onDutyTime;
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
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
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
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
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
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
    

}
