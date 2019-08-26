package com.sumavision.tetris.mvc.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import com.sumavision.tetris.mvc.ext.context.HttpSessionContext;

/**
 * session监听器<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月6日 上午10:37:13
 */
@WebListener
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		HttpSessionContext.put(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		HttpSessionContext.remove(session);
	}

}
