package com.chenxi.web.stock.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "stock_finance")
public class FinancePo extends AbstractBasePO {
	private long stockId;
	private int year;
	//营业收入
	private double allget;
	//营业利润
	private double fleft;
	//纯利润
	private double realLeft;
	//净资产收益率
	private double returnassets;
    //营业现金流
	private double realcash;
	//投资现金流
	private double postcash;
	//融资现金流
	private double getcash;
	public double getAllget() {
		return allget;
	}
	public void setAllget(double allget) {
		this.allget = allget;
	}
	
	public double getFleft() {
		return fleft;
	}
	public void setFleft(double fleft) {
		this.fleft = fleft;
	}
	public double getRealLeft() {
		return realLeft;
	}
	public void setRealLeft(double realLeft) {
		this.realLeft = realLeft;
	}
	public double getReturnassets() {
		return returnassets;
	}
	public void setReturnassets(double returnassets) {
		this.returnassets = returnassets;
	}
	public double getRealcash() {
		return realcash;
	}
	public void setRealcash(double realcash) {
		this.realcash = realcash;
	}
	public double getPostcash() {
		return postcash;
	}
	public void setPostcash(double postcash) {
		this.postcash = postcash;
	}
	public double getGetcash() {
		return getcash;
	}
	public void setGetcash(double getcash) {
		this.getcash = getcash;
	}
	public long getStockId() {
		return stockId;
	}
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
