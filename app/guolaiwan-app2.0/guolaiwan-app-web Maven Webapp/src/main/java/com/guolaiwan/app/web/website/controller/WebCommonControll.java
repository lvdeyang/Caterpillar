package com.guolaiwan.app.web.website.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.ArticleVO;
import com.guolaiwan.app.web.admin.vo.ClassVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ArticleDAO;
import com.guolaiwan.bussiness.admin.dao.ClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ClassPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

@Controller
@RequestMapping("/web")
public class WebCommonControll extends WebBaseControll{
	
	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;
	
	@Autowired
	private ClassDAO conn_Class;
	@Autowired
	private ArticleDAO conn_article;
	@Autowired
	private ActivityDAO conn_activity;
	
	@RequestMapping("/top")
	public ModelAndView commontop(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		long comId = getComIdSession();
		//获取模块(显示;无排序)
		List<ModularPO> modulars = conn_modular.findByComIsv(comId);
		List<ModularVO> modularsVO=ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);
		for (ModularVO modular : modularsVO) {
			//获取模块下的分类(模块、显示;顺序排序)
			List<ModularClassPO> classList = conn_modularClass.findByModular(modular.getModularCode());
			//获取模块下的产品(城市、显示、模块;无排序);
			modular.setModularClasses(classList);
		}
		//活动
		//活动
		List<ActivityPO> activitys = conn_activity.findByCom(comId);
		List<ActivityVO> _activitys = 
				ActivityVO.getConverter(ActivityVO.class).convert(activitys, ActivityVO.class);
		
		
		strMap.put("user", GetUserInfo());
		strMap.put("modulars",modularsVO);
		strMap.put("activitys",_activitys);
		ModelAndView mv = new ModelAndView("web/common/head",strMap);
		return mv;
	
	}
	
	
	@RequestMapping("/foot")
	public ModelAndView commonfoot() throws Exception {
		
		Map<String, Object> strMap=new HashMap<String, Object>();
		List<ClassPO> classes = conn_Class.findAll();
		List<ClassVO> classesvo=ClassVO.getConverter(ClassVO.class).convert(classes, ClassVO.class);
		for (ClassVO classvo : classesvo) {
			
			String[] fields={"classUUid","articleEnable"};
			Object[] values={classvo.getUuid(),1};
			
			List<ArticlePO> articles =conn_article.findByFields(fields, values);
			classvo.setArticles(articles);
		}
		
		strMap.put("classes",classesvo);
		
		ModelAndView mv = new ModelAndView("web/common/foot",strMap);
		return mv;
	
	}
	
	
	@RequestMapping("/ltop")
	public ModelAndView commonltop(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		strMap.put("user", GetUserInfo());
		ModelAndView mv = new ModelAndView("web/common/lhead",strMap);
		return mv;
	
	}
	
}
