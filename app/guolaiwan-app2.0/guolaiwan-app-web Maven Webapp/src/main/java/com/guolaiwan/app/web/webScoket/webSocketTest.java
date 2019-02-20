package com.guolaiwan.app.web.webScoket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.AuctionDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveProductDAO;
import com.guolaiwan.bussiness.admin.po.AuctionPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;

import pub.caterpillar.commons.context.SpringContext;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/webSocket/chat/{roomName}")
public class webSocketTest {
	//@Autowired
	//private LiveMessageDAO conn_liveMessage;
	// 使用map来收集session，key为roomName，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    @OnOpen
    public void connect(@PathParam("roomName") String roomName, Session session) throws Exception {
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("a client has connected!");
    }
 
    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("a client has disconnected!");
    }
 
    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           String msg, Session session) throws Exception {
    	
        // 此处应该有html过滤
//    	JSONObject msg_json = JSON.parseObject(msg);
//    	//获取key(live-直播ID-用户ID)
//    	String key = msg_json.getString("key");
//    	String l=key.split("-")[0];
    	String liveID=roomName;
//    	String userID=key.split("-")[2];
    	String T=msg;
        //msg = session.getId() + ":" + msg;
        System.out.println(msg);
        // 接收到信息后进行广播
       
        switch(T)
        {
           case "M":
        	   broadcast(roomName, MessageJson(Long.parseLong(liveID)));
        	break;
           case "P":
        	   broadcast(roomName, AuctionJson(Long.parseLong(liveID)));
        	break;
           case "A":
        	   broadcast(roomName, LiveProductJson(Long.parseLong(liveID)));
        	break;
        	default:
        		broadcast(roomName, "没有消息");
        		break;
        }
        
       
        
    }
 
    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
                session.getBasicRemote().sendText(msg);
        }
    }
    
    
    //发送消息
    private String MessageJson(Long liveId)
    {
    	
    	JSONObject sendJson = new JSONObject();
    	
    	LiveMessageDAO  conn_liveMessage = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.LiveMessageDAO");
    	try{
    	List<LiveMessagePO> liveMessages = conn_liveMessage.findByFlag(liveId);
    	if(liveMessages!=null&&liveMessages.size()>0){
			for (LiveMessagePO liveMessagePO : liveMessages) {
				liveMessagePO.setFlag(true);
				conn_liveMessage.update(liveMessagePO);
			}
			sendJson.put("liveMessages", liveMessages);
		}
    	}catch(Exception e)
		{
			sendJson.put("error","500");
		}
    	if(sendJson.size()<=0){
       		sendJson.put("error","400");
       	}
    	return sendJson.toJSONString();
    }
    //拍卖
    private String AuctionJson(Long liveId)
    {
    	JSONObject sendJson = new JSONObject();
    	AuctionDAO   conn_auction = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.AuctionDAO");
    	List<AuctionPO> auctions  = conn_auction.findByFlag(liveId);
    	try{
    	if(auctions!=null&&auctions.size()>0){
			for (AuctionPO auctionPO : auctions) {
				auctionPO.setFlag(true);
				conn_auction.update(auctionPO);
			}
			sendJson.put("auctions", auctions);
		}
    	}catch(Exception e)
		{
			sendJson.put("error","500");
		}
    	if(sendJson.size()<=0){
       		sendJson.put("error","400");
       	}
    	return sendJson.toJSONString();
    }
    //产品添加
    private String LiveProductJson(Long liveId)
    {
    	JSONObject sendJson = new JSONObject();
    	LiveProductDAO  conn_liveProduct = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.LiveProductDAO");
    	
    	try{
    		//直播的商品，信息，拍卖信息
			List<LiveProductPO> liveProducts = conn_liveProduct.findByFlag(liveId);
			if(liveProducts!=null&&liveProducts.size()>0){
				for (LiveProductPO liveProductPO : liveProducts) {
					liveProductPO.setFlag(true);
					
					if(liveProductPO.isLocked())
					{
						sendJson.put("CJ", liveProductPO);
					}
					
					if(liveProductPO.getProductIsDel().equals("1"))
					{
						sendJson.put("PL", "404");
						conn_liveProduct.delete(liveProductPO);
					}
					else{
					conn_liveProduct.update(liveProductPO);
					}
				}
				sendJson.put("liveProducts", liveProducts);
			}
    	}catch(Exception e)
		{
			sendJson.put("error","500");
		}
    	if(sendJson.size()<=0){
       		sendJson.put("error","400");
       	}
    	return sendJson.toJSONString();
       	
    }

}
