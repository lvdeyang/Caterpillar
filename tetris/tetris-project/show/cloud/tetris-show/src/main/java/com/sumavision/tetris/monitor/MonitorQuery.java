package com.sumavision.tetris.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonitorQuery {
    @Autowired
    private MonitorDao monitorDao;

    public Page<MonitorPo> findAll(int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<MonitorPo> monitors = monitorDao.findAll(page);
		return monitors;
	}
}
