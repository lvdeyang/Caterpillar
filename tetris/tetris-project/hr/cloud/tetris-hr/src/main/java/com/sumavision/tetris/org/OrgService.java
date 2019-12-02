package com.sumavision.tetris.org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.util.wrapper.HashSetWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;

/**
 * 内容模板增删改操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年2月18日 下午1:31:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgService {

	@Autowired
	private OrgDAO orgDao;	

	@Autowired
	private OrgQuery orgQuery;
	
	

	public OrgPO addRoot() throws Exception {

		OrgPO orgPO = new OrgPO();
		orgPO.setName("新建的标签");
		orgPO.setUpdateTime(new Date());
		orgDao.save(orgPO);
		return orgPO;
	}

	public OrgPO append(OrgPO parent) throws Exception {

		StringBufferWrapper parentPath = new StringBufferWrapper();
		if (parent.getParentId() == null) {
			parentPath.append("/").append(parent.getId());
		} else {
			parentPath.append(parent.getParentPath()).append("/").append(parent.getId());
		}

		OrgPO orgPO = new OrgPO();
		orgPO.setName("新建的标签");
		orgPO.setParentId(parent.getId());
		orgPO.setParentPath(parentPath.toString());
		orgPO.setUpdateTime(new Date());

		List<OrgPO> orgVOs = orgDao.findByParentId(parent.getId());

		orgDao.save(orgPO);

		return orgPO;
	}

	public OrgPO update(OrgPO orgPO, String name) throws Exception {
		orgPO.setName(name);
		orgPO.setUpdateTime(new Date());
		orgDao.save(orgPO);
		return orgPO;
	}

	public void remove(OrgPO orgPO) throws Exception {
        List<OrgPO> subOrgPOs = orgQuery.findAllSubTags(orgPO.getId());
		if (subOrgPOs == null)
			subOrgPOs = new ArrayList<OrgPO>();
		subOrgPOs.add(orgPO);
		orgDao.deleteInBatch(subOrgPOs);

	}

}
