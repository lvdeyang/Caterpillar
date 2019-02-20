package com.guolaiwan.app.web.admin.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.LogVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.bussiness.admin.dao.LogDAO;
import com.guolaiwan.bussiness.admin.po.LogPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;

import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping("/admin/log")
public class LogController extends BaseController{

	@Autowired
	private LogDAO conn_log;

	//同时添加
	@RequestMapping(value="/logInfo",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		int count = conn_log.getCountByPage();
		strMap.put("count",count);
		int allpages = GetAllPages(count, 10);
		strMap.put("allpages", GetAllPages(count, 10));
		ModelAndView mv = new ModelAndView("admin/log/list",strMap);
		return mv;
	}


	@ResponseBody
	@RequestMapping(value="/logList1.do",method= RequestMethod.POST)
	public String upload(HttpServletRequest request) throws Exception{
		//创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String folderName = sdf.format(d);  //文件名
		
		//写数据库
		LogPO log = new LogPO();
		log.setId(1l);
		log.setUpdateTime(d);
		conn_log.save(log);
		return "success";
	}

	// 异步读取。。。
	@ResponseBody
	@RequestMapping(value="/logList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int pagecurr) throws Exception {
		List<LogPO> listpo = conn_log.GetListbyPage(pagecurr,10);
		List<LogVO> listvo = LogVO.getConverter(LogVO.class).convert(listpo, LogVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}
	


	//	long  startTime=System.currentTimeMillis();
	//	long  endTime=System.currentTimeMillis();
	//	System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
}
