package com.guolaiwan.app.web.chapman.source.tree.handler.impl;

import java.util.ArrayList;
import java.util.List;

import com.guolaiwan.app.web.chapman.source.tree.handler.SourceFolderQueryHandler;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.exception.RootFolderNotFondException;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

public class ChapmanRootFolderQueryHandler extends SourceFolderQueryHandler<ChapmanPO> {

	public ChapmanRootFolderQueryHandler(){}
	
	public ChapmanRootFolderQueryHandler(ChapmanPO chapman){
		super(chapman);
	}
	
	@Override
	public List<SourceFolderPO> getFolders() throws Exception {
		ChapmanPO chapman = this.getParams();
		SourceFolderPO rootFolder = this.conn_source_folder.getRootFolder(chapman.getId());
		
		if(rootFolder == null){
			throw new RootFolderNotFondException(chapman.getChapmanname());
		}
		
		List<SourceFolderPO> folders = new ArrayList<SourceFolderPO>();
		folders.add(rootFolder);
		
		return folders;
	}

	@Override
	public List<SourcePO> getSources() {
		return null;
	}

}
