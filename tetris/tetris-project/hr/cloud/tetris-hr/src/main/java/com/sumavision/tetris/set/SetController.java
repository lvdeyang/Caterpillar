package com.sumavision.tetris.set;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.org.OrgDAO;
import com.sumavision.tetris.org.OrgPO;
import com.sumavision.tetris.org.OrgQuery;
import com.sumavision.tetris.org.OrgService;
import com.sumavision.tetris.org.OrgUserDAO;
import com.sumavision.tetris.org.OrgUserPO;
import com.sumavision.tetris.org.OrgVO;
import com.sumavision.tetris.sche.ScheDAO;
import com.sumavision.tetris.sche.SchePO;
import com.sumavision.tetris.sche.ScheVO;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

import javassist.expr.NewArray;

@Controller
@RequestMapping(value = "/hr/set")
public class SetController {

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
	
	
	@Autowired
	private OrgUserDAO orgUserDao;
	
	@Autowired
	private SetDAO setDao;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list/user/{orgId}")
	public Object listUser(@PathVariable long orgId,int year,int month,HttpServletRequest request) throws Exception {
		Map<Integer,String> dic=new HashMapWrapper().put(1,"yi")
			.put(2, "er").put(3, "san").put(4, "si").put(5, "wu").put(6, "liu")
			.put(7, "qi").put(8, "ba").put(9, "jiu").put(10, "shi").put(11, "shiyi")
			.put(12, "shier").put(13, "shisan").put(14, "shisi").put(15, "shiwu").put(16, "shiliu")
			.put(17, "shiqi").put(18, "shiba").put(19, "shijiu")
			.put(20, "ershi").put(21, "eryi").put(22, "erer").put(23, "ersan").put(24, "ersi")
			.put(25, "erwu").put(26, "erliu").put(27, "erqi").put(28, "erba").put(29, "erjiu")
			.put(30, "sanshi").put(31, "sanyi")
			.getMap();

		Map<String, Object> ret=new HashMap<String, Object>();
		List<Long> ids=new ArrayList<Long>();
		List<OrgUserPO> orgUserPOs=orgUserDao.findByOrgId(orgId);
	    for (OrgUserPO orgUserPO : orgUserPOs) {
			ids.add(orgUserPO.getUserId());
		}
	    List<UserVO> userVOs=userQuery.findByIdIn(ids);
	    List<Map<String, Object>> userMaps=new ArrayList<Map<String,Object>>();
	    List<Map<String, Object>> daysMaps=new ArrayList<Map<String,Object>>();
	    int count=DateUtil.getDaysByYearMonth(year, month);
		for(int i=0;i<count;i++){
			Map<String, Object> daysMap=new HashMap<String, Object>();
			daysMap.put("name", i+1);
			daysMap.put("title", dic.get(i+1));
			int week=DateUtil.getDayOfWeek(DateUtil.parse(year+"-"+month+"-"+
			((i+1+"").length()==1?("0"+(i+1)):(i+1+"")),"yyyy-MM-dd"));
			daysMap.put("week", week);
            daysMaps.add(daysMap);
		}
	    for (UserVO userVO : userVOs) {
			Map<String, Object> userMap=new HashMap<String, Object>();
			userMap.put("name", userVO.getNickname());
			userMap.put("code",userVO.getCode());
			userMap.put("id",userVO.getId());
			for (Map<String, Object> map : daysMaps) {
				int current=Integer.parseInt(map.get("name").toString());
				SetPO setPO=setDao.findOneByUserIdAndCurDate(userVO.getId(),
						DateUtil.parse(year+"-"+month+"-"+
								((current+"").length()==1?("0"+current):(current+"")),"yyyy-MM-dd"));
				if(setPO==null){
					userMap.put(map.get("title").toString(), "");
				}else{
					userMap.put(map.get("title").toString(), setPO.getScheName());
				}
			}
			userMaps.add(userMap);
		}
	    ret.put("userMaps", userMaps);
	    ret.put("daysMaps", daysMaps);
        return ret;
	}
	

	@Autowired
	ScheDAO scheDao;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/set")
	public Object doset(
			Long sche,
			String start,
			String end,
			Long userId,
			HttpServletRequest request) throws Exception{
		
		
	    List<Date> list=DateUtil.getDates(start, end);
	    for (Date date : list) {
	    	SetPO setPO=new SetPO();
	    	setPO.setCurDate(date);
	    	setPO.setUserId(userId);
	    	SchePO schePo=scheDao.findOne(sche);
	    	if(schePo!=null){
	    		setPO.setScheName(schePo.getName());
		    	setPO.setStart(schePo.getStart());
		    	setPO.setEnd(schePo.getEnd());
		    	setDao.save(setPO);
	    	}
	    	
		}
		return null;
		
	}	
	
	

}
