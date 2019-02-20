package pub.caterpillar.packing.business.qr.dao;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.packing.business.qr.dto.QrCodeDTO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;

@Repository
public class QrCodeDAO extends AbstractBaseDao<QrCodePO>{

	//获取管理员生成的二维码
	public List<QrCodeDTO> queryByAdmin(Long adminId) {
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT qr.id, qr.identification, IF(ISNULL(u.id), '否', '是') registed ")
																  .append("FROM t_app_qr_code qr ")
																  .append("LEFT JOIN t_app_user u ON qr.identification=u.identification ")
																  .append("WHERE qr.admin_id=").append(adminId).append(" ")
																  .append("ORDER BY qr.updateTime DESC ");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlBuffer.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("identification")
											.addScalar("registed");
		query.setResultTransformer(Transformers.aliasToBean(QrCodeDTO.class));
		return query.list();
	}
	
}
