package com.sumavision.tetris.init;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sumavision.tetris.commons.context.SystemInitialization;



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
