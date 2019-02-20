package com.guolaiwan.app.web.chapman.source.tree.handler;

import java.util.List;
import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.handler.BaseHandler;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;

/**
 * 资源树形结构层级查询接口
 * lvdeyang  2017年4月26日
 */
public abstract class SourceFolderQueryHandler<T> extends BaseHandler<T> {
	
	protected SourceFolderDAO conn_source_folder = SpringContext.getBean(SourceFolderDAO.class);
	
	protected SourceDAO conn_source = SpringContext.getBean(SourceDAO.class);
	
	//获取资源目录
	public abstract List<SourceFolderPO> getFolders() throws Exception;
	
	//获取资源列表
	public abstract List<SourcePO> getSources() throws Exception;
	
	public SourceFolderQueryHandler(){}
	
	public SourceFolderQueryHandler(T params){
		super(params);
	}
	
}
