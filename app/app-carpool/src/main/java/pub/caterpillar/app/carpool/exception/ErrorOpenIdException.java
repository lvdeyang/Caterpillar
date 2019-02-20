package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class ErrorOpenIdException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ErrorOpenIdException(String message, String openId) {
		super(StatusCode.ERROR, new StringBufferWrapper().append(message).append("openid：").append(openId).toString());
	}
	
}
