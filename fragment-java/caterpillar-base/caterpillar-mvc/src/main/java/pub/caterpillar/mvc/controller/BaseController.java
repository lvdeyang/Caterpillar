package pub.caterpillar.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.mvc.controller.exception.ExceptionController;


/**
 * 控制器基类
 * lvdeyang 2017年6月25日
 */
public class BaseController extends ExceptionController{



	@Autowired  
	private  HttpServletRequest request;  


    protected void clearSession(){
    	request.getSession().setAttribute("adminName",null);
    	
    }

	protected AdminPO getLoginInfo(){
        if(request.getSession().getAttribute("adminName")==null){
        	return null;
        }

		AdminPO l= new AdminPO();

		l.setAdminName((String)request.getSession().getAttribute("adminName"));
		l.setRoleId((int)request.getSession().getAttribute("roleid"));
		l.setCityCode((String)request.getSession().getAttribute("cityCode"));
		l.setCityName((String)request.getSession().getAttribute("cityName"));
		l.setComId((long)request.getSession().getAttribute("comId"));
		l.setComName((String)request.getSession().getAttribute("comName"));
		return l;
	}


	protected MerchantPO getMerchantInfo(){

		MerchantPO l= new MerchantPO();
		l.setMerchantName((String)request.getSession().getAttribute("merchant"));
		l.setRoleId((int)request.getSession().getAttribute("mroleid"));
		l.setMerchantId((long)request.getSession().getAttribute("merchantId"));
		l.setCityCode((String)request.getSession().getAttribute("mcityCode"));
		l.setCityName((String)request.getSession().getAttribute("mcityName"));
		l.setComId((long)request.getSession().getAttribute("mComId"));
		l.setComName((String)request.getSession().getAttribute("mComName"));
		return l;
	}

	//返回成功码
	public Map<String, Object> success(Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", StatusCode.SUCCESS.getCode());
		result.put("message", "请求成功！");
		result.put("data", data);
		return result;
	}
	//返回成功码(无参)
	public Map<String, Object> success(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", StatusCode.SUCCESS.getCode());
		result.put("message", "请求成功！");
		result.put("data", "");
		return result;
	}
	//返回无数据
	public Map<String, Object> NULLDATA(String message){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", StatusCode.NULLDATA.getCode());
		result.put("message", message);
		return result;
	}

	//返回成功码
	public Map<String, Object> successOld(Map<String, Object> result){
		result.put("status", StatusCode.SUCCESS.getCode());
		return result;
	}

	//返回错误码
	public Map<String, Object> ERROROld(Map<String, Object> result){
		result.put("status", StatusCode.ERROR.getCode());
		return result;
	}
	//返回错误码
	public Map<String, Object> ERROR(String message){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", StatusCode.ERROR.getCode());
		result.put("message", message);
		return result;
	}

	//返回拒绝码
	public Map<String, Object> FORBIDDEN(String message){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", StatusCode.FORBIDDEN.getCode());
		result.put("message", message);
		return result;
	}
	//解决中文乱码
	public static String encodeStr(String str) {  
		try {  
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
		} catch (UnsupportedEncodingException e) {  

			return null;  
		}  
	}  
	//获取总页数
	public int  GetAllPages(int allcount,int pagesize) {
		int allpages=allcount%pagesize==0?allcount/pagesize:(allcount/pagesize)+1;
		return allpages;
	}



}

