package com.guolaiwan.app.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.domain.Picture;
import com.guolaiwan.app.web.admin.vo.ArticleVO;
import com.guolaiwan.app.web.admin.vo.BewriteVO;
import com.guolaiwan.bussiness.admin.dao.BewriteDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ClassPO;
import com.guolaiwan.bussiness.admin.po.BewritePO;
import com.guolaiwan.bussiness.admin.po.PicturePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/bewrite")
public class BewriteController extends BaseController {
	@Autowired
	private BewriteDAO conn_bewrite;
	@Autowired
	private PictureDAO conn_picture;

	// 显示列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getdescribes(HttpServletRequest request) {
		long picId =Long.parseLong(request.getParameter("picId"));
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("allcount", conn_bewrite.countByField("imgId", picId));
		strMap.put("picId", picId);
		ModelAndView mv = new ModelAndView("admin/bewrite/list", strMap);
		return mv;
	}

	// 添加页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addbewrite(HttpServletRequest request) {
		String picId =request.getParameter("picId");
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("picId", picId);
		ModelAndView mv = new ModelAndView("admin/bewrite/add",strMap);
		return mv;
	}

	// 异步读取
	@ResponseBody
	@RequestMapping(value = "/bewriteList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList() throws Exception {
		List<BewritePO> bewritepo = conn_bewrite.findAll();
		List<BewriteVO>  bewritevo = BewriteVO.getConverter(BewriteVO.class).convert( bewritepo, BewriteVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",  bewritevo);
		return map;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int pagecurr,long picId) throws Exception {
		List<BewritePO>  bewritepo = conn_bewrite.getPagesByImg(picId, pagecurr, 5);
		List<BewriteVO>  bewritevo = BewriteVO.getConverter(BewriteVO.class).convert( bewritepo, BewriteVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alist",  bewritevo);
		return map;
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		long imgId =Long.parseLong(request.getParameter("imgId"));
		String url = request.getParameter("url").trim();
		String miaoshu = request.getParameter("miaoshu").trim();

		BewritePO  bewrite = new BewritePO();
		 bewrite.setUrl(url);
		 bewrite.setMiaoshu(miaoshu);
		 bewrite.setImgId(imgId);
		 

		conn_bewrite.save(bewrite);
		return "success";

	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		BewritePO bewrite = conn_bewrite.get(uuid);
		conn_bewrite.delete(bewrite);
		return "success";
	}

	// 显示修改页面
	@RequestMapping(value = "/updatev", method = RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		BewritePO bewrite = conn_bewrite.get(uuid);
		strMap.put("list", bewrite);

		List<BewritePO> bewritelist = conn_bewrite.findAll();
		strMap.put("alist", bewritelist);

		ModelAndView mv = new ModelAndView("admin/bewrite/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {

		String uuid = request.getParameter("uuid").trim();
		BewritePO bewrite = conn_bewrite.get(uuid);

		String url = request.getParameter("url").trim();
		String miaoshu = request.getParameter("miaoshu").trim();

		bewrite.setUrl(url);
		bewrite.setMiaoshu(miaoshu);

		conn_bewrite.update(bewrite);

		return "success";
	}

}
