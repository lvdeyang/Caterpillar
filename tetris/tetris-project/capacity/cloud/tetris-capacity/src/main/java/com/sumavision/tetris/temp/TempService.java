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
			int x,int y,String ratio,String name, int rate,int frame,String type,String filePath 
			) throws Exception{
		
		TempPo temp = new TempPo();
		temp.setX(x);
		temp.setY(y);
		temp.setRate(rate);
		temp.setRatio(ratio);
		temp.setFrame(frame);
		temp.setName(name);
		temp.setType(type);
		temp.setFilePath(filePath);
		tempDao.save(temp);
		return temp;
	}
	

	 
	public TempPo edit(
			TempPo temp, 
			int x,int y,String ratio,String name, int rate,int frame,String type,String filePath ) throws Exception{
		
		temp.setX(x);
		temp.setY(y);
		temp.setRate(rate);
		temp.setRatio(ratio);
		temp.setFrame(frame);
		temp.setName(name);
		temp.setType(type);
		temp.setFilePath(filePath);
		tempDao.save(temp);
		return temp;
	}
	


	public void remove(TempPo temp) throws Exception{
		
		tempDao.delete(temp);
	}
	
	
	@Autowired
	private GlsDao glsDao;
	
	public GlsPo glsadd(
			String content,
		    int x,
		    int y,
		    int width,
		    int height,
		    String backgroundColor,
		    String fontColor,
		    int fontSize,
		    int rollSpead,
		    String fontFamily,
		    String trackType,
		    String logoPath,
		    int type,
		    Long tempId) throws Exception{
		
		GlsPo gls=new GlsPo();
		gls.setBackgroundColor(backgroundColor);
		gls.setContent(content);
		gls.setFontColor(fontColor);
		gls.setFontFamily(fontFamily);
		gls.setFontSize(fontSize);
		gls.setHeight(height);
		gls.setLogoPath(logoPath);
		gls.setRollSpead(rollSpead);
		gls.setTempId(tempId);
		gls.setTrackType(trackType);
		gls.setWidth(width);
		gls.setX(x);
		gls.setY(y);
		gls.setType(type);
		glsDao.save(gls);
		return gls;
	}
	

	 
	public GlsPo glsedit(
			GlsPo gls, 
			String content,
		    int x,
		    int y,
		    int width,
		    int height,
		    String backgroundColor,
		    String fontColor,
		    int fontSize,
		    int rollSpead,
		    String fontFamily,
		    String trackType,
		    String logoPath,
		    int type,
		    Long tempId) throws Exception{
		
		gls.setBackgroundColor(backgroundColor);
		gls.setContent(content);
		gls.setFontColor(fontColor);
		gls.setFontFamily(fontFamily);
		gls.setFontSize(fontSize);
		gls.setHeight(height);
		gls.setLogoPath(logoPath);
		gls.setRollSpead(rollSpead);
		gls.setTempId(tempId);
		gls.setTrackType(trackType);
		gls.setWidth(width);
		gls.setX(x);
		gls.setY(y);
		gls.setType(type);
		glsDao.save(gls);
		return gls;
	}
	


	public void glsremove(GlsPo gls) throws Exception{
		
		glsDao.delete(gls);
	}
	
}
