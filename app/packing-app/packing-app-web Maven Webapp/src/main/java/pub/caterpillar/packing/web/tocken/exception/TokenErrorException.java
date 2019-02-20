package pub.caterpillar.packing.web.tocken.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class TokenErrorException extends BaseException{

	private static final long serialVersionUID = 1L;

	public TokenErrorException(String forwardPath) {
		super(StatusCode.FORBIDDEN, "验证码输入错误！", forwardPath);
	}
	
	public TokenErrorException() {
		super(StatusCode.FORBIDDEN, "验证码输入错误！");
	}

}
