package com.sumavision.tetris.org;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/hr/org")
public class OrgController {

	@Autowired
	private UserQuery userQuery;

	@Autowired
	private OrgDAO orgDao;

	@Autowired
	private OrgQuery orgQuery;

	@Autowired
	private OrgService orgService;
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/tree")
	public Object listTree(HttpServletRequest request) throws Exception {

		UserVO user = userQuery.current();

		List<OrgVO> rootOrgs = orgQuery.queryorgTree();

		return rootOrgs;
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add/root")
	public Object addRoot(HttpServletRequest request) throws Exception {

		OrgPO org = orgService.addRoot();

		return new OrgVO().set(org);
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/append")
	public Object append(Long parentId, HttpServletRequest request) throws Exception {

		UserVO user = userQuery.current();

		OrgPO parent = orgDao.findOne(parentId);
		
		OrgPO org = orgService.append(parent);

		return new OrgVO().set(org);
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/update/{id}")
	public Object update(@PathVariable Long id, String name, HttpServletRequest request)
			throws Exception {


		OrgPO orgPO = orgDao.findOne(id);
	
		orgPO = orgService.update(orgPO, name);

		return new OrgVO().set(orgPO);
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(@PathVariable Long id, HttpServletRequest request) throws Exception {

		UserVO user = userQuery.current();

		OrgPO orgPO = orgDao.findOne(id);

		if (orgPO != null) {
			orgService.remove(orgPO);
		}

		return null;
	}
	
	@Autowired
	private OrgUserDAO orgUserDao;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/user/{orgId}")
	public Object listUser(@PathVariable long orgId,HttpServletRequest request) throws Exception {
		List<Long> ids=new ArrayList<Long>();
		List<OrgUserPO> orgUserPOs=orgUserDao.findByOrgId(orgId);
	    for (OrgUserPO orgUserPO : orgUserPOs) {
			ids.add(orgUserPO.getUserId());
		}
	    List<UserVO> userVOs=userQuery.findByIdIn(ids);
        return userVOs;
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add/user")
	public Object addUser(String name,String code,Long orgId,String mobile,String mail,HttpServletRequest request) throws Exception {
		Long userId=userQuery.add(name, Long.parseLong(userQuery.current().getGroupId()), "111111", mobile, mail, UserClassify.COMPANY.getName(),code);
        OrgUserPO orgUserPO=new OrgUserPO();
        orgUserPO.setOrgId(orgId);
        orgUserPO.setUserId(userId);
        orgUserDao.save(orgUserPO);
		return null;
	}
	
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delete/user")
	public Object deleteUser(long id,HttpServletRequest request) throws Exception {
		orgService.deleteUser(id);
		return null;
	}

}
