package pub.caterpillar.mvc.verifycode.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class NoRequestFoundForTokenCheckException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoRequestFoundForTokenCheckException(String handler) {
		super(StatusCode.ERROR, new StringBufferWrapper().append("当前处理器：")
														 .append(handler)
														 .append("没有找到HttpServletRequest参数，在token校验中，这个参数是必要的！")
														 .toString());
	}
	
}
