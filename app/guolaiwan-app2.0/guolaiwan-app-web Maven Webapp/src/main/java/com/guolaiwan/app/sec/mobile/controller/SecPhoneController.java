package com.guolaiwan.app.sec.mobile.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.guolaiwan.app.aoyou.AoYouV1Service;
import com.guolaiwan.app.aoyou.AoYouV2Service;
import com.guolaiwan.app.aoyou.util.AoyouIDUtil;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.interfac.util.FilterSensitive;
import com.guolaiwan.app.interfac.util.KdniaoTrackQueryAPI;
import com.guolaiwan.app.tianshitongcheng.api.TianShiTongChengAPI;
import com.guolaiwan.app.web.admin.vo.ActiveBundleVO;
import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.CarouselVO;
import com.guolaiwan.app.web.admin.vo.ChildPicAndContentVO;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.LanVO;
import com.guolaiwan.app.web.admin.vo.LiveAdvertisementVO;
import com.guolaiwan.app.web.admin.vo.LiveGiftVO;
import com.guolaiwan.app.web.admin.vo.LiveProductVO;
import com.guolaiwan.app.web.admin.vo.LiveRebroadcastVO;
import com.guolaiwan.app.web.admin.vo.LiveRecordVO;
import com.guolaiwan.app.web.admin.vo.LiveVO;
import com.guolaiwan.app.web.admin.vo.LogisticVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.app.web.admin.vo.ProLatitudeLongitudeVO;
import com.guolaiwan.app.web.admin.vo.ProTourismPictureVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;

import com.guolaiwan.app.web.admin.vo.ShareVO;
import com.guolaiwan.app.web.admin.vo.SubLiveVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.admin.vo.VPCommentVO;
import com.guolaiwan.app.web.admin.vo.VideoPicVO;
import com.guolaiwan.app.web.website.alisms.controller.Alisms;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.website.user.exception.PasswordErrorException;
import com.guolaiwan.app.web.website.user.exception.UnkonwnUserException;
import com.guolaiwan.app.web.website.vo.AddressVO;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.AoYouOrderDao;
import com.guolaiwan.bussiness.admin.dao.AuctionDAO;
import com.guolaiwan.bussiness.admin.dao.CarouselDAO;
import com.guolaiwan.bussiness.admin.dao.ChildPicAndContentDAO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.CollectionDAO;
import com.guolaiwan.bussiness.admin.dao.ColumnDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;

import com.guolaiwan.bussiness.admin.dao.ExpressDao;
import com.guolaiwan.bussiness.admin.dao.GroupBuyDAO;
import com.guolaiwan.bussiness.admin.dao.GroupTeamDAO;
import com.guolaiwan.bussiness.admin.dao.LanDAO;
import com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveGiftDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveProductDAO;
import com.guolaiwan.bussiness.admin.dao.LiveRebroadcastDAO;
import com.guolaiwan.bussiness.admin.dao.LogisticsDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.OrderPeopleDao;
import com.guolaiwan.bussiness.admin.dao.PhoneCodeDAO;
import com.guolaiwan.bussiness.admin.dao.PicproRelationDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProLatitudeLongitudeDAO;
import com.guolaiwan.bussiness.admin.dao.ProTourismPictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;

import com.guolaiwan.bussiness.admin.dao.RoomStatusDao;
import com.guolaiwan.bussiness.admin.dao.ShareDAO;
import com.guolaiwan.bussiness.admin.dao.SurpportBuyDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.TodayHotSearchDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.dao.live.LiveOrderDao;
import com.guolaiwan.bussiness.admin.dao.live.LiveRecordDao;
import com.guolaiwan.bussiness.admin.dao.live.SubLiveDao;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.LiveProductType;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AoYouOrderPO;
import com.guolaiwan.bussiness.admin.po.AuctionPO;
import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.CollectionPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.ExpressPO;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.GroupTeamPO;
import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;
import com.guolaiwan.bussiness.admin.po.LiveRebroadcastPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.OrderPeoplePo;
import com.guolaiwan.bussiness.admin.po.PhoneCodePO;
import com.guolaiwan.bussiness.admin.po.PicproRelationPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.ProLatitudeLongitudePO;
import com.guolaiwan.bussiness.admin.po.ProTourismPicturePO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.SharePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.TodayHotSearchPO;
import com.guolaiwan.bussiness.admin.po.TodayHotSearchsPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.guolaiwan.bussiness.admin.po.live.LiveOrderPO;
import com.guolaiwan.bussiness.admin.po.live.LiveRecordPO;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;
import com.guolaiwan.bussiness.javacv.GuoliawanLiveServiceWrapper;
import com.guolaiwan.bussiness.nanshan.dao.CurrentRoomSateDao;
import com.guolaiwan.bussiness.nanshan.po.CurrentRoomSatePO;
import com.guolaiwan.bussiness.sec.dao.SecUserDAO;
import com.guolaiwan.bussiness.sec.enums.SecUserStatus;
import com.guolaiwan.bussiness.sec.po.SecUserPo;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.html2text.ReduceHtml2Text;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPayApp;
import pub.caterpillar.weixin.wxpay.WXPayConstants.SignType;
import pub.caterpillar.weixin.wxpay.WXPayUtil;

@Controller
@RequestMapping("/sec/phoneapp")
public class SecPhoneController extends WebBaseControll {
	@Autowired
	SecUserDAO conn_secuser;
	
	@RequestMapping(value = "/regist/index")
	public ModelAndView registIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("sec/mobile/regist");
		HttpSession session = request.getSession();
		String userId=session.getAttribute("userId").toString();
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> apply(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		HttpSession session = request.getSession();
		String userId=session.getAttribute("userId").toString();
		String type=request.getParameter("type");
		String companyId=request.getParameter("companyId");
		List<SecUserPo> secUserPos=conn_secuser.findByField("companyId",Long.parseLong(companyId));
		SecUserPo secUserPo=new SecUserPo();
		if(!secUserPos.isEmpty()&&secUserPos.size()!=0){
			secUserPo=secUserPos.get(0);
		}
		secUserPo.setStatus(SecUserStatus.CHECKING);
		secUserPo.setName(name);
		secUserPo.setPhone(phone);
		secUserPo.setCompanyId(Long.parseLong(companyId));
		secUserPo.setType(type);
		secUserPo.setUserId(Long.parseLong(userId));
		conn_secuser.saveOrUpdate(secUserPo);
		return success(dataMap);
	}

}