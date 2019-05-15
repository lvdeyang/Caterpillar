package com.guolaiwan.app.xinglongshan.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.xinglongshan.pojo.ApiConfig;
import com.guolaiwan.app.xinglongshan.utils.BusinessException;
import com.guolaiwan.app.xinglongshan.utils.MD5;


@Controller
@RequestMapping("/notice")
public class Notice {
	private static final Logger logger = LoggerFactory.getLogger(Notice.class);

	
	private ApiConfig apiConfig=new ApiConfig();
	
	@ResponseBody
	@RequestMapping("consume")
	public void notice(HttpServletRequest request, HttpServletResponse response) {
		/**
		 *  消费通知
		 */
		logger.info("消费通知");
		try {
			//订单号
			String orderCode = request.getParameter("order_no");
			//子订单号
			String subOrderCode = request.getParameter("sub_order_no");
			//退票批次号
			String status = request.getParameter("status");
			//审核结果
			String checkNum = request.getParameter("checkNum");
			//退票数量
			String returnNum = request.getParameter("returnNum");
			// 总数量
			String total = request.getParameter("total");
			//签名
			String sign = request.getParameter("sign");
			
			String mySign = MD5.md5("order_no="+orderCode+apiConfig.getKey());
			if(!mySign.equals(sign)){
				throw new BusinessException("通知签名错误");
			}else{
				logger.info("签名校验成功");
			}
			if(status.equals("check")){
			/**
			 * 分销商处理订单核销通知业务
			 */
				logger.info("分销商订单号{}",orderCode);
				logger.info("分销商子订单号{}",subOrderCode);
				logger.info("订单状态{}",status);
				logger.info("本次检票数量{}",checkNum);
				logger.info("退票数量{}",returnNum);
				logger.info("分销商订单号{}",total);
			}
			try {
				OutputStream out = response.getOutputStream();
		        out.write("success".getBytes("UTF-8"));  
		        out.flush();  
			} catch (IOException e) {
				logger.info("回写异常",e);
			} 
		
		} catch (Exception e) {
			returnJson4Utf8(e.getMessage(), response);
		}
	}
	
	
	@ResponseBody
	@RequestMapping("finish")
	public void finish(HttpServletRequest request, HttpServletResponse response){
		/**
		 * 订单完结通知
		 */
		logger.info("完结通知");
		try {
			//状态
			String status = request.getParameter("status");
			//检票数量
			String checkNum =request.getParameter("checkNum");
			//退票数量
			String returnNum =request.getParameter("returnNum");
			//总数量
			String total =request.getParameter("total");
			//第三方订单号
			String orderCode =request.getParameter("order_code");
			//总数量
			String sign =request.getParameter("sign");
			//本地签名
			String mySign=MD5.md5("order_code="+orderCode+apiConfig.getKey());
			if(!mySign.equals(sign)){
				throw new BusinessException("通知签名错误");
			}
			if(status.equals("success")){
				/**
				 * 分销商业务处理--检票
				 */
				
			}else if(status.equals("cancel")){
				/**
				 * 分销商业务处理--取消
				 */
			}else{
				throw new BusinessException("未知的通知状态");
			}
			returnJson4Utf8("success", response);

		} catch (Exception e) {
			returnJson4Utf8(e.getMessage(), response);
		}
	}
	
	@ResponseBody
	@RequestMapping("retreat")
	public void retreat(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 退票通知
		 */
		logger.info("退票通知");
		try {
			//订单号
			String orderCode = request.getParameter("orderCode");
			//子订单号
			String subOrderCode = request.getParameter("subOrderCode");
			//退票批次号
			String retreatBatchNo = request.getParameter("retreatBatchNo");
			//审核结果
			String auditStatus = request.getParameter("auditStatus");
			//退票数量
			String returnNum = request.getParameter("returnNum");
			//签名
			String sign = request.getParameter("sign");
			
			String mySign = MD5.md5(orderCode+apiConfig.getKey());
			if(!mySign.equals(sign)){
				throw new BusinessException("通知签名错误");
			}
			if("failure".equals(auditStatus)){
				/**
				 * 分销商处理退票审核失败的业务
				 */
			}else if("success".equals(auditStatus)){
				/**
				 * 分销商处理退票审核成功的业务
				 */
			}else{
				throw new BusinessException("未知的状态");
			}
			returnJson4Utf8("success", response);
		} catch (Exception e) {
			returnJson4Utf8(e.getMessage(), response);
		}
	}
	
	
	
	public void returnJson4Utf8(String content, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (Exception e) {
			logger.error("返回json格式异常",e);
		}

	}
	
	
	
	
}
