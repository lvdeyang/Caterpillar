package com.sumavision.tetris.sche;

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

/**
 * 内容模板增删改操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年2月18日 下午1:31:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScheService {

	@Autowired
	ScheDAO scheDao;
	
	public SchePO add(
			UserVO user, 
			String name, 
			String start,
			String end) throws Exception{
		
		SchePO Sche = new SchePO();
		Sche.setName(name);
		Sche.setStart(DateUtil.parse(start,"HH:mm"));
		Sche.setEnd(DateUtil.parse(end,"HH:mm"));
		Sche.setUpdateTime(new Date());

		scheDao.save(Sche);
		

		return Sche;
	}
	

	 
	public SchePO edit(
			SchePO Sche, 
			String name, 
			String start,
			String end) throws Exception{
		
		Sche.setName(name);
		Sche.setStart(DateUtil.parse(start,"HH:mm"));
		Sche.setEnd(DateUtil.parse(end,"HH:mm"));
		Sche.setUpdateTime(new Date());
		scheDao.save(Sche);
		
		return Sche;
	}
	


	public void remove(SchePO Sche) throws Exception{
		
		scheDao.delete(Sche);
	}
}
