package com.sumavision.tetris.show;

import com.sumavision.tetris.orm.po.AbstractBasePO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TETRIS_SHOW")
public class LivePo extends AbstractBasePO {
    private static final long serialVersionUID = 1L;
    /*直播名称*/
    private String title;
    /*rtmp url*/
    private String urlRtmp;
    /*主播ID*/
    private long anchorId;
    /*观看人数-热度*/
    private Integer peoNum;
    /*封面图片路径*/
    private String coverPic;
    /*直播类型 唱歌 卖货 */
    private Integer type;
    /*http url*/
    private String urlHttp;
    /*每一个直播间对应一个直播回放的文件夹*/
    private String playback;
    /*是否是推荐 0：不是推荐  1:人情事故的 '推荐' */
    private Integer isRecommend;
    /*0：未直播， 1：直播中 ，2：已被封停 */
    private Integer status;
    
    private int recordstatus;//是否开始录制1：开始0：停止
    
    private long tempId;
    
    private String recordPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlRtmp() {
        return urlRtmp;
    }

    public void setUrlRtmp(String urlRtmp) {
        this.urlRtmp = urlRtmp;
    }

    public long getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(long anchorId) {
        this.anchorId = anchorId;
    }

    public Integer getPeoNum() {
        return peoNum;
    }

    public void setPeoNum(Integer peoNum) {
        this.peoNum = peoNum;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrlHttp() {
        return urlHttp;
    }

    public void setUrlHttp(String urlHttp) {
        this.urlHttp = urlHttp;
    }

    public String getPlayback() {
        return playback;
    }

    public void setPlayback(String playback) {
        this.playback = playback;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LivePo set(LiveVo entity) throws Exception {
        this.setAnchorId(entity.getAnchorId());
        this.setCoverPic(entity.getCoverPic());
        this.setIsRecommend(entity.getIsRecommend());
        this.setPeoNum(entity.getPeoNum());
        this.setPlayback(entity.getPlayback());
        this.setStatus(entity.getStatus());
        this.setTitle(entity.getTitle());
        this.setType(entity.getType());
        this.setUrlHttp(entity.getUrlHttp());
        this.setUrlRtmp(entity.getUrlRtmp());
        this.setRecordstatus(entity.getRecordstatus());
        this.setTempId(entity.getTempId());
        this.setRecordPath(entity.getRecordPath());
        return this;
    }

	public int getRecordstatus() {
		return recordstatus;
	}

	public void setRecordstatus(int recordstatus) {
		this.recordstatus = recordstatus;
	}

	public long getTempId() {
		return tempId;
	}

	public void setTempId(long tempId) {
		this.tempId = tempId;
	}

	public String getRecordPath() {
		return recordPath;
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}
	
	
}
