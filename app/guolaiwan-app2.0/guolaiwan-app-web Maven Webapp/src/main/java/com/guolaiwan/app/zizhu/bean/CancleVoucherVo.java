package com.guolaiwan.app.zizhu.bean;


public class CancleVoucherVo {
	private Data data;
    private Error error;
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
        private int status;
    	//凭证号
    	private String voucherNumber;
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getVoucherNumber() {
			return voucherNumber;
		}
		public void setVoucherNumber(String voucherNumber) {
			this.voucherNumber = voucherNumber;
		}
    	

    }
}
