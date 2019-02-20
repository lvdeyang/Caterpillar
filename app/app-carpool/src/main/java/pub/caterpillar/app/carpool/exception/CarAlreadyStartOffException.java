package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class CarAlreadyStartOffException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CarAlreadyStartOffException() {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前行程已经出发，无法取消订单，请与司机联系！")
															 .toString());
	}
	
}
