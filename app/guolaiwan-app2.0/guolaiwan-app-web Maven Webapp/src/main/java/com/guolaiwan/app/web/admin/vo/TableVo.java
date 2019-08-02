package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.app.web.smartParking.vo.CarPositionVo;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.admin.po.TablePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class TableVo  extends AbstractBaseVO<TableVo, TablePO>{


	private long merchantId; //商家id
	private String tableNo;  //  桌号
	private String tier;  //  层
	private long size; //  房间人数
	private int room; // 是否是包间
	private String Tablename; // 名称
	private long bookprice; //定桌钱数
	private String tableMenu; //菜品
	private String userName; //用户名称
	private String userPhone; //手机号
	private String menuTime; //订桌时间
	private String type; //午晚
	private String  tableState; //桌子状态
	private long   tableStatusId;

	@Override
	public TableVo set(TablePO entity) throws Exception {
		this.setMerchantId(entity.getMerchantId())
		.setTableNo(entity.getTableNo())
		.setId(entity.getId())
		.setSize(entity.getSize())
		.setRoom(entity.getRoom())
		.setTablename(entity.getTablename())
		.setBookprice(entity.getBookprice())
		/*.setTableMenu(entity.getTableMenu())
		.setUserName(entity.getUserName())
		.setUserPhone(entity.getUserPhone())
		.setMenuTime(entity.getMenuTime())
		.setType(entity.getType())*/
		.setTableState(entity.getTableState())
		.setTier(entity.getTier());
		return this;
	}


	public long getTableStatusId() {
		return tableStatusId;
	}


	public void setTableStatusId(long tableStatusId) {
		this.tableStatusId = tableStatusId;
	}


	public long getMerchantId() {
		return merchantId;
	}


	public TableVo setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}


	public String getTableNo() {
		return tableNo;
	}


	public TableVo setTableNo(String tableNo) {
		this.tableNo = tableNo;
		return this;
	}


	public String getTier() {
		return tier;
	}


	public TableVo setTier(String tier) {
		this.tier = tier;
		return this;
	}


	public long getSize() {
		return size;
	}


	public TableVo setSize(long size) {
		this.size = size;
		return this;
	}


	public int getRoom() {
		return room;
	}


	public TableVo setRoom(int room) {
		this.room = room;
		return this;
	}


	public String getTablename() {
		return Tablename;
	}


	public TableVo setTablename(String tablename) {
		Tablename = tablename;
		return this;
	}


	public long getBookprice() {
		return bookprice;
	}


	public TableVo setBookprice(long bookprice) {
		this.bookprice = bookprice;
		return this;
	}


	public String getTableMenu() {
		return tableMenu;
	}


	public TableVo setTableMenu(String tableMenu) {
		this.tableMenu = tableMenu;
		return this;
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


	public String getMenuTime() {
		return menuTime;
	}


	public void setMenuTime(String menuTime) {
		this.menuTime = menuTime;
	}


	public String getType() {
		return type;
	}


	public TableVo setType(String type) {
		this.type = type;
		return this;
	}


	public String getTableState() {
		return tableState;
	}


	public TableVo setTableState(String tableState) {
		this.tableState = tableState;
		return this;
	}
	
	
}
