package com.sumavision.tetris.organization;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.organization.exception.CompanyNotExistException;
import com.sumavision.tetris.organization.exception.OrganizationNotExistException;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private OrganizationDAO organizationDao;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrganizationQuery organizationQuery;
	
	@Autowired
	private CompanyDAO companyDao;
	
	/**
	 * 查询公司内部门树<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午5:07:15
	 * @param Long companyId 公司id
	 * @return List<OrganizationVO> 树列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/query/tree")
	public Object queryTree(
			Long companyId,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//权限校验
		
		List<OrganizationVO> roots = organizationQuery.queryTree(companyId);
		
		return roots;
	}
	
	/**
	 * 添加一个组织机构<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午2:57:11
	 * @param Long companyId 公司id
	 * @param Long parentId 上级部门id
	 * @param String name 部门名称
	 * @return OrganizationVO 部门数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			Long companyId,
			Long parentId,
			String name, 
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		CompanyPO company = companyDao.findOne(companyId);
		if(company == null){
			throw new CompanyNotExistException(companyId);
		}
		
		OrganizationPO parent = null;
		if(parentId != null){
			parent = organizationDao.findOne(parentId);
			if(parent == null){
				throw new OrganizationNotExistException(parentId);
			}
		}
		
		OrganizationPO organization = new OrganizationPO();
		organization.setCompanyId(company.getId());
		organization.setName(name);
		organization.setUpdateTime(new Date());
		if(parent != null){
			organization.setParentId(parent.getId());
			StringBufferWrapper parentPath = new StringBufferWrapper();
			if(parent.getParentId() == null){
				parentPath.append("/").append(parent.getId());
			}else{
				parentPath.append(parent.getParentPath()).append("/").append(parent.getId());
			}
			organization.setParentPath(parentPath.toString());
			organization.setLevel(parent.getLevel() + 1);
		}else{
			organization.setLevel(1);
		}
		organizationDao.save(organization);
		
		return new OrganizationVO().set(organization);
	}
	
	/**
	 * 修改部门名称<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午3:18:28
	 * @param PathVariable Long id 部门id
	 * @param String name 部门名称
	 * @return OrganizationVO 部门数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		OrganizationPO organization = organizationDao.findOne(id);
		if(organization == null){
			throw new OrganizationNotExistException(id);
		}
		
		organization.setName(name);
		organization.setUpdateTime(new Date());
		
		organizationDao.save(organization);
		
		return new OrganizationVO().set(organization);
	}
	
	/**
	 * 删除部门<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月7日 下午3:40:24
	 * @param @PathVariable Long id 部门id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public Object delete(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		OrganizationPO organization = organizationDao.findOne(id);
		
		if(organization != null){
			organizationService.delete(organization);
		}
		
		return null;
	}
	
}
