package com.sumavision.tetris.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.HashSetWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;


@Service
@Transactional(rollbackFor = Exception.class)
public class SetService {

	@Autowired
	SetDAO setDao;
	
	public SetPO add(
			Long userId,
			String start, 
			String end,
			String curDate,
			String scheName) throws Exception{
		
		SetPO set = new SetPO();
		set.setUserId(userId);
		set.setStart(DateUtil.parse(start,"HH:mm"));
		set.setEnd(DateUtil.parse(end,"HH:mm"));
		set.setUpdateTime(new Date());
		set.setCurDate(DateUtil.parse(curDate,"yyyy-MM-dd"));
        set.setScheName(scheName);
		setDao.save(set);
		

		return set;
	}

}
