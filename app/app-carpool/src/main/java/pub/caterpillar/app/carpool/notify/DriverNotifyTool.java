package pub.caterpillar.app.carpool.notify;

import java.util.Collection;
import java.util.Date;

import javax.websocket.Session;

import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.commons.util.date.DateUtil;

public class DriverNotifyTool {

	private static DriverNotifyTool instance;
	
	public static DriverNotifyTool getInstance(){
		if(instance == null){
			instance = new DriverNotifyTool();
		}
		return instance;
	}
	
	public void orderNotify(Collection<String> mobiles, long orderId, Date travelTime, float addMoney, String remark) throws Exception{
		if(mobiles!=null && mobiles.size()>0){
			for(String mobile:mobiles){
				orderNotify(mobile, orderId, travelTime, addMoney, remark);
			}
		}
	}
	
	public void orderNotify(String mobile, long orderId, Date travelTime, float addMoney, String remark) throws Exception{
    	JSONObject message = new JSONObject();
    	message.put("cmdname", "/new/order");
    	message.put("orderId", orderId);
    	message.put("travelTime", DateUtil.format(travelTime, DateUtil.shortDateTimePatten));
    	message.put("addMoney", addMoney);
    	message.put("remark", remark);
    	send(mobile, message);
    }
	
	public void visitorCancelOrder(String mobile, String orderUuid) throws Exception{
		JSONObject message = new JSONObject();
    	message.put("cmdname", "/visitor/cancel/order");
    	message.put("orderUuid", orderUuid);
    	send(mobile, message);
	}
	
	public void adminDistributeSystemOrder(String mobile, String orderUuid) throws Exception{
		JSONObject message = new JSONObject();
    	message.put("cmdname", "/admin/distribute/system/order");
    	message.put("orderUuid", orderUuid);
    	send(mobile, message);
	}
	
	private void send(String mobile, JSONObject message) throws Exception{
		Session session = DriverSessionManager.getInstance().getSession(mobile);
		if(session != null) session.getBasicRemote().sendText(message.toJSONString());
	}
	
}
