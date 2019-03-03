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
import java.util.Base64;
import java.util.List;

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

public class GateTcpServer extends IoHandlerAdapter {

	NioSocketAcceptor acceptor;

	SocketAddress localAddress;

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
					//身份证
					photo=valueEle.getValue();
				}
			}
			//这里比对照片和身份证验单 刘岫琳
			SendMessage("1", session);
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

}
