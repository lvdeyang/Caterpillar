package com.guolaiwan.app.zizhu.bean;

import java.util.List;

public class RefundVoucherPo {
	//签名
	String sign;
	//票单号
	String voucherNumber;
	List<Details> details;
	public class Details{
		//商品ID
		int productId;
		//退票数量
		int refundQuantity;
		//要退的出票码 门票票号，多个以|分隔
		String ticketNos;
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getRefundQuantity() {
			return refundQuantity;
		}
		public void setRefundQuantity(int refundQuantity) {
			this.refundQuantity = refundQuantity;
		}
		public String getTicketNos() {
			return ticketNos;
		}
		public void setTicketNos(String ticketNos) {
			this.ticketNos = ticketNos;
		}
		
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	
}
