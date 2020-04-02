package com.sumavision.tetris.temp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@RestController
@RequestMapping(value = "/capacity/temp")
public class TempController {
	@Autowired
	private UserQuery userQuery;
	@Autowired
	private TempQuery tempQuery;
	@Autowired
	private TempDao tempDao;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<TempPo> page = tempQuery.findAll(currentPage, pageSize);
		List<TempPo> entities =page.getContent();
		List<TempVo> temps = TempVo.getConverter(TempVo.class).convert(entities, TempVo.class);
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", temps)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gls/list")
	public Object list(
			Long tempId,
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<GlsPo> page = tempQuery.findAllByTempId(tempId,currentPage, pageSize);
		List<GlsPo> entities =page.getContent();
		List<GlsVo> gls = GlsVo.getConverter(GlsVo.class).convert(entities, GlsVo.class);
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", gls)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alllist")
	public Object alllist(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		
		List<TempPo> entities =tempDao.findAll();
		List<TempVo> temps = TempVo.getConverter(TempVo.class).convert(entities, TempVo.class);

			
		return temps;
	}
	

	@Autowired
	TempService tempService;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			int x,int y,String ratio,String name, int rate,int frame,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		//TODO 权限校验
		TempPo tempPo =tempService.add(x,y,ratio,name,rate,frame);
		return new TempVo().set(tempPo);
	}	
	
	@Autowired
	TempDao deviceDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			int x,int y,String ratio,String name, int rate,int frame,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		TempPo tempPo = tempDao.findOne(id);
	
		
		tempPo = tempService.edit(tempPo, x,y,ratio,name,rate,frame);
		
		return new TempVo().set(tempPo);
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id) throws Exception{
		
		
		TempPo device = tempDao.findOne(id);
		
		
		tempService.remove(device);
		
		
		return null;
	}
	
	
		
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gls/add")
	public Object glsadd(
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
		    Long tempId,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		//TODO 权限校验
		GlsPo glsPo =tempService.glsadd(content,
			     x,
			     y,
			     width,
			     height,
			     backgroundColor,
			     fontColor,
			     fontSize,
			     rollSpead,
			     fontFamily,
			     trackType,
			     logoPath,
			     type,
			     tempId);
		return new GlsVo().set(glsPo);
	}	
	
	@Autowired
	GlsDao glsDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gls/edit/{id}")
	public Object glsedit(
			@PathVariable Long id,
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
		    Long tempId,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		GlsPo glsPo = glsDao.findOne(id);
	
		
		glsPo =tempService.glsedit(glsPo,content,
			     x,
			     y,
			     width,
			     height,
			     backgroundColor,
			     fontColor,
			     fontSize,
			     rollSpead,
			     fontFamily,
			     trackType,
			     logoPath,
			     type,
			     tempId);
		
		return new GlsVo().set(glsPo);
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gls/remove/{id}")
	public Object glsremove(
			@PathVariable Long id) throws Exception{
		
		
		GlsPo gls = glsDao.findOne(id);
		
		
		tempService.glsremove(gls);
		
		
		return null;
	}
	
}
