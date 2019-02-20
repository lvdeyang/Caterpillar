package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class OrderAlreadyTakedException extends BaseException{
	
	private static final long serialVersionUID = 1L;

	public OrderAlreadyTakedException() {
		super(StatusCode.FORBIDDEN, "订单已经被接了！");
	}

}
