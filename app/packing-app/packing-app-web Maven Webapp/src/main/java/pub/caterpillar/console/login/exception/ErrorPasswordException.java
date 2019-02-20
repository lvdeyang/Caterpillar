package pub.caterpillar.console.login.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class ErrorPasswordException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ErrorPasswordException() {
		super(StatusCode.FORBIDDEN, "密码错误！");
	}
	
}
