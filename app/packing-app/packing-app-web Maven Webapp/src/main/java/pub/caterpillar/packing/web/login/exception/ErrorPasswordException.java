package pub.caterpillar.packing.web.login.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ErrorPasswordException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	public ErrorPasswordException() {
		super(StatusCode.FORBIDDEN, "密码错误！");
	}
	
}
