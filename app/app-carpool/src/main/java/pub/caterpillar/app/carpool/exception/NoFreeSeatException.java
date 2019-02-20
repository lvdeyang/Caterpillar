package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class NoFreeSeatException extends BaseException{

	private static final long serialVersionUID = 1L;

	public NoFreeSeatException() {
		super(StatusCode.FORBIDDEN, "当前已达最大接单数！");
	}
	
}
