package com.sumavision.tetris.organization;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.mvc.wrapper.MultipartHttpServletRequestWrapper;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private CompanyQuery companyQuery;
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 分页查询公司<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 上午11:44:44
	 * @param int currentPage 当前页码
	 * @param int pageSize 每页数据量
	 * @return int total 总数据量
	 * @return List<CompanyVO> rows 公司列表
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			int currentPage,
			int pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		return companyQuery.list(currentPage, pageSize);
	}
	
	/**
	 * 获取用户公司信息<br/>
	 * <b>作者:</b>lzp<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年5月24日 下午4:54:11
	 * @return CompanyVO 修改后的数据
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/subordinate")
	public Object company(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		CompanyVO company = null;
		if(user.getId() != null){
			company = companyQuery.findByUserId(user.getId());
		}else if(user.getUuid() != null){
			company = companyQuery.findByUserId(Long.parseLong(user.getUuid()));
		}
		
		return company;
	}
	
	/**
	 * 个性化设置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月8日 下午4:03:00
	 * @param Long companyId 公司id
	 * @param Long themeId 主题id
	 * @param String name 公司名称
	 * @param String homeLink 公司首页地址
	 * @param String platformFullName 平台全名
	 * @param String platformShortName 平台名缩写
	 * @param String logoStyle logo样式
	 * @param String logoShortName logo缩写名
	 * @param blob logo 图片数据
	 * @return
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/persional/settings")
	public Object personalSettings(HttpServletRequest nativeRequest) throws Exception{
		
		MultipartHttpServletRequestWrapper request = new MultipartHttpServletRequestWrapper(nativeRequest);
		
		Long companyId = request.getLong("companyId");
		Long themeId = null;
		if(request.contains("themeId")) themeId = request.getLong("themeId");
		String name = null;
		if(request.contains("name")) name = request.getString("name");
		String homeLink = null;
		if(request.contains("homeLink")) homeLink = request.getString("homeLink");
		String platformFullName = null;
		if(request.contains("platformFullName")) platformFullName = request.getString("platformFullName");
		String platformShortName = null;
		if(request.contains("platformShortName")) platformShortName = request.getString("platformShortName");
		String logoStyle = null;
		if(request.contains("logoStyle")) logoStyle = request.getUnDecodeString("logoStyle");
		String logoShortName = null;
		if(request.contains("logoShortName")) logoShortName = request.getString("logoShortName");
		
		String fileName = null;
		if(request.contains("fileName")) fileName = request.getString("fileName");
		
		InputStream logo = null;
		if(request.contains("file")) logo = request.getInputStream("file");
		
		return companyService.personalSettings(companyId, themeId, name, homeLink, platformFullName, platformShortName, logoStyle, logoShortName, fileName, logo);
	}
	
}
