package com.guolaiwan.app.web.chapman.source.tree.handler.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.chapman.source.tree.handler.SourceFolderQueryHandler;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

public class ChapmanFoldersAndPicturesQueryHandler extends SourceFolderQueryHandler<JSONObject> {

	public ChapmanFoldersAndPicturesQueryHandler(){}
	
	public ChapmanFoldersAndPicturesQueryHandler(JSONObject params){
		super(params);
	}
	
	@Override
	public List<SourceFolderPO> getFolders() throws Exception {
		
		JSONObject params = this.getParams();
		
		ChapmanPO chapman = params.getObject("chapman", ChapmanPO.class);
		
		SourceFolderPO folder = params.getObject("folder", SourceFolderPO.class);
		
		List<SourceFolderPO> folders = conn_source_folder.getSubFolders(chapman.getId(), folder.getId());
		
		return folders;
	}

	@Override
	public List<SourcePO> getSources() throws Exception {
		
		JSONObject params = this.getParams();
		
		SourceFolderPO folder = params.getObject("folder", SourceFolderPO.class);
		
		List<SourcePO> pictures = conn_source.getFolderPictures(folder.getId());
		
		return pictures;
	}

}
