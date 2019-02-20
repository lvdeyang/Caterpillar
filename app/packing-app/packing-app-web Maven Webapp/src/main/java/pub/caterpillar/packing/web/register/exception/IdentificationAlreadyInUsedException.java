package pub.caterpillar.packing.web.register.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class IdentificationAlreadyInUsedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public IdentificationAlreadyInUsedException(String identification){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前绑定id：“")
															 .append(identification)
															 .append("”已经被注册，请联系客服获取新的二维码！")
														     .toString());
	}
	
}
