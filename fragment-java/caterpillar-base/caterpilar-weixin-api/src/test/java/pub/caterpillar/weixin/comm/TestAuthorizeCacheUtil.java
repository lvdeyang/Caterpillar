package pub.caterpillar.weixin.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.cache.ehcache.EhCacheManage;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.weixin.comm.bo.WeixinAuthorizeBO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/base_config.xml"})
public class TestAuthorizeCacheUtil extends AbstractJUnit4SpringContextTests {
	
	//缓存功能测试
	@Test
	public void test01(){
		try{
			//初始化缓存
			EhCacheManage.cacheInit();
			
			AuthorizeCacheUtil util = AuthorizeCacheUtil.getInstance();
			Long key01 = 123456l;
			
			WeixinAuthorizeBO autho = util.findAuthorizeInfo(key01);
			
			if(autho == null){
				JSONObject newInfo = new JSONObject();
				newInfo.put("openId", "123456789");
				newInfo.put("nickname", "lvdeyang");
				util.cacheAuthorizeInfo(key01, newInfo);
				autho = util.findAuthorizeInfo(key01);
				
				newInfo.put("nickname", "zhangcheng");
				util.cacheAuthorizeInfo(key01, newInfo);
				autho = util.findAuthorizeInfo(key01);
			}
			
			System.out.println("结束喽~~~");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//测试https请求
	@Test
	public void test02() throws Exception{
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "123456789");
		params.put("secret", "123456");
		params.put("code", "adasdfasdf");
		params.put("grant_type", "authorization_code");
		
		String result = HttpClient.postHttps("https://api.weixin.qq.com/sns/oauth2/access_token", params, "utf-8");
		
		System.out.println(result);
	}
	
	//测试json转换
	@Test
	public void test03(){
		A a = new A();
		a.setId("123456");
		a.setName("hehe");
		a.setArr(new ArrayList<String>(){{
			this.add("aaa");
			this.add("bbb");
		}});
		
		String aJson = JSON.toJSONString(a);
		
		A newA = JSON.parseObject(aJson, A.class);
		System.out.println("结束喽~~~");
	}
	
	//测试拉取用户信息post请求
	@Test
	public void test05() throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", "123456789");
		params.put("openid", "123456");
		params.put("lang", "zh_CN");
		
		String result = HttpClient.postHttps("https://api.weixin.qq.com/sns/userinfo", params, "utf-8");
		
		System.out.println(result);
	}
}

class A{
	private List<String> arr;
	private String id;
	private String name;
	public List<String> getArr() {
		return arr;
	}
	public void setArr(List<String> arr) {
		this.arr = arr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
