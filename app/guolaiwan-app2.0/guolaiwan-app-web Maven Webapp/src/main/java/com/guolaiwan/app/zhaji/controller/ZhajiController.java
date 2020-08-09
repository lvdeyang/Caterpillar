package com.guolaiwan.app.zhaji.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.guolaiwan.app.zhaji.AvailablePo;
import com.guolaiwan.app.zhaji.ZhajiService;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import cn.hutool.json.JSONObject;
import pub.caterpillar.commons.util.date.DateUtil;

@Controller
@RequestMapping("/zhaji")
public class ZhajiController {
	
	@ResponseBody
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
    public String checkIn(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		String version = request.getParameter("version");
		JSONObject response=new JSONObject();
		JSONObject data=new JSONObject();
		JSONObject error=new JSONObject();
		//闸机名称
		data.put("gateName", "通道1");
		//商家名称
		data.put("siteName", "万佛园");
		//刷卡事件间隔
		data.put("swipeInterval", 0);
		//验证方式 0 - 常规验证, 1 - 过人计数, 2 - 常开验证, 3 - 手持验证，4 - 身份采样
		data.put("workMode", 3);
		//网络模式1 - 离线, 0 - 在线, 2 - 离线+在线
		data.put("networkMode", 2);
		//通道类型  0 - 常规通道，1 - 场次通道，2 - 常规+场次
		data.put("accessType", 2);
		//服务器时间
		data.put("systemDate",DateUtil.format(new Date(),DateUtil.dateTimePattern));
		error.put("code", 0);
		error.put("message", "success");
		response.put("data", data);
		response.put("error", error);
		return response.toString(); 
    }
	
	@ResponseBody
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
    public String checkAvailable(HttpServletRequest request){
		
	    String result = "";
		try {
            InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
            int respInt = insr.read();
            System.out.println(respInt);
            while(respInt != -1){
                result += (char)respInt;
                respInt = insr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		JSONObject jsonObject=new JSONObject(result);
		OrderInfoPO orderInfo=null;
		try {
			orderInfo=ydnow(jsonObject.getStr("serialNumber"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject response=new JSONObject();
		JSONObject data=new JSONObject();
		JSONObject error=new JSONObject();
		//唯一标识
		data.put("guid", orderInfo.getId());
		//进出方向0进，1,2出方向
		//data.put("accessDir", jsonObject.getStr("accessDir"));
		//票号
		data.put("serialNumber", jsonObject.getStr("serialNumber"));
		//此票是否可以开门 0 - 可进，1 - 不可进
		if(orderInfo!=null){
			data.put("accessDir", 1);
		}else{
			data.put("accessDir", 0);
		}
		
		//产品名称
		data.put("productName", orderInfo.getProductName());
		//0 - 默认类型，1 - 票，2 - 卡
		data.put("cardType", 1);
		//卡类型 0	普通
		data.put("cardUsage", 0);
		//卡状态 0	普通票
		data.put("cardState", 0);
		//过闸方式 0 - 一刷一人 1 - 一次刷完 2 - 一单一刷 3 - 刷卡落杆
		data.put("passWay", 0 );
		//开始时间
		data.put("startTime", "2019-07-12 09:29:30");
		//结束时间
		data.put("endTime",  "2019-11-12 09:29:30");
		//单次消费次数
		data.put("singleTimes", 1);
		//剩余次数
		error.put("code", 0);
		error.put("message", "success");
		response.put("data", data);
		response.put("error", error);
		return response.toString();      
    }
	
	
	
	@ResponseBody
	@RequestMapping(value = "/hasHeartbeat", method = RequestMethod.GET)
    public String hasHeartbeat(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		JSONObject response=new JSONObject();
		JSONObject data=new JSONObject();
		JSONObject error=new JSONObject();
		data.put("result", true);
		error.put("code", 0);
		error.put("message", "success");
		response.put("data", data);
		response.put("error", error);
		return response.toString(); 
    }
	
	@Autowired
	OrderInfoDAO conn_order;
	
	private OrderInfoPO ydnow(String orderNO) throws ParseException{
		OrderInfoPO orderInfoPO = null;

		
		// 获取订单
		orderInfoPO = conn_order.get(Long.parseLong(orderNO));
		if (orderInfoPO != null) {

			if (orderInfoPO.getOrderBookDate() != null) {

				String today = DateUtil.format(new Date(), "yyyy-MM-dd");
				Date sDate = DateUtil.parse(today + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date eDate = DateUtil.parse(today + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				if (sDate.after(orderInfoPO.getOrderBookDate()) || eDate.before(orderInfoPO.getOrderBookDate())) {
					String message = "这条订单日期不是今天！";
					return null;
				}

			}

			// 判断订单状态？？？
			if (!orderInfoPO.getOrderState().equals(OrderStateType.PAYSUCCESS)) {
				//return "订单状态是：" + orderInfoPO.getOrderState();
				return null;
			}
			// 更改订单的状态，验单时间
			orderInfoPO.setOrderState(OrderStateType.TESTED);
			Date date = new Date();
			orderInfoPO.setYdDate(date);
			conn_order.saveOrUpdate(orderInfoPO);
			return orderInfoPO;
		} else {
			String message = "订单信息不对！";
			return null;
		}
	}
	
}
