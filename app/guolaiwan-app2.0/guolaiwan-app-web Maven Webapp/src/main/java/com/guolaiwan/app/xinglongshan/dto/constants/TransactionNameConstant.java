package com.guolaiwan.app.xinglongshan.dto.constants;

public interface TransactionNameConstant {
	
	/**
	 * 下单请求头
	 */
	String SEND_CODE_REQ="SEND_CODE_REQ";
	/**
	 * 下单正常响应头
	 */
	String SEND_CODE_RES="SEND_CODE_RES";
	/**
	 * 图片请求
	 */
	String SEND_CODE_IMG_REQ="SEND_CODE_IMG_REQ";
	/**
	 * 图片响应
	 */
	String SEND_CODE_IMG_RES="SEND_CODE_IMG_RES";
	/**
	 * 取消订单
	 */
	String SEND_CODE_CANCEL_REQ="SEND_CODE_CANCEL_REQ";
	/**加批次号**/
	String SEND_CODE_CANCEL_NEW_REQ="SEND_CODE_CANCEL_NEW_REQ";

	/**
	 * 取消订单响应
	 */
	String SEND_CODE_CANCEL_RES="SEND_CODE_CANCEL_RES";
	/**加批次号**/
	String SEND_CODE_CANCEL_NEW_RES="SEND_CODE_CANCEL_NEW_RES";

	/**
	 * 退票数请求
	 */
    String RETURN_TICKET_NUM_REQ="RETURN_TICKET_NUM_REQ";
    /**加批次号**/
    String RETURN_TICKET_NUM_NEW_REQ="RETURN_TICKET_NUM_NEW_REQ";

    /**
     * 退票数响应
     */
    String RETURN_TICKET_NUM_RES="RETURN_TICKET_NUM_RES";
    /**加批次号**/
    String RETURN_TICKET_NUM_NEW_RES="RETURN_TICKET_NUM_NEW_RES";

    /**
     * 批量退票请求
     */
    String BATCH_RETURN_TICKET_NUM_NEW_REQ="BATCH_RETURN_TICKET_NUM_NEW_REQ";
    String BATCH_RETURN_TICKET_NUM_REQ="BATCH_RETURN_TICKET_NUM_REQ";

    /**
     * 批量退票响应
     */
    String BATCH_RETURN_TICKET_NUM_NEW_RES="BATCH_RETURN_TICKET_NUM_NEW_RES";
    String BATCH_RETURN_TICKET_NUM_RES="BATCH_RETURN_TICKET_NUM_RES";

	/**
	 * 检票状态查询请求
	 */
	String CHECK_STATUS_QUERY_REQ="CHECK_STATUS_QUERY_REQ";
	/**
	 * 订单明细线下检票查询请求
	 */
	String ORDER_DETAIL_CHECK_NUM_QUERY_REQ="ORDER_DETAIL_CHECK_NUM_QUERY_REQ";
	/**
	 *  订单明细线下检票查询响应
	 */
	String ORDER_DETAIL_CHECK_NUM_QUERY_RES="ORDER_DETAIL_CHECK_NUM_QUERY_RES";
	/**
	 *检票状态查询响应
	 */
	String CHECK_STATUS_QUERY_RES="CHECK_STATUS_QUERY_RES";
	/**
	 * 发短信请求
	 */
	String SEND_SM_REQ="SEND_SM_REQ";
	/**
	 * 发短信响应
	 */
	String SEND_SM_RES="SEND_SM_RES";
	/***订单信息查询**/
	String QUERY_ORDER_REQ = "QUERY_ORDER_REQ";
	/***订单信息查询响应**/
	String QUERY_ORDER_RES = "QUERY_ORDER_RES";
	/***查询子明细检票记录***/
	String QUERY_SUB_ORDER_CHECK_RECORD_REQ ="QUERY_SUB_ORDER_CHECK_RECORD_REQ";
	String QUERY_SUB_ORDER_CHECK_RECORD_RES = "QUERY_SUB_ORDER_CHECK_RECORD_RES";
	
	/**剧院查询二维码地址**/
	String QUERY_SHOW_IMG_URL_REQ ="QUERY_SHOW_IMG_URL_REQ";
	/**查询二维码地址**/
	String QUERY_IMG_URL_REQ ="QUERY_IMG_URL_REQ";
	
	String QUERY_IMG_URL_RES="QUERY_IMG_URL_RES";
	/**
	 * 二维码短链接
	 */
	String QUERY_SHORT_IMG_URL_REQ = "QUERY_SHORT_IMG_URL_REQ";
	String QUERY_SHORT_IMG_URL_RES = "QUERY_SHORT_IMG_URL_RES";
	/**退票状态查询***/
	String  QUERY_RETREAT_STATUS_REQ = "QUERY_RETREAT_STATUS_REQ";
	String  QUERY_RETREAT_STATUS_RES = "QUERY_RETREAT_STATUS_RES";
	
	/***检票通知**/
	String CHECKING_NOTICE_REQ="CHECKING_NOTICE_REQ";
	String CHECKING_NOTICE_RES="CHECKING_NOTICE_RES";
	/***到付订单取消**/
	String CANCEL_SPOT_ORDER_REQ = "CANCEL_SPOT_ORDER_REQ";
	String CANCEL_SPOT_ORDER_RES = "CANCEL_SPOT_ORDER_RES";
	/***校验订单**/
	String  CHECK_ORDER_REQ ="CHECK_ORDER_REQ";
	String  CHECK_ORDER_RES ="CHECK_ORDER_RES";
	/**下单**/
	String CREATE_ORDER_REQ ="CREATE_ORDER_REQ";
	String CREATE_ORDER_RES ="CREATE_ORDER_RES";
	/**支付订单**/
	String PAY_ORDER_REQ = "PAY_ORDER_REQ";
	String PAY_ORDER_RES = "PAY_ORDER_RES";
	/**退票**/
	String RETURN_TICKET_REQ = "RETURN_TICKET_REQ";
	/**退单*/
	String RETURN_ORDER_REQ = "RETURN_ORDER_REQ";
	String RETURN_ORDER_RES = "RETURN_ORDER_RES";
	/***查询退票状态*/
	String QUERY_RETURN_TICKET_STATUS_REQ = "QUERY_RETURN_TICKET_STATUS_REQ";
	String QUERY_RETURN_TICKET_STATUS_RES = "QUERY_RETURN_TICKET_STATUS_RES";


		

}
