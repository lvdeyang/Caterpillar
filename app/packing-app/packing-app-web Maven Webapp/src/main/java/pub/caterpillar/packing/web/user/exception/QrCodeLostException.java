package pub.caterpillar.packing.web.user.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class QrCodeLostException extends BaseException {

	private static final long serialVersionUID = 1L;

	public QrCodeLostException(String identification) {
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("绑定id “")
															.append(identification)
															.append("” 二维码丢失！")
															.toString());
	}

}
