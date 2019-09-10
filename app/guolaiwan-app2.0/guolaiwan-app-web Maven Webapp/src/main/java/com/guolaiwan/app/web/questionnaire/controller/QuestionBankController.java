package com.guolaiwan.app.web.questionnaire.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.po.VoteOptionsPo;
import com.guolaiwan.bussiness.questionnaire.dao.QuestionBankDAO;
import com.guolaiwan.bussiness.questionnaire.po.QuestionBankPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/questionbank")
public class QuestionBankController extends BaseController {
	
	@Autowired
	private QuestionBankDAO conn_questionbankdao;
	
	// 答题库列表页面
	@ResponseBody
	@RequestMapping(value = "/questionbanklist")
	public ModelAndView questionbanklist(HttpServletRequest request,String questionnaireId) {
		ModelAndView mv = new ModelAndView("admin/questionnaire/questionbanklist");
		mv.addObject("questionnaireId", questionnaireId);
		return mv;
	}
	
	//查询所有问卷
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getquestionbank")
	public Map<String, Object> getquestionbanks(HttpServletRequest request,int page, int limit) throws Exception {
		long questionnaireId = Long.parseLong(request.getParameter("questionnaireId"));
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_questionbankdao.countByQId(questionnaireId);
		List<QuestionBankPO> questionbank = conn_questionbankdao.findByQId(questionnaireId,page,limit);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", questionbank);
		return strMap;
	}
	
	// 添加新的问卷
	@ResponseBody
	@RequestMapping(value = "/apendquestionbank", method = RequestMethod.POST)
	public String apendQuestionnaire(HttpServletRequest request) throws Exception {
		QuestionBankPO PO=new QuestionBankPO();
		conn_questionbankdao.save(PO);
		return "success";
	}
	
	// 添加新题目页面
	@RequestMapping(value = "/addquestion")
	public ModelAndView addOptions(HttpServletRequest request,String questionnaireId) {
		ModelAndView mv = new ModelAndView("admin/questionnaire/addquestion");
		mv.addObject("questionnaireId", questionnaireId);
		return mv;
	}
	
	// 添加新题目数据
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/insertquestion", method = RequestMethod.POST)
	public String insertOptions(HttpServletRequest request) throws Exception {
		String questionnaireId = request.getParameter("questionnaireId");
		String questiontitle = request.getParameter("questiontitle");
		String questiontype = request.getParameter("questiontype");
		String options1 = request.getParameter("options1");
		String options2 = request.getParameter("options2");
		String options3 = request.getParameter("options3");
		String options4 = request.getParameter("options4");
		String options5 = request.getParameter("options5");
		String answer = request.getParameter("answer");
		String alloption="";
		if(options1!=null&&options1!="")alloption=alloption+options1;
		if(options2!=null&&options2!="")alloption=alloption+"#"+options2;
		if(options3!=null&&options3!="")alloption=alloption+"#"+options3;
		if(options4!=null&&options4!="")alloption=alloption+"#"+options4;
		if(options5!=null&&options5!="")alloption=alloption+"#"+options5;
		QuestionBankPO question=new QuestionBankPO();
		question.setOptions(alloption);
		question.setQuestionnaireId(Long.parseLong(questionnaireId));
		question.setQuestiontype(questiontype);
		question.setTopic(questiontitle);
		question.setAnswer(answer);
		conn_questionbankdao.save(question);
		return "success";
	}
	//修改题目数据
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/updatequestion", method = RequestMethod.POST)
	public String updateQuestion(HttpServletRequest request) throws Exception {
		String questionId = request.getParameter("questionId");
		String questiontitle = request.getParameter("questiontitle");
		String questiontype = request.getParameter("questiontype");
		String options1 = request.getParameter("options1");
		String options2 = request.getParameter("options2");
		String options3 = request.getParameter("options3");
		String options4 = request.getParameter("options4");
		String options5 = request.getParameter("options5");
		String answer = request.getParameter("answer");
		String alloption="";
		if(options1!=null&&options1!="")alloption=alloption+options1;
		if(options2!=null&&options2!="")alloption=alloption+"#"+options2;
		if(options3!=null&&options3!="")alloption=alloption+"#"+options3;
		if(options4!=null&&options4!="")alloption=alloption+"#"+options4;
		if(options5!=null&&options5!="")alloption=alloption+"#"+options5;
		QuestionBankPO question = conn_questionbankdao.get(Long.parseLong(questionId));
		question.setOptions(alloption);
		question.setQuestiontype(questiontype);
		question.setTopic(questiontitle);
		question.setAnswer(answer);
		conn_questionbankdao.save(question);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delquestion", method = RequestMethod.POST)
	public String delQuestion(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		conn_questionbankdao.delete(id);
		return "success";
	}
	
	// 答题问卷列表页面
	@ResponseBody
	@RequestMapping(value = "/infoquestion")
	public ModelAndView questionnaireList(HttpServletRequest request) {
		long questionId = Long.parseLong(request.getParameter("id"));
		QuestionBankPO questionPO = conn_questionbankdao.get(questionId);
		ModelAndView mv = new ModelAndView("admin/questionnaire/updatequestion");
		mv.addObject("questiontitle", questionPO.getTopic());
		mv.addObject("questiontype", questionPO.getQuestiontype());
		mv.addObject("answer", questionPO.getAnswer());
		mv.addObject("options", questionPO.getOptions());
		mv.addObject("questionId", questionId);
		return mv;
	}
}
