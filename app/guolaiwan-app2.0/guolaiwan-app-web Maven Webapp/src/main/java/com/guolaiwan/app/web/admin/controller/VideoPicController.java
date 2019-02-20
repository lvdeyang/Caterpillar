package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.VideoPicVO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/videopic")
public class VideoPicController extends BaseController {
	@Autowired
	private VideoPicDAO conn_videoPic;
	@Autowired
	private VPRelDAO conn_vPRel;
	@Autowired
	private VPCommentDAO conn_vPComment;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getArticles(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("allcount", conn_videoPic.GetCountByPage());
		ModelAndView mv = new ModelAndView("admin/videopic/list",strMap);
		return mv;
	     }
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(
			Integer page,
			String userId,
			String vType,
			String sName) throws Exception {
		
		//判断类型
		VideoPicType t=null;
		if(vType!=null && vType.length()>0){
		t=VideoPicType.fromString(vType);
		}
		
		//用户
		Long userIds=null;
		UserInfoPO seuser = null;
		if(userId!=null&&userId.length()>0){
			userIds=Long.parseLong(userId);
		}
		
		List<VideoPicPO> _VideoPicPO=conn_videoPic.findAllDesc(page, 10,t,sName,userIds);
		List<VideoPicVO> _VideoPicVO = VideoPicVO.getConverter(VideoPicVO.class).convert(_VideoPicPO, VideoPicVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", _VideoPicVO);
		return map;
	}
	
	//删除
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String videoPicDel(
			
			HttpServletRequest request, 
			HttpServletResponse response)
					throws Exception 
	{
		
		Long vpId = Long.parseLong(request.getParameter("vpid"));
		
	    
		//删除点赞列表
		//conn_vPRel.deleteByField("videoPic_id",vpId);
		conn_vPRel.deleteByFile(vpId);
		//conn_videoPic.deleteByHql(hql);
		//删除评论列表
		conn_vPComment.deleteByFile(vpId);
		//删除主表
		conn_videoPic.delete(vpId);
		return "success";
	}
		
	
}
