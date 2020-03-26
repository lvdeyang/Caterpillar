package com.sumavision.tetris.temp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.user.UserVO;

@Component
public class TempService {
	@Autowired
	private TempDao tempDao;
	
	public TempPo add(
			int x,int y,String ratio,String name, int rate,int frame 
			) throws Exception{
		
		TempPo temp = new TempPo();
		temp.setX(x);
		temp.setY(y);
		temp.setRate(rate);
		temp.setRatio(ratio);
		temp.setFrame(frame);
		temp.setName(name);
		tempDao.save(temp);
		return temp;
	}
	

	 
	public TempPo edit(
			TempPo temp, 
			int x,int y,String ratio,String name, int rate,int frame ) throws Exception{
		
		temp.setX(x);
		temp.setY(y);
		temp.setRate(rate);
		temp.setRatio(ratio);
		temp.setFrame(frame);
		temp.setName(name);
		tempDao.save(temp);
		return temp;
	}
	


	public void remove(TempPo temp) throws Exception{
		
		tempDao.delete(temp);
	}
}
