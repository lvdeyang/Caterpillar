package com.guolaiwan.bussiness.gateway;

import java.io.IOException;

import org.apache.mina.core.session.IoSession;
import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.communication.tcp.TcpServer;
import pub.caterpillar.communication.tcp.component.ByteTool;
import pub.caterpillar.communication.tcp.component.TcpCallBack;

public class GateWayTcpManager implements TcpCallBack{

	private TcpServer tcpServer;
	private static final String ip="172.17.144.244";
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
		long devId=ByteTool.byteArrayToLong(devIdByte);
		String data=new String(dataByte);
		System.out.println("recv gateway msg-cmd:"+cmd+"*deviceId:"+devId+"*data:"+data);
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
			byte[] sHeaderByte=ByteTool.getBytes((short)0xFEEF);
			cmdSend[0]=sHeaderByte[0];
			cmdSend[1]=sHeaderByte[1];
			byte[] sCmdbyte=ByteTool.getBytes((short)11000);
			cmdSend[2]=sCmdbyte[0];
			cmdSend[3]=sCmdbyte[1];
			byte[] sDevidbyte=ByteTool.getBytes((short)devId);
			cmdSend[6]=sDevidbyte[0];
			cmdSend[7]=sDevidbyte[1];
			byte[] sLenbyte=ByteTool.getBytes(28);
			cmdSend[8]=sLenbyte[0];
			cmdSend[9]=sLenbyte[1];
			cmdSend[10]=sLenbyte[2];
			cmdSend[11]=sLenbyte[3];
			/*struct LLRLY
			{
				   int opt;  //=1开继电器，=0连续开继电器
				   int rly;
				   int cnt;
			};
			Opt=1时rly=继电器，cnt=开继电器时间（单位：10毫秒）
			继电器1开门方向1，2 亮红灯，3开门方向2，4其他
			Opt=0时rly=继电器，cnt=开继电器次数，（继电器的开、关时间调用CMD_SETRLYTIME设置）*/
			byte[] optbyte=ByteTool.getBytes(1);
			byte[] cntbyte=ByteTool.getBytes(2000);
			//opt
			cmdSend[16]=optbyte[0];
			cmdSend[17]=optbyte[1];
			cmdSend[18]=optbyte[2];
			cmdSend[19]=optbyte[3];
			//cnt
			cmdSend[24]=cntbyte[0];
			cmdSend[25]=cntbyte[1];
			cmdSend[26]=cntbyte[2];
			cmdSend[27]=cntbyte[3];
			session.write(cmdSend);
		}
		
	}
}
