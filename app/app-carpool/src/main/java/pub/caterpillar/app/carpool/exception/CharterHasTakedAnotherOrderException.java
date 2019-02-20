package pub.caterpillar.app.carpool.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class CharterHasTakedAnotherOrderException extends BaseException{

	private static final long serialVersionUID = 1L;

	public CharterHasTakedAnotherOrderException(){
		super(StatusCode.FORBIDDEN, "您已经接过其他单，无法再接包车单！");
	}
	
}
