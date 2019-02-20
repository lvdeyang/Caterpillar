package pub.caterpillar.packing.web.tocken.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class TokenTimeoutException extends BaseException{

	private static final long serialVersionUID = 1L;

	public TokenTimeoutException(String forwardPath) {
		super(StatusCode.FORBIDDEN, "验证码已经失效，请重新获取！", forwardPath);
	}
	
	public TokenTimeoutException(){
		super(StatusCode.FORBIDDEN, "验证码已经失效，请重新获取！");
	}

}
