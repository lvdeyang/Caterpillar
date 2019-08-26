package com.sumavision.tetris.user;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumavision.tetris.commons.util.encoder.MessageEncoder.Sha256Encoder;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

@Component
public class BasicDevelopmentQuery {

	@Autowired
	private BasicDevelopmentDAO basicDevelopmentDao;
	
	@Autowired
	private Sha256Encoder sha256Encoder;
	
	/**
	 * 根据开发者id查询开发者基础配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午1:52:33
	 * @param String appId 开发者id
	 * @return BasicDevelopmentVO 开发者基础配置
	 */
	public BasicDevelopmentVO findByAppId(String appId) throws Exception{
		BasicDevelopmentPO entity = basicDevelopmentDao.findByAppId(appId);
		BasicDevelopmentVO basicDevelopment = new BasicDevelopmentVO().set(entity);
		basicDevelopment.setAppSecret(entity.getAppSecret())
					    .setUserId(entity.getUserId());
		return basicDevelopment;
	}
	
	/**
	 * 数字签名<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月22日 下午3:51:59
	 * @param String appId 开发者id
	 * @param String timestamp 时间戳
	 * @param String appSecret 开发者密码
	 * @return String 签名
	 */
	public String sign(String appId, String timestamp, String appSecret) throws Exception{
		List<String> resources = new ArrayListWrapper<String>().add(appId)
															   .add(timestamp)
															   .add(appSecret)
															   .getList();
		Collections.sort(resources, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String unSigned = new StringBufferWrapper().append(resources.get(0))
												   .append(resources.get(1))
												   .append(resources.get(2))
												   .toString();
		return sha256Encoder.encode(unSigned);
	}
	
	public static void main(String[] args) throws Exception{
		String appId = "327a89ed0afd0042880af8b0b98b6218ee64";
		String timestamp = "1555925360574";
		String appSecret = "sumavisionrd";
		String sign = "7c20ec619dd34e212a46e8c9b66ef3eb471a5139a72d9b7cde210be6965b5e70";
		
		List<String> resources = new ArrayListWrapper<String>().add(appId)
															   .add(timestamp)
															   .add(appSecret)
															   .getList();
		Collections.sort(resources, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String unSigned = new StringBufferWrapper().append(resources.get(0))
												   .append(resources.get(1))
												   .append(resources.get(2))
												   .toString();
		System.out.println(new Sha256Encoder().encode(unSigned));
	}
	
}
