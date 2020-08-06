package com.guolaiwan.app.zizhu.bean;

import cn.hutool.core.date.DateTime;

public class RefundVo {
	//返回码	SUCCESS/FAIL
	String code;
	//返回码说明
	String msg;
	//商户号
	String mchid;
	//应用id
	String appid;
	//交易金额	 单位：分
	int amount;
	//交易订单号
	String trans_no;
	//交易状态 TRADING：交易中SUCCESS：交易成功FAIL：交易失败REFUND：已退款CANCEL：已撤销
	String trans_status;
	//交易类型 00：消费，01：撤销，02：退货
	String trans_type;
	//交易时间
	DateTime trans_time;
	//商户订单号
	String trade_no;
	//第三方订单号
	String refer_no;
	//操作类型
	String option_type;
	//原始数据
	String memo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTrans_no() {
		return trans_no;
	}
	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}
	public String getTrans_status() {
		return trans_status;
	}
	public void setTrans_status(String trans_status) {
		this.trans_status = trans_status;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public DateTime getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(DateTime trans_time) {
		this.trans_time = trans_time;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getRefer_no() {
		return refer_no;
	}
	public void setRefer_no(String refer_no) {
		this.refer_no = refer_no;
	}
	public String getOption_type() {
		return option_type;
	}
	public void setOption_type(String option_type) {
		this.option_type = option_type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
