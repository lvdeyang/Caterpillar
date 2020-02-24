package com.sumavision.tetris.playback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName PlaybackController
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 14:42
 **/
@Controller
@RequestMapping(value = "/show/playback")
public class PlaybackController {
    @Autowired
    private PlaybackService playbackService;

    @GetMapping("/getPlayByShowId/{show_id}")
    public List<String> getPlayBackListByShowId(@PathVariable Long show_id) {
        return playbackService.getPlayBackListByShowId(show_id);
    }

    @DeleteMapping("/removePlayByPath/{playBackPath}")
    public Boolean removePlayByPath(@PathVariable String playBackPath) {
        return playbackService.removePlayByPath(playBackPath);
    }
}
