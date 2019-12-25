package com.sumavision.tetris.capacity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumavision.tetris.business.common.dao.TaskInputDAO;
import com.sumavision.tetris.business.common.po.TaskInputPO;

@Service
public class InitService {
	
	private static final Logger LOG = LoggerFactory.getLogger(InitService.class);
	
	@Autowired
	private TaskInputDAO taskInputDao;

	public void init() throws Exception{
		
		Thread deleteInputThread = new Thread(new Runnable() {
			
			@Override
			public void run() {

				while(true){
					try {
						Thread.sleep(60000);
					} catch (Exception e){
						LOG.error("清除输入线程被打断！", e);
					}
					
					try {
						
						//1小时清除
						List<TaskInputPO> inputs = taskInputDao.findByCount(0);
						if(inputs != null && inputs.size() > 0){
							Date date = new Date();
							List<TaskInputPO> needRemoveInputs = new ArrayList<TaskInputPO>();
							for(TaskInputPO input: inputs){
								if((date.getTime() - input.getUpdateTime().getTime()) > 1000 * 60 * 60){
									needRemoveInputs.add(input);
								}
							}
							
							if(needRemoveInputs.size() > 0){
								taskInputDao.deleteInBatch(needRemoveInputs);
							}
						}

					} catch (Exception e) {
						LOG.error("清除输入线程执行异常！", e);
					}
				}
				
			}
		});
		
		LOG.info("初始化清除输入线程！");
		deleteInputThread.start();
		
	}
	
}
