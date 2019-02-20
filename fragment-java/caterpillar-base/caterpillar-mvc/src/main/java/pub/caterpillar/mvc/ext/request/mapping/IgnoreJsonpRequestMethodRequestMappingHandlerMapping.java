package pub.caterpillar.mvc.ext.request.mapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import pub.caterpillar.mvc.ext.response.json.aop.JsonBodyWrapper;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.ext.response.jsonp.aop.annotation.JsonpBody;

public class IgnoreJsonpRequestMethodRequestMappingHandlerMapping extends RequestMappingHandlerMapping{

	@Override
	protected RequestMappingInfo getMatchingMapping(RequestMappingInfo info, HttpServletRequest request) {
		
		Map<RequestMappingInfo, HandlerMethod> mappedHandlers = getHandlerMethods();
		HandlerMethod handler = mappedHandlers.get(info);
		Method target = handler.getMethod();
		JsonBody jsonBody = target.getAnnotation(JsonBody.class);
		JsonpBody jsonpBody = target.getAnnotation(JsonpBody.class);
		RequestMapping requestMapping = target.getAnnotation(RequestMapping.class);
		RequestMethod[] methods = requestMapping.method();
		if((methods!=null && methods.length>0)
				&&
				(jsonpBody!=null || (jsonBody!=null && JsonBodyWrapper.DEBUG))){
			return doMatching(target, info, request);
		}else{
			return super.getMatchingMapping(info, request);
		}
		
	}
	
	//判断当前mapping 和request是否mathch（不做method比较）
	//Access-Control-Request-Method 头在jQuery中不让设置，取代原有的跨域资源处理
	private RequestMappingInfo doMatching(Method method, RequestMappingInfo info, HttpServletRequest request){
		
		RequestMethodsRequestCondition methods = null;
		for (RequestMethod item : info.getMethodsCondition().getMethods()) {
			methods = new RequestMethodsRequestCondition(item);
			break;
		}
		
		ParamsRequestCondition params = info.getParamsCondition().getMatchingCondition(request);
		HeadersRequestCondition headers = info.getHeadersCondition().getMatchingCondition(request);
		ConsumesRequestCondition consumes = info.getConsumesCondition().getMatchingCondition(request);
		ProducesRequestCondition produces = info.getProducesCondition().getMatchingCondition(request);

		if (methods == null || params == null || headers == null || consumes == null || produces == null) {
			return null;
		}
		
		PatternsRequestCondition patterns = info.getPatternsCondition().getMatchingCondition(request);
		if (patterns == null) {
			return null;
		}
		
		//这个地方的RequestConditionHolder 重新构造了，以为原来构造的是private的获取不到
		RequestConditionHolder custom = new RequestConditionHolder(this.getCustomMethodCondition(method)).getMatchingCondition(request);
		if (custom == null) {
			return null;
		}

		return info;
	}
	
}
