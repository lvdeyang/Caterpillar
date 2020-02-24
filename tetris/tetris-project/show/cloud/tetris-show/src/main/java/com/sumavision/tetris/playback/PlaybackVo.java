package com.sumavision.tetris.playback;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PlaybackVo
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 14:41
 **/
@Data
public class PlaybackVo {
    private String path;
    private Long showId;
    private Date createDate;
    private String videoName;
}
