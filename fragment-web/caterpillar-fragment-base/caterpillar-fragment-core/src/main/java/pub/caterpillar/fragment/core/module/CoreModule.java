package pub.caterpillar.fragment.core.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.fragment.core.module.base.CaterpillarModule;

public class CoreModule extends CaterpillarModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoreModule.class);
	
	@Override
	public int getIndex() {
		return -1000;
	}
	
	@Override
	public void init() {
		
	}

}
