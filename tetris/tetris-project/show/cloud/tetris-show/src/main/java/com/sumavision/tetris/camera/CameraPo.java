package com.sumavision.tetris.camera;

import com.sumavision.tetris.orm.po.AbstractBasePO;
import com.sumavision.tetris.show.LivePo;
import com.sumavision.tetris.show.LiveVo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName CameraPo
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 11:01
 **/
@Entity
@Table(name = "TETRIS_CAMERA")
@Data
public class CameraPo extends AbstractBasePO {
    private static final long serialVersionUID = 1L;
    //url
    private String rtmpUrl;
    private String httpUrl;
    //机位编号
    private String cameraName;
    //正在使用中的机位
    private Integer isInUse;
    //状态 0 删除状态 1可用状态
    private Integer type;
    //用户Id 用于关联N个机位
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

    public void setContentUp(String contentUp) {
        this.contentUp = contentUp;
    }

    public void setUserId(String contentDown) {
        this.contentDown = contentDown;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
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

    public void setContentDown(String contentDown) {
		this.contentDown=contentDown;
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


    public CameraPo set(CameraVo entity) throws Exception {
        this.setContentUp(entity.getContentUp());
        this.setContentDown(entity.getContentDown());
        this.setPicture(entity.getPicture());
        this.setShowId(entity.getShowId());
        this.setIsInUse(entity.getIsInUse());
        this.setCameraName(entity.getCameraName());
        this.setHttpUrl(entity.getHttpUrl());
        this.setRtmpUrl(entity.getRtmpUrl());
        this.setType(entity.getType());
        this.setId(entity.getId());
        this.setUuid(entity.getUuid());
        this.setUserId(entity.getUserId());
        return this;
    }
}
