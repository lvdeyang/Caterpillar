package com.guolaiwan.app.zizhu.bean;

import java.util.List;

import com.guolaiwan.app.zizhu.bean.ProductVo.Data;
import com.guolaiwan.app.zizhu.bean.ProductVo.Error;

public class RefundVoucherVo {
	private Data data;
    private Error error;
    public void setData(Data data) {
         this.data = data;
     }
     public Data Data() {
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
    	 //退票状态	退票失败、1:退票成功
        private int status;
        //	票务系统退票凭证号
        private String refundNumber;
        //票务系统凭证号
    	private String voucherNumber;
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getRefundNumber() {
			return refundNumber;
		}
		public void setRefundNumber(String refundNumber) {
			this.refundNumber = refundNumber;
		}
		public String getVoucherNumber() {
			return voucherNumber;
		}
		public void setVoucherNumber(String voucherNumber) {
			this.voucherNumber = voucherNumber;
		}
    	
    }
}
