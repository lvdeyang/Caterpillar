package com.sumavision.tetris.show;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.capacity.server.CapacityFeignService;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserVO;
import com.sumavision.tetris.util.http.HttpUtil;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/show/live")

public class LiveController {
    @Autowired
    private LiveService service;

    @GetMapping("/{id}")
    public Object getById(@PathVariable long id) throws Exception {
        LiveVo liveVo = service.getById(id);
        return liveVo;
    }

  

    @PostMapping()
    public void save(@RequestBody LiveVo liveVo) throws Exception {
        service.save(liveVo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        service.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public void update(@PathVariable Long id, @RequestBody LiveVo liveVo) {
        service.update(liveVo);
    }
    
    //mr后台管理页面
    @Autowired
    LiveQuery liveQuery;
    @JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		
		
		//TODO 权限校验		
		Page<LivePo> page = liveQuery.findAll(currentPage, pageSize);
		List<LivePo> entities =page.getContent();
		List<LiveVo> devices = LiveVo.getConverter(LiveVo.class).convert(entities, LiveVo.class);
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", devices)
													   .put("total", total)
													   .getMap();
	}
    
    
    @Autowired
    LiveService liveService;
    @Autowired
    LiveDao liveDao;
    @Autowired
    CapacityFeignService CapacityFeignService;
    
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/startRecord/{id}", method = {RequestMethod.POST})
    public Object startRecord(@PathVariable Long id) throws Exception {
        LivePo livePo=liveDao.findOne(id);
        String recordPath="/home/hls/"+UUID.randomUUID();
        File recordDic=new File(recordPath);
        if(!recordDic.exists())	recordDic.mkdir();
        livePo.setRecordstatus(1);
        livePo.setRecordPath(recordPath);
        liveDao.save(livePo);
        CapacityFeignService.createRecordTask("camera"+livePo.getAnchorId(), recordPath);
        return new LiveVo().set(livePo);
    }
    @JsonBody
    @ResponseBody
    @RequestMapping(value = "/stopRecord/{id}", method = {RequestMethod.POST})
    public Object stopRecord(@PathVariable Long id) throws Exception {
    	LivePo livePo=liveDao.findOne(id);
        livePo.setRecordstatus(0);
        liveDao.save(livePo);
        CapacityFeignService.deleteRecordTask("camera"+livePo.getAnchorId());
        JSONObject jsonParam=new JSONObject();
        jsonParam.put("liveId", livePo.getId());
        File file=new File(livePo.getRecordPath());
        String url="http://47.95.241.89:6690/"+livePo.getRecordPath().replace("/home/hls/", "");
        File[] subfiles=file.listFiles();
        for (File file2 : subfiles) {
			if(file2.isDirectory()){
				url+="/"+file2.getName()+"/vod.m3u8";
				break;
			}
		}
        jsonParam.put("url", url);
        HttpUtil.httpPost("http://www.guolaiwan.net/phoneApp/addrecord", jsonParam);
        return new LiveVo().set(livePo);
    }
    
}
