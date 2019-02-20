package pub.caterpillar.commons.handler;

/**
 * handler 参数处理：避免用final变量
 * lvdeyang 2017年4月26日
 */
public class BaseHandler<T> {
	
	private T params;
	
	public BaseHandler(){}
	
	public BaseHandler(T params){
		this.params = params;
	}

	public T getParams() {
		return params;
	}
	
	public void setParams(T params){
		this.params = params;
	}
}
