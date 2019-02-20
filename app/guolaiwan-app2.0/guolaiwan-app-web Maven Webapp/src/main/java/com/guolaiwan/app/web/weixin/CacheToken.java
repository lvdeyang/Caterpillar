package com.guolaiwan.app.web.weixin;

import java.util.Date;

import com.guolaiwan.bussiness.javacv.GuoliawanLiveServiceWrapper;

public class CacheToken {
	private CacheTokenBo bo;
	private long expire=3600000;
	private static CacheToken instance;
    private CacheToken() {}
    public static CacheToken getInstance() {
        if (instance == null) {
            instance = new CacheToken();
        }
        return instance;
    }
    
    public CacheTokenBo fetchTokenBo(){
    	if(bo==null){
    		return null;
    	}
    	Date time=new Date();
    	long disTime=time.getTime()-bo.getFecthTime().getTime();
    	if(disTime<expire){
    		return bo;
    	}else{
    		return null;
    	}
    }
	public CacheTokenBo getBo() {
		return bo;
	}
	public void setBo(CacheTokenBo bo) {
		this.bo = bo;
	}
	public long getExpire() {
		return expire;
	}
	public void setExpire(long expire) {
		this.expire = expire;
	}
    
    
    

}
