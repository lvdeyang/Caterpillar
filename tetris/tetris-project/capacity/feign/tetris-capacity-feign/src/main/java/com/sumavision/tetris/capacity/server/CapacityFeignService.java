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

    /**
     * 添加收录<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年12月3日 上午10:46:43
     *
     * @param String recordInfo 收录信息
     * @return String 收录标识
     */
    public String addRecord(String recordInfo) throws Exception {
        return JsonBodyResponseParser.parseObject(capacityFeign.addRecord(recordInfo), String.class);
    }

    /**
     * 停止收录<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年12月3日 上午10:47:20
     *
     * @param String id 收录标识
     */
    public void deleteRecord(String id) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.deleteRecord(id), null);
    }

    /**
     * 添加流转码<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年12月16日 上午9:19:33
     *
     * @param String transcodeInfo 流转码信息
     */
    public void addTranscode(String transcodeInfo) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.addTranscode(transcodeInfo), null);
    }

    /**
     * 删除流转码<br/>
     * <b>作者:</b>wjw<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2019年12月16日 上午9:20:04
     *
     * @param String id 任务标识
     */
    public void deleteTranscode(String id) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.deleteTranscode(id), null);
    }

    /**
     * 创建推流<br/>
     * <b>作者:</b>yud<br/>
     * <b>版本：</b>1.0<br/>
     * <b>日期：</b>2020年01月11日 上午9:20:04
     *
     * @param
     */
    public void createTask(String list, Long userId) throws Exception {
        JsonBodyResponseParser.parseObject(capacityFeign.createTask(list, userId), null);
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
}
