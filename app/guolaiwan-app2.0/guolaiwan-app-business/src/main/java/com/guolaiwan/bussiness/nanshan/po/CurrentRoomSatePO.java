package com.guolaiwan.bussiness.nanshan.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_currentRoomSate")
public class CurrentRoomSatePO extends AbstractBasePO {
   //房间ID	
   private Long roomId; 
   //房间状态   状态0为空  1为已售
   private String roomState;
   //住店时间
   private String inRoomDate;
   //房间上下架      (0为 下架  1为 上架)   
   private String currentRoomState;
   
   
   
   public String getCurrentRoomState() {
	return currentRoomState;
}
public void setCurrentRoomState(String currentRoomState) {
	this.currentRoomState = currentRoomState;
}
public Long getRoomId() {
	return roomId;
}
public void setRoomId(Long roomId) {
	this.roomId = roomId;
}
public String getRoomState() {
	return roomState;
}
public void setRoomState(String roomState) {
	this.roomState = roomState;
}
public String getInRoomDate() {
	return inRoomDate;
}
public void setInRoomDate(String inRoomDate) {
	this.inRoomDate = inRoomDate;
}
public String getOutRoomDate() {
	return outRoomDate;
}
public void setOutRoomDate(String outRoomDate) {
	this.outRoomDate = outRoomDate;
}
//离店时间
   private String outRoomDate;
   
}
