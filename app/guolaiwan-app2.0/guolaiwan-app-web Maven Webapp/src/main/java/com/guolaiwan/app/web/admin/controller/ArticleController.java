
package com.guolaiwan.app.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ArticleVO;

import com.guolaiwan.bussiness.admin.dao.ArticleDAO;
import com.guolaiwan.bussiness.admin.dao.ClassDAO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ClassPO;


import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController{
	@Autowired
	private ArticleDAO conn_article;
	@Autowired 
	private ClassDAO conn_Class;
	//显示列表
			@RequestMapping(value="/list",method= RequestMethod.GET)
			public ModelAndView getArticles(HttpServletRequest request){
				Map<String, Object> strMap = new HashMap<String, Object>();
				strMap.put("allcount", conn_article.GetCountByPage());
				ModelAndView mv = new ModelAndView("admin/article/list",strMap);
				return mv;
			     }
			//添加页面
			@RequestMapping("/addv")
			public String addArticle(HttpServletRequest request,  HttpServletResponse response,Model model){
				List<ClassPO> list=conn_Class.findAll();
				
				model.addAttribute("list",list);
				return "admin/article/add";
			}
			//异步读取
			@ResponseBody
			@RequestMapping(value="/classList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
			public Map<String, Object> getList() throws Exception {
				List<ArticlePO> articlepo = conn_article.findAll();
				List<ArticleVO> articlevo = ArticleVO.getConverter(ArticleVO.class).convert(articlepo, ArticleVO.class);
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("list", articlevo);
				return map;
				}
			//异步读取列表分页
			@ResponseBody
			@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
			public Map<String, Object> GetList(int pagecurr) throws Exception {
				List<ArticlePO> articlepo=conn_article.GetListbyPage(pagecurr, 5);
				List<ArticleVO> articlevo = ArticleVO.getConverter(ArticleVO.class).convert(articlepo, ArticleVO.class);
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("alist", articlevo);
				return map;
			}
			//添加数据
			@ResponseBody
			@RequestMapping(value="/add.do", method= RequestMethod.POST)
			public String add(HttpServletRequest request) throws Exception {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                    
				String className = request.getParameter("className").trim();
		         String classUUid = request.getParameter("classUUid").trim();
				String articleName = request.getParameter("articleName").trim();
				String articleCon = request.getParameter("articleCon").trim();
				
				
				int articleSort=Integer.parseInt(request.getParameter("articleSort"));
				
				int articleEnable =1;
				if(request.getParameter("articleEnable")==null)
					articleEnable=0;
				String articleDate = request.getParameter("articleDate");
				ArticlePO article= new ArticlePO();
				article.setClassName(className);
			   article.setClassUUid(classUUid);
				article.setArticleName(articleName);
				article.setArticleCon(articleCon);
				
				article.setArticleSort(articleSort);
				article.setArticleEnable(articleEnable);
				article.setArticleDate(sdf.parse(articleDate));
				conn_article.save(article);
				return "success";
				
			}
			//删除数据
			@ResponseBody
			@RequestMapping(value="/del.do", method= RequestMethod.POST)
			public String del(HttpServletRequest request) throws Exception {
				String uuid =request.getParameter("uuid");
				ArticlePO Article = conn_article.get(uuid);
				conn_article.delete(Article);
				return "success";
}
			//显示修改页面
			@RequestMapping(value="/updatev",method= RequestMethod.GET)
			public ModelAndView UpdateView(HttpServletRequest request) throws Exception{
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				Map<String, Object> strMap=new HashMap<String, Object>();
				String uuid =request.getParameter("uuid").trim();
				
				ArticlePO ArticleP = conn_article.get(uuid);
				ArticleVO Article = new ArticleVO().set(ArticleP);
				strMap.put("Article", Article);
			
				List<ClassPO> list=conn_Class.findAll();
				strMap.put("list", list);
				
				
				List<ArticlePO> Articlelist=conn_article.findAll();
				strMap.put("alist", Articlelist);
				
				ModelAndView mv = new ModelAndView("admin/article/Modify");
				mv.addAllObjects(strMap);
			
				return mv;
			}
			//修改数据
			@ResponseBody
			@RequestMapping(value="/update.do", method= RequestMethod.POST)
			public String update(HttpServletRequest request) throws Exception {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String uuid =request.getParameter("uuid").trim();
				ArticlePO article = conn_article.get(uuid);
		 
	            
				String className = request.getParameter("className").trim();
                String classUUid = request.getParameter("classUUid").trim();
				String articleName = request.getParameter("articleName").trim();
				String articleCon = request.getParameter("articleCon").trim();
				
				
				int articleSort=Integer.parseInt(request.getParameter("articleSort"));
				
				int articleEnable =1;
				if(request.getParameter("articleEnable")==null)
					articleEnable=0;
				String articleDate = request.getParameter("articleDate");
				article.setClassName(className);
                article.setClassUUid(classUUid);
				article.setArticleName(articleName);
				article.setArticleCon(articleCon);
				
				article.setArticleSort(articleSort);
				article.setArticleEnable(articleEnable);
				article.setArticleDate(sdf.parse(articleDate));
						
				
				conn_article.update(article);
				
				return "success";
			}

			}
