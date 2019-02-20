package pub.caterpillar.mvc.verifycode.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class ErrorVerifyCodeException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ErrorVerifyCodeException() {
		super(StatusCode.ERROR, "二维码输入不正确！");
	}
	
	public ErrorVerifyCodeException(String fPath) {
		super(StatusCode.ERROR, "二维码输入不正确！", fPath);
	}
	
}
