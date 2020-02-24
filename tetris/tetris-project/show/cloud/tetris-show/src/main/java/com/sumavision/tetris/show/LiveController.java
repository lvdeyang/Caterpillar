package com.sumavision.tetris.show;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;

import java.util.List;

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
}
