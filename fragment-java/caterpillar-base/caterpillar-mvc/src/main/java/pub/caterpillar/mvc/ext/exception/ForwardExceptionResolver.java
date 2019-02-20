package pub.caterpillar.mvc.ext.exception;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class ForwardExceptionResolver extends AbstractHandlerExceptionResolver{

	private static final Logger LOG = LoggerFactory.getLogger(ForwardExceptionResolver.class);
	
	private static final String DEFAULTERRORPATH = "error/error_500";
	
	@Override
	protected ModelAndView doResolveException(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			Exception ex) {
		
		LOG.error("捕获到异常啦！", ex);
		
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		
		ModelAndView mv = null;
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(ex instanceof BaseException){
			BaseException bex = (BaseException)ex;
			String fPath = bex.getForwardPath();
			if(fPath != null){
				mv = new ModelAndView(fPath);
			}else{
				mv = new ModelAndView(DEFAULTERRORPATH);
			}
			
			String message = bex.getMessage();
			if(fPath!=null && fPath.indexOf("redirect")>=0){
				//解决url带参数取出时乱码问题
				try {
					message = URLEncoder.encode(message, "utf-8");
				} catch (UnsupportedEncodingException e) {}
			}
			
			model.put("status", bex.getCode().getCode());
			model.put("message", message);
			mv.addAllObjects(model);
		}else{
			mv = new ModelAndView(DEFAULTERRORPATH);
			model.put("status", StatusCode.ERROR.getCode());
			model.put("message", "服务器端异常");
			mv.addAllObjects(model);
		}
		
		return mv;
	}
	
}
