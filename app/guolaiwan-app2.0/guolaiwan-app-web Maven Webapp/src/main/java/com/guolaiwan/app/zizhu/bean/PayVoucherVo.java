package com.guolaiwan.app.zizhu.bean;

import java.util.List;

public class PayVoucherVo {
	 private Data data;
	 private Error error;
	 public class Data {
		 //凭证号
	    private String voucherNumber ;
	    private List<Details> details;
	    public class Details {
	    	//产品Id
	        private int productId;
	        //门票票号
	        private String ticketNo;
	        //门票二维码图片地址
	        private String ticketNoPic;
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

	    }
	    public void setVoucherNumber (String voucherNumber ) {
	         this.voucherNumber  = voucherNumber ;
	     }
	     public String getVoucherNumber () {
	         return voucherNumber ;
	     }

	    public void setDetails(List<Details> details) {
	         this.details = details;
	     }
	     public List<Details> getDetails() {
	         return details;
	     }
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
