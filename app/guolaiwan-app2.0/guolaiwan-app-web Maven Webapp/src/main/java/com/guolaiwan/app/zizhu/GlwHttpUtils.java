package com.guolaiwan.app.zizhu;

import java.util.List;
import java.util.Map;

import com.guolaiwan.app.zizhu.bean.PayCodePo;
import com.guolaiwan.app.zizhu.bean.ProductVo.Data;
import com.guolaiwan.app.zizhu.bean.RefundPo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherPo;
import com.guolaiwan.app.zizhu.bean.CreateVoucherPo;
import com.guolaiwan.app.zizhu.bean.CreateVoucherVo.Data.Details;

import cn.hutool.http.HttpUtil;

/**
 * 过来玩内部接口调用工具类
 * @author 86138
 *
 */
public class GlwHttpUtils {
public final static String IP="localhost:8080/guolaiwan-app-web/newPhoneController";
public final static String GET_PRODUCT="/getProductsByMer";
public final static String GET_PAYCODE="";
public final static String POST_CREATE_VOUCHER="";
public final static String POST_CANCE_VOUCHER="";
public final static String POST_PAY_VOUCHER="";
public final static String POST_REFUND_VOUCHER="";
public final static String POST_REFUND="";

	/**
	 * 根据商家ID分页查询商品数据
	 * @param page
	 * @param merchanId
	 * @return
	 */
	public static List<Data> getProduct(int page,int merchanId){
		String response=null;
		String Url=IP+GET_PRODUCT+"?merchantID="+merchanId+"&page=page";
		response=HttpUtil.get(Url);
//		JSONoBJE
		return null;
	}

	
	/**
	 * 获取支付二维码
	 * @param page
	 * @param merchanId
	 * @return
	 */
	public static Map getPayCode(PayCodePo paycodepo) {
		String response=null;
		String Url=IP+GET_PAYCODE;
		response=HttpUtil.get(Url);
//		JSONoBJE
		return null;
	}


	/**
	 * 创建凭证
	 * @param page
	 * @param merchanId
	 * @
	 */
	public static List<Details> createVoucher(CreateVoucherPo po) {
//		String response=null;
//		String Url=IP+POST_CREATE_VOUCHER;
//		response=HttpUtil.post(urlString, paramMap);
////		JSONoBJE
		return null;
	}


	/**
	 * 取消凭证
	 * @param page
	 * @param merchanId
	 * @
	 */
	public static int cancelVoucher(String voucherNumber) {
//		String response=null;
//		String Url=IP+POST_CANCE_VOUCHER;
//		response=HttpUtil.post(urlString, paramMap);
////		JSONoBJE
		return 0;
	}


	/**
	 * 凭证出票
	 * @param voucherNumber
	 * @return
	 */
	public static List<com.guolaiwan.app.zizhu.bean.PayVoucherVo.Data.Details> payPreVoucher(String voucherNumber){
//			String Url=IP+POST_PAY_VOUCHER;
//			response=HttpUtil.post(urlString, paramMap);
////			JSONoBJE) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 凭证退票
	 * @param voucherNumber
	 * @return
	 */
	public static Map refundPreVoucher(RefundVoucherPo po) {
//		String Url=IP+POST_REFUND_VOUCHER;
//		response=HttpUtil.post(urlString, paramMap);
//		JSONoBJE) {
	// TODO Auto-generated method stub
		return null;
	}


	public static Map refund(RefundPo po) {
//		String Url=IP+POST_REFUND;
//		response=HttpUtil.post(urlString, paramMap);
//		JSONoBJE) {
		return null;
	}
	
}
