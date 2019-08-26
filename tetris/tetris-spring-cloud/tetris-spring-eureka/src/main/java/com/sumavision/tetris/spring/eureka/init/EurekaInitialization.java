package com.sumavision.tetris.spring.eureka.init;

import org.springframework.stereotype.Service;

import com.sumavision.tetris.commons.context.SystemInitialization;
import com.sumavision.tetris.spring.eureka.heartbeat.HeartbeatThread;

@Service
public class EurekaInitialization implements SystemInitialization{

	@Override
	public int index() {
		return 0;
	}

	@Override
	public void init() {
		new HeartbeatThread().start();
	}

}
