package com.guolaiwan.app.web.merchant.car.vo;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;
import com.guolaiwan.bussiness.merchant.car.po.SitePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class RouteVO extends AbstractBaseVO<RouteVO, RoutePO>{
	//路线名称
	private String name;
	//路线
	private String lab;
	//始发地
	private String origin;
	//终点
	private String end;
	//发车时间
	private String startTime;
	//价格
	private String price;
	//站点
	private String sites; 



	public String getName() {
		return name;
	}

	public RouteVO setName(String name) {
		this.name = name;
		return this;
	}


	public String getLab() {
		return lab;
	}

	public RouteVO setLab(String lab) {
		this.lab = lab;
		return this;
	}

	public String getOrigin() {
		return origin;
	}

	public RouteVO setOrigin(String origin) {
		this.origin = origin;
		return this;
	}

	public String getEnd() {
		return end;
	}

	public RouteVO setEnd(String end) {
		this.end = end;
		return this;
	}

	public String getStartTime() {
		return startTime;
	}

	public RouteVO setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public String getPrice() {
		return price;
	}

	public RouteVO setPrice(String price) {
		this.price = price;
		return this;
	}

	public String getSites() {
		return sites;
	}

	public RouteVO setSites(String sites) {
		this.sites = sites;
		return this;

	}



	@SuppressWarnings("null")
	public RouteVO set(RoutePO entity)throws Exception {
		DecimalFormat df = new DecimalFormat("0.00"); 


		//站点
		String sitesStr="";
		List<SitePO> sites = entity.getSites();
		for (int i = 0; i < sites.size(); i++) {
			if(i==sites.size()-1){
				sitesStr = sitesStr+sites.get(i).getName();	
			}else{
				sitesStr = sitesStr+sites.get(i).getName()+"-";	
			}
		}
		if(sitesStr.equals("")){
			sitesStr="--";
		}

		//价格
		String startTimeStr;
		String priceStr ="";
		List<FlightPO> flights = entity.getFlights();
		if(flights.size()==0){
			priceStr = "--";
			startTimeStr="--";
		}else{
			List<FlightPO> fs1 = new ArrayList<FlightPO>();//固定班次
			List<FlightPO> fs2 = new ArrayList<FlightPO>();//包车
			for (int i = 0; i < flights.size(); i++) {
				if(flights.get(i).getFlightType().equals(FlightType.CARTIME)){
					fs1.add(flights.get(i));
				}else{
					fs2.add(flights.get(i));
				}
			}
			//固定路线
			if(fs1.size()==0){
				startTimeStr="--";
			}else{
				long maxcp =fs1.get(0).getPrice();
				long mincp =fs1.get(0).getPrice();
				for (int i = 0; i < fs1.size(); i++) {
					if(maxcp<fs1.get(i).getPrice()){
						maxcp = fs1.get(i).getPrice();
					};
					if(mincp>fs1.get(i).getPrice()){
						mincp = fs1.get(i).getPrice();
					};
				}
				if(mincp==maxcp){
					priceStr =priceStr+ "固定班次："+df.format((double)(maxcp)/100)+"	";
				}else{
					priceStr =priceStr+ "固定班次："+df.format((double)(mincp)/100)+"-"+df.format((double)maxcp/100)+"	";
				}
				long max =fs1.get(0).getGoTime().getTime();
				long min =max;
				for (int j = 0; j < fs1.size(); j++) {
					Time cartime1= fs1.get(j).getGoTime();
					if(max<cartime1.getTime()){
						max = cartime1.getTime();
					};
					if(min>cartime1.getTime()){
						min = cartime1.getTime();
					};
				}
				Time maxTime = new Time(max);
				Time minTime = new Time(min);
				if(min==max){
					startTimeStr =maxTime.toString() ;
				}else{
					startTimeStr =minTime.toString() +"-"+maxTime.toString();
				}
			}
			//包车
			if(fs2.size()!=0){
				long maxbp =fs2.get(0).getPrice();
				long minbp =fs2.get(0).getPrice();
				for (int i = 0; i < fs2.size(); i++) {
					if(maxbp<fs2.get(i).getPrice()){
						maxbp = fs2.get(i).getPrice();
					};
					if(minbp>fs2.get(i).getPrice()){
						minbp = fs2.get(i).getPrice();
					};
				}
				if(maxbp==minbp){
					priceStr =priceStr+ "包车："+df.format((double)(maxbp)/100)+"	";
				}else{
					priceStr =priceStr+ "包车："+df.format((double)(minbp)/100)+"-"+df.format((double)(maxbp)/100) +"	";
				}
			}
		}












		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setName(entity.getName())
		.setOrigin(entity.getOrigin())
		.setEnd(entity.getEnd())
		.setStartTime(startTimeStr)
		.setPrice(priceStr)
		.setSites(sitesStr)
		.setLab(entity.getLab());
		return this;


	}



}
