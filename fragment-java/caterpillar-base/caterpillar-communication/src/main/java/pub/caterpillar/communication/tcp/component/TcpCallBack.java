package pub.caterpillar.communication.tcp.component;

import org.apache.mina.core.session.IoSession;

public interface TcpCallBack {

	public void dealMessage(byte[] bytes,IoSession session);
}
