package com.guolaiwan.app.web.chapman.statistics.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.web.chapman.statistics.order.vo.OrderDetailStatisticsBarVO;
import com.guolaiwan.app.web.chapman.statistics.order.vo.OrderDetailStatisticsPieVO;
import com.guolaiwan.app.web.chapman.statistics.order.vo.OrderDetailStatisticsTableVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.order.dao.OrderDetailDAO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsBarDTO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsPieDTO;
import com.guolaiwan.bussiness.order.dto.OrderDetailStatisticsTableDTO;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/chapman/statistics/order")
public class ChapmanStatisticsOrderController extends BaseController {

	private final int pageNum = 1;
	private final int pageSize = 100;
	
	@Autowired
	private OrderDetailDAO conn_order_detail;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	//订单统计表
	@ResponseBody
	@RequestMapping(value = "/result/table/{chapmanId}", method = RequestMethod.GET)
	public Map<String, Object> resultTable(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<OrderDetailStatisticsTableDTO>  statistics = conn_order_detail.orderDetailStatisticsTable(chapman.getId(), 1, 100);
		
		List<OrderDetailStatisticsTableVO> _statistics = OrderDetailStatisticsTableVO.getConverter(OrderDetailStatisticsTableVO.class).convert(statistics, OrderDetailStatisticsTableVO.class);
		
		result.put("statistics", _statistics);
		
		return success(result);
	}
	
	//总营业额并图
	@ResponseBody
	@RequestMapping(value = "/result/pie/{chapmanId}", method = RequestMethod.GET)
	public Map<String, Object> resultPie(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<OrderDetailStatisticsPieDTO>  statistics = conn_order_detail.orderDetailStatisticsPie(chapman.getId());
		
		List<OrderDetailStatisticsPieVO> _statistics = OrderDetailStatisticsPieVO.getConverter(OrderDetailStatisticsPieVO.class).convert(statistics, OrderDetailStatisticsPieVO.class);
		
		result.put("statistics", _statistics);
		
		return success(result);
	}
	
	//月营业额柱状图
	@ResponseBody
	@RequestMapping(value = "/result/bar/{chapmanId}", method = RequestMethod.GET)
	public Map<String, Object> resultBar(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<OrderDetailStatisticsBarDTO>  statistics = conn_order_detail.orderDetailStatisticsBar(chapman.getId());
		
		List<OrderDetailStatisticsBarVO> _statistics = OrderDetailStatisticsBarVO.getConverter(OrderDetailStatisticsBarVO.class).convert(statistics, OrderDetailStatisticsBarVO.class);
		
		result.put("statistics", _statistics);
		
		return success(result);
	}
	
}
