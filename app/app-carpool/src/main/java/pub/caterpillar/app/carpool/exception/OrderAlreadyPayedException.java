package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class OrderAlreadyPayedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public OrderAlreadyPayedException(String orderUuid){
		super(StatusCode.FORBIDDEN, new StringBufferWrapper().append("当前订单已经支付！订单号：")
															 .append(orderUuid)
															 .toString());
	}
	
}
