package pub.caterpillar.packing.web.admin.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class AdminDoseNotExistException extends BaseException{

	private static final long serialVersionUID = 1L;

	public AdminDoseNotExistException(long id) {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("id为 “")
															 .append(id)
															 .append("” 的管理员不存在！")
															 .toString());
	}

}
