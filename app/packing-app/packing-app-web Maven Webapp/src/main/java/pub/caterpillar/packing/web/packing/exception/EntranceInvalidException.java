package pub.caterpillar.packing.web.packing.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class EntranceInvalidException extends BaseException{

	private static final long serialVersionUID = 1L;

	public EntranceInvalidException(String entrance){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前二维码：“")
															 .append(entrance)
															 .append("”无效！")
														     .toString());
	}
	
}
