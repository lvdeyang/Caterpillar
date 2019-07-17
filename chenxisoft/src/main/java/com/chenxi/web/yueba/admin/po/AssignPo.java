package com.chenxi.web.yueba.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "assign")
public class AssignPo extends AbstractBasePO {
    //上班写起来太尴尬了，所以用拼音
	private long orderId;
	private String rufang;
	private String elu;
	private String ruhan;
	
	private String qidai;
	private String tunbu;
	private String dabancishu;
	private String xiaobiancishu;
	
	private int rufanghuli;
	private int muyucashen;
	private int chanhoucao;
	
	private int yingerxizao;
	private int yingertiwen;
	private int yingerfuchu;
	private int qidaichuli;
	private int bianhouxitun;
	private int xiaodunaiju;
	private int xiezhuweiyang;
	
	private int userConfirm;
	public String getRufang() {
		return rufang;
	}
	public void setRufang(String rufang) {
		this.rufang = rufang;
	}
	public String getElu() {
		return elu;
	}
	public void setElu(String elu) {
		this.elu = elu;
	}
	public String getRuhan() {
		return ruhan;
	}
	public void setRuhan(String ruhan) {
		this.ruhan = ruhan;
	}
	public String getQidai() {
		return qidai;
	}
	public void setQidai(String qidai) {
		this.qidai = qidai;
	}
	public String getTunbu() {
		return tunbu;
	}
	public void setTunbu(String tunbu) {
		this.tunbu = tunbu;
	}
	public String getDabancishu() {
		return dabancishu;
	}
	public void setDabancishu(String dabancishu) {
		this.dabancishu = dabancishu;
	}
	public String getXiaobiancishu() {
		return xiaobiancishu;
	}
	public void setXiaobiancishu(String xiaobiancishu) {
		this.xiaobiancishu = xiaobiancishu;
	}
	public int getRufanghuli() {
		return rufanghuli;
	}
	public void setRufanghuli(int rufanghuli) {
		this.rufanghuli = rufanghuli;
	}
	public int getMuyucashen() {
		return muyucashen;
	}
	public void setMuyucashen(int muyucashen) {
		this.muyucashen = muyucashen;
	}
	public int getChanhoucao() {
		return chanhoucao;
	}
	public void setChanhoucao(int chanhoucao) {
		this.chanhoucao = chanhoucao;
	}
	public int getYingerxizao() {
		return yingerxizao;
	}
	public void setYingerxizao(int yingerxizao) {
		this.yingerxizao = yingerxizao;
	}
	public int getYingertiwen() {
		return yingertiwen;
	}
	public void setYingertiwen(int yingertiwen) {
		this.yingertiwen = yingertiwen;
	}
	public int getYingerfuchu() {
		return yingerfuchu;
	}
	public void setYingerfuchu(int yingerfuchu) {
		this.yingerfuchu = yingerfuchu;
	}
	public int getQidaichuli() {
		return qidaichuli;
	}
	public void setQidaichuli(int qidaichuli) {
		this.qidaichuli = qidaichuli;
	}
	public int getBianhouxitun() {
		return bianhouxitun;
	}
	public void setBianhouxitun(int bianhouxitun) {
		this.bianhouxitun = bianhouxitun;
	}
	public int getXiaodunaiju() {
		return xiaodunaiju;
	}
	public void setXiaodunaiju(int xiaodunaiju) {
		this.xiaodunaiju = xiaodunaiju;
	}
	public int getXiezhuweiyang() {
		return xiezhuweiyang;
	}
	public void setXiezhuweiyang(int xiezhuweiyang) {
		this.xiezhuweiyang = xiezhuweiyang;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getUserConfirm() {
		return userConfirm;
	}
	public void setUserConfirm(int userConfirm) {
		this.userConfirm = userConfirm;
	}
	
	
	
	
}
