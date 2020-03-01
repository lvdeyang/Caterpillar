package com.sumavision.tetris.show;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/show/live")

public class LiveController {
    @Autowired
    private LiveService service;

    @JsonBody
    @ResponseBody
    @RequestMapping(value = "")
    public Object getAllAttend() throws Exception {
        List<LiveVo> liveVos = service.getAllLive();
        return liveVos;
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable long id) throws Exception {
        LiveVo liveVo = service.getById(id);
        return liveVo;
    }

    @GetMapping("getByType/{type}")
    public Object getByType(@PathVariable Integer type) throws Exception {
        List<LiveVo> liveVos = service.getByType(type);
        return liveVos;

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
}
