package pub.caterpillar.mvc.verifycode.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class NoVeriryCodeFoundException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoVeriryCodeFoundException() {
		super(StatusCode.ERROR, new StringBuffer().append("当前验证码已经过期！").toString());
	}
	
	public NoVeriryCodeFoundException(String fPath) {
		super(StatusCode.ERROR, new StringBuffer().append("当前验证码已经过期！").toString(), fPath);
	}
	
	
}
