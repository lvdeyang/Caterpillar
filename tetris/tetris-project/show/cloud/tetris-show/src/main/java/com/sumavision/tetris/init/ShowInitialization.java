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

	@Autowired
	MonitorDao monitorDao;
	@Autowired
	ReportLiveDao reportliveDao;
	@Autowired
	CapacityFeignService service;
	
	 
	private Map<Long, Boolean> checkMap=new HashMap<Long, Boolean>();
	
	private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
	
	private void startSwitch(ReportLivePo reportLivePo){
		checkMap.put(reportLivePo.getId(), true);
		final ReportLivePo tempReportLive=reportLivePo;
		fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
            	int count=tempReportLive.getMoniterIds().split(",").length;
            	int index=0;
                while(checkMap.get(tempReportLive.getId())){
                	try {
                		//service.switchTask(tempReportLive.getId(), index);
                		System.out.println("监控轮询【"+tempReportLive.getId()+"】"+"切换到："+index);
                		index++;
                		if(index==count){
                			index=0;
                		}
						Thread.sleep(tempReportLive.getCinterval()*1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                checkMap.remove(tempReportLive.getId());
                //这里还要删除任务，不想写了
                System.out.println("监控轮询停止【"+reportLivePo.getId()+"】");
            }
        });
	}
	
	@Override
	public void init() {
		

		fixedThreadPool.execute(new Runnable() {
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					List<ReportLivePo> startReportLiveList=reportliveDao.findAllByIsOld(1);
					for (Long checkId : checkMap.keySet()) {
						boolean bFind=false;
						for (ReportLivePo reportLivePo : startReportLiveList) {
							if(reportLivePo.getId().equals(checkId)){
								bFind=true;
								break;
							}
						}
						if(!bFind){
							checkMap.put(checkId, false);
						}
					}
					
					
					
					List<ReportLivePo> reportLiveList=reportliveDao.findAllByIsOld(0);
					for (ReportLivePo reportLivePo : reportLiveList) {
						//新的给丫改了
						reportLivePo.setIsOld(1);
						reportliveDao.save(reportLivePo);
						
						
						String list="";
						String[] monitors=reportLivePo.getMoniterIds().split(",");
						int index=0;
						for (String monitor : monitors) {
							MonitorPo monitorPo=monitorDao.findOne(Long.parseLong(monitor));
							if(monitorPo==null){
								continue;
							}
							if(index!=0){
								list+=",";
							}
							index++;
							list+="rtsp://"+monitorPo.getUserName()+":"+monitorPo.getPassword()+"@"+
							monitorPo.getIp()+":"+monitorPo.getPort();
							
							
						}
						try {
							//service.createRtspTask(list, reportLivePo.getId());
							System.out.println("监控轮询创建【"+reportLivePo.getId()+"】");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						startSwitch(reportLivePo);
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
	
		List<ReportLivePo> reportLiveList=reportliveDao.findAll();
		for (ReportLivePo reportLivePo : reportLiveList) {
			startSwitch(reportLivePo);
			
		}
		
	}

}
