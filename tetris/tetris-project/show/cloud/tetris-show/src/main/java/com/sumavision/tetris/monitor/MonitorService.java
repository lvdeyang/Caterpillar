package com.sumavision.tetris.monitor;


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
public class MonitorService {

    @Autowired
    private MonitorQuery monitorQuery;
    @Autowired
    private MonitorDao monitorDao;

    public MonitorPo add(
			UserVO user, 
			String name, 
			String ip,
			int port,
			String userName,
			String password) throws Exception{
		
		MonitorPo monitor = new MonitorPo();
		monitor.setName(name);
		monitor.setIp(ip);
		monitor.setPort(port);
		monitor.setUserName(userName);
		monitor.setPassword(password);
		monitorDao.save(monitor);
		return monitor;
	}
	

	 
	public MonitorPo edit(
			MonitorPo monitor, 
			String name, 
			String ip,
			int port,
			String userName,
			String password) throws Exception{
		
		monitor.setName(name);
		monitor.setIp(ip);
		monitor.setPort(port);
		monitor.setUserName(userName);
		monitor.setPassword(password);
		monitorDao.save(monitor);
		
		return monitor;
	}
	


	public void remove(MonitorPo monitor) throws Exception{
		
		monitorDao.delete(monitor);
	}

}
