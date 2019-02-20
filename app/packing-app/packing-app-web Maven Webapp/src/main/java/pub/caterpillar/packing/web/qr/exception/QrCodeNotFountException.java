package pub.caterpillar.packing.web.qr.exception;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class QrCodeNotFountException extends BaseException {

	private static final long serialVersionUID = 1L;

	public QrCodeNotFountException(Long id) {
		super(StatusCode.NOTFOUND, new StringBufferWrapper().append("二维码  “")
															.append(id)
															.append("” 不存在！")
															.toString());
	}

}
