package com.guolaiwan.app.web.publicnum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.LuckDrawRecordVO;
import com.guolaiwan.app.web.publicnum.util.CommonUtil;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.LuckDrawContactDao;
import com.guolaiwan.bussiness.admin.dao.LuckDrawDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.LuckDrawContact;
import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;
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
	
	
    @RequestMapping("/sendRedPacket")
    public ModelAndView sendRedPacket(HttpServletRequest request){
    	ModelAndView mv = new ModelAndView("luckdraw/package");
    	
    	HttpSession session = request.getSession();
        UserInfoPO userInfoPO =conn_userinfo.get(Long.parseLong(session.getAttribute("userId").toString()));
    	if(userInfoPO.getFirstTime()==1){
    		mv.addObject("status","感谢关注,老用户不要太贪心哦~");
    		return mv;
    	}
    	mv.addObject("status","感谢您关注过来玩，收下大红包~");
    	userInfoPO.setFirstTime(1);
    	conn_userinfo.save(userInfoPO);
    	
        try{
            
            //公众号的appid
            String appid = WXContants.AppId;
            /**
             * 根据APPID获取access_token
             * 我的access_token是做了一个定时器，每隔两个小时刷新一次access_token的值，
             * 并且保存在redis当中（需要详情的话请留言）。
             */
            String access_token = "";
            //发给谁，该用户的openid
            String openid = userInfoPO.getUserOpenID();
            //红包的值，最低100分
            Integer redValue = 100;
            //开始发送红包

            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            /** 当前时间 yyyyMMddHHmmss */
            String currTime = CommonUtil.getCurrTime();
            /** 8位日期 */
            String strTime = currTime.substring(8, currTime.length());
            /** 四位随机数 */
            String strRandom = CommonUtil.buildRandom(4) + "";
            //商户订单号
            parameters.put("mch_billno",strTime + strRandom);
            /** 商户号 */
            String mch_id = WXContants.MchId;
            parameters.put("mch_id", mch_id);
            /** 随机字符串 */
            parameters.put("nonce_str", CommonUtil.getNonceStr());
            /** 公众号APPID */
            parameters.put("wxappid", appid);
            /** 商户名称 */
            String mch_name = "过来玩";
            parameters.put("send_name",mch_name);
            /** 用户openid */
            parameters.put("re_openid",openid);
            /** 付款金额 */
            parameters.put("total_amount",redValue);
            /** 红包发放总人数 */
            parameters.put("total_num",1);
            /** 红包祝福语 */
            parameters.put("wishing","感谢您关注过来玩");
            /** 调用接口的机器Ip地址 */
            parameters.put("client_ip",request.getRemoteAddr());
            /** 活动名称 */
            String activityName = "关注领红包";
            parameters.put("act_name",activityName);
            /** 备注 */
            parameters.put("remark","江山父老能容我，不使人间造孽钱。");
            /** 场景id  发放红包使用场景，红包金额大于200时必传
             * PRODUCT_1:商品促销 PRODUCT_2:抽奖 PRODUCT_4:企业内部福利  PRODUCT_5:渠道分润 */
            //parameters.put("scene_id","PRODUCT_2");
            /** 资金授权商户号 */
            //parameters.put("consume_mch_id","");
            /** 活动信息  资金授权商户号，服务商替特约商户发放时使用*/
            //parameters.put("risk_info","");
            /** MD5进行签名，必须为UTF-8编码，注意上面几个参数名称的大小写 */
            String api_key = WXContants.AppKey;
            String sign = CommonUtil.createSign("UTF-8", parameters,api_key);
            String requestJsonStr = JSON.toJSONString(parameters);
            parameters.put("sign", sign);//
            /** 生成xml结构的数据，用于统一下单接口的请求 */
            String requestXML = CommonUtil.getRequestXml(parameters);
            /**
             * 读取证书
             * 
             */
            CloseableHttpClient httpclient = null;
            Map<String,String> result = new HashMap<String,String>();
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                String pathname = "/usr/sbin/guolaiwan/tomcat/apache-tomcat-7.0.85-windows-x64/apache-tomcat-7.0.85/webapps/guolaiwan/WEB-INF/classes/apiclient_cert.p12";
                FileInputStream instream = new FileInputStream(new File(pathname)); //此处为证书所放的绝对路径
                try {
                    keyStore.load(instream, mch_id.toCharArray());
                } finally {
                    instream.close();
                }
                // Trust own CA and all self-signed certs
                SSLContext sslcontext = SSLContexts.custom()
                        .loadKeyMaterial(keyStore, mch_id.toCharArray())
                        .build();
                // Allow TLSv1 protocol only
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                        sslcontext,
                        new String[]{"TLSv1"},
                        null,
                        SSLConnectionSocketFactory.getDefaultHostnameVerifier());//SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
                httpclient = HttpClients.custom()
                        .setSSLSocketFactory(sslsf)
                        .build();
            }
            catch (Exception e){
              
                e.printStackTrace();
            }
            try {
                String requestUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
                HttpPost httpPost = new HttpPost(requestUrl);
                StringEntity reqEntity  = new StringEntity(requestXML, "utf-8");
                // 设置类型
                reqEntity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(reqEntity);
               
                CloseableHttpResponse response = httpclient.execute(httpPost);
                try {
                    HttpEntity entity = response.getEntity();
                    System.out.println(response.getStatusLine());
                    if (entity != null) {
                        // 从request中取得输入流
                        InputStream inputStream = entity.getContent();
                        // 读取输入流
                        SAXReader reader = new SAXReader();
                        Document document = reader.read(inputStream);
                        // 得到xml根元素
                        Element root = document.getRootElement();
                        // 得到根元素的所有子节点
                        List<Element> elementList = root.elements();
                        // 遍历所有子节点
                        for (Element e : elementList)
                        {
                            result.put(e.getName(), e.getText());
                        }
                        // 释放资源
                        inputStream.close();
                    }
                    EntityUtils.consume(entity);
                }
                finally {
                    if(response!=null) {
                        response.close();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    httpclient.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
           
            //假如发送成功的话，保存发送的信息
            if(result.get("return_msg").equals("发放成功")) {
                return mv;
                }
            else {
                return mv;
            }
        }
        catch (Exception e){
            return mv;
        }
    }
	
	
}
