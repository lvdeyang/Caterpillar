package pub.caterpillar.app.carpool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.CarDAO;
import pub.caterpillar.app.carpool.dao.OrderDAO;
import pub.caterpillar.app.carpool.dao.SeatInfoDAO;
import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPayStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPriceType;
import pub.caterpillar.app.carpool.enumeration.OrderTakingStatus;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.exception.CarAlreadyStartOffException;
import pub.caterpillar.app.carpool.exception.CharterHasTakedAnotherOrderException;
import pub.caterpillar.app.carpool.exception.NoEnoughSeatsForOrderException;
import pub.caterpillar.app.carpool.exception.NoFreeSeatException;
import pub.caterpillar.app.carpool.exception.OrderAlreadyPayedException;
import pub.caterpillar.app.carpool.exception.OrderAlreadySuccessedException;
import pub.caterpillar.app.carpool.exception.OrderAlreadyTakedException;

import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.app.carpool.po.SeatInfoPO;



/**
 * 订单业务<br/>
 * <p>
 *     1、需要订单锁的业务
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年5月11日 上午9:57:47
 */
@Service("pub.caterpillar.app.carpool.service.OrderService")
public class OrderService {
	
	@Autowired
	private OrderDAO conn_order;
	
	@Autowired
	private SeatInfoDAO conn_seat_info;
	
	@Autowired
	private CarDAO conn_car;

	public JSONObject visitorCancelOrder(long orderId) throws Exception{
		Long orderLock = Long.valueOf(orderId);
		synchronized(orderLock){
			
			JSONObject result = null;
			
			//查看订单是否已经被接并且接单的车辆是否已经出发
			CarStatus status = conn_car.queryStatusByOrder(orderId);
			if(CarStatus.STARTOFF.equals(status)){
				throw new CarAlreadyStartOffException();
			}
			
			OrderPO order = conn_order.get(orderId);
			
			//判断订单状态是否已经完成
			if(OrderTakingStatus.SUCCESS.equals(order.getTakingStatus())){
				throw new OrderAlreadySuccessedException();
			}
			
			//修改接单状态
			List<SeatInfoPO> seats = conn_seat_info.queryByOrder(order.getUuid());
			if(seats!=null && seats.size()>0){
				for(SeatInfoPO seat:seats){
					seat.setStatus(SeatStatus.FREE);
					seat.setPriceType(null);
					seat.setPrice(0);
					seat.setAddMoney(0);
					seat.setOrderUuid(null);
					seat.setMobile(null);
					seat.setTravelTime(null);
					seat.setPassengerNum(0);
					seat.setRemark(null);
				}
				conn_seat_info.saveOrUpdateAll(seats);
				
				//消息推送内容
				result = new JSONObject();
				result.put("mobile", seats.get(0).getCar().getDriver().getMobile());
				result.put("orderUuid", order.getUuid());
			}
			
			//删除订单
			conn_order.delete(order);
			
			return result;
		}
	}
	
	/**
	 * 司机接系统订单<br/>
	 * <p>
	 *     1、订单锁<br/>
	 *     2、根据订单人数占座<br/>
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月11日 上午9:58:43
	 * @param mobile 司机电话
	 * @param orderId 系统订单id
	 * @return {@link pub.caterpillar.app.carpool.po.SeatInfoPO}
	 * @throws Exception
	 */
	public List<SeatInfoPO> takingSystemOrder(String mobile, long orderId) throws Exception{
		Long orderLock = Long.valueOf(orderId);
		synchronized(orderLock){
			OrderPO order = conn_order.get(orderId);
			
			//订单已经被接了
			if(!OrderTakingStatus.NEW.equals(order.getTakingStatus())) throw new OrderAlreadyTakedException();
			
			List<SeatInfoPO> seatInfos = conn_seat_info.queryFreeByDriverWithSerailASC(mobile);
			
			//没有空余座位了
			if(seatInfos==null || seatInfos.size()<=0) throw new NoFreeSeatException();
			
			int passengerNum = 0;
			if(OrderPriceType.CARPOOL.equals(order.getPriceType())){
				//空余座位不足
				if(order.getPassengerNum() > seatInfos.size()) throw new NoEnoughSeatsForOrderException();
				passengerNum = order.getPassengerNum();
			}else{
				CarPO car = seatInfos.get(0).getCar();
				if(car.getSeats() > seatInfos.size()){
					//已经接过订单无法再接包车的订单
					throw new CharterHasTakedAnotherOrderException();
				}
				passengerNum = seatInfos.size();
			}
			
			List<SeatInfoPO> selectedSeatInfos = null;
			if(passengerNum == seatInfos.size()){
				selectedSeatInfos = seatInfos;
			}else{
				selectedSeatInfos = new ArrayList<SeatInfoPO>();
				for(int i=0; i<passengerNum; i++){
					selectedSeatInfos.add(seatInfos.get(i));
				}
			}
					
			for(SeatInfoPO seatInfo:selectedSeatInfos){
				seatInfo.setStatus(SeatStatus.SYSTEM_ORDER);
				seatInfo.setPriceType(order.getPriceType().getName());
				seatInfo.setPrice(order.getPrice());
				seatInfo.setAddMoney(order.getAddMoney());
				seatInfo.setOrderUuid(order.getUuid());
				seatInfo.setMobile(order.getMobile());
				seatInfo.setTravelTime(order.getTravelTime());
				seatInfo.setPassengerNum(order.getPassengerNum());
				seatInfo.setRemark(order.getRemark());
			}
			
			conn_seat_info.saveOrUpdateAll(selectedSeatInfos);
			
			order.setTakingStatus(OrderTakingStatus.TAKED);
			order.setDriverMobile(mobile);
			conn_order.saveOrUpdate(order);
			
			return selectedSeatInfos;
		}
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
	 * <b>日期：</b>2018年5月11日 上午10:02:03
	 * @param seatInfoId 座位id
	 */
	public Set<SeatInfoPO> driverCancelTakingSystemOrder(Long seatInfoId){
		SeatInfoPO currSeat = conn_seat_info.get(seatInfoId);
		List<SeatInfoPO> otherSeats = conn_seat_info.queryByCarAndOrderNeSelf(currSeat.getCar().getId(), currSeat.getOrderUuid(), seatInfoId);
		Set<SeatInfoPO> seats = new HashSet<SeatInfoPO>();
		seats.add(currSeat);
		if(otherSeats!=null && otherSeats.size()>0) seats.addAll(otherSeats);
		OrderPO order = conn_order.get(currSeat.getOrderUuid());
		Long orderLock = Long.valueOf(order.getId().longValue());
		synchronized(orderLock){
			for(SeatInfoPO seat:seats){
				seat.setStatus(SeatStatus.FREE);
				seat.setPriceType(null);
				seat.setPrice(0);
				seat.setAddMoney(0);
				seat.setOrderUuid(null);
				seat.setMobile(null);
				seat.setTravelTime(null);
				seat.setPassengerNum(0);
				seat.setRemark(null);
			}
			conn_seat_info.saveOrUpdateAll(seats);
			
			order.setTakingStatus(OrderTakingStatus.NEW);
			order.setDriverMobile(null);
			conn_order.saveOrUpdate(order);
		}
		return seats;
	}
	
	/**
	 * 司机完成订单<br/>
	 * <p>
	 *     1、查找当前订单<br/>
	 *     2、修改订单状态<br/>
	 *     3、修改车辆状态<br/>
	 *     4、清空接单信息<br/>
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年5月11日 上午10:02:03
	 * @param mobile 司机电话
	 */
	public void completeOrder(String mobile){
		
		//查询订单
		List<SeatInfoPO> seats = conn_seat_info.queryByDriver(mobile);
		Set<String> orderUuids = new HashSet<String>();
		for(SeatInfoPO seat:seats){
			orderUuids.add(seat.getOrderUuid());
		}
		List<OrderPO> orders = conn_order.getAllByUuids(orderUuids);
		
		//修改订单状态
		for(OrderPO order:orders){
			Long orderLock = Long.valueOf(order.getId().longValue());
			synchronized (orderLock){
				order.setTakingStatus(OrderTakingStatus.SUCCESS);
				conn_order.saveOrUpdate(order);
			}
		}
		
		//修改车辆状态
		CarPO car = conn_car.queryByDriverMobile(mobile);
		car.setStatus(CarStatus.WAITING);

		//清空接单信息
		for(SeatInfoPO seat:seats){
			seat.setStatus(SeatStatus.FREE);
			seat.setPriceType(null);
			seat.setPrice(0);
			seat.setAddMoney(0);
			seat.setOrderUuid(null);
			seat.setMobile(null);
			seat.setTravelTime(null);
			seat.setPassengerNum(0);
			seat.setRemark(null);
		}
		conn_seat_info.saveOrUpdateAll(seats);
	}
	
	public void wxPay(long orderId,String appId,String appScret,String mchId,String mchKey,String resUrl,String openId){
		
		//微信公众账号支付配置

//		WxPayH5Config wxPayH5Config = new WxPayH5Config();
//		wxPayH5Config.setAppId(appId);
//		wxPayH5Config.setAppSecret(appScret);
//		wxPayH5Config.setMchId(mchId);
//		wxPayH5Config.setMchKey(mchKey);
//		wxPayH5Config.setNotifyUrl(resUrl);
//		
//	
//		//支付类, 所有方法都在这个类里
//		BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//		bestPayService.setWxPayH5Config(wxPayH5Config);
//
//		//OrderPO orderPO=conn_order.get(orderId);
//		PayRequest payRequest=new PayRequest();
//		payRequest.setOpenid(openId);
//		payRequest.setOrderAmount(1d);
//		String orderNum="yuebaorder-"+String.valueOf(orderId);
//		payRequest.setOrderId(orderNum);
//		payRequest.setOrderName("test");
//		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_JSAPI);
//		payRequest.setSpbillCreateIp("192.165.56.64");
//		//发起支付
//		PayResponse res=bestPayService.pay(payRequest);

		//异步回调
		//bestPayService.asyncNotify(res.get);
		
		
	}
	
	public void notifyPay(String notifyData){
//		BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//		PayResponse response=bestPayService.asyncNotify(notifyData);
//		String orderId=response.getOrderId();
//		//这里处理订单
//		return orderId;
	}
	
	
	
	public void prePay(long orderId, String cip) throws Exception{
		Long orderLock = Long.valueOf(orderId);
		synchronized (orderLock){
			OrderPO order = conn_order.get(orderId);
			
			//订单是否支付
			if(OrderPayStatus.PAYED.equals(order.getPayStatus())){
				throw new OrderAlreadyPayedException(order.getUuid());
			}
			
			//向微信下单
			if(order.getPrepayId() == null){
				
		       
			}
		}
	}
	
}
