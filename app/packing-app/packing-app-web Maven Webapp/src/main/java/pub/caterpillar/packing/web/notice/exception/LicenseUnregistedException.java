package pub.caterpillar.packing.web.notice.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class LicenseUnregistedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public LicenseUnregistedException(String license){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前车牌号：“")
															 .append(license)
															 .append("”还没有被车主注册！")
														     .toString());
	}
	
}
