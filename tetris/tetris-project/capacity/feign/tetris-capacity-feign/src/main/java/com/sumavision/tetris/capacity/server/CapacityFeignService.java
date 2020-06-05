package com.sumavision.tetris.capacity.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.mvc.ext.response.parser.JsonBodyResponseParser;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CapacityFeignService {

    @Autowired
    private CapacityFeign capacityFeign;

 
    
    public void createRtspTask(String list, Long userId) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.createRtspTask(list, userId), null);
    }
    
    
    public void createTempTask(String list, Long userId,Long tempId) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.createTempTask(list, userId,tempId), null);
    }

    /**
     * 创建推流<br/>
     * <b>作者:</b>yud<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2020年01月11日 上午9:20:04
     *
     * @param
     */
    public void deleteTask(String list, Long userId) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.deleteTask(list, userId), null);
    }


    /**
     * 创建推流<br/>
     * <b>作者:</b>yud<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2020年02月05日 上午9:39:09
     *
     * @param
     */
    public void switchTask(Long userId, int index) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.swtichTask(userId, index), null);
    }
    /**
     *@描述  创建录制
     *@参数
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/6
     */
    public void createRecordTask(String pubName,String path) throws Exception{
        JsonBodyResponseParser.parseObject(capacityFeign.createRecordTask(pubName,path),null);
    }
    /**
     *@描述   删除录制任务
     *@参数  pubName
     *@返回值
     *@创建人  yud
     *@创建时间  2020/2/6
     */
    public void deleteRecordTask(String pubName) throws Exception{
        JsonBodyResponseParser.parseObject(capacityFeign.deleteRecordTask(pubName),null);
    }
    
    public List<TempVo> tempList() throws Exception{
        return JsonBodyResponseParser.parseArray(capacityFeign.tempList(),TempVo.class);
    }
    
    public List<TempVo> pushtempList() throws Exception{
        return JsonBodyResponseParser.parseArray(capacityFeign.pushtempList(),TempVo.class);
    }
    
    public List<TempVo> transcodetempList() throws Exception{
        return JsonBodyResponseParser.parseArray(capacityFeign.transcodetempList(),TempVo.class);
    }
}
