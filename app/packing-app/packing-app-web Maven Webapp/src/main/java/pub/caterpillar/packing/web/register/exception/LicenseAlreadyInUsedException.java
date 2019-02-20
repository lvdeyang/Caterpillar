package pub.caterpillar.packing.web.register.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class LicenseAlreadyInUsedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public LicenseAlreadyInUsedException(String license){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前车牌号：“")
															 .append(license)
															 .append("”已经被注册，请确认注册信息是否填写正确！")
														     .toString());
	}
	
}
