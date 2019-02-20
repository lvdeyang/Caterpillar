package pub.caterpillar.mvc.wrapper;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 针对GET请求的url参数解码
 * lvdeyang 2018年02月05日
 */
public class UrlDecoderHttpServletRequestWrapper  extends HttpServletRequestWrapper{

	private String encoding;
	
	public UrlDecoderHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.encoding = "UTF-8";
	}
	
	public UrlDecoderHttpServletRequestWrapper(HttpServletRequest request, String encoding) {
		super(request);
		this.encoding = encoding;
	}

	@Override
	public String getParameter(String name) {
		String param = super.getParameter(name);
		if(param == null) return param;
		try{
			param = URLDecoder.decode(param, this.encoding);
		}catch(Exception e){
			e.printStackTrace();
		}
		return param;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = super.getParameterMap();
		Map<String, String[]> decodeMap = new HashMap<String, String[]>();
		Set<String> names = paramMap.keySet();
		for(String name:names){
			String[] values = paramMap.get(name);
			String[] decodeValues = decodeValues(values);
			decodeMap.put(name, decodeValues);
		}
		return decodeMap;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		return decodeValues(values);
	}
	
	//解码多个值
	private String[] decodeValues(String[] values){
		if(values==null || values.length==0) return values;
		String[] decodeValues = new String[values.length];
		for(int i=0; i<values.length; i++){
			try{
				decodeValues[i] = URLDecoder.decode(values[i], this.encoding);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return decodeValues;
	}
	
}