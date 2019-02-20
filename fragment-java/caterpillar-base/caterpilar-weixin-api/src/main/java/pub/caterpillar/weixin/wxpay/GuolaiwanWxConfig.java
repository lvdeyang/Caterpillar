package pub.caterpillar.weixin.wxpay;

import java.io.InputStream;

import pub.caterpillar.weixin.wxpay.IWXPayDomain.DomainInfo;


public class GuolaiwanWxConfig extends WXPayConfig implements IWXPayDomain {

	private String appId;
	private String mchId;
	private String key;

	
	public GuolaiwanWxConfig(String _appId,String _mchId,String _key) {
          appId=_appId;
          mchId=_mchId;
          key=_key;
      
	}
	
	
	@Override
	String getAppID() {
		// TODO Auto-generated method stub
		return appId;
	}

	@Override
	String getMchID() {
		// TODO Auto-generated method stub
		return mchId;
	}

	@Override
	String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	InputStream getCertStream() {
		// TODO Auto-generated method stub
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("apiclient_cert.p12");
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	public void report(String domain, long elapsedTimeMillis, Exception ex) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public DomainInfo getDomain(WXPayConfig config) {
		// TODO Auto-generated method stub
		return new DomainInfo("api.mch.weixin.qq.com", true);
	}
	
	

}
