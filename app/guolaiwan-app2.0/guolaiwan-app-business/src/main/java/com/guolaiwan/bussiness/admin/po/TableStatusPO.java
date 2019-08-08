package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.enumeration.TableStatus;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_tableStatus")
public class TableStatusPO extends AbstractBasePO {
	/*
	 * 桌子中间表
	 */
	private static final long serialVersionUID = 5013010096704514202L;

	private long tableId; //桌号Id
	private long merchantId;   //商户id
	private String tableDate; //时间
	private BookType type;  
	private long  userId; //用户id
	private String tableMenu; //菜品
  	private String	tableState; // //  NOTPAY 未支付        PAYSUCCESS 已预订          PAST已过期     REFUNDING 申请退款             REFUNDED 退款完成     REFUNDFAIL 退款失败
  	private String userName; //用户名称
	private String userPhone; // 用户手机号
	private String oderNo; // 订单号
	private String ydNO; //订单二维码
	
	
	
	public String getYdNO() {
		return ydNO;
	}


	public void setYdNO(String ydNO) {
		this.ydNO = ydNO;
	}


	public String getOderNo() {
		return oderNo;
	}


	public void setOderNo(String oderNo) {
		this.oderNo = oderNo;
	}


	public long getMerchantId() {
		return merchantId;
	}
	
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
 

	public long getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getTableState() {
		return tableState;
	}
	public void setTableState(String tableState) {
		this.tableState = tableState;
	}
	public String getTableMenu() {
		return tableMenu;
	}
	public void setTableMenu(String tableMenu) {
		this.tableMenu = tableMenu;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	
	


	public String getTableDate() {
		return tableDate;
	}


	public void setTableDate(String tableDate) {
		this.tableDate = tableDate;
	}


	


	@Enumerated(EnumType.STRING)
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		this.type = type;
	}
	
	
}
