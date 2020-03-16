package com.sumavision.tetris.show;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LiveService {

    @Autowired
    private LiveQuery liveQuery;
    @Autowired
    private LiveDao liveDao;

    /**
     * @描述 查询所有
     * @参数
     * @返回值 List<LiveVo>
     * @创建人 yud
     * @创建时间 2019/12/23
     */
    public List<LiveVo> getAllLive() throws Exception {
        List<LivePo> livePos = liveQuery.getAllLive();
        List<LiveVo> liveVos = this.generateRootattends(livePos);
        return liveVos;
    }

    /**
     * @描述 根据Id查询
     * @参数 id
     * @返回值 List<LiveVo>
     * @创建人 yud
     * @创建时间 2019/12/23
     */
    public LiveVo getById(long id) throws Exception {
        LivePo livePo = liveQuery.getById(id);
        return new LiveVo().set(livePo);
    }

   
    
    public LiveVo getByAnchorId(long anchorId) throws Exception{
    	List<LivePo> livePos = liveQuery.getByAnchorId(anchorId);
    	if(livePos==null||livePos.isEmpty()){
    		LivePo livePo=new LivePo();
    		livePo.setAnchorId(anchorId);
    		liveDao.save(livePo);
    		return new LiveVo().set(livePo);
    	}else{
    		return new LiveVo().set(livePos.get(0));
    	}
    	
    }
    

    /**
     * @描述 save
     * @参数 LiveVo
     * @返回值 void
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public void save(LiveVo liveVo) throws Exception {
        LivePo livePo = new LivePo().set(liveVo);
        livePo.setUpdateTime(new Date());
        liveDao.save(livePo);
    }

    /**
     * @描述 根据Id删除
     * @参数 id
     * @返回值 void
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public void deleteById(Long id) throws Exception {
        liveDao.delete(id);
    }

    public void update(LiveVo liveVo) {
        LivePo livePo = liveDao.findOne(liveVo.getId());
        livePo.setUpdateTime(new Date());
        liveDao.save(livePo);
    }

    /**
     * VO PO转换
     *
     * @param attends
     * @return
     * @throws Exception
     */
    private List<LiveVo> generateRootattends(Collection<LivePo> attends) throws Exception {
        if (attends == null || attends.size() <= 0)
            return null;
        List<LiveVo> liveVos = new ArrayList<LiveVo>();
        for (LivePo attend : attends) {
            liveVos.add(new LiveVo().set(attend));
        }

        return liveVos;
    }

}
