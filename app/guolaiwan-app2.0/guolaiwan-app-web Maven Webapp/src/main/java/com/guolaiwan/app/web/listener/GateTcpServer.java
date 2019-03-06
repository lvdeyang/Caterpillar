/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.guolaiwan.app.web.listener;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

public class GateTcpServer extends IoHandlerAdapter {

	NioSocketAcceptor acceptor;

	SocketAddress localAddress;
	
	@Autowired
	private OrderInfoDAO orderDao;
	@Autowired
	private AddressDAO addressDao;

	public void initServer(String ip, int port) throws IOException {

		acceptor = new NioSocketAcceptor();
		acceptor.setHandler(this);
		acceptor.getFilterChain().addLast("executor", new ExecutorFilter());
		@SuppressWarnings("unused")
		SocketSessionConfig scfg = acceptor.getSessionConfig();
		acceptor.bind(localAddress = new InetSocketAddress(ip, port));
		System.out.println("Server started...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		/*
		 * <WebEditTable> <EditTable> <Index>ZJLX</Index> <Value>10</Value>
		 * </CS> <CS> <Index>ZJHM</Index> <Value>深圳市公安局 </Value> </CS> <CS>
		 * <Index>ZJZP</Index> <Value>identifybase64</Value> </CS> <CS>
		 * <Index>ZJQYRQ</Index> <Value>2004.03.28 </Value> </CS> <CS>
		 * <Index>ZJZJRQ</Index> <Value>2024.03.28 </Value> </CS> <CS>
		 * <Index>XM</Index> <Value>陈正轩 </Value> </CS> <CS> <Index>MZ</Index>
		 * <Value>汉 </Value> </CS> <CS> <Index>JLSJ</Index> <Value>2018-3-28
		 * 23:06:41 </Value> </CS> <CS> <Index>Photo</Index>
		 * <Value>imagephotobase64</Value> </CS> <CS> </EditTable>
		 * </WebEditTable>
		 */

		if (message instanceof IoBuffer) {
			IoBuffer ioBuffer = (IoBuffer) message;
			byte[] b = new byte[ioBuffer.limit()];
			ioBuffer.get(b);
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				stringBuffer.append((char) b[i]);
			}
            //处理消息
			String idNum="";
			String photo="";
			String cardPic = "";
			Reader reader = new StringReader(stringBuffer.toString());
			SAXBuilder saxb = new SAXBuilder();
			Document doc;
			doc = saxb.build(reader);
			Element rootElement = (Element) doc.getRootElement();
			Element editElement=rootElement.getChild("EditTable");
			List<Element> elements=editElement.getChildren("CS");
			for (Element element : elements) {
				Element indexEle=element.getChild("Index");
				Element valueEle=element.getChild("Value");
				if(indexEle.getValue().equals("ZJLX")){
					//身份证号
					idNum=valueEle.getValue();
				}else if(indexEle.getValue().equals("Photo")){
					//现场照片
					photo=valueEle.getValue();
				}else if(indexEle.getValue().equals("ZJZP")){
					//身份证
					cardPic=valueEle.getValue();
				}
			}
			//这里比对照片和身份证验单 刘岫琳
			// 比对照片face++ 无活体检测（对比百度AI API）
			Boolean faceMatch = faceMatch(photo,cardPic);
			if(faceMatch){
				// 身份证验单
				// TODO 景区信息
				List<AddressPO> addressPOs = addressDao.getAddressIdsByIdNum(idNum);
				List<Long> ids = new ArrayList<Long>();
				for (AddressPO po : addressPOs) {
					ids.add(po.getId());
				}
				List<OrderInfoPO> orderInfoList = orderDao.getOrdersByIds(ids);
				SendMessage("1", session);
			}else{
				SendMessage("0", session);
			}
		}

	}
	
	public void SendMessage(String message, IoSession ioSession) throws UnsupportedEncodingException {
		IoSession session = ioSession;
		if (ioSession.isClosing()) {
			return;
		}
		IoBuffer SendBuffer = IoBuffer.allocate(1024);

		byte[] msg = message.getBytes("utf-8");
		int i = 0;
		int left = 0;
		while (true) {
			if (msg.length - i < SendBuffer.remaining()) {
				SendBuffer.put(msg, i, msg.length - i);
				SendBuffer.flip();
				SendBuffer.shrink();// 只改变limit
				WriteFuture writeFuture = session.write(SendBuffer.duplicate());
				writeFuture.awaitUninterruptibly();
				SendBuffer.clear();
				return;
			}
			left = SendBuffer.remaining();
			SendBuffer.put(msg, i, SendBuffer.remaining());
			i += left;
			SendBuffer.flip();
			WriteFuture writeFuture = session.write(SendBuffer.duplicate());
			writeFuture.awaitUninterruptibly();// 这句话不要去掉
			SendBuffer.clear();
		}

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("receive a session");
	}

	public void dealMessage(Object msg) {

	}
	
	/**
	 * face++
	 * @param cardPic 
	 * @param photo 
	 * @throws IOException 
	 */
	public Boolean faceMatch(String photo, String cardPic) throws IOException{
		// face++
		String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";
		List<BasicNameValuePair> formparams = new ArrayList<>();  
        formparams.add(new BasicNameValuePair("api_key", "yx1hVkTMa_gpLaUgOYzYod5yntdpyCrq"));  
        formparams.add(new BasicNameValuePair("api_secret", "h_1zwX6OMTKpCt9b2hX5VZdqOz564HWR")); 
        formparams.add(new BasicNameValuePair("image_base64_1", photo));  
        formparams.add(new BasicNameValuePair("image_base64_2", cardPic));  
        return post(formparams,url);
	}
	/** 
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
	 * @return 
     */  
    public Boolean post(List<BasicNameValuePair> formparams,String url) {  
    	Boolean flag = false;
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            // System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	/*{
                		"faces1": [{
                			"face_rectangle": {
                				"width": 58,
                				"top": 46,
                				"left": 135,
                				"height": 58
                			},
                			"face_token": "9f3015837626de83b473e858165cf2f5"
                		}],
                		"faces2": [{
                			"face_rectangle": {
                				"width": 58,
                				"top": 46,
                				"left": 135,
                				"height": 58
                			},
                			"face_token": "dabadeb0bd4e9185795f1376ad686cc3"
                		}],
                		"time_used": 721,
                		"thresholds": {
                			"1e-3": 62.327,
                			"1e-5": 73.975,
                			"1e-4": 69.101
                		},
                		"confidence": 97.389,
                		"image_id2": "Q488lxuQJ/VXonO/MccFhw==",
                		"image_id1": "Q488lxuQJ/VXonO/MccFhw==",
                		"request_id": "1551681610,60c6b919-3af9-448a-8736-16db9cf34b1c"
                	}*/
                	// confidence (Float)
                	// 比对结果置信度，范围 [0,100]，小数点后3位有效数字，数字越大表示两个人脸越可能是同一个人。
                	// 注：如果传入图片但图片中未检测到人脸，则无法进行比对，本字段不返回。
                	String res = EntityUtils.toString(entity, "UTF-8");
                	JSONObject jsonObject = JSONObject.parseObject(res);
                	Object object = jsonObject.get("confidence");
                	if(object != null){
                		Float confidence = (Float) object;
                		if(confidence > 50){
                			flag = true;
                		}
                	}
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } 
        }
		return flag;  
    }

}
