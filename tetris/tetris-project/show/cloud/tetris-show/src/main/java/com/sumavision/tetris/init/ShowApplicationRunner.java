package com.sumavision.tetris.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.capacity.server.CapacityFeignService;
import com.sumavision.tetris.monitor.MonitorDao;
import com.sumavision.tetris.monitor.MonitorPo;
import com.sumavision.tetris.reportlive.ReportLiveDao;
import com.sumavision.tetris.reportlive.ReportLivePo;
@Component
public class ShowApplicationRunner implements ApplicationRunner {
	@Autowired
	MonitorDao monitorDao;
	@Autowired
	ReportLiveDao reportliveDao;
	@Autowired
	CapacityFeignService service;
	
	 
	private Map<Long, Boolean> checkMap=new HashMap<Long, Boolean>();
	
	private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
	
	
	//测试代码2
	/*@Override
	public void run(ApplicationArguments args) throws Exception {
		final ReportLivePo tempReportLive=reportliveDao.findAll().get(0);
		fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
            	int count=tempReportLive.getMoniterIds().split(",").length;
            	int index=0;
                while(true){
                	try {
                		service.switchTask(10000+tempReportLive.getId(), index);
                		System.out.println("监控轮询【"+tempReportLive.getId()+"】"+"切换到："+index);
                		index++;
                		if(index==count){
                			index=0;
                		}
						Thread.sleep(10000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }


            }
        });
	}*/
	
	//正式代码
	
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
                		service.switchTask(10000+tempReportLive.getId(), index);
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
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		/*List<ReportLivePo> reportLiveList=reportliveDao.findAll();
		for (ReportLivePo reportLivePo : reportLiveList) {
			if(reportLivePo.getIsOld()==0){
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
					//list+="rtsp://"+monitorPo.getUserName()+":"+monitorPo.getPassword()+"@"+
					//monitorPo.getIp()+":"+monitorPo.getPort();
					list+="rtmp://"+monitorPo.getIp()+"/live/"+monitorPo.getUserName();

					
				}
				try {
					service.createRtmpTask(list, 10000+reportLivePo.getId());
					System.out.println("监控轮询创建【"+reportLivePo.getId()+"】");	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			startSwitch(reportLivePo);
			
		}
		

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
							//list+="rtsp://"+monitorPo.getUserName()+":"+monitorPo.getPassword()+"@"+
							//monitorPo.getIp()+":"+monitorPo.getPort();
							list+="rtmp://"+monitorPo.getIp()+"/live/"+monitorPo.getUserName();
							
							
						}
						try {
							service.createRtspTask(list, 10000+reportLivePo.getId());
							System.out.println("监控轮询创建【"+reportLivePo.getId()+"】");
							
							startSwitch(reportLivePo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});*/
	
	}

}
