package com.guolaiwan.app.web.smartParking.vo;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.MoneyPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MoneyVo  extends AbstractBaseVO<MoneyVo, MoneyPO>{
	    //停车场区名称
		private  String attractionsId;
		//车位型
		private String parkingModel;
		//停车费用 
		private int  Money;
		//区域 
		private String  area;
		//层域 
		private String  tier;

		@Override
		public MoneyVo set(MoneyPO entity) throws Exception {
		this.setParkingModel(entity.getParkingModel())
		.setAttractionsId(entity.getAttractionsId()+"")
		.setMoney(entity.getMoney())
		.setArea(entity.getArea())
		.setTier(entity.getTier())
		.setId(entity.getId());
		return this;
		}
		




		/**
		 * 层域 
		 * @return
		 */

		public String getTier() {
			return tier;
		}

		/**
		 * 层域 
		 * @return 
		 * @return
		 */
		public MoneyVo setTier(String tier) {
			this.tier = tier;
			return this;
		}


		/**
		 * 区域 
		 * @return
		 */
		public String getArea() {
			return area;
		}


		/**
		 * 区域 
		 * @return 
		 * @return
		 */
		public MoneyVo setArea(String area) {
			this.area = area;
			return this;
		}
		public String getAttractionsId() {
			return attractionsId;
		}
		/**
		 * 停车场区id
		 * @return 
		 * @return
		 */
		public MoneyVo setAttractionsId(String attractionsId) {
			this.attractionsId = attractionsId;
			return this;
		}

		/**
		 * 车位型
		 * @return
		 */
		public String getParkingModel() {
			return parkingModel;
		}
		/**
		 * 车位型
		 * @return 
		 * @return
		 */
		public MoneyVo setParkingModel(String parkingModel) {
			this.parkingModel = parkingModel;
			return this;
		}
		/**
		 * 钱数
		 * @return
		 */
		public int getMoney() {
			return Money;
		}
		/**
		 * 钱
		 * @return 
		 * @return
		 */
		public MoneyVo setMoney(int money) {
			Money = money;
			return this;
		}


	

}
