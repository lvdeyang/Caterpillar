package com.sumavision.tetris.attend;

import java.util.Date;

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
    Date addTime;
    //主键id
    int attendRecId;
    //企业
    int companyId;
    //企业名称
    String companyName;
    //设备
    int deviceId;
    //考勤日期
    Date dutyDay;
    //集团
    int groupId;
    //下班状态 0正常 1早退
    int offDutuState;
    //下班打卡时间
    Date offDutyTime;
    //上班状态
    int onDutyState;
    //上班打卡时间
    Date onDutyTime;
    //员工
    int workerId;
    //员工名称
    String workerName;
    //员工工号
    String workerNo;
	
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getDutyDay() {
		return dutyDay;
	}
	public void setDutyDay(Date dutyDay) {
		this.dutyDay = dutyDay;
	}
	public Date getOffDutyTime() {
		return offDutyTime;
	}
	public void setOffDutyTime(Date offDutyTime) {
		this.offDutyTime = offDutyTime;
	}
	public Date getOnDutyTime() {
		return onDutyTime;
	}
	public void setOnDutyTime(Date onDutyTime) {
		this.onDutyTime = onDutyTime;
	}
    
	

}
