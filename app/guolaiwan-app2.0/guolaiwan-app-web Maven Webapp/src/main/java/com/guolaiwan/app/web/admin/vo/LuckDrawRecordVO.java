package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class LuckDrawRecordVO extends AbstractBaseVO<LuckDrawRecordVO, LuckDrawRecord>{
	
	private String phone;
    private String userName;
    private long userId;
    private String drawProductId;//0未中奖，1电影票，2眼镜
    private int useit;//0未用1已用
    
    public String getPhone()
    {
    	return phone;
    }

    public LuckDrawRecordVO setPhone(String phone)
    {
    	this.phone = phone;
    	return this;
    }
    
    public String getUserName()
    {
    	return userName;
    }

    public LuckDrawRecordVO setUserName(String userName)
    {
    	this.userName = userName;
    	return this;
    }
    
    public long getUserId()
    {
    	return userId;
    }

    public LuckDrawRecordVO setUserId(long userId)
    {
    	this.userId = userId;
    	return this;
    }
    
    public String getDrawProductId()
    {
    	return drawProductId;
    }

    public LuckDrawRecordVO setDrawProductId(String drawproductId)
    {
    	this.drawProductId = drawproductId;
    	return this;
    }
    
    public int getUseit()
    {
    	return useit;
    }

    public LuckDrawRecordVO setUseit(int useit)
    {
    	this.useit = useit;
    	return this;
    }

	@Override
	public LuckDrawRecordVO set(LuckDrawRecord entity) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String prize = "";
		if (entity.getDrawProductId()==1) {
			prize = "电影票";
		}else if (entity.getDrawProductId()==2) {
			prize = "眼镜";
		}
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(formatter.format(entity.getUpdateTime()))
		.setUserName(entity.getUserName())
		.setDrawProductId(prize)
		.setPhone(entity.getPhone())
		.setUserId(entity.getUserId())
		.setUseit(entity.getUseit());
		
		return this;
	}
}
