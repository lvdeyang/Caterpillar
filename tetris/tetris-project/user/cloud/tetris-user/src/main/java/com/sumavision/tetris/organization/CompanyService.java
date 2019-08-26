package com.sumavision.tetris.organization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.commons.util.binary.ByteUtil;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.listener.ServletContextListener.Path;
import com.sumavision.tetris.system.theme.SystemThemeDAO;
import com.sumavision.tetris.system.theme.SystemThemePO;
import com.sumavision.tetris.user.UserPO;
import com.sumavision.tetris.user.UserQuery;

/**
 * 公司操作（主增删改）<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月24日 上午9:58:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyService {

	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private SystemThemeDAO systemThemeDao;
	
	@Autowired
	private CompanyUserPermissionService companyUserPermissionService;
	
	@Autowired
	private UserQuery userQuery;
	
	@Autowired
	private Path path;
	
	/**
	 * 添加一个公司<br/>
	 * <b>作者:</b>Administrator<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 上午10:09:02
	 * @param String companyName 公司名
	 * @param UserPO user 创建用户
	 */
	public CompanyVO add(String companyName, UserPO user) throws Exception{
		CompanyPO company = new CompanyPO();
		company.setName(companyName);
		company.setUserId(user.getId().toString());
		company.setUpdateTime(new Date());
		companyDao.save(company);
		//加关联
		companyUserPermissionService.add(company, user);
		return new CompanyVO().set(company);
	}
	
	/**
	 * 个性化设置br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年7月8日 下午4:55:08
	 * @param Long companyId 公司id
	 * @param Long themeId 主题id
	 * @param String name 公司名称
	 * @param String homeLink 公司首页地址
	 * @param String platformFullName 平台全名
	 * @param String platformShortName 平台名缩写
	 * @param String logoStyle logo样式
	 * @param String logoShortName logo缩写名
	 * @param String fileName 文件名称
	 * @param blob logo 图片数据
	 * @return 
	 */
	public CompanyVO personalSettings(
			Long companyId,
			Long themeId,
			String name,
			String homeLink,
			String platformFullName,
			String platformShortName,
			String logoStyle,
			String logoShortName,
			String fileName,
			InputStream logo) throws Exception{
		
		CompanyPO entity = companyDao.findOne(companyId);
		if(themeId != null){
			entity.setThemeId(themeId);
		}else{
			if(entity.getThemeId() == null){
				SystemThemePO defaultTheme = systemThemeDao.findByUrl("");
				entity.setThemeId(defaultTheme.getId());
			}
		} 
		if(name != null) entity.setName(name);
		if(homeLink != null) entity.setHomeLink(homeLink);
		if(platformFullName != null) entity.setPlatformFullName(platformFullName);
		if(platformShortName != null) entity.setPlatformShortName(platformShortName);
		if(logoStyle != null) entity.setLogoStyle(logoStyle);
		if(logoShortName != null) entity.setLogoShortName(logoShortName);
		
		if(logo != null){
			String webappPath = path.webappPath();
			String storePath = new StringBufferWrapper().append(webappPath)
														.append("logos/")
														.append(entity.getName())
														.append("/")
														.append(fileName)
														.toString();
			
			String folderPath = new StringBufferWrapper().append(webappPath)
														 .append("logos/")
														 .append(entity.getName())
														 .append("/")
														 .toString();
			
			//删除已有的logo
			File folderFile = new File(folderPath);
			if(folderFile.exists()){
				File[] existLogos = folderFile.listFiles();
				if(existLogos!=null && existLogos.length>0){
					for(int i=0; i<existLogos.length; i++){
						existLogos[i].delete();
					}
				}
			}else{
				folderFile.mkdirs();
			}
			
			//写文件
			FileOutputStream out = null;
			try{
				File file = new File(storePath);
				if(!file.exists()) file.createNewFile();
				byte[] blockBytes = ByteUtil.inputStreamToBytes(logo);
				out = new FileOutputStream(file, true);
				out.write(blockBytes);
			}finally{
				logo.close();
				if(out != null) out.close();
			}
			
			String previewUrl = new StringBufferWrapper().append("/logos/")
														 .append(entity.getName())
														 .append("/")
														 .append(fileName)
														 .toString();
			entity.setLogo(previewUrl);
		}
		
		companyDao.save(entity);
		CompanyVO company = new CompanyVO().set(entity);
		SystemThemePO currentTheme = systemThemeDao.findOne(company.getThemeId());
		company.setThemeId(currentTheme.getId())
			   .setThemeName(currentTheme.getName())
			   .setThemeUrl(currentTheme.getUrl());
		
		userQuery.clearCurrentUser();
		
		return company;
	}
	
}
