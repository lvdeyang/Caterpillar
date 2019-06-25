package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.bussiness.admin.dao.ColumnDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.ColumnType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/column")
public class ColumnController extends BaseController {

	@RequestMapping(value = "/wxlist", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = null;
		mav = new ModelAndView("admin/column/modularlist");
		return mav;
	}

	@Autowired
	private ColumnDAO conn_column;

	@Autowired
	private MerchantDAO conn_merchant;

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> getList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ColumnPO> polist = conn_column.findAll();
		List<MerchantPO> list = new ArrayList<MerchantPO>();
		for (ColumnPO cpo : polist) {
			list.add(conn_merchant.getMerchantById(cpo.getMerchantId()).get(0));
		}
		map.put("data", list);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", 0);
		return map;
	}

	@RequestMapping(value = "/child/list", method = RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView mav = null;
		String code = request.getParameter("uuid");
		ModularPO modular = conn_modular.getModularByCode(code);
		request.setAttribute("modularCode", code);
		request.setAttribute("modularId", modular.getId());
		mav = new ModelAndView("admin/column/wxlist");
		return mav;
	}

	@Autowired
	private ProductDAO conn_product;

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/addvlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit, HttpServletRequest request) throws Exception {
		String modularId = request.getParameter("modularId");
		ModularPO modular = conn_modular.getModularById(Long.parseLong(modularId));
		List<MerchantPO> listpo = new ArrayList<MerchantPO>();
		List<ColumnPO> columnByCode = conn_column.getColumnByCode(modular.getModularCode());
		for(ColumnPO cpo : columnByCode){
			if (cpo.getSortindex()== 0 ) {
				ColumnPO columnBy = conn_column.getsortindex(modular.getModularCode()).get(0);
				if (columnBy.getSortindex()  == 0 && columnBy != null) {
					cpo.setSortindex(1L);
					conn_column.saveOrUpdate(cpo);
					conn_column.flush();
				}else {
					cpo.setSortindex(columnBy.getSortindex()+1);
					conn_column.saveOrUpdate(cpo);
					conn_column.flush();
				}
			}
			List<MerchantPO> tempMerchantPOs=conn_merchant.getMerchantById(cpo.getMerchantId());
			if(tempMerchantPOs!=null && !tempMerchantPOs.isEmpty()){
				listpo.add(tempMerchantPOs.get(0));
			}
		}
		int allcount = columnByCode.size();
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		for (MerchantVO merchantVO : listvo) {
			String[] field = { "productMerchantID" };
			Object[] value = { merchantVO.getId() };
			int productCount = conn_product.countByFields(field, value);
			merchantVO.setProductCount(productCount);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	public String addColumn(HttpServletRequest request) {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		ColumnPO col = conn_column.getColumnByMerchantId(Long.parseLong(id));
		if (col==null) {
			ColumnPO cpo = new ColumnPO();
			cpo.setMerchantId(Long.parseLong(id));
			cpo.setType(ColumnType.RECOMMEND);
			cpo.setCode(code);
			conn_column.save(cpo);
			return "success";
		}else {
			return "err";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getAddList(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		Long comId = getLoginInfo().getComId();
		String mName = request.getParameter("mName");
		strMap.put("comId", comId);
		strMap.put("shopName", mName);
		strMap.put("modularCode", request.getAttribute("modularCode"));
		strMap.put("shopAuditState", ShopAuditStateType.T);
		List<MerchantPO> listpo = conn_merchant.findMerchant(strMap, page, limit);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		int count = conn_merchant.countMerchant(strMap);
		for (MerchantVO merchantVO : listvo) {
			String[] field = { "productMerchantID" };
			Object[] value = { merchantVO.getId() };
			int productCount = conn_product.countByFields(field, value);
			merchantVO.setProductCount(productCount);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/del.do" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request){
		String id = request.getParameter("id");
		ColumnPO column = conn_column.getColumnByMerchantId(Long.parseLong(id));
		if (column!=null) {
			conn_column.delete(column);
			return "success";
		}else {
			return "err";
		}
	}
	
	@Autowired
	private ModularDAO conn_modular;
	
	@ResponseBody
	@RequestMapping(value = "/modularlist.do" , method = RequestMethod.POST)
	public Map<String, Object> getModularList(int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModularPO> list = conn_modular.getfindAll(page, limit);
		int allcount = conn_modular.countAll();
		map.put("data", list);
		map.put("code", "0");
		map.put("count", allcount);
		map.put("msg", "");
		return map;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/modification.do" , method = RequestMethod.POST)
	public Map<String, Object> getModiFication(long id, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		if ("T".equals(state)) {
			ModularPO list = conn_modular.getByField("id",id);
			long index  =  list.getSortindex();
			ModularPO Sortindex = conn_modular.getByField("sortindex",index-1);
			if (Sortindex != null && list.getComId() == Sortindex.getComId() ){
				list.setSortindex(index-1);
				conn_modular.saveOrUpdate(list);
				conn_modular.flush();
				Sortindex.setSortindex(index);
				conn_modular.saveOrUpdate(Sortindex);
				conn_modular.flush();
			}else {
		     map.put("data", "已是优先显示");
			 return  map;
			}
		}else {
			ModularPO list = conn_modular.getByField("id",id);
			long index  =  list.getSortindex();
			ModularPO Sortindex = conn_modular.getByField("sortindex",index+1);
			if (Sortindex != null && list.getComId() == Sortindex.getComId()) {
				list.setSortindex(index+1);
				conn_modular.saveOrUpdate(list);
				conn_modular.flush();
				Sortindex.setSortindex(index);
				conn_modular.saveOrUpdate(Sortindex);
				conn_modular.flush();
			}else {
		     map.put("data", "没有下一条");
			 return  map;
			}
			
		}
		map.put("data", "success");
		return map;
	}
   //TODO	
	@ResponseBody
	@RequestMapping(value = "/shopmodification.do" , method = RequestMethod.POST)
	public Map<String, Object> getShopModiFication(long merchantId, String state,String code){
		Map<String, Object> map = new HashMap<String, Object>();
		if ("T".equals(state)) { // 上架
			ColumnPO columnBy = conn_column.getColumnByCode(merchantId,code);
			long index  =  columnBy.getSortindex();
			ColumnPO column = conn_column.getByCode(code,index-1);
			if (column != null){
				columnBy.setSortindex(index-1);
				conn_column.saveOrUpdate(columnBy);
				conn_column.flush();
				column.setSortindex(index);
				conn_column.saveOrUpdate(column);
				conn_column.flush();
			}else {
				map.put("data", "已是优先显示");
				return  map;
			}
		}else { // 下架
			ColumnPO columnBy = conn_column.getColumnByCode(merchantId,code);
			long index  =  columnBy.getSortindex();
			ColumnPO column = conn_column.getByCode(code,index+1);
			if (column != null){
				columnBy.setSortindex(index+1);
				conn_column.saveOrUpdate(columnBy);
				conn_column.flush();
				column.setSortindex(index);
				conn_column.saveOrUpdate(column);
				conn_column.flush();
			}else {
				map.put("data", "已是最尾");
				return  map;
			}
		}
		map.put("data", "success");
		return map;
	}
	
	@RequestMapping(value = "/addv" , method = RequestMethod.GET)
	public ModelAndView toAdd(){
		ModelAndView mav = null;
		mav = new ModelAndView("admin/column/add");
		return mav;
	}
	
}