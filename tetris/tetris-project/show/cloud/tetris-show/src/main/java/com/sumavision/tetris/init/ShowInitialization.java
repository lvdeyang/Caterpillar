package com.sumavision.tetris.init;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.capacity.server.CapacityFeignService;
import com.sumavision.tetris.commons.context.SystemInitialization;
import com.sumavision.tetris.monitor.MonitorDao;
import com.sumavision.tetris.monitor.MonitorPo;
import com.sumavision.tetris.reportlive.ReportLiveDao;
import com.sumavision.tetris.reportlive.ReportLivePo;



@Service
@Transactional(rollbackFor = Exception.class)
public class ShowInitialization implements SystemInitialization{
	
	private static final Logger LOG = LoggerFactory.getLogger(ShowInitialization.class);
	@Override
	public int index() {

		return 0;
	}

	
	
	@Override
	public void init() {
		

	}

}
