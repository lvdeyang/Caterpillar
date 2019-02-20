package pub.caterpillar.app.carpool.welcome.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.DriverDAO;
import pub.caterpillar.app.carpool.dao.OrderDAO;
import pub.caterpillar.app.carpool.dao.SeatInfoDAO;
import pub.caterpillar.app.carpool.dao.SiteDAO;
import pub.caterpillar.app.carpool.dao.UserDAO;
import pub.caterpillar.app.carpool.enumeration.DriverDirections;
import pub.caterpillar.app.carpool.enumeration.SeatStatus;
import pub.caterpillar.app.carpool.exception.ErrorOpenIdException;
import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.app.carpool.po.UserPO;
import pub.caterpillar.app.carpool.vo.OrderVO;
import pub.caterpillar.app.carpool.vo.SeatInfoVO;
import pub.caterpillar.app.carpool.vo.UserVO;
import pub.caterpillar.app.carpool.vo.WeixinConfigVO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.communication.http.client.HttpClient;
import sun.tools.jconsole.inspector.Utils.EditFocusAdapter;

@Controller
@RequestMapping(value = "")
public class WelcomeController {

	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private OrderDAO conn_order;
	
	@Autowired
	private DriverDAO conn_driver;
	
	@Autowired
	private SeatInfoDAO conn_seat_info;
	
	@Autowired
	private SiteDAO conn_site;
	
	@RequestMapping(value = "edit")
	public ModelAndView edit(){
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/carpool/visitor/edit-info");
		return mv;
	}
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		WeixinConfigVO config = WeixinConfigVO.getInstance();
		String redirect = "http://www.yueba.net.cn/app-carpool/index1?";
		redirect = URLEncoder.encode(redirect);
		StringBufferWrapper weixinLogin = new StringBufferWrapper().append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(config.getAppid())
																   .append("&redirect_uri=").append(redirect).append("response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		System.out.println(weixinLogin.toString());
		mv = new ModelAndView("redirect:" + weixinLogin.toString());
		return mv;
	}
	
	
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public ModelAndView index1(
			String code, 
			String state, 
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		WeixinConfigVO config = WeixinConfigVO.getInstance();
		
		//获取授权access_token
		JSONObject params = new JSONObject();
		params.put("appid", config.getAppid());
		params.put("secret", config.getAppsecret());
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		String result = HttpClient.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
		JSONObject accessTokenInfo = JSON.parseObject(result);
		String access_token = accessTokenInfo.getString("access_token");
		String openid = accessTokenInfo.getString("openid");
		
		//拉取用户详细信息
		params = new JSONObject();
		params.put("access_token", access_token);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		result = HttpClient.get("https://api.weixin.qq.com/sns/userinfo", params);
		System.out.println(result);
		JSONObject userInfo = JSON.parseObject(result);
		String nickname = userInfo.getString("nickname");
		String headimgurl = URLDecoder.decode(userInfo.getString("headimgurl"));
		
		//处理登录
		UserPO user = conn_user.queryByOpenId(openid);
		if(user == null){
			user = new UserPO();
			user.setOpenId(openid);
		}
		user.setName(nickname);
		user.setHeadImgUrl(headimgurl);
		conn_user.saveOrUpdate(user);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//跳转判断
		DriverPO driver = conn_driver.queryByMobile(user.getMobile());
		if(driver != null){
			mv = new ModelAndView(new StringBufferWrapper().append("redirect:/driver/index/").append(user.getMobile()).toString());
		}else{
			mv = new ModelAndView(new StringBufferWrapper().append("redirect:/visitor/index/").append(openid).toString());
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/visitor/index/{openId}")
	public ModelAndView visitorIndex(
			@PathVariable String openId,
			HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		UserPO user = (UserPO)session.getAttribute("user");
		
		//测试代码
		if(user == null) user = conn_user.queryByOpenId(openId);
		UserVO view_user = new UserVO().set(user);
		
		ModelAndView mv = null;
		
		//判断信息是否齐全
		if(user.getMobile() == null){
			mv = new ModelAndView("mobile/carpool/visitor/edit-info");
			mv.addObject("user", view_user);
			return mv;
		}
		
		OrderPO newOrder = conn_order.queryNewOrderByUser(user.getId());
		if(newOrder != null){
			//有新订单返回等待接单页面
			Date createTime = newOrder.getCreateTime();
			Date now = new Date();
			double totalSeconds = DateUtil.getDoubleSeconds(now, createTime);
			int seconds = (int)(totalSeconds % 60);
			int minutes = (int)((totalSeconds-seconds) / 60);
			mv = new ModelAndView("mobile/carpool/visitor/order-waiting");
			mv.addObject("minutes", minutes);
			mv.addObject("seconds", seconds);
			mv.addObject("orderId", newOrder.getId());
		}else {
			OrderPO takedOrder = conn_order.queryTakedOrderByUser(user.getId());
			if(takedOrder != null){
				//有订单并且已经有司机接单
				mv = new ModelAndView("mobile/carpool/visitor/order-taked");
				OrderVO view_takedOrder = new OrderVO().set(takedOrder);
				mv.addObject("order", view_takedOrder);
				
				//String driverMobile = conn_driver.queryMobileByOrder(takedOrder.getId());
				//mv.addObject("driverMobile", driverMobile);
			}else{
				//没有未完成订单返回订单编辑页面
				mv = new ModelAndView("mobile/carpool/visitor/order-editor");
				mv.addObject("user", view_user);
			}
		}
		
		mv.addObject("mobile", user.getMobile());
		
		return mv;
	}
	
	@RequestMapping(value = "/bind/mobile")
	public ModelAndView bindMobile(String mobile, String token, HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		
		//token校验
		
		HttpSession session = request.getSession();
		UserPO user = (UserPO)session.getAttribute("user");
		
		user.setMobile(mobile);
		session.setAttribute("user", user);
		
		conn_user.update(user);
		
		//查看是否是司机绑定
		DriverPO driver = conn_driver.queryByMobile(mobile);
		if(driver == null){
			mv = new ModelAndView(new StringBufferWrapper().append("redirect:/visitor/index/").append(user.getOpenId()).toString());
		}else {
			mv = new ModelAndView(new StringBufferWrapper().append("redirect:/driver/index/").append(user.getMobile()).toString());
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/driver/index/{mobile}")
	public ModelAndView driverIndex(@PathVariable String mobile) throws Exception{
		
		ModelAndView mv = new ModelAndView("mobile/carpool/driver/index");
		
		DriverPO driver = conn_driver.queryByMobile(mobile); 
		if(driver == null){
			throw new ErrorOpenIdException("未获取到当前司机，", mobile);
		}
		
		//mv.addObject("openId", openId);
		mv.addObject("mobile", driver.getMobile());
		
		//车辆状态
		CarPO car = driver.getCar();
		mv.addObject("carStatusName", car.getStatus().getName());
		mv.addObject("carStatus", car.getStatus());
		
		//座位与接单信息
		List<SeatInfoPO> seatInfos = conn_seat_info.queryByCarWithSerialASC(car.getId());
		List<SeatInfoVO> view_seatInfos = SeatInfoVO.getConverter(SeatInfoVO.class).convert(seatInfos, SeatInfoVO.class);
		mv.addObject("seatInfos", view_seatInfos);
		
		int unFreeNum = 0;
		for(SeatInfoPO info:seatInfos){
			if(!SeatStatus.FREE.equals(info.getStatus())){
				unFreeNum++;
			}
		}
		mv.addObject("seatStatus", new StringBufferWrapper().append(unFreeNum)
															.append("/")
															.append(seatInfos.size())
															.toString());
		//行车路线
		RoutePO route = null;
		if(DriverDirections.ROUTE.equals(driver.getDirection())){
			route = driver.getRoute();
		}else if(DriverDirections.REVERT.equals(driver.getDirection())){
			route = driver.getRevert();
		}
		SitePO departure = conn_site.get(route.getDeparture());
		SitePO destination = conn_site.get(route.getDestination());
		mv.addObject("route", new StringBufferWrapper().append(departure.getName())
													   .append(" - ")
													   .append(destination.getName())
													   .toString());
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/index")
	public ModelAndView adminIndex(){
		
		ModelAndView mv = new ModelAndView("web/carpool/admin/index");
		
		return mv;
	}
	
	public static void main(String[] args){
		String redirect = "http://caterpillar.iok.la/carpool/index?";
		redirect = URLEncoder.encode(redirect);
		StringBufferWrapper weixinLogin = new StringBufferWrapper().append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append("wxf8ef4ee92241d7fa")
																   .append("&redirect_uri=").append(redirect)
																   .append("response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		System.out.println(weixinLogin.toString());
	}
	
	
	
}
