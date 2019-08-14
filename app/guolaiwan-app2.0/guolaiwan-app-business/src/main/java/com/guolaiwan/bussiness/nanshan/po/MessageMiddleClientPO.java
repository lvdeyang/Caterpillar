package com.guolaiwan.bussiness.nanshan.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_Message_Client")
public class MessageMiddleClientPO extends AbstractBasePO{
	
	//用户Message id
		private Long messageId;
		//订单id
		private Long orderId;
		//商家id
		private Long merchantId;
		//UserId
		private Long userId;
		//产品Id
		private Long proId;
		
		//房间Id
		private Long roomId;
		
		//开始日期(房间)
		private String startDate;
		
		//结束时间
		private String endDate;
		
		public Long getRoomId() {
			return roomId;
		}
		public void setRoomId(Long roomId) {
			this.roomId = roomId;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		//支付状态 0代表未支付，1代表已支付，2取消支付
		private String payState;
		
		public Long getMessageId() {
			return messageId;
		}
		public void setMessageId(Long messageId) {
			this.messageId = messageId;
		}
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public Long getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(Long merchantId) {
			this.merchantId = merchantId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getProId() {
			return proId;
		}
		public void setProId(Long proId) {
			this.proId = proId;
		}
		public String getPayState() {
			return payState;
		}
		public void setPayState(String payState) {
			this.payState = payState;
		}
		

}
