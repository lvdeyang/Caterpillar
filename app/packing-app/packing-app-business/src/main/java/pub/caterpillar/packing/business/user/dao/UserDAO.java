package pub.caterpillar.packing.business.user.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
import pub.caterpillar.packing.business.qr.dto.QrCodeDTO;
import pub.caterpillar.packing.business.user.po.UserPO;

@Repository
public class UserDAO extends AbstractBaseDao<UserPO> {

	//根据商户入口注册的用户
	public List<UserPO> queryByAdmin(String uuid){
		QueryHql hql = this.newQueryHql();
		hql.andBy("identifiction", Condition.eq, uuid);
		return this.findByHql(hql);
	}
	
	//获取商户绑定id注册用户
	public List<UserPO> queryByAdmin(Long adminId){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT u.id id, u.uuid uuid, ")
																 .append("u.updateTime updateTime, u.identification identification, ")
																 .append("u.license license, u.mobile mobile, ")
																 .append("u.name name, u.region region, u.type type ")
																 .append("FROM t_app_user u ")
																 .append("LEFT JOIN t_app_qr_code qr ON u.identification=qr.identification ")
																 .append("WHERE qr.admin_id=")
																 .append(adminId);
		 SQLQuery query = getCurrentSession().createSQLQuery(sqlBuffer.toString())
											 .addScalar("id", StandardBasicTypes.LONG)
											 .addScalar("uuid")
											 .addScalar("updateTime", StandardBasicTypes.DATE)
											 .addScalar("identification")
											 .addScalar("license")
											 .addScalar("mobile")
											 .addScalar("name")
											 .addScalar("region")
											 .addScalar("type");
		query.setResultTransformer(Transformers.aliasToBean(UserPO.class));
		return query.list();
	}
	
	//根据车牌号码获取用户
	public List<UserPO> queryByLicense(String license){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT id, updateTime, uuid, identification, ")
																 .append("license, mobile, name, region, type ")
																 .append("FROM t_app_user ")
																 .append("WHERE CONCAT(region,license)='").append(license).append("'");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlBuffer.toString())
										    .addScalar("id", StandardBasicTypes.LONG)
										    .addScalar("uuid")
										    .addScalar("updateTime", StandardBasicTypes.DATE)
										    .addScalar("identification")
										    .addScalar("license")
										    .addScalar("mobile")
										    .addScalar("name")
										    .addScalar("region")
										    .addScalar("type");
		query.setResultTransformer(Transformers.aliasToBean(UserPO.class));
		return query.list();														 
	}
	
	//根据手机号码获取用户
	public List<UserPO> queryByMobile(String mobile){
		QueryHql hql = this.newQueryHql();
		hql.andBy("mobile", Condition.eq, mobile);
		return this.findByHql(hql);
	}
	
}

