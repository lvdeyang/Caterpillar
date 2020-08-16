package com.guolaiwan.app.zizhu.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateVoucherVo {
	public CreateVoucherVo(){
		error=new Error();
		data=new Data();
	}
	private Data data;
    private Error error;
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
    public static class Data {
    	//凭证号
        private String voucherNumber;
        //创建时间
        private Date createTime;
        //总数量
        private int totalQuantity;
        //总金额
        private double totalAmount;
        private List<Details> details;
        public static  class Details {
        	//产品Id
            private int productId;
            //门票票号
            private String ticketNo;
            //门票二维码图片地址
            private String ticketNoPic;
            //产品
            private String productName;
            //业务
            private String businessName;
            //人群
            private String crowdKindName;
            //价格类型
            private String priceName;
            //实际售价
            private double price;
            //票面价格
            private int basicPrice;
            //数量
            private int quantity;
            //金额
            private double amount;
            //有效起始时间
            private Date useStartDate;
            //效截止时间
            private Date useEndDate;
            //证件号码
            private String idCard;
            //姓名
            private String name;
            //联系电话
            private String phone;
            //人脸
            private String face;
            
            public String getFace() {
				return face;
			}
			public void setFace(String face) {
				this.face = face;
			}
			public void setProductId(int productId) {
                 this.productId = productId;
             }
             public int getProductId() {
                 return productId;
             }

            public void setTicketNo(String ticketNo) {
                 this.ticketNo = ticketNo;
             }
             public String getTicketNo() {
                 return ticketNo;
             }

            public void setTicketNoPic(String ticketNoPic) {
                 this.ticketNoPic = ticketNoPic;
             }
             public String getTicketNoPic() {
                 return ticketNoPic;
             }

            public void setProductName(String productName) {
                 this.productName = productName;
             }
             public String getProductName() {
                 return productName;
             }

            public void setBusinessName(String businessName) {
                 this.businessName = businessName;
             }
             public String getBusinessName() {
                 return businessName;
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

            public void setPrice(double price) {
                 this.price = price;
             }
             public double getPrice() {
                 return price;
             }

            public void setBasicPrice(int basicPrice) {
                 this.basicPrice = basicPrice;
             }
             public int getBasicPrice() {
                 return basicPrice;
             }

            public void setQuantity(int quantity) {
                 this.quantity = quantity;
             }
             public int getQuantity() {
                 return quantity;
             }

            public void setAmount(double amount) {
                 this.amount = amount;
             }
             public double getAmount() {
                 return amount;
             }

            public void setUseStartDate(Date useStartDate) {
                 this.useStartDate = useStartDate;
             }
             public Date getUseStartDate() {
                 return useStartDate;
             }

            public void setUseEndDate(Date useEndDate) {
                 this.useEndDate = useEndDate;
             }
             public Date getUseEndDate() {
                 return useEndDate;
             }

            public void setIdCard(String idCard) {
                 this.idCard = idCard;
             }
             public String getIdCard() {
                 return idCard;
             }

            public void setName(String name) {
                 this.name = name;
             }
             public String getName() {
                 return name;
             }

            public void setPhone(String phone) {
                 this.phone = phone;
             }
             public String getPhone() {
                 return phone;
             }

        }
        public void setVoucherNumber(String voucherNumber) {
             this.voucherNumber = voucherNumber;
         }
         public String getVoucherNumber() {
             return voucherNumber;
         }

        public void setCreateTime(Date createTime) {
             this.createTime = createTime;
         }
         public Date getCreateTime() {
             return createTime;
         }

        public void setTotalQuantity(int totalQuantity) {
             this.totalQuantity = totalQuantity;
         }
         public int getTotalQuantity() {
             return totalQuantity;
         }

        public void setTotalAmount(double totalAmount) {
             this.totalAmount = totalAmount;
         }
         public double getTotalAmount() {
             return totalAmount;
         }

        public void setDetails(List<Details> details) {
             this.details = details;
         }
         public List<Details> getDetails() {
             return details;
         }

    }
    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setError(Error error) {
         this.error = error;
     }
     public Error getError() {
         return error;
     }
}
