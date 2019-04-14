/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.tcp.TcpManagerInterface
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp;

import org.apache.mina.core.session.IdleStatus;


public interface ITcpManager {
  TcpClient getOneConnect(Object key) throws Exception;
  
  
  void reconnectAll();
  
  void shutDown(Object key) throws InterruptedException;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.exceptionCaught
 * @Description:
 *
 * @param key
 * @param cause
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 涓嬪崍04:37:41
 *
 */
void exceptionCaught(Object key, Throwable cause) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.messageReceived
 * @Description:
 *
 * @param key
 * @param message
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:37:46
 *
 */
void messageReceived(Object key, String message) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.messageSent
 * @Description:
 *
 * @param key
 * @param message
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:38:32
 *
 */
void messageSent(Object key, Object message) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.sessionClosed
 * @Description:
 *
 * @param key
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:38:37
 *
 */
void sessionClosed(Object key) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.sessionCreated
 * @Description:
 *
 * @param key
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:38:41
 *
 */
void sessionCreated(Object key) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.sessionIdle
 * @Description:
 *
 * @param key
 * @param status
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:38:46
 *
 */
void sessionIdle(Object key, IdleStatus status) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.sessionOpened
 * @Description:
 *
 * @param key
 * @throws Exception
 *
 * @author:zhuzheng
 * @date:2014-3-26 04:38:51
 *
 */
void sessionOpened(Object key) throws Exception;


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.alarmReceived
 * @Description:
 *
 * @param key
 * @param msg
 *
 * @author:zhuzheng
 * @date:2014-4-15 07:55:07
 *
 */
void alarmReceived(Object key, String msg);


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.notifyOnline
 * @Description:
 *
 * @param key
 *
 * @author:zhuzheng
 * @date:2014-4-24 09:06:31
 *
 */
void notifyOnline(Object key);


/**
 * 
 * @Function: platform.communication.tcp.TcpManagerInterface.notifyOffline
 * @Description:
 *
 * @param key
 *
 * @author:zhuzheng
 * @date:2014-4-24 09:06:42
 *
 */
void notifyOffline(Object key);



/**
 * 
 * @Function: platform.communication.tcp.ITcpManager.createClient
 * @Description:
 *
 * @param localHost
 * @param remoteHost
 * @param remotePort
 *
 * @author:zhuzheng
 * @throws Exception 
 * @date:2014-6-12 10:11:46
 *
 */
TcpClient createClient(Object key, String localHost, String remoteHost,
                       int remotePort) throws Exception;





/**
 * 
 * @Function: platform.communication.tcp.ITcpManager.recoverReceived
 * @Description:
 *
 * @param key
 * @param msg
 *
 * @author:zhuzheng
 * @date:2014-6-12 10:44:28
 *
 */
void recoverReceived(Object key, String msg);


/**
 * 
 * @Function: platform.communication.tcp.ITcpManager.sendMsg
 * @Description:
 *
 * @param key
 * @param msgId
 * @param msg
 * @return

 *
 * @author:zhuzheng
 * @throws Exception 
 * @date:2014-6-12 01:59:06
 *
 */
String sendMsg(Object key, Long msgId, String msg) throws Exception;

String sendMsg(Object key, Long msgId, String msg, boolean msgSet) throws Exception;


/**
 * @Function: platform.communication.tcp.ITcpManager.isOnline
 * @Description:
 * @param key
 * @return
 * @author:zhuzheng
 * @throws Exception 
 * @date:20143:04:33
 */
boolean isOnline(Object key) throws Exception;

/**
 * 
 * @param key
 * @param msg
 */
void requestMsgReceived(Object key, String msg);


boolean sendByteMsg(Object key, byte[] msg, boolean msgSet) throws Exception;


void statusMsgReceived(Object key, byte[] msg);

}
