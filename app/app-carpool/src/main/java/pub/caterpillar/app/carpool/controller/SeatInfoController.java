package pub.caterpillar.app.carpool.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.caterpillar.app.carpool.dao.SeatInfoDAO;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.app.carpool.service.OrderService;
import pub.caterpillar.app.carpool.vo.SeatInfoVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

/**
 * 车辆座位信息，接单信息<br/>
 * <p>
 *    1、如果接单、会把订单中所有的详细信息复制到座位信息数据中保存<br/>
 *    2、一个订单可能占用多个座位，占用的座位会保存相同的信息<br/>
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年5月9日 下午5:03:18
 */
@Controller
@RequestMapping(value = "/seat/info")
public class SeatInfoController {

	@Autowired
	private SeatInfoDAO conn_seat_info;
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/seats/{mobile}", method = RequestMethod.GET)
	public Object querySeats(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		List<SeatInfoPO> seats = conn_seat_info.queryByDriver(mobile);
		List<SeatInfoVO> view_seats = SeatInfoVO.getConverter(SeatInfoVO.class).convert(seats, SeatInfoVO.class);
		
		return view_seats;
	}
	
	/**
	 * 获取接单详情数据<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月9日 下午4:59:45
	 * @param id 座位信息id
	 * @param request @JsonBody必要参数
	 * @return {@link pub.caterpillar.app.carpool.vo.SeatInfoVO}
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/taked/details/{id}", method = RequestMethod.GET)
	public Object queryTakedDetails(
			@PathVariable long id,
			HttpServletRequest request) throws Exception{
		
		SeatInfoPO seatInfo = conn_seat_info.get(id);
		SeatInfoVO view_seatInfo = new SeatInfoVO().set(seatInfo);
		
		return view_seatInfo;
	}
	
	/**
	 * 司机取消接单<br/>
	 * <p>
	 *     1、清空当前座位信息<br/>
	 *     2、查找当前订单<br/>
	 *     3、清空相同订单座位信息<br/>
	 *     4、修改当前订单状态<br/>
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月11日 上午9:47:36
	 * @param seatInfoId 座位id
	 * @param request @JsonBody必要参数
	 * @return {@link pub.caterpillar.app.carpool.vo.SeatInfoVO}
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/driver/cancel/order/{seatInfoId}", method = RequestMethod.POST)
	public Object driverCancelOrder(
			@PathVariable Long seatInfoId,
			HttpServletRequest request) throws Exception{
		Set<SeatInfoPO> seatInfos = orderService.driverCancelTakingSystemOrder(seatInfoId);
		List<SeatInfoVO> view_seatInfos = SeatInfoVO.getConverter(SeatInfoVO.class).convert(seatInfos, SeatInfoVO.class);
		return view_seatInfos;
	}
	
}
