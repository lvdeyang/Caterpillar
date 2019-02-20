package pub.caterpillar.weixin.pay.redpack;

import java.net.InetAddress;
import java.net.UnknownHostException;
import pub.caterpillar.weixin.comm.ConfigUtil;
import pub.caterpillar.weixin.comm.bo.WeixinConfigBO;
import pub.caterpillar.weixin.pay.redpack.bo.RedpackBO;

public class RedpackUtil {
	
	public static void main(String[] args) throws UnknownHostException{
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	}
	
	private static RedpackUtil instance;
	
	//单例模式
	private RedpackUtil(){}
	
	public static RedpackUtil getInstance(){
		if(instance == null){
			instance = new RedpackUtil();
		}
		return instance;
	}
	
	public void sendRedpack(Long key, RedpackBO redpack) throws Exception{
		//获取微信配置
		WeixinConfigBO config_weixin = ConfigUtil.getInstance().getWeixinConfigBean();
		
		//获取本机ip
		InetAddress.getLocalHost().getHostAddress();
	}

}
