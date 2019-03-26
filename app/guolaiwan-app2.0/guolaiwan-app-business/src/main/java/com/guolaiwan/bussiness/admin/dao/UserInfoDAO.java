package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.UserInfoDAO")
public class UserInfoDAO extends AbstractBaseDao<UserInfoPO> {

	public UserInfoPO getModularByCode(String userphone) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userinfo", Condition.eq, userphone);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos.get(0);
	}

	public List<UserInfoPO> GetListbyPage(int pageNum, int pageSize, String userPhone, String nickname, String openId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userPhone", Condition.lk, userPhone);
		hql.andBy("userNickname", Condition.lk, nickname);
		hql.andBy("userOpenID", Condition.lk, openId);
		hql.orderBy("updateTime", true);
		List<UserInfoPO> userinfos = findByHqlPage(hql, pageNum, pageSize);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos;
	}

	/**
	 * app专用
	 * 
	 * @param userphone
	 * @return
	 */
	public UserInfoPO getUserByPhone(String userphone) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userPhone", Condition.eq, userphone);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos.get(0);
	}

	/**
	 * app专用
	 * 
	 * @param openId
	 * @return
	 */
	public UserInfoPO getUserByOpenId(String openId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userOpenID", Condition.eq, openId);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos.get(0);
	}

	public List<UserInfoPO> getUsersByOpenId(String openId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userOpenID", Condition.eq, openId);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos;
	}

	// 统计总数
	public int countByPhone(String userPhone) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("userPhone", Condition.lk, userPhone);
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 统计总数
	public int countAll() {
		CountHql cHql = this.newCountHql();
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 根据用户id获取用户全部
	public List<UserInfoPO> getUserByUid(Long userId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, userId);
		List<UserInfoPO> findByHql = findByHql(hql);
		if (findByHql == null || findByHql.size() == 0) {
			return null;
		}
		return findByHql;
	}

	// 获取新增
	public int getCountByDate(String date) throws ParseException {
		CountHql hql = this.newCountHql();
		hql.andBy("updateTime", Condition.gt, DateUtil.parse(date + " 00:00:00"));
		hql.andBy("updateTime", Condition.lt, DateUtil.parse(date + " 23:59:59"));
		return countByHql(hql);
	}

}
