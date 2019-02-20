package pub.caterpillar.weixin.comm;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import pub.caterpillar.cache.ehcache.EhCacheManage;
import pub.caterpillar.weixin.comm.bo.WeixinAuthorizeBO;
import pub.caterpillar.weixin.constants.CommonConstant;
import pub.caterpillar.weixin.pay.redpack.RedpackUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ldy
 * 微信授权信息缓存相关
 */
public class AuthorizeCacheUtil {
	
	private static AuthorizeCacheUtil instance;
	
	//单例模式
	private AuthorizeCacheUtil(){}
	
	public static AuthorizeCacheUtil getInstance(){
		if(instance == null){
			instance = new AuthorizeCacheUtil();
		}
		return instance;
	}

	//根据key查找用户授权缓存  线程安全
	public WeixinAuthorizeBO findAuthorizeInfo(Long key){
		Cache authorizeCache = null;
		try{
			authorizeCache = getWeixinAuthorizeCache();
			authorizeCache.acquireReadLockOnKey(key);
			Element element = authorizeCache.get(key);
			if(element != null){
				return (WeixinAuthorizeBO)element.getObjectValue();
			}
			return null;
		}finally{
			if(authorizeCache != null){
				authorizeCache.releaseReadLockOnKey(key);
			}
		}
	}
	
	//缓存用户授权信息  线程安全  重载
	public void cacheAuthorizeInfo(Long key, JSONObject info){
		WeixinAuthorizeBO autho = JSON.parseObject(info.toJSONString(), WeixinAuthorizeBO.class);
		cacheAuthorizeInfo(key, autho);
	}
	
	//缓存用户授权信息  线程安全
	public void cacheAuthorizeInfo(Long key, WeixinAuthorizeBO info){
		Cache authorizeCache = null;
		try{
			authorizeCache = getWeixinAuthorizeCache();
			authorizeCache.acquireWriteLockOnKey(key);
			
			//先取缓存
			Element element = authorizeCache.get(key);
			if(element != null){
				WeixinAuthorizeBO autho = (WeixinAuthorizeBO)element.getObjectValue();
				
				//合并缓存
				autho.merge(info);
				autho.setCacheTime();
				authorizeCache.put(new Element(key, autho));
				
			}else{
				//直接缓存
				info.setCacheTime();
				authorizeCache.put(new Element(key, info));
			}
		}finally{
			if(authorizeCache != null){
				authorizeCache.releaseWriteLockOnKey(key);
			}
		}
	}
	
	//获取用户授权缓存
	private Cache getWeixinAuthorizeCache(){
		EhCacheCacheManager cacheManager = EhCacheManage.returnManager();
		org.springframework.cache.Cache authorizeCache = cacheManager.getCache(CommonConstant.CACHENAME_WEIXIN_AUTHORIZE);
		if(authorizeCache == null){
			//生成一个新的授权缓存
			synchronized (RedpackUtil.class) {
				cacheManager.getCacheManager().addCache(CommonConstant.CACHENAME_WEIXIN_AUTHORIZE);
				authorizeCache = cacheManager.getCache(CommonConstant.CACHENAME_WEIXIN_AUTHORIZE);
			}
		}
		return (Cache)authorizeCache.getNativeCache();
	}
}
