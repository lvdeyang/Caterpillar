package com.guolaiwan.bussiness.admin.enumeration;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

/**
 * 描述特殊的商户业务<br/>
 * <p>
 *    与已有的业务模式（商户：产品）不同或者数据层级不同导致无法用统一的页面展示，用该字段进行区别定制
 * </p>
 * @author 吕德阳
 */
public enum MerchantSpecialBusiness {

	/** 商户：产品模式 */
	BASIC("基本业务"),
	
	/** 租车服务 */
	CONVOYS("车队"),
	
	RESTAURANT("饭店"),
	
	SCENICSPOT("景点");
	
	private String name;
	
	private MerchantSpecialBusiness(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	public String getFiled() throws Exception{
		if(this.equals(BASIC)){
			return "BASIC";
		}else if(this.equals(CONVOYS)){
			return "CONVOYS";
		}else if(this.equals(RESTAURANT)){
			return "RESTAURANT";
		}else if(this.equals(SCENICSPOT))
		{
			return "SCENICSPOT";
		}
		else{
			throw new Exception("错误的商户业务名称！");
		}
	}
	
	public static MerchantSpecialBusiness fromName(String name) throws Exception{
		MerchantSpecialBusiness[] values = MerchantSpecialBusiness.values();
		for(MerchantSpecialBusiness value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new Exception(new StringBufferWrapper().append("错误的商户业务名称：").append(name).toString());
	}
	public static MerchantSpecialBusiness fromString(String s) throws Exception{
		MerchantSpecialBusiness[] values = MerchantSpecialBusiness.values();
		for(MerchantSpecialBusiness value:values){
			if(value.getFiled().equals(s)){
				return value;
			}
		}
		throw new Exception(new StringBufferWrapper().append("错误的商户业务名称：").append(s).toString());
	}
}
