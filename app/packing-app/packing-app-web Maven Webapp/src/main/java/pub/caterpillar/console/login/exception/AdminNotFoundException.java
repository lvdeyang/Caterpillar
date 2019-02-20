package pub.caterpillar.console.login.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class AdminNotFoundException extends BaseException{

	private static final long serialVersionUID = 1L;

	public AdminNotFoundException(String username) {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("用户：")
															 .append(username)
															 .append("不存在！")
															 .toString());
	}
	
}
