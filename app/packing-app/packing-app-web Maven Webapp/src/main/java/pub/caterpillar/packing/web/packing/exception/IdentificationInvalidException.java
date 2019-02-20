package pub.caterpillar.packing.web.packing.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class IdentificationInvalidException extends BaseException{

	private static final long serialVersionUID = 1L;

	public IdentificationInvalidException(String identification){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前绑定id：“")
															 .append(identification)
															 .append("”无效！")
														     .toString());
	}
	
}
