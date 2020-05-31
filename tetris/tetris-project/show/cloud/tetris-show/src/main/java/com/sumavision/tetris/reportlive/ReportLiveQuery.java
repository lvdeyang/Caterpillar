package com.sumavision.tetris.reportlive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportLiveQuery {
    @Autowired
    private ReportLiveDao monitorDao;

    public Page<ReportLivePo> findAll(int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<ReportLivePo> monitors = monitorDao.findAll(page);
		return monitors;
	}
}
