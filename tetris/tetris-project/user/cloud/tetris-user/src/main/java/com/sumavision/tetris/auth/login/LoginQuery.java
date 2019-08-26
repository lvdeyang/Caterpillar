package com.sumavision.tetris.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.config.server.ServerProps;
import com.sumavision.tetris.config.server.UserServerPropsQuery;
//import com.sumavision.tetris.mims.config.server.MimsServerPropsQuery;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserDAO;
import com.sumavision.tetris.user.UserPO;

@Component
public class LoginQuery {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserServerPropsQuery userServerPropsQuery;
	
	//@Autowired
	//private MimsServerPropsQuery mimsServerPropsQuery;
	
	/**
	 * 登录成功后重定向的页面<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月7日 下午4:19:00
	 * @param String token 登录token
	 * @return String 重定向的url
	 */
	public String queryRedirectUrl(String token) throws Exception{
		
		UserPO user = userDao.findByToken(token);
		
		String redirectUrl = null;
		if(UserClassify.INTERNAL.equals(user.getClassify())){
			ServerProps props = userServerPropsQuery.queryProps();
			redirectUrl = new StringBufferWrapper().append("http://").append(props.getIp()).append(":").append(props.getPort()).append("/index/").append(token).append("#/page-user").toString();
		}else if(UserClassify.COMPANY.equals(user.getClassify())){
			//com.sumavision.tetris.mims.config.server.ServerProps props = mimsServerPropsQuery.queryProps();
			//redirectUrl = new StringBufferWrapper().append("http://").append(props.getIp()).append(":").append(props.getPort()).append("/index/material/").append(token).toString();
		}else if(UserClassify.NORMAL.equals(user.getClassify())){
			//com.sumavision.tetris.mims.config.server.ServerProps props = mimsServerPropsQuery.queryProps();
			//redirectUrl = new StringBufferWrapper().append("http://").append(props.getIp()).append(":").append(props.getPort()).append("/index/media/picture/").append(token).toString();
		}
		
		return redirectUrl;
	}
	
}
