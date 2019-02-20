package pub.caterpillar.packing.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class UserNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long id) {
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("用户：“")
															.append(id)
															.append("”不存在！")
															.toString());
	}
	
}
