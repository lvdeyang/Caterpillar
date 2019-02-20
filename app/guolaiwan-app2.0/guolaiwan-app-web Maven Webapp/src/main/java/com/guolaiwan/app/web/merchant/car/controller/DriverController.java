package com.guolaiwan.app.web.merchant.car.controller;

import java.io.File;
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

import com.guolaiwan.app.web.merchant.car.vo.DriverVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.merchant.car.dao.DriverDAO;
import com.guolaiwan.bussiness.merchant.car.po.DriverPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/merchant/driver")

public class DriverController extends BaseController{
	@Autowired
	private DriverDAO conn_Driver;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private MerchantDAO conn_merchant;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getCarousel(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("allcount", conn_Driver.GetCountByPage());

		ModelAndView mv = new ModelAndView("merchant/car/driver/list",strMap);
		return mv;
	}
	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addDriver(){

		ModelAndView mv = new ModelAndView("merchant/car/driver/add");
		return mv;
	}

	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	public Map<String,Object> GetList(int pagecurr) throws Exception{

		//绑定商家根据登录名和商家登录名相等
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);


		List<DriverPO> Driverpo = conn_Driver.findByField("merchant",merchant,pagecurr,5);
		List<DriverVO> Drivervo = DriverVO.getConverter(DriverVO.class).convert(Driverpo, DriverVO.class);

		String webUrl = conn_sysConfig.getSysConfig().getWebUrl();
		for (DriverVO driver : Drivervo) {
			driver.setPhoto(webUrl+driver.getPhoto());
			driver.setDrivingBook(webUrl+driver.getDrivingBook());
		}

		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", Drivervo);
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		//绑定商家根据登录名和商家登录名相等
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);
		if(merchant==null){
			return "norool";
		}

		String photo = request.getParameter("photo");
		String name  = request.getParameter("name");
		String IDnumber = request.getParameter("IDnumber");
		String drivingBook = request.getParameter("drivingBook");
		String bankNumber = request.getParameter("bankNumber");
		String carNumber  =  request.getParameter("carNumber");
		String carModel   =  request.getParameter("carModel");
		int passenger = Integer.parseInt(request.getParameter("passenger"));

		DriverPO Driverhas = conn_Driver.getByField("iDnumber", IDnumber);
		if(Driverhas!=null){
			return "iDnumberHas";
		}

		DriverPO Driver = null;
		Driver = new DriverPO();
		Driver.setPhoto(photo);
		Driver.setName(name);
		Driver.setiDnumber(IDnumber);
		Driver.setDrivingBook(drivingBook);
		Driver.setBankNumber(bankNumber);
		Driver.setCarNumber(carNumber);
		Driver.setCarModel(carModel);
		Driver.setPassenger(passenger);
		Driver.setMerchant(merchant);

		conn_Driver.save(Driver);
		return "success";


	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		DriverPO Driver = conn_Driver.get(uuid);
		MerchantPO merchant = Driver.getMerchant();
		//解关联
		Driver.setMerchant(null);
		merchant.getDrivers().remove(Driver);
		conn_Driver.delete(Driver);

		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		DriverPO Driverp = conn_Driver.get(uuid);
		DriverVO Driver = new DriverVO().set(Driverp);

		String photo =  conn_sysConfig.getSysConfig().getWebUrl()+Driver.getPhoto();
		String drivingBook = conn_sysConfig.getSysConfig().getWebUrl()+Driver.getDrivingBook();
		strMap.put("list", Driver);
		strMap.put("photo", photo);
		strMap.put("drivingBook", drivingBook);
		ModelAndView mv = new ModelAndView("merchant/car/driver/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		//绑定商家根据登录名和商家登录名相等
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);
		if(merchant==null){
			return "norool";
		}

		String uuid =request.getParameter("uuid");
		DriverPO Driver = conn_Driver.get(uuid);

		String photo = request.getParameter("photo");
		String name  = request.getParameter("name");
		String IDnumber = request.getParameter("IDnumber");
		String drivingBook = request.getParameter("drivingBook");
		String bankNumber = request.getParameter("bankNumber");
		String carNumber  =  request.getParameter("carNumber");
		String carModel   =  request.getParameter("carModel");
		int passenger = Integer.parseInt(request.getParameter("passenger"));


		Driver.setPhoto(photo);
		Driver.setName(name);
		Driver.setiDnumber(IDnumber);
		Driver.setDrivingBook(drivingBook);
		Driver.setBankNumber(bankNumber);
		Driver.setCarNumber(carNumber);
		Driver.setCarModel(carModel);
		Driver.setPassenger(passenger);
		Driver.setMerchant(merchant);
		conn_Driver.update(Driver);

		return "success";

	}

	//上传正面照
	@ResponseBody
	@RequestMapping(value="/peopic.do",method= RequestMethod.POST)
	public Map<String,Object> uppeopic(@RequestParam("file") CommonsMultipartFile file) throws Exception{			
		Map<String, Object> map= new HashMap<String, Object>();
		//创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String folderName = "car/people/"+sdf.format(d)+"/";  //文件名
		String path=conn_sysConfig.getSysConfig().getFolderUrl()+folderName;

		//文件名
		String fileName = file.getOriginalFilename();  
		String newName = DateUtil.getCurrentDateTime()+fileName.substring(fileName.lastIndexOf(".") ); //时间戳+后缀名
		String foldepath = folderName+newName;

		File folder =new File(path);
		if(folder.exists() ==false){     //如果路径不存在
			if(folder.getParentFile().exists()==false){
				map.put("code", "1");
				map.put("error", "文件路径错误！");
				return map;
			}
			folder.mkdirs();
		}
		//上传
		File newFile=new File(path+newName);
		file.transferTo(newFile);           //写
		map.put("code", "0");
		map.put("path", foldepath);
		//写数据库
		return map;
	}



	//上传驾驶本
	@ResponseBody
	@RequestMapping(value="/bookpic.do",method= RequestMethod.POST)
	public Map<String,Object> upbookpic(@RequestParam("file") CommonsMultipartFile file) throws Exception{			
		Map<String, Object> map= new HashMap<String, Object>();
		//创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String folderName = "car/drivingBook/"+sdf.format(d)+"/";  //文件名
		String path=conn_sysConfig.getSysConfig().getFolderUrl()+folderName;

		//文件名
		String fileName = file.getOriginalFilename();  
		String newName = DateUtil.getCurrentDateTime()+fileName.substring(fileName.lastIndexOf(".") ); //时间戳+后缀名
		String foldepath = folderName+newName;

		File folder =new File(path);
		if(folder.exists() ==false){     //如果路径不存在
			if(folder.getParentFile().exists()==false){
				map.put("code", "1");
				map.put("error", "文件路径错误！");
				return map;
			}
			folder.mkdirs();
		}
		//上传
		File newFile=new File(path+newName);
		file.transferTo(newFile);           //写
		map.put("code", "0");
		map.put("path", foldepath);
		//写数据库
		return map;
	}



}


