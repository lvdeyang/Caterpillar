package com.sumavision.tetris.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.user.UserVO;
@Component
public class OrgQuery {
	
	@Autowired
	private OrgDAO orgDao;
	
	
	
	public List<OrgVO> queryOrgRoot() throws Exception {

		List<OrgPO> orgs = null;

	    orgs = orgDao.findAll();

		List<OrgVO> rootOrgs = generateRootorgs(orgs);

		return rootOrgs;
	}
	

	public List<OrgVO> queryorgTree() throws Exception {

		List<OrgPO> orgs = orgDao.findAll();
	

		List<OrgVO> rootorgs = generateRootorgs(orgs);

		packorgTree(rootorgs, orgs);

		return rootorgs;
	}
	
	
	public List<OrgVO> queryUserorgTree(long orgId) throws Exception {

		List<OrgPO> orgs = new ArrayList<OrgPO>();
		orgs.add(orgDao.findOne(orgId));
	
		List<OrgVO> rootorgs = generateUserRootorgs(orgs);

		packorgTree(rootorgs, orgs);

		return rootorgs;
	}
	

	private List<OrgVO> generateRootorgs(Collection<OrgPO> orgs) throws Exception {
		if (orgs == null || orgs.size() <= 0)
			return null;
		List<OrgVO> rootorgs = new ArrayList<OrgVO>();
		for (OrgPO org : orgs) {
			if (org.getParentId() == null) {
				rootorgs.add(new OrgVO().set(org));
			}
		}
		
		return rootorgs;
	}
	
	private List<OrgVO> generateUserRootorgs(Collection<OrgPO> orgs) throws Exception {
		if (orgs == null || orgs.size() <= 0)
			return null;
		List<OrgVO> rootorgs = new ArrayList<OrgVO>();
		for (OrgPO org : orgs) {
			
			rootorgs.add(new OrgVO().set(org));
			
		}
		
		return rootorgs;
	}
	

	public void packorgTree(List<OrgVO> rootorgs, List<OrgPO> totalorgs) throws Exception {
		if (rootorgs == null || rootorgs.size() <= 0)
			return;
		for (int i = 0; i < rootorgs.size(); i++) {
			OrgVO rootorg = rootorgs.get(i);
			List<OrgVO> orgVOs = new ArrayList<OrgVO>();
			for (int j = 0; j < totalorgs.size(); j++) {
				OrgPO org = totalorgs.get(j);
				if (org.getParentId() != null && org.getParentId() == rootorg.getId()) {
					if (rootorg.getSubOrgs() == null)
						rootorg.setSubOrgs(new ArrayList<OrgVO>());
					orgVOs.add(new OrgVO().set(org));
				}
			}
			if (orgVOs.size() > 0) {
				
				rootorg.getSubOrgs().addAll(orgVOs);
			}
			if (rootorg.getSubOrgs() != null && rootorg.getSubOrgs().size() > 0) {
				packorgTree(rootorg.getSubOrgs(), totalorgs);
			}
		}
	}
	
	public List<OrgPO> findAllSubTags(Long id) throws Exception{
		return orgDao.findAllSubColumns(new StringBufferWrapper().append("%/")
															          .append(id)
															          .toString(), 
											 new StringBufferWrapper().append("%/")
																      .append(id)
																      .append("/%")
																      .toString());
	}
	
}
