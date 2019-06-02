package com.guolaiwan.app.web.webScoket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.mvc.controller.BaseController;

/**
 * webview不支持websocket 没用的类
 * @author Administrator
 *
 * @param <session>
 */
@ServerEndpoint(value = "/echo")
public class WebsocketController<session> extends BaseController{
	private String merchantId;
	private long userId;
	private static final Map<Long,Session> userSession=new HashMap<Long, Session>();
	private static final Map<String, Set<Session>> merchant = new ConcurrentHashMap<>();
	
	
	/**
	 * 打开通信通道方法
	 * 在进入商家页面时就打开
	 * @param session
	 */
	@OnOpen 
	public void open(Session session){
		merchantId=session.getQueryString().split("=")[1];
		userId=Long.parseLong(session.getQueryString().split("=")[2]);
			userSession.put(userId, session);
		System.out.println("-------"+session.getId()+"++++++++"+merchantId);
		// 将session按照房间名来存储，将各个房间的用户隔离
        if (!merchant.containsKey(merchantId)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            merchant.put(merchantId, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
        	merchant.get(merchantId).add(session);
        }
        
        
	}
	
	
	/**
	 * 关闭通道方法 
	 * 当用户退出页面时就关闭当前通道
	 * @param session
	 */
	@OnClose
	public void close(Session session){
		merchantId=session.getQueryString().split("=")[1];
		userId=Long.parseLong(session.getQueryString().split("=")[2]);
		merchant.get(merchantId).remove(session);
		userSession.remove(userId);
	}
	
	/**
	 * 获取前台聊天框中发送的信息
	 * 并调用发送的方法广播到页面
	 * @param session
	 * @param msg
	 * @throws Exception 
	 */
	@OnMessage
	public void message(Session session,String msg) throws Exception{
		System.out.println(msg);
		merchantId=session.getQueryString().split("=")[1];
		userId=Long.parseLong(session.getQueryString().split("=")[2]);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		UserInfoDAO user = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.UserInfoDAO");
		UserInfoPO userInfoPO = user.get(userId);
		String username=userInfoPO.getUserNickname();
		System.out.println(username+"------------------------");
		String relmsg=username+" "+time+"</br>"+msg;
		chat(merchantId, relmsg,userId);
		
	}
	
	
	/**
	 * 
	 * @param merchant
	 * @param msg
	 */
	public static void chat(String merchantId,String msg,long userId)throws Exception{
		
		for (Session session : merchant.get(merchantId)) {
			if(session!=userSession.get(userId)){
				session.getBasicRemote().sendText(msg);
			}
    }
	}
	
	
}
