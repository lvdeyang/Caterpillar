package pub.caterpillar.commons.handler;

public interface InvokeHandler<P, R> {

	public R invoke(P param);
	
}
