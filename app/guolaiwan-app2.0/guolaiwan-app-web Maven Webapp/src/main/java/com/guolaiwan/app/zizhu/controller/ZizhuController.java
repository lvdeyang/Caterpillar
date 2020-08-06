package com.guolaiwan.app.zizhu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.zhaji.ZhajiService;
import com.guolaiwan.app.zizhu.GlwHttpUtils;
import com.guolaiwan.app.zizhu.bean.CancelVoucherPo;
import com.guolaiwan.app.zizhu.bean.CancleVoucherVo;
import com.guolaiwan.app.zizhu.bean.PayCodePo;
import com.guolaiwan.app.zizhu.bean.PayCodeVo;
import com.guolaiwan.app.zizhu.bean.PayVoucherVo;
import com.guolaiwan.app.zizhu.bean.ProductPo;
import com.guolaiwan.app.zizhu.bean.ProductVo;
import com.guolaiwan.app.zizhu.bean.RefundPo;
import com.guolaiwan.app.zizhu.bean.RefundVo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherPo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherVo;
import com.guolaiwan.app.zizhu.bean.CreateVoucherPo;
import com.guolaiwan.app.zizhu.bean.CreateVoucherVo;

import cn.hutool.core.date.DateTime;

@Controller
@RequestMapping("/ticketSales")
public class ZizhuController {

	/**
	 * 获取商品列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryProduct", method = RequestMethod.GET)
    public String checkIn(@RequestBody ProductPo po){
		List<ProductVo.Data> list=null;
		ProductVo vo=new ProductVo();
		ProductVo.Error error=vo.getError();
		if (po!=null) {
			 list=GlwHttpUtils.getProduct(po.getPage(), po.getId());
		}
		if (list!=null&&list.size()>0) {
			vo.setData(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 获取微信支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxpay/scanPay", method = RequestMethod.GET)
    public String payCodeWx(@RequestBody PayCodePo po){
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (po!=null) {
			payData=GlwHttpUtils.getPayCode(po);
		}
		if (!payData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setMchid((String)payData.get("mchid"));
			vo.setAppid((String)payData.get("appid"));
			vo.setQrcode_url((String)payData.get("qrcode_url"));
			vo.setTrans_status((String)payData.get("trans_status"));
			vo.setTrans_time((DateTime)payData.get("trans_time"));
			vo.setMemo((String)payData.get("memo"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 获取支付宝支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/scanPay", method = RequestMethod.GET)
    public String payCodeAli(@RequestBody PayCodePo po){
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (po!=null) {
			payData=GlwHttpUtils.getPayCode(po);
		}
		if (!payData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setMchid((String)payData.get("mchid"));
			vo.setAppid((String)payData.get("appid"));
			vo.setQrcode_url((String)payData.get("qrcode_url"));
			vo.setTrans_status((String)payData.get("trans_status"));
			vo.setTrans_time((DateTime)payData.get("trans_time"));
			vo.setMemo((String)payData.get("memo"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 创建凭证
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createVoucher", method = RequestMethod.POST)
    public String createVoucher(@RequestBody CreateVoucherPo po){
		CreateVoucherVo vo=new CreateVoucherVo();
		List<CreateVoucherVo.Data.Details> list=null;
		CreateVoucherVo.Error error=vo.getError();
		if (po!=null) {
			list=GlwHttpUtils.createVoucher(po);
		}
		if (list!=null&&list.size()>0) {
			vo.getData().setDetails(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 取消凭证
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelPreVoucher", method = RequestMethod.POST)
    public String cancelVoucher(@RequestBody CancelVoucherPo po){
		CancleVoucherVo vo=new CancleVoucherVo();
		CancleVoucherVo.Error error=vo.getError();
		int status=-1;
		if (po!=null) {
		 status=GlwHttpUtils.cancelVoucher(po.getVoucherNumber());
		}
		if (status!=-1) {
			vo.getData().setVoucherNumber(po.getVoucherNumber());
			vo.getData().setStatus(status);
			switch(status){
				case 0:{
					error.setCode(0);
					error.setMessage("success");
				}
				case 1:{
					error.setCode(1);
					error.setMessage("faile");
				}
			}
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 凭证出票
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payPreVoucher", method = RequestMethod.POST)
    public String payPreVoucher(@RequestBody CancelVoucherPo po){
		PayVoucherVo vo=new PayVoucherVo();
		List<PayVoucherVo.Data.Details> list=null;
		PayVoucherVo.Error error=vo.getError();
		if (po!=null) {
			list=GlwHttpUtils.payPreVoucher(po.getVoucherNumber());
		}
		if (list!=null&&list.size()>0) {
			vo.getData().setDetails(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	
	/**
	 * 凭证退票
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payPreVoucher", method = RequestMethod.POST)
    public String refundPreVoucher(@RequestBody RefundVoucherPo po){
		RefundVoucherVo vo=new RefundVoucherVo();
		RefundVoucherVo.Error error=vo.getError();
		Map refundData=new HashMap<String, Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refundPreVoucher(po);
		}
		if (!refundData.isEmpty()) {
			vo.Data().setRefundNumber((String)refundData.get("refundNumber"));
			vo.Data().setStatus((Integer)refundData.get("status"));
			vo.Data().setVoucherNumber((String)refundData.get("voucherNumber"));
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 微信退款
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxpay/refund", method = RequestMethod.POST)
    public String reFundWx(@RequestBody RefundPo po){
		RefundVo vo=new RefundVo();
		Map refundData=new HashMap<String,Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refund(po);
		}
		if (!refundData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setAmount((Integer)refundData.get("amount"));
			vo.setAppid((String)refundData.get("appid"));
			vo.setMchid((String)refundData.get("mchid"));
			vo.setMemo((String)refundData.get("memo"));
			vo.setOption_type((String)refundData.get("option_type"));
			vo.setRefer_no((String)refundData.get("refer_no"));
			vo.setTrade_no((String)refundData.get("trade_no"));
			vo.setTrans_no((String)refundData.get("trans_no"));
			vo.setTrans_status((String)refundData.get("trans_no"));
			vo.setTrans_time((DateTime)refundData.get("trans_time"));
			vo.setTrans_type((String)refundData.get("trans_type"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 支付宝退款
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/refund", method = RequestMethod.POST)
    public String reFundAli(@RequestBody RefundPo po){
		RefundVo vo=new RefundVo();
		Map refundData=new HashMap<String,Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refund(po);
		}
		if (!refundData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setAmount((Integer)refundData.get("amount"));
			vo.setAppid((String)refundData.get("appid"));
			vo.setMchid((String)refundData.get("mchid"));
			vo.setMemo((String)refundData.get("memo"));
			vo.setOption_type((String)refundData.get("option_type"));
			vo.setRefer_no((String)refundData.get("refer_no"));
			vo.setTrade_no((String)refundData.get("trade_no"));
			vo.setTrans_no((String)refundData.get("trans_no"));
			vo.setTrans_status((String)refundData.get("trans_no"));
			vo.setTrans_time((DateTime)refundData.get("trans_time"));
			vo.setTrans_type((String)refundData.get("trans_type"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
}
