package com.guolaiwan.bussiness.gateway;

import java.io.IOException;

import org.apache.mina.core.session.IoSession;
import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.communication.tcp.TcpServer;
import pub.caterpillar.communication.tcp.component.ByteTool;
import pub.caterpillar.communication.tcp.component.TcpCallBack;

public class GateWayTcpManager implements TcpCallBack{

	private TcpServer tcpServer;
	private static final String ip="www.guolaiwan.net";
	private static final int port=2537;
	public GateWayTcpManager(){
		try {
			tcpServer=new TcpServer(ip, port, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void dealMessage(byte[] bytes, IoSession session) {
		// TODO Auto-generated method stub
		byte[] cmdByte=ByteTool.subBytes(bytes, 2, 2);
		byte[] devIdByte=ByteTool.subBytes(bytes, 6, 2);
		byte[] dataByte=ByteTool.subBytes(bytes, 16, bytes.length-16);
		long cmd=ByteTool.byteArrayToLong(cmdByte);
		System.out.println(cmd);
		/*const WORD CMD_S1DATA		= 12400;			//串口1数据上传
		DATA为串口1数据
		const WORD CMD_S2DATA		= 12401;			//串口2数据上传
		DATA为串口2数据
		const WORD CMD_S3DATA		= 12402;			//串口3数据上传
		DATA为串口3数据
		const WORD CMD_S4DATA		= 12403;			//串口4数据上传
		DATA为串口4数据
		const WORD CMD_S1SFZ		= 12500;			//身份证1数据上传
		DATA为身份证数据，*/
		if(cmd==12400){
			byte[] cmdSend=new byte[28];
			//const WORD CMD_OPENRLY = 11000;
			byte[] sHeaderByte=ByteTool.getBytes(0xFEEF);
			cmdSend[0]=sHeaderByte[0];
			cmdSend[1]=sHeaderByte[1];
			byte[] sCmdbyte=ByteTool.getBytes(11000);
			cmdSend[2]=sCmdbyte[0];
			cmdSend[3]=sCmdbyte[1];
		}
		
	}
}
