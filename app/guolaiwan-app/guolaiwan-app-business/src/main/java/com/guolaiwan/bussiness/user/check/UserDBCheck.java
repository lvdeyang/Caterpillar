package com.guolaiwan.bussiness.user.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guolaiwan.bussiness.user.dao.BasketDAO;
import com.guolaiwan.bussiness.user.dao.UserDAO;
import com.guolaiwan.bussiness.user.exception.BasketNotFoundException;
import com.guolaiwan.bussiness.user.exception.UserIllegalException;
import com.guolaiwan.bussiness.user.exception.UserNoPermissionForBasketException;
import com.guolaiwan.bussiness.user.exception.UserNotFoundException;
import com.guolaiwan.bussiness.user.po.BasketPO;
import com.guolaiwan.bussiness.user.po.UserPO;

/**
 * 数据库校验
 * 用户校验
 * lvdeyang 2017年7月11日
 */
@Service("com.guolaiwan.bussiness.user.check.UserDBCheck")
public class UserDBCheck {

	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private BasketDAO conn_basket;
	
	//校验当前操作用户是否合法
	public UserPO userLegit(Long userId, Long targetUserId) throws Exception{
		if(!userId.equals(targetUserId)){
			throw new UserIllegalException(targetUserId);
		}
		UserPO user = conn_user.get(targetUserId);
		if(user == null){
			throw new UserNotFoundException(targetUserId);
		}
		return user;
	}
	
	//校验当前购物车条目是否存在
	public BasketPO basketExist(Long basketId) throws Exception{
		BasketPO basket = conn_basket.get(basketId);
		if(basket == null){
			throw new BasketNotFoundException(basketId);
		}
		return basket;
	}
	
	//校验当前用户与购物车条目是否匹配
	public void userBasketMatching(UserPO user, BasketPO basket) throws Exception{
		UserPO bUser = basket.getUser();
		if(bUser==null || !bUser.getId().equals(user.getId())){
			throw new UserNoPermissionForBasketException(user.getUsername(), basket.getId());
		}
	}
	
}
