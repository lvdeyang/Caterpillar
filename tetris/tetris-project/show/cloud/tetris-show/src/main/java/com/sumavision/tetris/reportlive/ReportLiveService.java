package com.sumavision.tetris.reportlive;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.user.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportLiveService {

    @Autowired
    private ReportLiveQuery reportLiveQuery;
    @Autowired
    private ReportLiveDao reportLiveDao;

    public ReportLivePo add(
			UserVO user, 
			String name, 
			String moniterIds,
			int cinterval) throws Exception{
		
		ReportLivePo reportLive = new ReportLivePo();
		reportLive.setName(name);
	    reportLive.setMoniterIds(moniterIds);
	    reportLive.setCinterval(cinterval);
		reportLiveDao.save(reportLive);
		return reportLive;
	}
	

	 
	public ReportLivePo edit(
			ReportLivePo reportLive, 
			String name, 
			String moniterIds,
			int cinterval) throws Exception{
		
		reportLive.setName(name);
		reportLive.setMoniterIds(moniterIds);
	    reportLive.setCinterval(cinterval);
		reportLiveDao.save(reportLive);
		
		return reportLive;
	}
	


	public void remove(ReportLivePo monitor) throws Exception{
		
		reportLiveDao.delete(monitor);
	}

}
