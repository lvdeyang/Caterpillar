package pub.caterpillar.app.carpool.notify;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pub.caterpillar.app.carpool.dao.DriverDAO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

@ServerEndpoint("/driver/connect/tool")
public class DriverConnectTool {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverConnectTool.class);
	
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
    	
    	if(message == null) return;
    	
    	JSONObject msg_json = JSON.parseObject(message);
    	
    	String cmdname = msg_json.getString("cmdname");
    	if(cmdname.equals("init")){
    		//初始化
    		String mobile = msg_json.getString("mobile");
    		
    		//检验是否是系统司机
    		DriverDAO conn_driver = SpringContext.getBean(DriverDAO.class);
    		DriverPO driver = conn_driver.queryByMobile(mobile);
    		if(driver != null){
    			DriverSessionManager.getInstance().add(mobile, session);
    			LOGGER.info(new StringBufferWrapper().append("司机登录：").append(mobile).append("...").toString());
    		}else{
    			//非法连接。。。
    			session.close();
    			LOGGER.info(new StringBufferWrapper().append("有一个非系统合法司机尝试连接：").append(mobile).toString());
    		}
    	}
    }
    
    @OnOpen
    public void onOpen(Session session) throws Exception {
    	session.getBasicRemote().sendText("与服务端连接成功。。。");
    }

    @OnClose
    public void onClose(Session session) {
    	DriverSessionManager.getInstance().remove(session);
    }
    
}