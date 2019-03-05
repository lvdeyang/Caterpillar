package com.guolaiwan.app.web.smartParking.vo;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CarPositionVo   extends AbstractBaseVO<CarPositionVo, CarPositionPO>{

	
	//景区停车场id
		private  Long attractionsId;
		// 车位 经度
		private String  longitude;
		//车位 纬度
		private String latitude;
		//车位层数
		private String number;
		//车位那区
		private String  district;
		
		
		@Override
		public CarPositionVo set(CarPositionPO entity) throws Exception {
		this.setAttractionsId(entity.getAttractionsId())
		.setLongitude(entity.getLatitude())
		.setLatitude(entity.getLatitude())
		.setNumber(entity.getNumber())
		.setDistrict(entity.getDistrict());
		return this;
		}
		
		
		
		/**
		 * 景区停车场id
		 * @return
		 */
		public Long getAttractionsId() {
			return attractionsId;
		}
		/**
		 * 景区停车场id
		 * @return 
		 * @return
		 */
		public CarPositionVo setAttractionsId(Long attractionsId) {
			this.attractionsId = attractionsId;
			return this;
		}
		/**
		 * 景区车位经度
		 * @return
		 */
		public String getLongitude() {
			return longitude;
		}
		/**
		 * 景区车位经度
		 * @return 
		 * @return
		 */
		public CarPositionVo setLongitude(String longitude) {
			this.longitude = longitude;
			return this;
		}
		/**
		 * 景区车位纬度
		 * @return
		 */
		public String getLatitude() {
			return latitude;
		}
		/**
		 * 景区车位纬度
		 * @return 
		 * @return
		 */
		public CarPositionVo setLatitude(String latitude) {
			this.latitude = latitude;
			return this;
		}
		/**
		 * 景区车位层数
		 * @return
		 */
		public String getNumber() {
			return number;
		}
		/**
		 * 景区车位层数
		 * @return 
		 * @return
		 */
		public CarPositionVo setNumber(String number) {
			this.number = number;
			return this;
		}
		/**
		 * 车位那区
		 */
		public String getDistrict() {
			return district;
		}
		/**
		 * 车位那区
		 * @return 
		 */
		public CarPositionVo setDistrict(String district) {
			this.district = district;
			return this;
		}
	
	
	
	


}
