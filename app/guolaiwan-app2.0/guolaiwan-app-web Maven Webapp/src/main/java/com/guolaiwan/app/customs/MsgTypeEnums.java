package com.guolaiwan.app.customs;

public class MsgTypeEnums {

	/**
	 * 出口业务单据
	 */
	public static final String OUT_ORDER="CEB303Message";//订单报文结构Order
	public static final String OUT_ORDER_RETURN="CEB304Message";//订单回执报文结构OrderReturn
	
	public static final String OUT_RECEIPTS="CEB403Message";//收款单报文结构Receipts
	public static final String OUT_RECEIPTS_RETURN="CEB404Message";//收款单回执报文ReceiptsReturn
	
	public static final String OUT_LOGISTICS="CEB505Message";//运单报文结构Logistics
	public static final String OUT_LOGISTICS_RETURN="CEB506Message";//运单回执报文结构LogisticsReturn
	
	public static final String OUT_ARRIVAL="CEB507Message";//运抵单报文结构Arrival
	public static final String OUT_ARRIVAL_RETURN="CEB508Message";//运抵单回执报文结构ArrivalReturn
	
	public static final String OUT_DEPARTURE="CEB509Message";//离境单报文结构Departure
	public static final String OUT_DEPARTURE_RETURN="CEB510Message";//离境单回执报文结构DepartureReturn
	
	public static final String OUT_INVENTORY="CEB603Message";//出口清单报文结构Inventory
	public static final String OUT_INVENTORY_RETURN="CEB604Message";//出口清单回执报文结构InventoryReturn
	
	public static final String OUT_INVTCANCEL="CEB605Message";//撤销申请单报文结构InvtCancel
	public static final String OUT_INVTCANCEL_RETURN="CEB606Message";//撤销申请单回执报文结构InvtCancelReturn
	
	public static final String OUT_WAYBILL="CEB607Message";//清单总分单报文结构WayBill
	public static final String OUT_WAYBILL_RETURN="CEB608Message";//清单总分单回执报文结构WayBillReturn
	
	public static final String OUT_SUMMARYAPPLY="CEB701Message";//汇总申请单报文结构SummaryApply
	public static final String OUT_SUMMARY_RETURN="CEB702Message";//汇总申请单回执报文结构SummaryReturn
	
	public static final String OUT_SUMMARYRESULT="CEB792Message";//汇总结果单报文结构SummaryResult
	
	
	/**
	 * 进口业务单据
	 */
	public static final String IN_ORDER="CEB311Message";//订单报文结构Order
	public static final String IN_ORDER_RETURN="CEB312Message";//订单回执报文结构OrderReturn
	
	public static final String IN_PAYMENT="CEB411Message";//支付报文结构Payment
	public static final String IN_PAYMENT_RETURN="CEB412Message";//支付回执报文PaymentReturn
	
	public static final String IN_LOGISTICS="CEB511Message";//运单报文结构Logistics
	public static final String IN_LOGISTICS_RETURN="CEB512Message";//运单回执报文结构LogisticsReturn
	
	public static final String IN_LOGISTICS_STATUS="CEB513Message";//运单状态报文结构LogisticsStatus
	public static final String IN_LOGISTICS_STATUS_RETURN="CEB514Message";//运单状态回执报文结构LogisticsStatusReturn
	
	public static final String IN_INVENTORY="CEB621Message";//进口清单报文结构Inventory
	public static final String IN_INVENTORY_RETURN="CEB622Message";//进口清单回执报文结构InventoryReturn
	
	public static final String IN_INVTCANCEL="CEB623Message";//撤销申请单报文结构InvtCancel
	public static final String IN_INVTCANCEL_RETURN="CEB624Message";//撤销申请单回执报文结构InvtCancelReturn
	
	public static final String IN_INVTREFOUND="CEB625Message";//退货申请单报文结构InvtRefound
	public static final String IN_INVTREFOUND_RETURN="CEB626Message";//退货申请单回执报文结构InvtRefoundReturn
	
	public static final String IN_DELIVERY="CEB711Message";//入库明细单报文结构Delivery
	public static final String IN_DELIVERY_RETURN="CEB712Message";//入库明细单回执报文结构DeliveryReturn
	
}
