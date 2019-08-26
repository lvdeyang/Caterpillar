package com.sumavision.tetris.commons.init;

import com.sumavision.tetris.commons.context.SystemInitialization;

public class SystemPropertiesInitialization implements SystemInitialization{

	@Override
	public int index() {
		return 0;
	}

	@Override
	public void init() {
		
		//系统默认写文件内容编码
		System.setProperty("file.encoding", "utf-8");
		
		//系统创建文件文件名默认编码
		System.setProperty("sun.jnu.encoding", "utf-8");
		
	}

}
