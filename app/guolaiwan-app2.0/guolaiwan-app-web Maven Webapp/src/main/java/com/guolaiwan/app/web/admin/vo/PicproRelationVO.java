package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.PicproRelationPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class PicproRelationVO extends AbstractBaseVO<PicproRelationVO, PicproRelationPO>{

	//子产品id
	private long childProID;
	//图片id
	private long childPicID;
	public long getChildProID() {
		return childProID;
	}
	public PicproRelationVO setChildProID(long childProID) {
		this.childProID = childProID;
		return this;
	}
	public long getChildPicID() {
		return childPicID;
	}
	public PicproRelationVO setChildPicID(long childPicID) {
		this.childPicID = childPicID;
		return this;
	}
    public PicproRelationVO set(PicproRelationPO entity)throws Exception {
    	this.setId(entity.getId())
    	    .setUuid(entity.getUuid())
    	    .setChildProID(entity.getChildProID())
    	    .setChildPicID(entity.getChildPicID());
    	return this;
		
	}
}
