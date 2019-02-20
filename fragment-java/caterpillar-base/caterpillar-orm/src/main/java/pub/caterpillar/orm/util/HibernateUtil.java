package pub.caterpillar.orm.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class HibernateUtil {

	//解决新线程懒加载报错问题
	public static boolean bindSessionForThread(SessionFactory sessionFactory){
        boolean participate = false;  
        if (TransactionSynchronizationManager.hasResource(sessionFactory)) {  
        	participate = true;  
        } else {  
            Session session = sessionFactory.openSession();  
            session.setFlushMode(FlushMode.MANUAL);  
            SessionHolder sessionHolder = new SessionHolder(session);  
            TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);  
        }
        return participate;
	}
	
	//关闭新线程中的session
	public static void closeSession(boolean participate, SessionFactory sessionFactory){
		if (!participate) {  
	        SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);  
	        SessionFactoryUtils.closeSession(sessionHolder.getSession());  
	    }  
	}
}
