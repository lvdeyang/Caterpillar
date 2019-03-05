package com.guolaiwan.app.interfac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;
import pub.caterpillar.orm.hql.QueryHql;

@Controller
@RequestMapping("/gatesvr")
public class GateBrakeController {
	
	@Autowired
	private OrderInfoDAO orderDao;
	@Autowired
	private AddressDAO addressDao;
	
	//验单二维码，身份证
	@ResponseBody
	@RequestMapping(value = "/CheckCode", method = RequestMethod.POST)//id为0时使用备源
	public Object checkCode(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		OrderInfoPO orderInfoPO = null;
		Map<String, Object> ret=new HashMap<String, Object>(); 
	    //接收json
		//票号值	CodeVal	string	可为二维码值，IC卡卡号，身份证号码等
		//票号类型	CodeType	string	"Q"代表二维码；
		//"C"代表IC卡；
		//"I"代表身份证；
		//"F"代表指纹（1:N验证验票）；"F1"用于指纹1:1验证后，返回指纹验证成功数据给服务器；
		//"B"代表按钮（一般用于使用按钮出园，用于园区计数使用）
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		long productId=params.getLong("ViewId");
		String codeType=params.getString("CodeType");
		String orderNo=params.getString("CodeVal");
		// 获取订单
		if("Q".equals(codeType)){
			orderInfoPO = orderDao.get(orderNo);
			//二维码验单 刘岫琳
			if(orderInfoPO != null && orderInfoPO.getProductId() == productId){
				// 判断订单状态:支付完成&&支付成功
				if(!OrderStateType.PAYFINISH.equals(orderInfoPO.getOrderState())
						&& !OrderStateType.PAYSUCCESS.equals(orderInfoPO.getOrderState())){
					ret.put("Status", 0);
					ret.put("StatusDesc", "该订单状态是" + orderInfoPO.getOrderState());
				}else{
					// 修改订单状态、验单时间
					orderInfoPO.setOrderState(OrderStateType.TESTED);
					Date date = new Date();
					orderInfoPO.setYdDate(date);
					orderDao.saveOrUpdate(orderInfoPO);
					// 返回信息
					long productNum = orderInfoPO.getProductNum();
					ret.put("Status", 1);
					ret.put("StatusDesc", "验票成功："+productNum+"人");
					ret.put("TurnGateTimes", productNum);
				}
			}else{
				ret.put("Status", 0);
				ret.put("StatusDesc", "订单信息不对！");
			}
		}else if("I".equals(codeType)){
			//身份证验单 刘岫琳
			List<AddressPO> addressPOs = addressDao.getAddressIdsByIdNum(orderNo);
			List<Long> ids = new ArrayList<Long>();
			for (AddressPO po : addressPOs) {
				ids.add(po.getId());
			}
			List<OrderInfoPO> orderInfoList = orderDao.getOrdersByIds(ids);
			if(orderInfoList != null && orderInfoList.size() > 0){
				for (OrderInfoPO orderPO : orderInfoList) {
					long productIdOrder = orderPO.getProductId();
					if(productIdOrder == productId){
						if(!OrderStateType.PAYFINISH.equals(orderPO.getOrderState())
								&& !OrderStateType.PAYSUCCESS.equals(orderPO.getOrderState())){
							ret.put("Status", 0);
							ret.put("StatusDesc", "该订单状态是" + orderPO.getOrderState());
						}else{
							// 修改订单状态、验单时间
							orderPO.setOrderState(OrderStateType.TESTED);
							Date date = new Date();
							orderPO.setYdDate(date);
							orderDao.saveOrUpdate(orderPO);
							// 返回信息
							long productNum = orderPO.getProductNum();
							ret.put("Status", 1);
							ret.put("TurnGateTimes", productNum);
							ret.put("StatusDesc", "验票成功："+productNum+"人");
						}
					}else{
						ret.put("Status", 0);
						ret.put("StatusDesc", "此订单不属于本景点！");
					}
				}
			}else{
				ret.put("Status", 0);
				ret.put("StatusDesc", "订单信息不对！");
			}
		}
		//返回json
		//验票状态	Status	是	Int	验票状态 
		//0为非法票(不开闸)；
		//1为合法票(开闸)；
		//2为合法票，但需要验证指纹(1：1验证)，指纹验证成功后开闸； 
		//3为合法票，但需要验证指纹(1：N验证) ，指纹验证成功后开闸；
		//状态描述	StatusDesc	是	String	屏幕显示的提示信息(字符过长或换行显示时，字符串中可包含\r\n)
		//如：验票成功
		//可开闸次数	TurnGateTimes	否	Int	可开闸次数，默认为1
		//一般用于团体票，刷票一次后，闸机可转动多次。
		//指纹有效天数	FPEffDays	否	int	指纹有效天数。用于指纹在设备上保存时间
		//姓名	Name	否	String	人员姓名
		//编号	Number	否	String	人员编号或证件号码等
		//部门	DeptName	否	String	人员部门或单位
		//照片地址	PhotoAdd	否	String	可浏览的Http照片地址
		
//		ret.put("Status", 1);
//		ret.put("StatusDesc", "验票成功：2人");
//		ret.put("TurnGateTimes", 2);
        return ret;
	}
	
	@ResponseBody
	@RequestMapping(value = "/IsConnect", method = RequestMethod.POST)//id为0时使用备源
	public Object isConnect(HttpServletRequest request) throws Exception{
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		Map<String, Object> ret=new HashMap<String, Object>(); 
		ret.put("success", 1);
		return ret;
		//调用是否成功	String	返回任意字符串即可，如：
		//{"success":"1"}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/GetDateTime", method = RequestMethod.POST)//id为0时使用备源
	public Object getDateTime(HttpServletRequest request) throws Exception{
		//景点ID	ViewId	string	设备上可设置
		//设备ID（或用户ID）	UId	string	设置上可设置
		//用户密码	UPassword	string	设置上可设置
		//设备序列号	SN	String	唯一
		//随机数	Random	int
		Map<String, Object> ret=new HashMap<String, Object>(); 
		ret.put("DateTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return ret;
		//日期时间	DateTime	String	格式要求为：yyyy-MM-dd HH:mm:ss
	}
	
}
