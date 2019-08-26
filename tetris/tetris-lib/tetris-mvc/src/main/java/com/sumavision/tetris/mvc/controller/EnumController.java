package com.sumavision.tetris.mvc.controller;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumavision.tetris.commons.constant.DataType;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 枚举查询<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年3月22日 上午8:40:22
 */
@Controller
@RequestMapping(value = "/enum")
public class EnumController {

	/**
	 * 查询数据类型枚举<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月22日 上午8:38:28
	 * @return 
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/data/type")
	public Object dataType(HttpServletRequest request) throws Exception{
		Set<String> dataTypes = new HashSet<String>();
		DataType[] values = DataType.values();
		for(DataType value:values){
			dataTypes.add(value.getName());
		}
		return dataTypes;
	}
	
}
