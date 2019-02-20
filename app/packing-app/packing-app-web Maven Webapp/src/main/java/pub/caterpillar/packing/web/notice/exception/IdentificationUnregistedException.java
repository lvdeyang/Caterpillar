package pub.caterpillar.packing.web.notice.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class IdentificationUnregistedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public IdentificationUnregistedException(String identification){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前绑定id：“")
															 .append(identification)
															 .append("”还没有被车主绑定信息！")
														     .toString());
	}
	
}
