package com.guolaiwan.app.zizhu.bean;

import java.util.List;

public class ProductVo {
	private List<Data> data;
    private Error error;
    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setError(Error error) {
         this.error = error;
     }
     public Error getError() {
         return error;
     }
     public class Error {

    	    private int code;
    	    private String message;
    	    public void setCode(int code) {
    	         this.code = code;
    	     }
    	     public int getCode() {
    	         return code;
    	     }

    	    public void setMessage(String message) {
    	         this.message = message;
    	     }
    	     public String getMessage() {
    	         return message;
    	     }

    	}
     public class Data {
    	//产品Id
        private int id;
    	//产品代码
    	private String code;
    	 //产品名称
		private String name;
		 //产品种类
    	 private int productCategory;
    	 //人群名称
    	 private String crowdKindName;
    	 //价格类型
         private String priceName;
         //实际售价
    	 private int price;
    	 //票面价格
    	 private int basicPrice ;
    	 //有效期数量
    	 private int validityNum;
    	 //有效期类型
         private int validityUnits;
         //是否登记用户信息
   	     private boolean hasUser;
   	     //是否登记人脸信息
   	     private boolean hasFace;
     	 public void setId(int id) {
    	        this.id = id;
    	    }
	     public int getId() {
	         return id;
	     }
	     public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
	    public void setName(String name) {
	         this.name = name;
	     }
	     public String getName() {
	         return name;
	     }

	    public void setProductCategory(int productCategory) {
	         this.productCategory = productCategory;
	     }
	     public int getProductCategory() {
	         return productCategory;
	     }

	    public void setCrowdKindName(String crowdKindName) {
	         this.crowdKindName = crowdKindName;
	     }
	     public String getCrowdKindName() {
	         return crowdKindName;
	     }

	    public void setPriceName(String priceName) {
	         this.priceName = priceName;
	     }
	     public String getPriceName() {
	         return priceName;
	     }

	    public void setPrice(int price) {
	         this.price = price;
	     }
	     public int getPrice() {
	         return price;
	     }

	    public void setBasicPrice (int basicPrice ) {
	         this.basicPrice  = basicPrice ;
	     }
	     public int getBasicPrice () {
	         return basicPrice ;
	     }

	    public void setValidityNum(int validityNum) {
	         this.validityNum = validityNum;
	     }
	     public int getValidityNum() {
	         return validityNum;
	     }

	    public void setValidityUnits(int validityUnits) {
	         this.validityUnits = validityUnits;
	     }
	     public int getValidityUnits() {
	         return validityUnits;
	     }

	    public void setHasUser(boolean hasUser) {
	         this.hasUser = hasUser;
	     }
	     public boolean getHasUser() {
	         return hasUser;
	     }

	    public void setHasFace(boolean hasFace) {
	         this.hasFace = hasFace;
	     }
	     public boolean getHasFace() {
	         return hasFace;
	     }

    	}
}
