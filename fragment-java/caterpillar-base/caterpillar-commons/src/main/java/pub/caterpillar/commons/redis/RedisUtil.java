package pub.caterpillar.commons.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {

	/**
	 * Redis服务器IP
	 */
	private static String ADDR = "www.guolaiwan.net";
	
	/**
	 * Redis的端口号
	 */
	private static int PORT = 1935;
	
	/**
	 * 访问密码
	 */
	private static String AUTH = "guolaiwan99";
	
	/**
	 * 可用连接实例的最大数目,默认值为8;
	 * 如果赋值为-1，则表示不限制;
	 * 如果pool已经分配了maxActive个jedis实例,则此时pool的状态为exhausted(耗尽).
	 */
	private static int MAX_ACTIVE = 8;
	
	/**
	 * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例,默认值也是8.
	 */
	private static int MAX_IDLE = 8;
	
	/**
	 * 等待可用连接的最大时间,单位毫秒,默认值为-1,表示永不超时.
	 * 如果超过等待时间,则直接抛出JedisConnectionException.
	 */
	private static long MAX_WAIT = 5000;
	
	/**
	 * 最大延迟时间【单位：毫秒】
	 */
	private static int TIMEOUT = 5000;
	
	/**
	 * 在borrow一个jedis实例时,是否提前进行validate操作;
	 * 如果为true,则得到的jedis实例均是可用的;
	 */
	private static boolean TEST_ON_BORROW = true;
	
	/**
	 * 控制是否开启链接验证操作;
	 * 如果为true,则在链接redis服务器时需要开启验证
	 */
	private static boolean TEST_ON_VALIDATE = false;

	/**
	 * 定义一个Redis链接池
	 */
	private static JedisPool jedisPool = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			if(TEST_ON_VALIDATE) {
				jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
			} else {
				jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 异常回收jedis资源
	 * 
	 * @param jedis
	 * @author TaoNingBo
	 */
	public synchronized static void returnBrokenResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}
	
	/** 
     * 正常回收Jedis对象资源 
     *  
     * @param jedis 
     */  
	  public synchronized static void returnResource(Jedis jedis) {  
	        if (jedis != null) {  
	            jedisPool.returnResource(jedis);  
	        }  
	    }  
	
	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set("测试", "zadi");
		System.out.println(jedis.get("测试"));
	}
}
