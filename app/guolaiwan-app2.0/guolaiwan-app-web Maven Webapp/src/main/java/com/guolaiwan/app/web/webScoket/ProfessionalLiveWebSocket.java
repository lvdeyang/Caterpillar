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
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMessageDAO;
import com.guolaiwan.bussiness.admin.po.AuctionPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveMessagePO;

import pub.caterpillar.commons.context.SpringContext;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ProfessionalWebSocket/chat/{roomName}")
public class ProfessionalLiveWebSocket {

    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();
    
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
        System.out.println("专业直播:用户连接");
    }
 
    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           String msg, Session session) throws Exception {
    	String liveID=roomName;
    	String T = msg;
        // 接收到信息后进行广播
        switch(T){
           case "M":
        	   broadcastMessage(roomName, MessageToJson(Long.parseLong(liveID)));
        	break;
        	default:
        		broadcastMessage(roomName, "没有消息");
        		break;
        }  
    }
 
    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("专业直播:用户下线");
    }
    
    
   /**
    * 广播消息
    * @param roomName
    * @param msg
    * @throws Exception
    */
    public static void broadcastMessage(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
                session.getBasicRemote().sendText(msg);
        }
    }
    
    
    /**
     * 消息转换为json格式
     * @param liveId
     * @return
     */
    private String MessageToJson(Long liveId){
    	JSONObject sendJson = new JSONObject();
    	ProfessionalLiveMessageDAO  conn_professionalLiveMessage = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMessageDAO");
    	try{
	    	List<ProfessionalLiveMessagePO> liveMessages = conn_professionalLiveMessage.findByFlag(liveId);
	    	if(liveMessages != null && liveMessages.size() > 0){
				for (ProfessionalLiveMessagePO liveMessagePO : liveMessages) {
					liveMessagePO.setFlag(true);
					conn_professionalLiveMessage.update(liveMessagePO);
				}
				sendJson.put("liveMessages", liveMessages);
			}
    	}catch(Exception e){
			sendJson.put("error","500");
		}
    	if(sendJson.size() <= 0){
       		sendJson.put("error","400");
       	}
    	return sendJson.toJSONString();
    }
}
