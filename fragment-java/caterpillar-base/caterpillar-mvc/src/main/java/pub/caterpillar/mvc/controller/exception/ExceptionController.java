package pub.caterpillar.mvc.controller.exception;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

/**
 * 总异常处理
 * lvdeyang 2017年6月25日
 */
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler  
    public void doException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {  
		
		PrintWriter writer = null;
		
		try{
        	
			ex.printStackTrace();
			
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			
			writer = response.getWriter();
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(ex instanceof BaseException){
				BaseException bex = (BaseException)ex;
				result.put("status", bex.getCode().getCode());
				result.put("message", bex.getMessage());
			}else{
				result.put("status", StatusCode.ERROR.getCode());
				result.put("message", "服务器端异常");
			}
			
			writer.write(JSON.toJSONString(result));
			
        }finally{
        	if(writer != null){
        		writer.close();
        	}
        }
		
    }  
	
}
