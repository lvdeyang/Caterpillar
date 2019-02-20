package pub.caterpillar.app.carpool.controller;

import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pub.caterpillar.app.carpool.dao.CarDAO;
import pub.caterpillar.app.carpool.dao.SeatInfoDAO;
import pub.caterpillar.app.carpool.enumeration.CarStatus;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.app.carpool.vo.CarVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping(value = "/car")
public class CarController {

	@Autowired
	private CarDAO conn_car;
	
	@Autowired
	private SeatInfoDAO conn_seat_info;
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public Object queryAll(HttpServletRequest request) throws Exception{
		
		List<CarPO> entitys = conn_car.findAll();
		List<CarVO> view_cars = CarVO.getConverter(CarVO.class).convert(entitys, CarVO.class);
		
		return view_cars;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/free/car", method = RequestMethod.GET)
	public Object queryFreeCar(HttpServletRequest request) throws Exception{
		
		List<CarPO> entitys = conn_car.queryFreeCars();
		List<CarVO> view_cars = CarVO.getConverter(CarVO.class).convert(entitys, CarVO.class);
		
		return view_cars;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Object update(
			@PathVariable Long id,
			String type,
			String plateNumber,
			int seats,
			HttpServletRequest request) throws Exception{
		
		CarPO entity = conn_car.get(id);
		
		entity.setType(type);
		entity.setPlateNumber(plateNumber);
		
		//处理座位数量变动
		int oldSeats = entity.getSeats();
		if(seats > oldSeats){
			//加座位
			for(int i=oldSeats+1; i<=seats; i++){
				SeatInfoPO seatInfo = new SeatInfoPO();
				seatInfo.setSerialNum(i);
				seatInfo.setStatus(SeatStatus.FREE);
				seatInfo.setCar(entity);
				entity.getSeatInfos().add(seatInfo);
			}
		}else{
			//减座位
			conn_seat_info.deletByCarAndGTSerial(entity.getId(), seats);
		}
		
		entity.setSeats(seats);
		conn_car.saveOrUpdate(entity);
		
		CarVO view_car = new CarVO().set(entity);
		
		return view_car;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		conn_car.delete(id);
		
		return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(
			String type,
			String plateNumber,
			int seats,
			HttpServletRequest request) throws Exception{
		
		CarPO entity = new CarPO();
		entity.setType(type);
		entity.setPlateNumber(plateNumber);
		entity.setSeats(seats);
		
		//初始化车辆状态
		entity.setStatus(CarStatus.WAITING);
		
		//创建座位详情
		entity.setSeatInfos(new HashSet<SeatInfoPO>());
		for(int i=1; i<=seats; i++){
			SeatInfoPO seatInfo = new SeatInfoPO();
			seatInfo.setSerialNum(i);
			seatInfo.setStatus(SeatStatus.FREE);
			seatInfo.setCar(entity);
			entity.getSeatInfos().add(seatInfo);
		}
		conn_car.save(entity);
		
		CarVO view_car = new CarVO().set(entity);
		
		return view_car;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request) throws Exception{
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		conn_car.deleteAllByIds(ids);
		
		return null;
	}
	
}
