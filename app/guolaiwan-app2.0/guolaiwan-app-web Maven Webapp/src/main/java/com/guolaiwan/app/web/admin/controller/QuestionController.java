package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.guolaiwan.app.web.admin.vo.CommonProblemVO;

import com.guolaiwan.app.web.admin.vo.QuestionModularVO;
import com.guolaiwan.app.web.admin.vo.QuestionSonModularVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.CommonProblemDao;
import com.guolaiwan.bussiness.admin.dao.QuestionModularDao;
import com.guolaiwan.bussiness.admin.dao.QuestionSonModularDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.CommonProblemPO;
import com.guolaiwan.bussiness.admin.po.QuestionModularPO;
import com.guolaiwan.bussiness.admin.po.QuestionSonModularPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

@RestController
@RequestMapping("/admin/base")
public class QuestionController extends WebBaseControll {
	@Autowired
	private QuestionModularDao conn_quesMou;
	
	@Autowired
	private QuestionSonModularDao conn_ModularClass;
	
	@Autowired
	private SysConfigDAO conn_sysConfig;
	
	@Autowired
	private  CommonProblemDao conn_comPro;
	// 显示列表
	@RequestMapping(value="/question")
	public ModelAndView skipRequest(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<QuestionModularPO> modulars = conn_quesMou.findAll();
		map.put("modulars", modulars);
		return new ModelAndView("admin/question/questionHome",map);		
	}	 
	
	//添加页面
		@RequestMapping("/addv")
		public ModelAndView addModular(){
			ModelAndView mv = new ModelAndView("admin/question/add");
			return mv;
		}
		
		//添加数据
		@ResponseBody
		@RequestMapping(value="/add.do", method= RequestMethod.POST)
		public String add(HttpServletRequest request) throws Exception {
			String modularName = request.getParameter("modularName");
			String isv = request.getParameter("modularIsv");
			long id  = conn_quesMou.getMaxId();
			
			String modularCode = String.valueOf((id+2000l));
			QuestionModularPO modular = new QuestionModularPO();
			modular.setUpdateTime(new Date());
			modular.setModularCode(modularCode);
			modular.setModularName(modularName);
			if(null == isv){
				modular.setModularIsv(0);
			}else{
				modular.setModularIsv(1);
			}					
			conn_quesMou.save(modular);

			return "success";
		}
		
		//异步读取列表分页
		@ResponseBody
		@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public Map<String, Object> GetList(int page,int limit) throws Exception {
			int count = conn_quesMou.countAll();
			List<QuestionModularPO> modularpo=conn_quesMou.GetListbyPage(page, limit);
			List<QuestionModularVO> modularvo =  new QuestionModularVO().getConverter(QuestionModularVO.class).convert(modularpo, QuestionModularVO.class);
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("data", modularvo);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count",count);
			return map;
		}
		
		//改变显示状态modularList
		@ResponseBody
		@RequestMapping(value="/changeIsv.do", method= RequestMethod.POST)
		public String updateIsv(HttpServletRequest request) throws Exception {
			long id = Long.parseLong(request.getParameter("id"));
			int val = Integer.parseInt(request.getParameter("val"));
			QuestionModularPO modular =  conn_quesMou.get(id);
			modular.setModularIsv(val);
			conn_quesMou.update(modular);
			return "success";
		}
		
		//改变子模块显示状态
		@ResponseBody
		@RequestMapping(value="/changeIsv2.do", method= RequestMethod.POST)
		public String updateSonIsv(HttpServletRequest request) throws Exception {
			long id = Long.parseLong(request.getParameter("id"));
			int val = Integer.parseInt(request.getParameter("val"));
			QuestionSonModularPO modular =  conn_ModularClass.get(id);
			modular.setClassIsv(val);
			conn_ModularClass.update(modular);
			return "success";
		}
		
		//显示修改页面
		@RequestMapping(value="/updatev",method= RequestMethod.GET)
		public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

			Map<String, Object> strMap=new HashMap<String, Object>();
			String uuid =request.getParameter("uuid");
			QuestionModularPO modular = conn_quesMou.get(uuid);
			QuestionModularVO _modular = new QuestionModularVO().set(modular);			
			strMap.put("list", _modular);
			ModelAndView mv = new ModelAndView("admin/question/Modify");
			mv.addAllObjects(strMap);
			return mv;
		}
		
		//修改数据
		@ResponseBody
		@RequestMapping(value="/update.do", method= RequestMethod.POST)
		public String update(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			QuestionModularPO modular = conn_quesMou.get(uuid);
			String modularName = request.getParameter("modularName");
			
			modular.setModularName(modularName);				
		    modular.setModularIsv(1);										
			modular.setUpdateTime(new Date());
			conn_quesMou.update(modular);
			return "success";
		}
		
		//删除数据
		@ResponseBody
		@RequestMapping(value="/del.do", method= RequestMethod.POST)
		public String del(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			QuestionModularPO modular = conn_quesMou.get(uuid);
			conn_quesMou.delete(modular);
			return "success";
		}
		
		
		//添加子页面
		@RequestMapping(value="/sonMoudle",method= RequestMethod.GET)
		public ModelAndView getModulars(HttpServletRequest request,String uuid){			
			Map<String, Object> strMap = new HashMap<String, Object>();
			List<QuestionModularPO> modulars = conn_quesMou.findAll();			
			strMap.put("modulars", modulars);
			strMap.put("uuid", uuid);
			ModelAndView mv = new ModelAndView("admin/question/sonHomepage",strMap);
			return mv;
		}
		
		//异步读取子列表列表分页
		@ResponseBody
		@RequestMapping(value="/list2.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public Map<String, Object> GetList2(int page,int limit,HttpServletRequest request)throws Exception{
			//图片访问公共地址 
		    SysConfigPO sysConfig = conn_sysConfig.getSysConfig();			
            String uuid = (String) request.getParameter("uuid");
            QuestionModularPO  qModularPO = conn_quesMou.get(uuid);           
            String mCode = qModularPO.getModularCode();
			List<QuestionSonModularPO> modularclasspo=conn_ModularClass.GetListbyPage(page, limit,mCode);
			List<QuestionSonModularVO> modularclassvo = QuestionSonModularVO.getConverter(QuestionSonModularVO.class).convert(modularclasspo, QuestionSonModularVO.class);
            int count = conn_ModularClass.countBySelect(mCode);
			for (QuestionSonModularVO modularClassVO2 : modularclassvo) {
				//图片处理
				modularClassVO2.setModularPic(sysConfig.getWebUrl()+modularClassVO2.getModularPic());
				QuestionModularPO modularPO = conn_quesMou.getModularByCode(modularClassVO2.getClassmodularCode());
				if(modularPO!=null){
					modularClassVO2.setClassmodularCode(modularPO.getModularName());
				}else{
					modularClassVO2.setClassmodularCode("");
				}
			}

			Map<String, Object> map= new HashMap<String, Object>();
			map.put("data", modularclassvo);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count",count);
			return map;
		}
		//添加子模块页面
		@RequestMapping("/addv2")
		public ModelAndView addModularClass(HttpServletRequest request,  HttpServletResponse response ,String uuid){
			System.out.println("uuid:"+uuid);
			ModelAndView mView = new ModelAndView("admin/question/addsonmodular");
			QuestionModularPO list=conn_quesMou.get(uuid);
			mView.addObject("list",list);
			return mView;
		}
		
		//删除子模块数据
		@ResponseBody
		@RequestMapping(value="/del2.do", method= RequestMethod.POST)
		public String del2(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			QuestionSonModularPO modularclass = conn_ModularClass.get(uuid);
			conn_ModularClass.delete(modularclass);
			return "success";
		}
		
		//显示修改子模块页面
		@RequestMapping(value="/updatev2",method= RequestMethod.GET)
		public ModelAndView UpdateView2(HttpServletRequest request) throws Exception{

			Map<String, Object> strMap=new HashMap<String, Object>();
			String uuid =request.getParameter("uuid");
			QuestionSonModularPO modularclass = conn_ModularClass.get(uuid);
			QuestionSonModularVO _modular = new QuestionSonModularVO().set(modularclass);
			String weburl = conn_sysConfig.getSysConfig().getWebUrl();
			List<QuestionModularPO> modulars=conn_quesMou.findByField("modularCode",_modular.getClassmodularCode());
			strMap.put("list", _modular);
			strMap.put("modularss", modulars);
			strMap.put("weburl", weburl);
			ModelAndView mv = new ModelAndView("admin/question/sonModify",strMap);

			return mv;
		}
		
		//添加子模块数据
		@ResponseBody
		@RequestMapping(value="/add2.do", method= RequestMethod.POST)
		public String addSonDate(HttpServletRequest request) throws Exception {
			String classmodularCode = request.getParameter("classmodularCode");
			String className = request.getParameter("className");
			String isv = request.getParameter("classIsv");
			String modularPic  = request.getParameter("modularPic");
			int classIsv = -1;
			if(null == isv){
				classIsv = 0;
			}else{
				classIsv = 1; 
			}
			
			long id  = conn_ModularClass.getMaxId();
			String classCode = String.valueOf((id+2000l));
			int classSort =Integer.parseInt(request.getParameter("classSort"));
			QuestionSonModularPO modularclass = new QuestionSonModularPO();
			modularclass.setUpdateTime(new Date());
			modularclass.setClassCode(classCode);
			modularclass.setClassmodularCode(classmodularCode);
			modularclass.setClassName(className);
			modularclass.setClassIsv(classIsv);
			modularclass.setClassSort(classSort);
			modularclass.setModularPic(modularPic);
			conn_ModularClass.save(modularclass);
			return "success";
		}
		
		//修改子模块数据
		@ResponseBody
		@RequestMapping(value="/update2.do", method= RequestMethod.POST)
		public String updateSonMoudle(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			QuestionSonModularPO modularclass = conn_ModularClass.get(uuid);						
			String className = request.getParameter("className");		
			String modularPic  = request.getParameter("modularPic");		
			int classSort =Integer.parseInt(request.getParameter("classSort"));			
			modularclass.setClassName(className);
			modularclass.setClassSort(classSort);
			if(null != modularPic && modularPic.length() >0){
				System.out.println("modularPic:"+modularPic);
		     modularclass.setModularPic(modularPic);	
			}			
			modularclass.setUpdateTime(new Date());

			conn_ModularClass.update(modularclass);

			return "success";
		}
		
		//跳转 子模块下的问题页面
		@RequestMapping(value="/questionText")
		public ModelAndView getQuestionText(String uuid){
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("uuid", uuid);
			 return new ModelAndView("admin/question/questionText",map);						
		}

		//问题查询列表  		
		@RequestMapping(value="/problemlist")
		public Map<String, Object> getProblemList(int page,int limit,HttpServletRequest request,String uuid) throws Exception{
			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
			//获取二级模块
	      	QuestionSonModularPO qSonModular = conn_ModularClass.get(uuid);
			 // 查询对应模块的的 问题		
	      	String[] fields = {"modularClassCode"};
	      	Object[] values = {Integer.valueOf(qSonModular.getClassCode())};
			List<CommonProblemPO> cProblem =  conn_comPro.findByFields(fields, values, page, limit);
		    List<CommonProblemVO> cVos = new CommonProblemVO().getConverter(CommonProblemVO.class)
			      .convert(cProblem, CommonProblemVO.class);
		    int count = conn_comPro.countAll();
		    for(CommonProblemVO vo:cVos){
		    	  //判断是否存在图片
		    	 if(null != vo.getProblemImg()){
		    		 vo.setProblemImg(sysConfig.getWebUrl()+vo.getProblemImg()); 
		    	 }
		    	QuestionSonModularPO modularPO = conn_ModularClass.getModularByCode(String.valueOf(vo.getModularClassCode()));
				if(modularPO!=null){
					vo.setModularClassName(modularPO.getClassName());
				}else{
					vo.setModularClassName("");
				}
		    	 
		    }
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("data", cVos);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count",count);
			return map;			
			
		}
		
		//添加问题
		@RequestMapping(value="/addproblem")
		public ModelAndView addProblem(String uuid){
			ModelAndView mView = new ModelAndView("admin/question/addProblem");
			//获取对应模块内容
			QuestionSonModularPO modularclass = conn_ModularClass.get(uuid);
			mView.addObject("modularClass", modularclass.getClassCode());
			mView.addObject("modularName", modularclass.getClassName()); 
			return mView;
		}
		
	  //添加问题保存
      @RequestMapping(value="/problem.do")
      public String problemMessage(HttpServletRequest request){
    	 String name = request.getParameter("name");
    	 String problemImg = request.getParameter("shopPic");
    	 String isView = request.getParameter("modularIsv");
    	 String sort = request.getParameter("Sort");
    	 String Introduce = request.getParameter("productIntroduce");
    	 String modularCode = request.getParameter("modularCode");
    	 //查询父级模块   	 
    	 List<QuestionSonModularPO> qSonModularPO = conn_ModularClass.findByField("classCode",modularCode);
    	 List<QuestionModularPO> qModular =  conn_quesMou.findByField("modularCode", qSonModularPO.get(0).getClassmodularCode());
    	 CommonProblemPO cProblem = new CommonProblemPO();
    	 cProblem.setIsView(Integer.valueOf(isView));
    	 cProblem.setProblemImg(problemImg);
    	 cProblem.setModularClassName(qSonModularPO.get(0).getClassName());
    	 cProblem.setModularClassCode(Integer.valueOf(modularCode));
    	 cProblem.setModularName(qModular.get(0).getModularName());
    	 cProblem.setModularCode(Integer.valueOf(qModular.get(0).getModularCode()));
    	 cProblem.setSort(Long.parseLong(sort));
    	 cProblem.setProblemdescribe(Introduce);
    	 cProblem.setProblemName(name);
    	 conn_comPro.save(cProblem);
    	 return "success";
      }   
      
      //常见问题删除 
      @RequestMapping(value="/problemDel.do")
      public String  deleteProblemMessage( String uuid){			
    	  CommonProblemPO cProblem =  conn_comPro.get(uuid);
    	  conn_comPro.delete(cProblem);
    	  return "success";
      }
      
      //常见问题修改
      @RequestMapping("/upCommPro")
      public ModelAndView updateCommPro(String uuid) throws Exception{   
    	  SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
    	  ModelAndView mView = new ModelAndView("admin/question/questionModify");
    	  List<CommonProblemPO> cProblem =  conn_comPro.findByField("uuid", uuid);
    	  List<CommonProblemVO> vProblemVOs =  new CommonProblemVO().getConverter(CommonProblemVO.class)
    	           .convert(cProblem, CommonProblemVO.class);
    	  for(CommonProblemVO vo:vProblemVOs){    		  
    		  vo.setProblemImg(sysConfig.getWebUrl()+vo.getProblemImg());
    	  }
    	  mView.addObject("Problem", vProblemVOs.get(0));
    	 return mView;
      }
      
      //常见问题修改 
      @RequestMapping(value= "/upProblem.do" )
      public String updateProblem(HttpServletRequest request,String uuid){    	  
    	 String name = request.getParameter("name");
     	 String problemImg = request.getParameter("shopPic");
     	 String sort = request.getParameter("Sort");
     	 String Introduce = request.getParameter("productIntroduce");
    	  
    	 CommonProblemPO cProblem = conn_comPro.get(uuid);
    	 if( null != problemImg  && problemImg.length() >0){
    		 cProblem.setProblemImg(problemImg);    		 
    	 }
    	 if(null != Introduce && Introduce.length() >0){
    		 cProblem.setProblemdescribe(Introduce); 
    	 }
    	 cProblem.setProblemName(name); 
    	 cProblem.setSort(Long.parseLong(sort));
    	 conn_comPro.update(cProblem);
    	 return "success";
      }
      //修改常见问题是否显示问题
    		@ResponseBody
    		@RequestMapping(value="/changeIsView.do", method= RequestMethod.POST)
    		public String changeIsView(HttpServletRequest request) throws Exception {
    			long id = Long.parseLong(request.getParameter("id"));
    			int val = Integer.parseInt(request.getParameter("val"));
    			CommonProblemPO modular =  conn_comPro.get(id);
    			modular.setIsView(val);
    			conn_comPro.update(modular);
    			return "success";
    		}      
} 
