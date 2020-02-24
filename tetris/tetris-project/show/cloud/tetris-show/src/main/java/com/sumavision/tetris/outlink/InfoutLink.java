package com.sumavision.tetris.outlink;

import org.springframework.stereotype.Component;

/**
 * @Author sjj
 * @Description //TODO 人员信息调用
 * @Date
 **/
@Component
public class InfoutLink {

	/**
		 * 更改员工信息<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月11日  
		 * @param jsonString
		 * @return
		 */
	public String updateInfo(String jsonString) {
		// TODO Auto-generated method stub
	     String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.UPDATEINFO,jsonString,null);
	     return response;
	}

	/**
		 * 删除员工信息<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月11日  
		 * @param woprkerId
		 * @return
		 */
	public String deleteInfo(String params) {
		// TODO Auto-generated method stub
		String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.DELETEINFO,params,null);
	    return response;
	}

	/**
		 *添加员工信息<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月11日  
		 * @param jsonString
		 * @return
		 */
	public String addInfo(String jsonString) {
		// TODO Auto-generated method stub
		   String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.ADDINFO,jsonString,null);
		return response;
	}
}
