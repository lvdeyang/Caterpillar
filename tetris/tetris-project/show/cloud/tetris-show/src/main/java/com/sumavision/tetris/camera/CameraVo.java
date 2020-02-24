package com.sumavision.tetris.camera;

import com.sumavision.tetris.mvc.converter.AbstractBaseVO;
import lombok.Data;


/**
 * @ClassName CameraVo
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 11:16
 **/
@Data
public class CameraVo extends AbstractBaseVO<CameraVo, CameraPo> {
    //url
    private String rtmpUrl;
    private String httpUrl;
    //机位编号
    private String cameraName;
    //正在使用中的机位
    private Integer isInUse;
    //状态 0未开播 1 在直播
    private Integer type;
    //userId
    private Long showId;

    private Long userId;
    private String picture;
    private String contentUp;
    private String contentDown;

    public String getContentUp() {
        return contentUp;
    }

    public String getPicture() {
        return picture;
    }

    public String getContentDown() {
        return contentDown;
    }

    public Long getUserId() {
        return userId;
    }
    public void setContentDown(String contentDown) {
		this.contentDown=contentDown;
	}
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setContentUp(String contentUp) {
        this.contentUp = contentUp;
    }

    public void setUserId(String contentDown) {
        this.contentDown = contentDown;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }


    public String getHttpUrl() {
        return httpUrl;
    }


    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }


    public String getCameraName() {
        return cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    public Integer getIsInUse() {
        return isInUse;
    }


    public void setIsInUse(Integer isInUse) {
        this.isInUse = isInUse;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public Long getShowId() {
        return showId;
    }


    public void setShowId(Long showId) {
        this.showId = showId;
    }


    @Override
    public CameraVo set(CameraPo entity) throws Exception {
        this.setContentUp(entity.getContentUp());
        this.setContentDown(entity.getContentDown());
        this.setPicture(entity.getPicture());
        this.setId(entity.getId());
        this.setUuid(entity.getUuid());
        this.setCameraName(entity.getCameraName());
        this.setHttpUrl(entity.getHttpUrl());
        this.setRtmpUrl(entity.getRtmpUrl());
        this.setIsInUse(entity.getIsInUse());
        this.setType(entity.getType());
        this.setUserId(entity.getUserId());
        this.setShowId(entity.getShowId());
        return this;
    }
}
