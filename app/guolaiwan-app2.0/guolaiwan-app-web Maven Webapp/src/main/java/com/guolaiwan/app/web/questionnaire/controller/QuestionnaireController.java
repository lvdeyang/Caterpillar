package com.guolaiwan.app.web.questionnaire.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.guolaiwan.app.web.publicnum.util.CommonUtil;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import com.guolaiwan.bussiness.admin.po.VoteOptionsPo;
import com.guolaiwan.bussiness.admin.po.VotePicsPo;
import com.guolaiwan.bussiness.admin.po.VoteProductPO;
import com.guolaiwan.bussiness.questionnaire.dao.QuestionBankDAO;
import com.guolaiwan.bussiness.questionnaire.dao.QuestionnaireDAO;
import com.guolaiwan.bussiness.questionnaire.dao.RedPacketRecordDAO;
import com.guolaiwan.bussiness.questionnaire.po.QuestionBankPO;
import com.guolaiwan.bussiness.questionnaire.po.QuestionnairePO;
import com.guolaiwan.bussiness.questionnaire.po.RedPacketRecordPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;

@Controller
@RequestMapping("/admin/questionnaire")
public class QuestionnaireController extends BaseController {
	@Autowired
	private QuestionnaireDAO conn_questionnaire;
	@Autowired
	private QuestionBankDAO conn_questionbankdao;
	@Autowired
	private RedPacketRecordDAO conn_redpacketrecord;
	@Autowired
	private UserInfoDAO conn_userinfo;
	
	// 答题问卷列表页面
	@ResponseBody
	@RequestMapping(value = "/questionnairelist")
	public ModelAndView questionnaireList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/questionnaire/questionnairelist");
		return mv;
	}
	
	//查询所有问卷
	@ResponseBody
	@RequestMapping(value = "/getquestionnaires", method = RequestMethod.POST)
	public Map<String, Object> getList(HttpServletRequest request,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_questionnaire.countAll();
		List<QuestionnairePO> questionnaires = conn_questionnaire.findAll();
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", questionnaires);
		return strMap;
	}
	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/delquestionnaires", method = RequestMethod.POST)
	public String delQuestionnaires(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		conn_questionbankdao.deleteByQuestionnaireId(id);
		conn_questionnaire.delete(id);
		return "success";
	}
	
	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/editquestionnaires", method = RequestMethod.POST)
	public String editQuestionnaires(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		QuestionnairePO questionnairePO = conn_questionnaire.get(id);
		String field = request.getParameter("field");
		if (field.equals("title")) {
			String title = request.getParameter("value");
			questionnairePO.setTitle(title);
			conn_questionnaire.save(questionnairePO);
			return "success";
		}
		else if(field.equals("onthertitle")) {
			String onthertitle = request.getParameter("value");
			questionnairePO.setOnthertitle(onthertitle);
			conn_questionnaire.save(questionnairePO);
			return "success";
		}
		else if(field.equals("questionnum")){
			String questionnum = request.getParameter("value");
			questionnairePO.setQuestionnum(Integer.parseInt(questionnum));
			conn_questionnaire.save(questionnairePO);
			return "success";
		}
		else if(field.equals("questiontime")){
			String questiontime = request.getParameter("value");
			questionnairePO.setQuestiontime(Integer.parseInt(questiontime));
			conn_questionnaire.save(questionnairePO);
			return "success";
		}
		return "error";
	}
	
	// 选择背景图图片
	@ResponseBody
	@RequestMapping(value = "/backgroundpic", method = RequestMethod.POST)
	public String addbackgroundpic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long id = Long.parseLong(request.getParameter("id"));
		QuestionnairePO questionnairePO = conn_questionnaire.get(id);
		questionnairePO.setQuestionnairePic(pic);
		conn_questionnaire.saveOrUpdate(questionnairePO);
		return "success";
	}
	
	// 添加新的问卷
	@ResponseBody
	@RequestMapping(value = "/apendquestionnaire", method = RequestMethod.POST)
	public String apendQuestionnaire(HttpServletRequest request) throws Exception {
		QuestionnairePO PO=new QuestionnairePO();
		PO.setTitle("请修改此处");
		PO.setOnthertitle("请修改此处");
		conn_questionnaire.save(PO);
		return "success";
	}
	
	// 前台问答首页
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gotoquestionnaire")
	public ModelAndView getoQuestionnaire(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mobile/questionnaire/responseHome");
		return mv;
	}
	
	//前台查询所有问卷
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/findallquestionnaire", method = RequestMethod.POST)
	public List<QuestionnairePO> findAllQuestionnaire(HttpServletRequest request){
		List<QuestionnairePO> findAll = conn_questionnaire.findAll();
		return findAll;
	}
	
	// 答题页面
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gotoanswer")
	public ModelAndView getoProductDetails(HttpServletRequest request) {
		long questionnaireId=Long.parseLong(request.getParameter("id"));
		QuestionnairePO questionnaire = conn_questionnaire.get(questionnaireId);
		ModelAndView mv = new ModelAndView("mobile/questionnaire/response");
		mv.addObject("questionnaireId", questionnaireId);
		mv.addObject("timelimit", questionnaire.getQuestiontime());
		mv.addObject("questionnum", questionnaire.getQuestionnum());
		return mv;
	}
	
	//获得题目
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getquestion", method = RequestMethod.POST)
	public List<QuestionBankPO> getQuestion(HttpServletRequest request){
		long questionnaireId=Long.parseLong(request.getParameter("questionnaireId"));
		QuestionnairePO questionnaire = conn_questionnaire.get(questionnaireId);
		int limit=questionnaire.getQuestionnum();
		int count = conn_questionbankdao.countByQId(questionnaireId);
		int page=(int) (Math.random()*(count/limit));
		List<QuestionBankPO> question = conn_questionbankdao.findByQId(questionnaireId,page,limit);
		return question;
	}
	
	// 投票规则页面
	@RequestMapping(value = "/gotoquestionrule")
	public ModelAndView goToQuestionRule(HttpServletRequest request) {
		String questionnaireId=request.getParameter("questionnaireId");
		QuestionnairePO questionnairePO = conn_questionnaire.get(Long.parseLong(questionnaireId));
		String questionnairerole = questionnairePO.getQuestionnairerole();
		ModelAndView mv = new ModelAndView("admin/questionnaire/addquestionrule");
		if(questionnairerole!=null&&questionnairerole!=""){
			mv.addObject("questionnairerole", questionnairerole);
		}
		mv.addObject("questionnaireId", questionnaireId);
		return mv;
	}
	
	//添加投票规则
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/addquestionrule", method = RequestMethod.POST)
	public String addVoteRule(HttpServletRequest request) throws Exception {
		String questionnaireId = request.getParameter("questionnaireId");
		String questionrule = request.getParameter("questionrule");
		QuestionnairePO questionnairePO = conn_questionnaire.get(Long.parseLong(questionnaireId));
		questionnairePO.setQuestionnairerole(questionrule);
		conn_questionnaire.saveOrUpdate(questionnairePO);
		return "success";
	}
	
	//获得规则（页面）
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getquestionrole", method = RequestMethod.POST)
	public QuestionnairePO getQuestionRole(HttpServletRequest request){
		long questionnaireId=Long.parseLong(request.getParameter("questionnaireId"));
		QuestionnairePO questionnaire = conn_questionnaire.get(questionnaireId);
		return questionnaire;
	}
	
	
	
	/*private long amount=1;*/
    @RequestMapping("/sendRedPacket")
    public ModelAndView sendRedPacket(HttpServletRequest request){
    	ModelAndView mv = new ModelAndView("luckdraw/package");
    	/*if(amount<=0){
    		mv.addObject("status","感谢参与,红包已经被抢完~");
    		return mv;
    	}*/
    	Random random=new Random();
		int thisturn=random.nextInt(490)+10;
		/*amount-=thisturn;*/
    	HttpSession session = request.getSession();
    	long questionnaireId=Long.parseLong(request.getParameter("questionnaireId"));
    	long userId = Long.parseLong(session.getAttribute("userId").toString());
    	System.out.println(userId);
    	UserInfoPO userInfoPO = conn_userinfo.get(userId);
        int count = conn_redpacketrecord.countByUId(userId,questionnaireId);
    	if(count>=2){
    		mv.addObject("status","感谢参与,老用户不要太贪心哦~");
    		return mv;
    	}
    	mv.addObject("status","感谢您的参与，收下大红包~");
    	RedPacketRecordPO po=new RedPacketRecordPO();
    	po.setQuestionnaireId(questionnaireId);
    	po.setUserId(userId);
    	conn_redpacketrecord.save(po);
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
            Integer redValue = thisturn;
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
            parameters.put("wishing","感谢您的参与");
            /** 调用接口的机器Ip地址 */
            parameters.put("client_ip",request.getRemoteAddr());
            /** 活动名称 */
            String activityName = "答题领红包";
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
