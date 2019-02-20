package com.guolaiwan.app.web.admin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;

import pub.caterpillar.mvc.controller.BaseController;

/**
 * Liw 最短路径算法:根据当前经纬度和目标经纬度算出最短路径
 * 
 * @author AngelBeatsCA 2018-11-20 完结
 */
@Controller
@RequestMapping("/admin/road")
public class LiwDijkstraController extends BaseController {

	@Autowired
	private ChildProductDAO conn_childProduct;

	private static final int FORNUMBER = 9;// 规定的经纬度位数,也是循环的次数

	/**
	 * Liw 负责接受页面和返回页面的控制方法
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoad", method = RequestMethod.POST)
	public Map<String, Object> getDijkstra(HttpServletRequest request) {
		String requestJson = getRequestJson(request);
		JSONObject parseObject = JSON.parseObject(requestJson);
		String nowLoAndLa = parseObject.getString("nowLoAndLa");
		String childId = parseObject.getString("childId");
		// 用户当前位置的n位数经纬度字符串
		String NLoAndLa = LoLaToSplit(nowLoAndLa);
		ChildProductPO child = conn_childProduct.getChildById(Long.parseLong(childId)).get(0);
		String childLoLa = child.getChildLongitude() + "," + child.getChildLatitude();
		// 目标导览点n位数经纬度字符串
		String NChildLoAndLa = LoLaToSplit(childLoLa);
		long productID = child.getProductID();
		List<ChildProductPO> cpolist = conn_childProduct.getChildByIsCen(productID);
		// 景区路线
		String oldChildRoad = cpolist.get(0).getChildRoad();
		String[] splits = oldChildRoad.split("=");
		String newChildRoad = "";
		for (int i = 0; i < splits.length; i++) {
			String[] split = splits[i].split("-");
			for (int j = 0; j < split.length; j++) {
				List<ChildProductPO> childPO = conn_childProduct.getChildById(Long.parseLong(split[j]));
				String str = childPO.get(0).getChildLongitude() + "," + childPO.get(0).getChildLatitude();
				String loLaToSplit = LoLaToSplit(str);
				if ("".equals(newChildRoad)) {
					newChildRoad = loLaToSplit + "-";
				} else if (j == split.length) {
					newChildRoad = newChildRoad + loLaToSplit;
				} else {
					newChildRoad = newChildRoad + loLaToSplit + "-";
				}
			}
		}
		List<String> demolist = main(NLoAndLa, NChildLoAndLa, newChildRoad, Long.parseLong(childId));
		List<Long> idlist = newToOld(demolist, childId);
		List<ChildProductPO> list = new ArrayList<ChildProductPO>();
		for (Long l : idlist) {
			list.add(conn_childProduct.get(l));
		}
		return success(list);
	}

	/**
	 * Liw 算法核心
	 */
	private List<String> main(String NLoAndLa, String NChildLoAndLa, String newChildRoad, long childId) {
		List<String> list = new ArrayList<String>();
		String[] splits = newChildRoad.split("=");
		String str = "";
		// 遍历判断当前位置是否正好在导览点上
		for (int i = 0; i < splits.length; i++) {
			String[] split = splits[i].split("-");
			for (int j = 0; j < split.length; j++) {
				if (split[j].equals(NLoAndLa)) {
					str = NLoAndLa;
				}
			}
		}
		// 当前位置正好在导览点上
		if (!"".equals(str)) {
			List<String> road = getRoad(str, newChildRoad, NChildLoAndLa);
			// 想去的导览点正好和当前位置处在一条线
			if (road.size() != 0) {
				for (String str1 : road) {
					list.add(str1);
				}
				/**
				 * 当前位置正好在导览点上并且和目标导览点正好在一条线路上返回
				 */
				return list;
				// 当前位置和想去的导览点不在一条线上
			} else {
				/**
				 * 当前位置正好在导览点上但是和目标导览点不处在一条线路上返回
				 */
				return roadPlan(road, str, newChildRoad, NChildLoAndLa);
			}
			// 如果当前位置不在导览点上
		} else {
			// 计算当前位置的最近导览点经纬度
			ChildProductPO cp = conn_childProduct.getChildById(childId).get(0);
			List<ChildProductPO> polist = conn_childProduct.getChildByProductId(cp.getProductID());
			String LoLa = "";// 最近的经纬度
			String[] nowSplit = NLoAndLa.split(",");
			double in = 0;// 两点经纬度之差
			double jn = 1;// 上一次两点经纬度之差
			double NowLoAndLa = Double.parseDouble(nowSplit[0]) + Double.parseDouble(nowSplit[1]);
			for (int i = 0; i < polist.size(); i++) {
				double cLoAndLa = Double.parseDouble(polist.get(i).getChildLongitude())
						+ Double.parseDouble((polist.get(i).getChildLatitude()));
				in = NowLoAndLa - cLoAndLa;
				if (in < 0) {
					in = -in;
				}
				if (in < jn) {
					jn = in;
					LoLa = polist.get(i).getChildLongitude() + "," + polist.get(i).getChildLatitude();
				}
			}
			List<String> road = getRoad(LoLaToSplit(LoLa), newChildRoad, NChildLoAndLa);
			if (road.size() != 0) {
				for (String demo : road) {
					list.add(demo);
				}
				/**
				 * 如果当前位置最近的导览点和目标导览点在一条线路上返回
				 */
				return list;
			} else {
				/**
				 * 如果当前位置最近的导览点和目标导览点不在一条线路上返回
				 */
				return roadPlan(road, LoLaToSplit(LoLa), newChildRoad, NChildLoAndLa);
			}
		}
	}

	/**
	 * Liw 算法支持 post提交方式获取参数
	 */
	private String getRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * Liw 根据老的经纬度查询id并返回
	 */
	private List<Long> newToOld(List<String> newlist, String childId) {
		List<Long> list = new ArrayList<Long>();
		List<String> newToOldLoLa = newToOldLoLa(newlist, childId);// 获取老的经纬度
		List<ChildProductPO> demolist = new ArrayList<ChildProductPO>();
		for (String str : newToOldLoLa) {
			String[] splits = str.split(",");
			for (int i = 0; i < splits.length; i++) {
				demolist = conn_childProduct.getIdByLoLa(splits[0], splits[1]);
			}
			if (demolist != null) {
				list.add(demolist.get(0).getId());
			}
		}
		return list;
	}

	/**
	 * Liw 把截取过的n位数经纬度还原为数据库原长度经纬度
	 */
	private List<String> newToOldLoLa(List<String> newlist, String childId) {
		List<String> list = new ArrayList<String>();// 路线上的导览点id
		ChildProductPO cp = conn_childProduct.getChildById(Long.parseLong(childId)).get(0);
		List<ChildProductPO> polist = conn_childProduct.getChildByProductId(cp.getProductID());
		String[] strs = new String[polist.size()];
		for (int i = 0; i < polist.size(); i++) {
			strs[i] = polist.get(i).getChildLongitude() + "," + polist.get(i).getChildLatitude();
		}
		for (String str : newlist) {
			for (int i = 0; i < strs.length; i++) {
				String demo = LoLaToSplit(strs[i]);
				if (str.equals(demo)) {
					list.add(strs[i]);
				}
			}
		}
		return list;
	}

	/**
	 * Liw 起始点和目标点不在一条线上时的路线规划
	 */
	private List<String> roadPlan(List<String> road, String str, String newChildRoad, String NChildLoAndLa) {
		List<String> list = new ArrayList<String>();
		String intersection = "";// 交集点
		road = getRoad2(str, newChildRoad);
		List<String> croad = getRoad2(NChildLoAndLa, newChildRoad);
		// 寻找两条线路的交集点
		for (String str1 : road) {
			for (String str2 : croad) {
				if (str1.equals(str2)) {
					intersection = str1;
				}
			}
		}
		// 循环当前点所在路线
		int math = 0;
		for (int i = 0; i < road.size(); i++) {
			if (road.get(i).equals(intersection)) {
				math = i;
			}
		}
		// 循环移除交集点到起点的经纬度
		for (int j = 0; j < math - 1; j++) {
			road.remove(j);
		}
		// 循环目标点所在路线
		math = 0;
		for (int l = 0; l < croad.size(); l++) {
			if (croad.get(l).equals(intersection)) {
				math = l;
			}
		}
		// 循环移除交集点到起点的经纬度
		for (int k = 0; k < math - 1; k++) {
			croad.remove(k);
		}
		// 循环添加当前点到交集点的经纬度
		math = 0;
		for (int h = road.size(); h > 0; h--) {
			list.add(road.get(math));
			math++;
		}
		// 循环添加交集点到目标点的经纬度
		for (int g = 0; g < croad.size() - 1; g++) {
			list.add(croad.get(g));
		}
		return list;
	}

	/**
	 * Liw 根据当前经纬度和目标经纬度确定线路
	 */
	private List<String> getRoad(String str, String newChildRoad, String NChildLoAndLa) {
		List<String> list = new ArrayList<String>();
		int in = 0;
		int jn = 0;
		String[] splits = newChildRoad.split("=");
		for (int i = 0; i < splits.length; i++) {
			String[] split = splits[i].split("-");
			for (int j = 0; j < split.length; j++) {
				if (split[j].equals(str)) {
					in = j;
				}
				if (split[j].equals(NChildLoAndLa)) {
					jn = j;
				}
			}
			for (int k = in; k < jn + 1; k++) {
				list.add(split[k]);
			}
		}
		return list;
	}

	/**
	 * Liw 如果想去的点和现在的位置不在一个路线的话执行这个方法
	 */
	private List<String> getRoad2(String str, String newChildRoad) {
		List<String> list = new ArrayList<String>();
		String[] splits = newChildRoad.split("=");
		for (int i = 0; i < splits.length; i++) {
			String[] split = splits[i].split("-");
			for (int j = 0; j < split.length; j++) {
				if (split[j].equals(str)) {
					for (String str1 : split) {
						list.add(str1);
					}
					return list;
				}
			}
		}
		return list;
	}

	/**
	 * Liw 把传进来的经纬度字符串分割成经度和纬度,再依次变成n位数
	 */
	private String LoLaToSplit(String LoAndLa) {
		// 去空
		if ("".equals(LoAndLa)) {
			return "";
		}
		// 逗号分割成经度和纬度数组
		String[] splits = LoAndLa.split(",");
		String Lo = splits[0];
		String La = splits[1];
		// n位数经度
		String NLo = LoLaToN(Lo);
		// n位数纬度
		String NLa = LoLaToN(La);
		return NLo + "," + NLa;
	}

	/**
	 * Liw 把传进来的经度或者纬度的长度变成n位数
	 */
	private String LoLaToN(String LoOrLa) {
		String str = "";
		// 长度大于n,需要把第n位以后的数字删除
		if (LoOrLa.length() > FORNUMBER) {
			char[] chars = LoOrLa.toCharArray();
			for (int i = 0; i < FORNUMBER; i++) {
				str = str + chars[i];
			}
			return str;
			// 长度等于n,直接返回
		} else if (LoOrLa.length() == FORNUMBER) {
			return LoOrLa;
			// 长度大于0小于n,缺几位添加几位随机数
		} else if (LoOrLa.length() > 0 && LoOrLa.length() < FORNUMBER) {
			int math = 0;
			for (int i = LoOrLa.length(); i < FORNUMBER; i++) {
				math = (int) (Math.random() * 10);
				LoOrLa = LoOrLa + String.valueOf(math);
			}
			return LoOrLa;
			// 特殊情况,返回""
		} else {
			return str;
		}
	}

}