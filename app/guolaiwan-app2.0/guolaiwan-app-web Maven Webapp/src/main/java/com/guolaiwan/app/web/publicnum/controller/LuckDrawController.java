package com.guolaiwan.app.web.publicnum.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.LuckDrawRecordVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.LuckDrawContactDao;
import com.guolaiwan.bussiness.admin.dao.LuckDrawDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.LuckDrawContact;
import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
//portal
@Controller
@RequestMapping("/luckdraw")
public class LuckDrawController extends WebBaseControll {

	
	@RequestMapping(value = "/index")
	public ModelAndView luckdraw(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		HttpSession session = request.getSession();
        UserInfoPO userInfoPO=conn_userinfo.get(Long.parseLong(session.getAttribute("userId").toString()));
        LuckDrawContact contact=conn_luckcontact.getByField("userId", userInfoPO.getId());
        int glassnum = conn_luckdraw.countGodlike(2);
        int ticketnum = conn_luckdraw.countGodlike(1);
        Date now=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //是否中奖
        LuckDrawRecord record=conn_luckdraw.getLuckRecordByUser(userInfoPO.getId());
        if(record!=null){
        	mv = new ModelAndView("luckdraw/luckresult");
        	mv.addObject("result","godloveu");
        	mv.addObject("useit", record.getUseit());
        	mv.addObject("uuid", record.getUuid());
        	mv.addObject("id", record.getId());
        	mv.addObject("drawProductId", record.getDrawProductId());
        	return mv;
        }
    	//活动没开始
    	if (now.getDate()==28) {
    		mv = new ModelAndView("luckdraw/luckresult");
			mv.addObject("result","notstart");
			return mv;
		}
    	//活动结束
    	if (now.getDate()!=29&&now.getDate()!=30&&now.getDate()!=31&&now.getDate()!=1) {
    		mv = new ModelAndView("luckdraw/luckresult");
    		mv.addObject("result","over");
    		return mv;
		}
    	//今日活动未开始
    	if (now.getHours()<=9) {
    		mv = new ModelAndView("luckdraw/luckresult");
    		mv.addObject("result","time");
        	return mv;
		}
    	//今日结束
    	if (now.getHours()>20) {
    		if (now.getDate()==1) {
    			mv = new ModelAndView("luckdraw/luckresult");
        		mv.addObject("result","over");
        		return mv;
			}
    		mv = new ModelAndView("luckdraw/luckresult");
    		mv.addObject("result","todayover");
        	return mv;
		}
        if(contact==null){
        	//奖品抽没了
            if (glassnum>=250&&ticketnum>=100) {
            	if (now.getDate()==1) {
        			mv = new ModelAndView("luckdraw/luckresult");
            		mv.addObject("result","over");
            		return mv;
    			}
            	mv = new ModelAndView("luckdraw/luckresult");
    	        mv.addObject("result","nullprize");
            	return mv;
    		}
        	mv = new ModelAndView("luckdraw/luckmsg");
        	return mv;
        }
        //今天抽过了
        LuckDrawRecord todayRecord=conn_luckdraw.getuserTodayRecord(userInfoPO.getId());
        if(todayRecord!=null){
        	mv = new ModelAndView("luckdraw/luckresult");
        	mv.addObject("result","today");
        	return mv;
        }
        //奖品抽没了
        if (glassnum>=250&&ticketnum>=100) {
        	if (now.getDate()==1) {
    			mv = new ModelAndView("luckdraw/luckresult");
        		mv.addObject("result","over");
        		return mv;
			}
        	mv = new ModelAndView("luckdraw/luckresult");
	        mv.addObject("result","nullprize");
        	return mv;
		}
		mv = new ModelAndView("luckdraw/luckdraw");
		return mv;
	}
	
	@RequestMapping(value = "/index1")
	public ModelAndView luckderive(HttpServletRequest request){

		ModelAndView mv = new ModelAndView("luckdraw/luckderive");
		return mv;
		
	}
	
	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(HttpServletRequest request, int page, int limit) throws Exception {

		String sName = request.getParameter("sName");
		List<LuckDrawRecord> luckDrawRecords = conn_luckdraw.getAllRecord(77,page,limit);
		if (sName !=null) {
			luckDrawRecords = conn_luckdraw.getAllRecordByPrize(sName,page,limit);
		}

		List<LuckDrawRecordVO> luckDrawRecordVOs = LuckDrawRecordVO.getConverter(LuckDrawRecordVO.class).convert(luckDrawRecords, LuckDrawRecordVO.class);
		if (sName == null) {
			sName ="";
		}
//		for (int i = 0; i < luckDrawRecordVOs.size(); i++) {
//			if (luckDrawRecordVOs.get(i).getDrawProductId().equals("电影票")) {
//				luckDrawRecordVOs.get(i).setPhone("");
//				luckDrawRecordVOs.get(i).setUserName("");
//			}
//		}
		int count = conn_luckdraw.countAllPrizeNum(sName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", luckDrawRecordVOs);
		map.put("code", 0);
		map.put("count", count);
		return map;
	}
	
	@RequestMapping(value = "/derive/{prizeid}")
	public String derive(@PathVariable int prizeid,HttpServletRequest request,HttpServletResponse response) throws Exception{

		List<LuckDrawRecord> luckDrawRecords = conn_luckdraw.getAllRecord(prizeid,1,100000);
		String title = "中奖名单" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 设置表格标题行
//		String[] headers = new String[] { "序号", "中奖时间", "兑换码", "中奖内容", "手机号", "姓名" };
//		if (prizeid == 1) {
		String[] headers = new String[] { "序号", "中奖时间", "兑换码", "中奖内容" };
//		}
		List<Object[]> dataList = new ArrayList<Object[]>();
		if (luckDrawRecords!=null) {
			for (int i = 0; i < luckDrawRecords.size(); i++) {
				if (luckDrawRecords.get(i).getDrawProductId()!=0) {
					Object[] obj = new Object[headers.length];
					obj[1] = luckDrawRecords.get(i).getUpdateTime();
					obj[2] = luckDrawRecords.get(i).getUuid();
					if (luckDrawRecords.get(i).getDrawProductId()==1) {
						obj[3] = "电影票";
					}else if (luckDrawRecords.get(i).getDrawProductId()==2) {
						obj[3] = "眼镜";
					}
//					if (prizeid!=1) {
//						obj[4] = luckDrawRecords.get(i).getPhone();
//						obj[5] = luckDrawRecords.get(i).getUserName();
//					}
					dataList.add(obj);
				}
			} 
		}
		outputList(title, headers, dataList, response);
		return "success";
		
	}
	
	@Autowired
	private LuckDrawDao conn_luckdraw;
	@Autowired
	private LuckDrawContactDao conn_luckcontact;
	@Autowired
	private UserInfoDAO conn_userinfo;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/luckdraw/dodraw", method = RequestMethod.GET)
	public Object dodraw(HttpServletRequest request, HttpServletResponse response,int pos)
			throws Exception {
        Map<String, Object> ret=new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserInfoPO userInfoPO=conn_userinfo.get(Long.parseLong(session.getAttribute("userId").toString()));
        LuckDrawContact contact=conn_luckcontact.getByField("userId", userInfoPO.getId());
        
        int result=0;
        int resultNum=getRandom(1, 10);
        if(resultNum<=pos){
        	int secondRandom=getRandom(1, 7);
        	if(secondRandom<=2){
        		int size=conn_luckdraw.countGodlike(1);
        		if(size>=100){
        			result=0;
        		}else{
        			result=1;
        		}
        		
        	}else{
        		int size=conn_luckdraw.countGodlike(2);
        		if(size>=250){
        			result=0;
        		}else{
        			result=2;
        		}
        	}
        }
        LuckDrawRecord luckDrawRecord=new LuckDrawRecord();
        luckDrawRecord.setDrawProductId(result);
        luckDrawRecord.setPhone(contact.getPhone());
        luckDrawRecord.setUserName(contact.getUserName());
        luckDrawRecord.setUserId(userInfoPO.getId());
        luckDrawRecord.setUpdateTime(new Date());
        conn_luckdraw.save(luckDrawRecord);
        ret.put("result", result);
		return ret;
	}
	
	private int getRandom(int min, int max){
	    Random random = new Random();
	    int s = random.nextInt(max) % (max - min + 1) + min;
	    return s;
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/saveMsg", method = RequestMethod.GET)
	public Object saveMsg(HttpServletRequest request, HttpServletResponse response,String phone,String name)
			throws Exception {
        Map<String, Object> ret=new HashMap<String, Object>();
//        String newName=new String(name.getBytes("ISO-8859-1"),"utf-8");
        LuckDrawContact luckDrawContact=new LuckDrawContact();
        luckDrawContact.setPhone(phone);
        luckDrawContact.setUserName(name);
        HttpSession session = request.getSession();
        luckDrawContact.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
        conn_luckcontact.save(luckDrawContact);
		return ret;
	}
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public Object check(HttpServletRequest request, HttpServletResponse response,long id)
			throws Exception {
        Map<String, Object> ret=new HashMap<String, Object>();
        LuckDrawRecord record=conn_luckdraw.get(id);
        record.setUseit(1);
        conn_luckdraw.save(record);
		return ret;
	}
	
	//输出表
	public void outputList(String title,String headers[],List<Object[]> dataList,HttpServletResponse response) throws Exception
	{
		String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "utf-8") + "\"";
		response.setContentType("octets/stream");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", headStr);
		ServletOutputStream out = response.getOutputStream();
		// ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
		ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);// 没有标题
		ex.export(out);
	}
	
}
