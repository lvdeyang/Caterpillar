package pub.caterpillar.commons.util.sequence;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 内存序列
 * lvdeyang 2017年6月12日
 */
public class MemorySequence {
	
	final AtomicLong sequenceGenerator = new AtomicLong(0);
	
	public Long generate(){
		return sequenceGenerator.getAndIncrement();
	}
}
