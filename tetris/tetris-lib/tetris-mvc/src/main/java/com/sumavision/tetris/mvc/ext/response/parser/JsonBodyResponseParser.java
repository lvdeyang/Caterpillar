package com.sumavision.tetris.mvc.ext.response.parser;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;
import org.springframework.stereotype.Component;

/**
 * JsonBody注解返回值解析器<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年1月16日 上午11:47:52
 */
@Component
public class JsonBodyResponseParser {

	/**
	 * 将JsonBody注解返回解析为集合<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 上午11:48:38
	 * @param JSONObject response JsonBody注解返回值
	 * @param Class<T> clazz 目标转换类型
	 * @return List<T> 转换结果
	 * @throws BaseException 业务异常
	 */
	public static <T> List<T> parseArray(JSONObject response, Class<T> clazz) throws Exception{
		int status = response.getIntValue("status");
		if(status != StatusCode.SUCCESS.getCode()){
			if(response.containsKey("redirect")){
				throw new BaseException(StatusCode.fromCode(status), response.getString("message"), response.getString("redirect"));
			}else{
				throw new BaseException(StatusCode.fromCode(status), response.getString("message"));
			}
		}else{
			if(response.get("data") == null){
				return null;
			}else{
				return JSON.parseArray(JSON.toJSONString(response.get("data")), clazz);
			}
		}
	}
	
	/**
	 * 将JsonBody注解返回解析为对象<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月16日 上午11:50:25
	 * @param JSONObject response JsonBody注解返回值
	 * @param Class<T> clazz 目标转换类型
	 * @return T 转换结果
	 * @throws BaseException 业务异常
	 */
	public static <T> T parseObject(JSONObject response, Class<T> clazz) throws Exception{
		int status = response.getIntValue("status");
		if(status != StatusCode.SUCCESS.getCode()){
			if(response.containsKey("redirect")){
				throw new BaseException(StatusCode.fromCode(status), response.getString("message"), response.getString("redirect"));
			}else{
				throw new BaseException(StatusCode.fromCode(status), response.getString("message"));
			}
		}else{
			if(response.get("data") == null){
				return null;
			}else{
				return JSON.parseObject(JSON.toJSONString(response.get("data")), clazz);
			}
		}
	}
	
}
