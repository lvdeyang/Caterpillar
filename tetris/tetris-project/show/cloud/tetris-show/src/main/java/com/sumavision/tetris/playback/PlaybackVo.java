package com.sumavision.tetris.playback;


import java.util.Date;

/**
 * @ClassName PlaybackVo
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 14:41
 **/

public class PlaybackVo {
    private String path;
    private Long showId;
    private Date createDate;
    private String videoName;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
    
    
}
