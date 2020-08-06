package com.guolaiwan.app.zizhu.bean;

import cn.hutool.core.date.DateTime;

public class PayCodeVo {
	//返回码 SUCCESS/FAIL
	String code;
	//返回码说明
	String msg;
	//商户号
	String mchid;
	//应用id
	String appid;
	//二维码串地址
	String qrcode_url;
//	/二维码图片数据
	String qrcode_data;
	//交易状态 TRADING：交易中 SUCCESS：交易成功 FAIL：交易失败	REFUND：已退款 CANCEL：已撤销
	String trans_status;
	//交易时间 YYYY-MM-DD HH:mm:ss
	DateTime trans_time;
	//原始数据	各交易接口返回给后台的数据
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
	public String getQrcode_url() {
		return qrcode_url;
	}
	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
	public String getQrcode_data() {
		return qrcode_data;
	}
	public void setQrcode_data(String qrcode_data) {
		this.qrcode_data = qrcode_data;
	}
	public String getTrans_status() {
		return trans_status;
	}
	public void setTrans_status(String trans_status) {
		this.trans_status = trans_status;
	}
	public DateTime getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(DateTime trans_time) {
		this.trans_time = trans_time;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
