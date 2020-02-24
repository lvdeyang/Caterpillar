//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.20 时间 09:49:27 AM CST 
//


package com.guolaiwan.app.aoyou.bo;

import java.util.List;

public class AoYouOrder {

    private String trade_no;
    private int prod_id;
    private String user_name;
    private String mobile_no;
    private int prod_count;
    private String book_date;
    
    private List<AoYouOrderDetail> order_detail;
    
    private BookDetail book_detail;
    
    private ExpressInfo express_info;
    
    public static class AoYouOrderDetail {
    	
    	public AoYouOrderDetail(){
    		
    	}
    	
    	private int ticket_count;
        private String holder_name;
        private String holder_mobile;
		private int id_type;
        private String id_no;
        
        public int getTicket_count() {
			return ticket_count;
		}
		public void setTicket_count(int ticket_count) {
			this.ticket_count = ticket_count;
		}
		public String getHolder_name() {
			return holder_name;
		}
		public void setHolder_name(String holder_name) {
			this.holder_name = holder_name;
		}
		public String getHolder_mobile() {
			return holder_mobile;
		}
		public void setHolder_mobile(String holder_mobile) {
			this.holder_mobile = holder_mobile;
		}
		public int getId_type() {
			return id_type;
		}
		public void setId_type(int id_type) {
			this.id_type = id_type;
		}
		public String getId_no() {
			return id_no;
		}
		public void setId_no(String id_no) {
			this.id_no = id_no;
		}
    }
    
    public static class BookDetail {
    	private String book_date;
        private String book_period;
        private String venue_code;
        private int book_count;
        
        public BookDetail(){
    		
    	}
        
        public String getBook_date() {
			return book_date;
		}
		public void setBook_date(String book_date) {
			this.book_date = book_date;
		}
		public String getBook_period() {
			return book_period;
		}
		public void setBook_period(String book_period) {
			this.book_period = book_period;
		}
		public String getVenue_code() {
			return venue_code;
		}
		public void setVenue_code(String venue_code) {
			this.venue_code = venue_code;
		}
		public int getBook_count() {
			return book_count;
		}
		public void setBook_count(int book_count) {
			this.book_count = book_count;
		}
    }
    
    public static class ExpressInfo {
    	
    	public ExpressInfo() {
    		
    	}
    	
    	private String sf_province;
        private String sf_city;
        private String sf_address;
        private String sf_man;
        private String sf_mobile;
        
		public String getSf_province() {
			return sf_province;
		}
		public void setSf_province(String sf_province) {
			this.sf_province = sf_province;
		}
		public String getSf_city() {
			return sf_city;
		}
		public void setSf_city(String sf_city) {
			this.sf_city = sf_city;
		}
		public String getSf_address() {
			return sf_address;
		}
		public void setSf_address(String sf_address) {
			this.sf_address = sf_address;
		}
		public String getSf_man() {
			return sf_man;
		}
		public void setSf_man(String sf_man) {
			this.sf_man = sf_man;
		}
		public String getSf_mobile() {
			return sf_mobile;
		}
		public void setSf_mobile(String sf_mobile) {
			this.sf_mobile = sf_mobile;
		}
    }

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public int getProd_count() {
		return prod_count;
	}

	public void setProd_count(int prod_count) {
		this.prod_count = prod_count;
	}

	public String getBook_date() {
		return book_date;
	}

	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}

	public List<AoYouOrderDetail> getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(List<AoYouOrderDetail> order_detail) {
		this.order_detail = order_detail;
	}

	public BookDetail getBook_detail() {
		return book_detail;
	}

	public void setBook_detail(BookDetail book_detail) {
		this.book_detail = book_detail;
	}

	public ExpressInfo getExpress_info() {
		return express_info;
	}

	public void setExpress_info(ExpressInfo express_info) {
		this.express_info = express_info;
	}
    
}
