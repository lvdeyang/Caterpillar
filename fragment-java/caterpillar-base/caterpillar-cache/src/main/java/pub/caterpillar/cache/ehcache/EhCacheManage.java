package pub.caterpillar.cache.ehcache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import pub.caterpillar.commons.context.SpringContext;

/**
 * <P>  缓存管理 
 * ClassName: EhCacheManage 
 * @Desc	: TODO
 * @info 	: ZBX 2016年2月1日
 */
public class EhCacheManage {
	
	private static EhCacheCacheManager manager;
	
	/**
	 * <P>	缓存业务流程对象和命令结构文件。
	 * @Descr : TODO 
	 * @Info  : ZBX  2016年1月29日
	 */
	public static void cacheInit(){
		manager = SpringContext.getBean("TetrisCacheManager");
	}

	public static EhCacheCacheManager returnManager() {
		return manager;
	}
}
