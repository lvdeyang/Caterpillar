package pub.caterpillar.packing.web.cloud.yunpian;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.constant.YunpianConstant;

public class Yunpian implements YunpianConstant {

	static final String TESTKEY = "4122ac51216504b89ca17c0536aadf7c";
	
	private static YunpianClient client;
	
	private Yunpian(){}
	
	public static YunpianClient getClient(){
		synchronized (Yunpian.class) {
			if(client == null){
				client = new YunpianClient(Yunpian.TESTKEY, Yunpian.class.getResourceAsStream("/cloud/yunpian/yunpian_online.properties")).init();
			}
		}
		return client;
	}
	
}
