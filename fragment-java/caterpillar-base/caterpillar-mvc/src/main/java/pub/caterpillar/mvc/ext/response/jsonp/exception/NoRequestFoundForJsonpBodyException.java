package pub.caterpillar.mvc.ext.response.jsonp.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

/**
 * 处理器有@JsonBody注解
 * 没有传入HttpServletRequest参数
 * lvdeyang 2018年01月10日
 */
public class NoRequestFoundForJsonpBodyException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoRequestFoundForJsonpBodyException(String handler) {
		super(StatusCode.ERROR, new StringBufferWrapper().append("当前处理器：")
														 .append(handler)
														 .append("没有找到HttpServletRequest参数，为支持jsonp，这个参数是必要的！")
														 .toString());
	}
	
}
