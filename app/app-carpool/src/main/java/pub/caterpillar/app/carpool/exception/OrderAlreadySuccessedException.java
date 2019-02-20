package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class OrderAlreadySuccessedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public OrderAlreadySuccessedException() {
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前订单已经完成，无法取消！")
													         .toString());
	}
	
}
