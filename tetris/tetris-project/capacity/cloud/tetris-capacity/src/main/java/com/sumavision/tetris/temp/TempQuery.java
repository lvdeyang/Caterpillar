package com.sumavision.tetris.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;



@Component
public class TempQuery {
	@Autowired
	TempDao tempDao;
	public Page<TempPo> findAll(int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<TempPo> temps = tempDao.findAll(page);
		return temps;
	}
	@Autowired
	GlsDao glsDao;
	public Page<GlsPo> findAllByTempId(Long tempId,int currentPage, int pageSize){
		Pageable page = new PageRequest(currentPage-1, pageSize);
		Page<GlsPo> gls = glsDao.findAllByTempId(tempId,page);
		return gls;
	}
}
