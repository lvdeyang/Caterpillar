package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class NoEnoughSeatsForOrderException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoEnoughSeatsForOrderException() {
		super(StatusCode.FORBIDDEN, "当前车辆无法容纳订单中人数！");
	}
	
}
