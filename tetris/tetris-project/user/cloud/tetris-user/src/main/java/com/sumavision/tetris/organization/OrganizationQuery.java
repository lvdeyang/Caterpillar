package com.sumavision.tetris.organization;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 组织架构查询操作<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 下午4:43:49
 */
@Component
public class OrganizationQuery {

	@Autowired
	private OrganizationDAO organizationDao;
	
	/**
	 * 查询公司内部门树<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午5:05:26
	 * @param Long companyId 公司id
	 * @return List<OrganizationVO> 部门树列表
	 */
	public List<OrganizationVO> queryTree(Long companyId) throws Exception{
		List<OrganizationPO> organizations = organizationDao.findByCompanyIdOrderBySerialAsc(companyId);
		if(organizations==null || organizations.size()<=0) return null;
		
		List<OrganizationVO> roots = findRoot(organizations);
		packSubOrganizations(roots, organizations);
		
		return roots;
	}
	
	/**
	 * 查找根节点<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午5:03:04
	 * @param List<OrganizationPO> organizations 部门列表
	 * @return List<OrganizationVO> 根节点列表
	 */
	private List<OrganizationVO> findRoot(List<OrganizationPO> organizations) throws Exception{
		List<OrganizationVO> root = new ArrayList<OrganizationVO>();
		for(OrganizationPO organization:organizations){
			if(organization.getParentId() == null){
				root.add(new OrganizationVO().set(organization));
			}
		}
		return root;
	}
	
	/**
	 * 设置子节点<br/>
	 * <b>作者:</b>lvdeynag<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午5:02:49
	 * @param List<OrganizationVO> roots 根节点列表
	 * @param List<OrganizationPO> organizations 部门列表
	 */
	private void packSubOrganizations(List<OrganizationVO> roots, List<OrganizationPO> organizations) throws Exception{
		for(OrganizationVO root:roots){
			for(OrganizationPO organization:organizations){
				if(root.getId().equals(organization.getParentId())){
					if(root.getSub() == null) root.setSub(new ArrayList<OrganizationVO>());
					root.getSub().add(new OrganizationVO().set(organization));
				}
			}
			if(root.getSub()!=null && root.getSub().size()>0){
				packSubOrganizations(root.getSub(), organizations);
			}
		}
	}
	
}
