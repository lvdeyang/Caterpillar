package com.guolaiwan.app.web.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.guolaiwan.app.web.admin.vo.OrderInfoEchartVO;
import com.guolaiwan.app.web.admin.vo.ParkEchartVO;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.operation.dao.WebsiteRecordDAO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/mreport")
public class ReportController extends BaseController {

	@Autowired
	private UserInfoDAO userInfoDao;
	@Autowired
	private WebsiteRecordDAO websiteRecordDAO;
	@Autowired
	private OrderInfoDAO orderInfoDAO;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ParkingPositionDao parkingPositionDao;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("merchant/mreport/report");
		return mv;
	}

	/**
	 * 获取服务器时间
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getsystemtime")
	public Map<String, Object> getSystemTime(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		long nowDate = DateUtil.getLongDate();
		map.put("serverTime", nowDate);
		return success(map);
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getuser")
	public Map<String, Object> getUser(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String yesterday = DateUtil.format(DateUtil.operDate(new Date(), -1), DateUtil.defaultDatePattern);
		String today = DateUtil.format(new Date(), DateUtil.defaultDatePattern);
		// TODO 用户ID
		// TODO 和景区关联？？？
		String id = request.getParameter("id");
		// TODO 粉丝量？？？
		// 今日新增
		int todayCount = userInfoDao.getCountByDate(today);
		// 昨日新增
		int yesterdayCount = userInfoDao.getCountByDate(today);
		// 用户总数
		int userCount = userInfoDao.countAll();
		// 今日访问量
		int countTodayRecord = websiteRecordDAO.countToday();
		// 昨日访问量
		int countYesterdayRecord = websiteRecordDAO.countByDate(yesterday);
		// 总访问量
		int countRecord = websiteRecordDAO.countAll();
		map.put("todayCount", todayCount);
		map.put("yesterdayCount", yesterdayCount);
		map.put("userCount", userCount);
		map.put("countTodayRecord", countTodayRecord);
		map.put("countYesterdayRecord", countYesterdayRecord);
		map.put("countRecord", countRecord);

		return success(map);
	}

	/**
	 * 获取实时事件及公众反馈
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getnews")
	public Map<String, Object> getNews(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		// TODO 获取实时事件等
		return success(map);
	}

	/**
	 * 获取渠道
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getdistribute")
	public Map<String, Object> getDistribute(HttpServletRequest request) throws Exception {
		// TODO OrderSource???
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> orderInfoList = orderInfoDAO.getOrderByOrderSource();
		JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(orderInfoList);
		List<String> orderInfoNames = new ArrayList<String>();
		List<Integer> orderInfoCounts = new ArrayList<Integer>();
		for (Object object : orderInfoArr) {
			JSONArray objec = (JSONArray) JSONArray.toJSON(object);
			String string = objec.getString(1);
			String name = OrderSource.fromStr2Name(string);
			orderInfoNames.add(name);
			orderInfoCounts.add(objec.getInteger(0));
		}
		map.put("distributeTitles", orderInfoNames);
		map.put("distributeCount", orderInfoCounts);
		return map;
	}

	/**
	 * 获取客源地
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getorigin")
	public Map<String, Object> getOrigin(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		DecimalFormat df = new DecimalFormat("0.00");
		List<Object> list = orderInfoDAO.findOrdersByStateByOrigin(OrderStateType.TESTED.getFiled(), id);
		List<Map<String, Object>> listVO = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			int size = 0;
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				Integer originCount = objec.getInteger(0);
				String originName = objec.getString(1);
				size += originCount;

				Map<String, Object> data = new HashMap<String, Object>();
				data.put("originName", originName);
				data.put("originCount", originCount);
				listVO.add(data);
			}
			for (Map<String, Object> map : listVO) {
				Integer count = (Integer) map.get("originCount");
				String originRadio = df.format((double) count * 100 / size);
				map.put("originRadio", originRadio + "%");
			}
		}
		result.put("origin", listVO);
		return result;
	}

	/**
	 * 年龄段
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getagestage")
	public Map<String, Object> getByAge(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		List<Object> list = orderInfoDAO.findOrdersByStateByAge(OrderStateType.TESTED.getFiled(), id);

		List<Map<String, Object>> ageList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				String ageStage = objec.getString(0);
				Integer count = objec.getInteger(1);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("name", ageStage);
				data.put("value", count);
				ageList.add(data);
			}
		}
		result.put("ageList", ageList);
		return result;
	}

	/**
	 * 性别
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getgender")
	public Map<String, Object> getByGender(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		List<Object> list = orderInfoDAO.findOrdersByStateByGender(OrderStateType.TESTED.getFiled(), id);
		List<Map<String, Object>> genderList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				String ageStage = objec.getString(0);
				Integer count = objec.getInteger(1);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("name", ageStage);
				data.put("value", count);
				genderList.add(data);
			}
		}
		result.put("genderList", genderList);
		return result;
	}

	/**
	 * 获取游客趋势
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getpeopel")
	public Map<String, Object> getPeople(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		List<Object> list = orderInfoDAO.findOrdersByStateByMonthForPeople(OrderStateType.TESTED.getFiled(), id);
		List<String> yearList = new ArrayList<String>();
		List<String> monthList = new ArrayList<String>();
		List<Integer> countList = new ArrayList<Integer>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				String year = objec.getString(0);
				String month = objec.getString(1);
				Integer count = objec.getInteger(2);
				yearList.add(year);
				monthList.add(month);
				countList.add(count);
			}
		}
		result.put("yearList", yearList);
		result.put("monthList", monthList);
		result.put("countList", countList);
		return result;
	}

	/**
	 * 获取交易量
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getdealinfo")
	public Map<String, Object> getDeal(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		List<Object> list = orderInfoDAO.findDealsByState(OrderStateType.TESTED.getFiled(), id);
		List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
		Set<String> nameSet = new HashSet<String>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				String name = objec.getString(0);
				String source = OrderSource.fromStr2Name(objec.getString(1));
				Integer count = objec.getInteger(2);
				Map<String, Object> data = new HashMap<String, Object>();
				nameSet.add(name);
				data.put("name", name);
				data.put("source", source);
				data.put("count", count);
				temp.add(data);
			}
		}
		result.put("nameSet", nameSet);
		result.put("deals", temp);
		return result;
	}

	/**
	 * 获取交易e
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getorderinfo")
	public Map<String, Object> getOrder(HttpServletRequest request) throws Exception {
		// TODO low code 优化SQL或逻辑代码
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		List<OrderInfoEchartVO> listVO = new ArrayList<OrderInfoEchartVO>();
		List<CompanyPO> comp = companyDAO.getAll();
		List<String> nameList = new ArrayList<String>();
		List<String> monthTemp = new ArrayList<String>();
		List<String> monthList = new ArrayList<String>();
		for (CompanyPO po : comp) {
			List<Object> list = orderInfoDAO.findOrdersByStateComIdProductId(OrderStateType.TESTED.getFiled(), id,
					po.getId());
			List<Integer> countList = new ArrayList<Integer>();
			if (list != null && list.size() > 0) {
				JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
				OrderInfoEchartVO vo = new OrderInfoEchartVO();
				for (Object object : orderInfoArr) {
					JSONArray objec = (JSONArray) JSONArray.toJSON(object);
					String month = objec.getString(0);
					Integer count = objec.getInteger(1);
					countList.add(count);
					monthTemp.add(month);
				}
				vo.setName(po.getComName());
				vo.setData(countList);
				listVO.add(vo);
				nameList.add(po.getComName());
			}
		}
		for (int i = 0; i < monthTemp.size(); i++) {
			if (!monthList.contains(monthTemp.get(i))) {
				monthList.add(monthTemp.get(i));
			}
		}
		result.put("month", monthList);
		result.put("order", listVO);
		result.put("name", nameList);
		return result;
	}

	/**
	 * 获取停车场数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getpark")
	public Map<String, Object> getPark(HttpServletRequest request) throws Exception {
		// TODO low code 优化SQL或逻辑代码
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, ParkEchartVO> data = new HashMap<String, ParkEchartVO>();
		long id = Long.parseLong(request.getParameter("id"));
		List<Object> obj = parkingPositionDao.getParkInfo(id);
		List<ParkEchartVO> temp = new ArrayList<ParkEchartVO>();
		if (obj != null && obj.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(obj);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				String veh = objec.getString(0);
				Integer used = objec.getInteger(1);
				Integer count = objec.getInteger(2);
				ParkEchartVO po = new ParkEchartVO();
				po.setName(veh);
				po.setValue(count);
				po.setUsed(used);
				temp.add(po);
			}
		}
		if (temp != null && temp.size() > 0) {
			for (ParkEchartVO vo : temp) {
				if (vo.getUsed() == 0) {
					if (data.containsKey("剩余车位")) {
						Integer count = data.get("剩余车位").getValue() + 1;
						data.get("剩余车位").setValue(count);
						data.get("剩余车位").setName("剩余车位");
					} else {
						ParkEchartVO v = new ParkEchartVO();
						v.setName("剩余车位");
						v.setUsed(0);
						v.setValue(1);
						data.put("剩余车位", v);
					}
				} else {
					data.put(vo.getName(), vo);
				}
			}
		}
		List<ParkEchartVO> res = new ArrayList<ParkEchartVO>();
		Iterator<ParkEchartVO> it = data.values().iterator();
		while (it.hasNext()) {
			res.add(it.next());
		}
		result.put("total", obj.size());
		result.put("list", res);
		return result;
	}

	/**
	 * 获取地图
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getmap")
	public Map<String, Object> getMap(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		// TODO 地名匹配问题需修改
		List<Object> list = orderInfoDAO.findOrdersByStateByOrigin(OrderStateType.TESTED.getFiled(), id);
		List<Map<String, Object>> listVO = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			JSONArray orderInfoArr = (JSONArray) JSONArray.toJSON(list);
			for (Object object : orderInfoArr) {
				JSONArray objec = (JSONArray) JSONArray.toJSON(object);
				Integer count = objec.getInteger(0);
				String name = objec.getString(1);
				Map<String, Object> data = new HashMap<String, Object>();
				if (name.contains("省") || name.contains("市")) {
					name = name.substring(0, name.length() - 1);
				} else if (name.contains("内蒙古")) {
					name = "内蒙古";
				} else if (name.contains("黑龙江")) {
					name = "黑龙江";
				}
				data.put("value", count);
				data.put("name", name);
				listVO.add(data);
			}
		}
		result.put("mapData", listVO);
		return result;
	}

}
