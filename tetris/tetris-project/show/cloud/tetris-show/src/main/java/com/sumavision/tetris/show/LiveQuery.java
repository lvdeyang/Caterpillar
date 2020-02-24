package com.sumavision.tetris.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiveQuery {
    @Autowired
    private LiveDao liveDao;

    public List<LivePo> getAllLive() throws Exception {
        // TODO Auto-generated method stub
        List<LivePo> livePos = liveDao.findAll();
        if (livePos != null && livePos.size() > 0) {
            return livePos;
        }
        return null;
    }

    public LivePo getById(long id) {
        LivePo livePo = (LivePo) liveDao.findOne(id);
        return livePo;
    }

    public List<LivePo> getByType(Integer type) {
        liveDao.getByType(type);
        return null;
    }
}
