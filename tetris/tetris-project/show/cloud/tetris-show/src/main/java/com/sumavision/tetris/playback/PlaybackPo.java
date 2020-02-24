package com.sumavision.tetris.playback;

import com.sumavision.tetris.orm.po.AbstractBasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName PlaybackPo
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 14:40
 **/

@Entity
@Table(name = "TETRIS_PLAYBACK")
public class PlaybackPo extends AbstractBasePO {
    private static final long serialVersionUID = 1L;
    //每一个主播对应一个直播间 每一个直播间对应一个回放的文件夹
    private String path;
    private Long showId;
    private Date createDate;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
