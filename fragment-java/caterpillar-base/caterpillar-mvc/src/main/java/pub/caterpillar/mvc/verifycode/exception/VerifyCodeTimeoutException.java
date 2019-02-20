package pub.caterpillar.mvc.verifycode.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

public class VerifyCodeTimeoutException extends BaseException{

	private static final long serialVersionUID = 1L;

	public VerifyCodeTimeoutException(){
		super(StatusCode.FORBIDDEN, "二维码已失效，请重新生成！");
	}
	
	public VerifyCodeTimeoutException(String fPath){
		super(StatusCode.FORBIDDEN, "二维码已失效，请重新生成！", fPath);
	}
	
}
