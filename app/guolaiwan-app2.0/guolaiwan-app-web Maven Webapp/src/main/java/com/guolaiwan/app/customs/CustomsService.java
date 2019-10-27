package com.guolaiwan.app.customs;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.customs.bo.CEB303Message;
import com.guolaiwan.app.customs.bo.CEB311Message;
import com.guolaiwan.app.customs.bo.CEB403Message;
import com.guolaiwan.app.customs.bo.CEB411Message;
import com.guolaiwan.app.customs.bo.CEB505Message;
import com.guolaiwan.app.customs.bo.CEB507Message;
import com.guolaiwan.app.customs.bo.CEB509Message;
import com.guolaiwan.app.customs.bo.CEB511Message;
import com.guolaiwan.app.customs.bo.CEB513Message;
import com.guolaiwan.app.customs.bo.CEB603Message;
import com.guolaiwan.app.customs.bo.CEB605Message;
import com.guolaiwan.app.customs.bo.CEB607Message;
import com.guolaiwan.app.customs.bo.CEB621Message;
import com.guolaiwan.app.customs.bo.CEB623Message;
import com.guolaiwan.app.customs.bo.CEB625Message;
import com.guolaiwan.app.customs.bo.CEB701Message;
import com.guolaiwan.app.customs.bo.CEB711Message;
import com.guolaiwan.app.customs.bo.CEB792Message;
import com.guolaiwan.app.customs.util.XMLUtil;

import pub.caterpillar.communication.http.client.HttpClient;

@Component
public class CustomsService {

	private static final String SendURL="http://47.95.71.154:8089/pub-cus-core/message/receiveEntMessage";
	//private static final String 云签名="fdd63960-448c-4048-8e74-596a48c331cd";
	private static final String entCode="13029609B5";	//企业备案号	
	private static final String dxpId="DXPENT0000024006";	//企业dxpId	
	private static final String signed="1";	//企业是否已加签	1-是，0-否
	//密钥暂时没用
	private static final String secret="30819f300d06092a864886f70d010101050003818d0030818902818100869d1dd642b037659f785a61e036a9da3fcfbba553e622f793acd380d07b6506e265ceff0967767170ad685a333df498adb8776fe8ee34cda7f7ebc0ae8529f53faedc3b68288f0590b7165f14167b8cea19249810f6a32c08dade8548bc2c57ed82071465e481ccad6fea8bea9517425e8cd325f129b9350d1e6a3fa583e6c10203010001";
	
	private static final String copCode="1105910159";//传输企业代码
	private static final String copName="东方物通科技(北京)有限公司";//传输企业名称
	private static final String dxpMode="DXP";//报文传输模式
//	private static final String dxpId="DXPLGS0000000001";//报文传输编号
	private static final String note="test";//备注
	private static final String guid="311af125-6fed-4603-8c5d-49b1fa4b4b9b";//xml报文的36位系统唯一序号（英文字母大写）
	private static final String version="1.0";//备注xml报文版本号，默认为1.0
	
	
	/**
	 * 传输报文接口
	 * @param msgType
	 * @param content
	 * @return
	 * @throws Exception 
	 */
	public String sendMessage(String msgType, String content) throws Exception{
		CustomBo customBo = new CustomBo();
		customBo.setEntCode(entCode);
		customBo.setMessageType(msgType);
		customBo.setDxpId(dxpId);
		customBo.setSigned(signed);
		
		//将content通过RSA加密处理
		content = RSA.encrypt(RSA.getPublicKey(secret), content, "utf8");
		customBo.setContent(content);
		//报文申报
		String result = HttpClient.postJson(SendURL,JSON.parseObject(JSONObject.toJSONString(customBo)));
		System.out.println(result);
		
		return result;
	}
	
	public String getCeb303(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Order");  
		
		CEB303Message.BaseTransfer baseTransfer = new CEB303Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB303Message ceb303 = new CEB303Message();
		ceb303.setGuid(guid);
		ceb303.setVersion(version);
		ceb303.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("OrderHead");  
            CEB303Message.Order order = new CEB303Message.Order();
            CEB303Message.Order.OrderHead orderHead = new CEB303Message.Order.OrderHead();
    		orderHead.setGuid(jh.getString("guid"));
    		orderHead.setAppType(jh.getString("appType"));
    		orderHead.setAppTime(jh.getString("appTime"));
    		orderHead.setAppStatus(jh.getString("appStatus"));
    		orderHead.setOrderType(jh.getString("orderType"));
    		orderHead.setOrderNo(jh.getString("orderNo"));
    		orderHead.setEbpCode(jh.getString("ebpCode"));
    		orderHead.setEbpName(jh.getString("ebpName"));
    		orderHead.setEbcCode(jh.getString("ebcCode"));
    		orderHead.setEbcName(jh.getString("ebcName"));
    		orderHead.setGoodsValue(jh.getDouble("goodsValue"));
    		orderHead.setFreight(jh.getDouble("freight"));
    		orderHead.setCurrency(jh.getDouble("currency"));
    		orderHead.setNote(jh.getString("note"));
    		order.setOrderHead(orderHead);
            JSONObject jl = ja.getJSONObject(i).getJSONObject("OrderList");  
            CEB303Message.Order.OrderList orderList = new CEB303Message.Order.OrderList();
    		orderList.setGnum(jl.getString("gnum"));
    		orderList.setItemNo(jl.getString("itemNo"));
    		orderList.setItemName(jl.getString("itemName"));
    		orderList.setItemDescribe(jl.getString("itemDescribe"));
    		orderList.setBarCode(jl.getString("barCode"));
    		orderList.setUnit(jl.getString("unit"));
    		orderList.setCurrency(jl.getString("currency"));
    		orderList.setQty(jl.getDouble("qty"));
    		orderList.setPrice(jl.getDouble("price"));
    		orderList.setTotalPrice(jl.getDouble("totalPrice"));
    		orderList.setNote(jl.getString("note"));
    		order.setOrderList(orderList);
    		ceb303.getOrder().add(order);
        } 
        
        String content = XMLUtil.convertToXml(ceb303);
        System.out.println(content);
		return content;
	}
	
	public String getCeb403(JSONObject obj){
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Receipts");  
		
		CEB403Message.BaseTransfer baseTransfer = new CEB403Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB403Message ceb403 = new CEB403Message();
		ceb403.setGuid(guid);
		ceb403.setVersion(version);
		ceb403.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i);  
            CEB403Message.Receipts receipts = new CEB403Message.Receipts();
            receipts.setGuid(jh.getString("guid"));
            receipts.setAppType(jh.getString("appType"));
            receipts.setAppTime(jh.getString("appTime"));
            receipts.setAppStatus(jh.getString("appStatus"));
            receipts.setEbpCode(jh.getString("ebpCode"));
            receipts.setEbpName(jh.getString("ebpName"));
            receipts.setEbcCode(jh.getString("ebcCode"));
            receipts.setEbcName(jh.getString("ebcName"));
            receipts.setOrderNo(jh.getString("orderNo"));
            receipts.setPayCode(jh.getString("payCode"));
            receipts.setPayName(jh.getString("payName"));
            receipts.setPayNo(jh.getString("payNo"));
            receipts.setCharge(jh.getDouble("charge"));
            receipts.setCurrency(jh.getString("currency"));
            receipts.setAccountingDate(jh.getString("accountingDate"));
            receipts.setNote(jh.getString("note"));
            ceb403.getReceipts().add(receipts);
        } 
        
        String content = XMLUtil.convertToXml(ceb403);
        System.out.println(content);
		return content;
	}
	
	public String getCeb505(JSONObject obj){
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Logistics");  
		
		CEB505Message.BaseTransfer baseTransfer = new CEB505Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB505Message ceb505 = new CEB505Message();
		ceb505.setGuid(guid);
		ceb505.setVersion(version);
		ceb505.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i);  
            CEB505Message.Logistics logistics = new CEB505Message.Logistics();
            logistics.setGuid(jh.getString("guid"));
            logistics.setAppType(jh.getString("appType"));
            logistics.setAppTime(jh.getString("appTime"));
            logistics.setAppStatus(jh.getString("appStatus"));
            logistics.setLogisticsCode(jh.getString("logisticsCode"));
            logistics.setLogisticsName(jh.getString("logisticsName"));
            logistics.setLogisticsNo(jh.getString("logisticsNo"));
            logistics.setFreight(jh.getDouble("freight"));
            logistics.setInsuredFee(jh.getDouble("insuredFee"));
            logistics.setCurrency(jh.getString("currency"));
            logistics.setGrossWeight(jh.getDouble("grossWeight"));
            logistics.setPackNo(jh.getDouble("packNo"));
            logistics.setGoodsInfo(jh.getString("goodsInfo"));
            logistics.setEbcCode(jh.getString("ebcCode"));
            logistics.setEbcName(jh.getString("ebcName"));
            logistics.setEbcTelephone(jh.getString("ebcTelephone"));
            logistics.setNote(jh.getString("note"));
            ceb505.getLogistics().add(logistics);
        } 
        
        String content = XMLUtil.convertToXml(ceb505);
        System.out.println(content);
		return content;
	}
	
	public String getCeb507(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Arrival");  
		
		CEB507Message.BaseTransfer baseTransfer = new CEB507Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB507Message ceb507 = new CEB507Message();
		ceb507.setGuid(guid);
		ceb507.setVersion(version);
		ceb507.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("ArrivalHead");  
            CEB507Message.Arrival arrival = new CEB507Message.Arrival();
            CEB507Message.Arrival.ArrivalHead arrivalHead = new CEB507Message.Arrival.ArrivalHead();
            arrivalHead.setGuid(jh.getString("guid"));
            arrivalHead.setAppType(jh.getString("appType"));
            arrivalHead.setAppTime(jh.getString("appTime"));
            arrivalHead.setAppStatus(jh.getString("appStatus"));
            arrivalHead.setCustomsCode(jh.getString("customsCode"));
            arrivalHead.setCopNo(jh.getString("copNo"));
            arrivalHead.setOperatorCode(jh.getString("operatorCode"));
            arrivalHead.setOperatorName(jh.getString("operatorName"));
            arrivalHead.setLoctNo(jh.getString("loctNo"));
            arrivalHead.setIeFlag(jh.getString("ieFlag"));
            arrivalHead.setTrafMode(jh.getString("trafMode"));
            arrivalHead.setBillNo(jh.getString("billNo"));
            arrivalHead.setDomesticTrafNo(jh.getString("domesticTrafNo"));
            arrivalHead.setLogisticsCode(jh.getString("logisticsCode"));
            arrivalHead.setLogisticsName(jh.getString("logisticsName"));
            arrivalHead.setMsgCount(jh.getDouble("msgCount"));
            arrivalHead.setMsgSeqNo(jh.getDouble("msgSeqNo"));
            arrivalHead.setNote(jh.getString("note"));
            arrival.setArrivalHead(arrivalHead);
    		JSONArray jb = ja.getJSONObject(i).getJSONArray("ArrivalList");
    		for (int j = 0; j < jb.size(); j++) {
    			JSONObject jl = jb.getJSONObject(j);
    			CEB507Message.Arrival.ArrivalList arrivalList = new CEB507Message.Arrival.ArrivalList();
    			arrivalList.setGnum(jl.getInteger("gnum"));
    			arrivalList.setLogisticsNo(jl.getString("logisticsNo"));
    			arrivalList.setTotalPackageNo(jl.getString("totalPackageNo"));
    			arrivalList.setNote(jl.getString("note"));
    			arrival.getArrivalList().add(arrivalList);
    		}
    		ceb507.getArrival().add(arrival);
        } 
        
        String content = XMLUtil.convertToXml(ceb507);
        System.out.println(content);
		return content;
	}

	public String getCeb509(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Departure");  
		
		CEB509Message.BaseTransfer baseTransfer = new CEB509Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB509Message ceb509 = new CEB509Message();
		ceb509.setGuid(guid);
		ceb509.setVersion(version);
		ceb509.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("DepartureHead");  
            CEB509Message.Departure departure = new CEB509Message.Departure();
            CEB509Message.Departure.DepartureHead departureHead = new CEB509Message.Departure.DepartureHead();
            departureHead.setGuid(jh.getString("guid"));
            departureHead.setAppType(jh.getString("appType"));
            departureHead.setAppTime(jh.getString("appTime"));
            departureHead.setAppStatus(jh.getString("appStatus"));
            departureHead.setCustomsCode(jh.getString("customsCode"));
            departureHead.setCopNo(jh.getString("copNo"));
            departureHead.setLogisticsCode(jh.getString("logisticsCode"));
            departureHead.setLogisticsName(jh.getString("logisticsName"));
            departureHead.setTrafMode(jh.getString("trafMode"));
            departureHead.setTrafName(jh.getString("trafName"));
            departureHead.setVoyageNo(jh.getString("voyageNo"));
            departureHead.setBillNo(jh.getString("billNo"));
            departureHead.setLeaveTime(jh.getString("leaveTime"));
            departureHead.setMsgCount(jh.getDouble("msgCount"));
            departureHead.setMsgSeqNo(jh.getDouble("msgSeqNo"));
            departureHead.setNote(jh.getString("note"));
            departure.setDepartureHead(departureHead);
            JSONObject jl = ja.getJSONObject(i).getJSONObject("DepartureList");  
            CEB509Message.Departure.DepartureList departurelist = new CEB509Message.Departure.DepartureList();
            departurelist.setGnum(jl.getInteger("gnum"));
            departurelist.setTotalPackageNo(jl.getString("totalPackageNo"));
            departurelist.setLogisticsNo(jl.getString("logisticsNo"));
            departurelist.setNote(jl.getString("note"));
            departure.setDepartureList(departurelist);
    		ceb509.getDeparture().add(departure);
        } 
        
        String content = XMLUtil.convertToXml(ceb509);
        System.out.println(content);
		return content;
	}

	public String getCeb603(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Inventory");  
		
		CEB603Message.BaseTransfer baseTransfer = new CEB603Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB603Message ceb603 = new CEB603Message();
		ceb603.setGuid(guid);
		ceb603.setVersion(version);
		ceb603.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("InventoryHead");  
            CEB603Message.Inventory inventory = new CEB603Message.Inventory();
            CEB603Message.Inventory.InventoryHead inventoryHead = new CEB603Message.Inventory.InventoryHead();
            inventoryHead.setGuid(jh.getString("guid"));
            inventoryHead.setAppType(jh.getString("appType"));
            inventoryHead.setAppTime(jh.getString("appTime"));
            inventoryHead.setAppStatus(jh.getString("appStatus"));
            inventoryHead.setCustomsCode(jh.getString("customsCode"));
            inventoryHead.setEbpCode(jh.getString("ebpCode"));
            inventoryHead.setEbpName(jh.getString("ebpName"));
            inventoryHead.setOrderNo(jh.getString("orderNo"));
            inventoryHead.setLogisticsCode(jh.getString("logisticsCode"));
            inventoryHead.setLogisticsName(jh.getString("logisticsName"));
            inventoryHead.setLogisticsNo(jh.getString("logisticsNo"));
            inventoryHead.setCopNo(jh.getString("copNo"));
            inventoryHead.setIeFlag(jh.getString("ieFlag"));
            inventoryHead.setPortCode(jh.getString("portCode"));
            inventoryHead.setIeDate(jh.getString("ieDate"));
            inventoryHead.setStatisticsFlag(jh.getString("statisticsFlag"));
            inventoryHead.setAgentCode(jh.getString("agentCode"));
            inventoryHead.setAgentName(jh.getString("agentName"));
            inventoryHead.setEbcCode(jh.getString("ebcCode"));
            inventoryHead.setEbcName(jh.getString("ebcName"));
            inventoryHead.setOwnerCode(jh.getString("ownerCode"));
            inventoryHead.setOwnerName(jh.getString("ownerName"));
            inventoryHead.setIacCode(jh.getString("iacCode"));
            inventoryHead.setIacName(jh.getString("iacName"));
            inventoryHead.setEmsNo(jh.getString("emsNo"));
            inventoryHead.setTradeMode(jh.getString("tradeMode"));
            inventoryHead.setTrafMode(jh.getString("trafMode"));
            inventoryHead.setTrafName(jh.getString("trafName"));
            inventoryHead.setVoyageNo(jh.getString("voyageNo"));
            inventoryHead.setBillNo(jh.getString("billNo"));
            inventoryHead.setTotalPackageNo(jh.getString("totalPackageNo"));
            inventoryHead.setLoctNo(jh.getString("loctNo"));
            inventoryHead.setCountry(jh.getString("country"));
            inventoryHead.setPOD(jh.getString("POD"));
            inventoryHead.setFreight(jh.getDouble("freight"));
            inventoryHead.setFCurrency(jh.getString("fCurrency"));
            inventoryHead.setFFlag(jh.getString("fFlag"));
            inventoryHead.setInsuredFee(jh.getDouble("insuredFee"));
            inventoryHead.setICurrency(jh.getString("iCurrency"));
            inventoryHead.setIFlag(jh.getString("iFlag"));
            inventoryHead.setWrapType(jh.getString("wrapType"));
            inventoryHead.setPackNo(jh.getInteger("packNo"));
            inventoryHead.setGrossWeight(jh.getDouble("grossWeight"));
            inventoryHead.setNetWeight(jh.getDouble("netWeight"));
            inventoryHead.setNote(jh.getString("note"));
            inventory.setInventoryHead(inventoryHead);
            JSONObject jl = ja.getJSONObject(i).getJSONObject("InventoryList");  
            CEB603Message.Inventory.InventoryList inventoryList = new CEB603Message.Inventory.InventoryList();
            inventoryList.setGnum(jl.getInteger("gnum"));
            inventoryList.setItemNo(jl.getString("itemNo"));
            inventoryList.setItemRecordNo(jl.getString("itemRecordNo"));
            inventoryList.setItemName(jl.getString("itemName"));
            inventoryList.setGcode(jl.getString("gcode"));
            inventoryList.setGname(jl.getString("gname"));
            inventoryList.setGmodel(jl.getString("gmodel"));
            inventoryList.setBarCode(jl.getString("barCode"));
            inventoryList.setCountry(jl.getString("country"));
            inventoryList.setCurrency(jl.getString("currency"));
    		inventoryList.setQty(jl.getDouble("qty"));
    		inventoryList.setQty1(jl.getDouble("qty1"));
    		inventoryList.setQty2(jl.getDouble("qty2"));
    		inventoryList.setUnit(jl.getString("unit"));
    		inventoryList.setUnit1(jl.getString("unit1"));
    		inventoryList.setUnit2(jl.getString("unit2"));
    		inventoryList.setPrice(jl.getDouble("price"));
    		inventoryList.setTotalPrice(jl.getDouble("totalPrice"));
    		inventoryList.setNote(jl.getString("note"));
    		inventory.setInventoryList(inventoryList);
    		ceb603.getInventory().add(inventory);
        } 
        
        String content = XMLUtil.convertToXml(ceb603);
        System.out.println(content);
		return content;
	}

	public String getCeb605(JSONObject obj){
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("InvtCancel");  
		
		CEB605Message.BaseTransfer baseTransfer = new CEB605Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB605Message ceb605 = new CEB605Message();
		ceb605.setGuid(guid);
		ceb605.setVersion(version);
		ceb605.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i);  
            CEB605Message.InvtCancel invtCancel = new CEB605Message.InvtCancel();
            invtCancel.setGuid(jh.getString("guid"));
            invtCancel.setAppType(jh.getString("appType"));
            invtCancel.setAppTime(jh.getString("appTime"));
            invtCancel.setAppStatus(jh.getString("appStatus"));
            invtCancel.setCustomsCode(jh.getString("customsCode"));
            invtCancel.setCopNo(jh.getString("copNo"));
            invtCancel.setInvtNo(jh.getString("invtNo"));
            invtCancel.setReason(jh.getString("reason"));
            invtCancel.setAgentCode(jh.getString("agentCode"));
            invtCancel.setAgentName(jh.getString("agentName"));
            invtCancel.setEbcCode(jh.getString("ebcCode"));
            invtCancel.setEbcName(jh.getString("ebcName"));
            invtCancel.setNote(jh.getString("note"));
            ceb605.getInvtCancel().add(invtCancel);
        } 
        
        String content = XMLUtil.convertToXml(ceb605);
        System.out.println(content);
		return content;
	}

	public String getCeb607(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("WayBill");  
		
		CEB607Message.BaseTransfer baseTransfer = new CEB607Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB607Message ceb607 = new CEB607Message();
		ceb607.setGuid(guid);
		ceb607.setVersion(version);
		ceb607.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("WayBillHead");  
            CEB607Message.WayBill wayBill = new CEB607Message.WayBill();
            CEB607Message.WayBill.WayBillHead wayBillHead = new CEB607Message.WayBill.WayBillHead();
            wayBillHead.setGuid(jh.getString("guid"));
            wayBillHead.setAppType(jh.getString("appType"));
            wayBillHead.setAppTime(jh.getString("appTime"));
            wayBillHead.setAppStatus(jh.getString("appStatus"));
            wayBillHead.setCustomsCode(jh.getString("customsCode"));
            wayBillHead.setCopNo(jh.getString("copNo"));
            wayBillHead.setAgentCode(jh.getString("agentCode"));
            wayBillHead.setAgentName(jh.getString("agentName"));
            wayBillHead.setLoctNo(jh.getString("loctNo"));
            wayBillHead.setTrafMode(jh.getString("trafMode"));
            wayBillHead.setTrafName(jh.getString("trafName"));
            wayBillHead.setVoyageNo(jh.getString("voyageNo"));
            wayBillHead.setBillNo(jh.getString("billNo"));
            wayBillHead.setDomesticTrafNo(jh.getString("domesticTrafNo"));
            wayBillHead.setGrossWeight(jh.getDouble("grossWeight"));
            wayBillHead.setLogisticsCode(jh.getString("logisticsCode"));
            wayBillHead.setLogisticsName(jh.getString("logisticsName"));
            wayBillHead.setMsgCount(jh.getInteger("msgCount"));
            wayBillHead.setMsgSeqNo(jh.getInteger("msgSeqNo"));
            wayBillHead.setNote(jh.getString("note"));
            wayBill.setWayBillHead(wayBillHead);
            JSONObject jl = ja.getJSONObject(i).getJSONObject("WayBillList");  
            CEB607Message.WayBill.WayBillList wayBillList = new CEB607Message.WayBill.WayBillList();
            wayBillList.setGnum(jl.getInteger("gnum"));
            wayBillList.setTotalPackageNo(jl.getString("totalPackageNo"));
            wayBillList.setLogisticsNo(jl.getString("logisticsNo"));
            wayBillList.setNote(jh.getString("note"));
            wayBill.setWayBillList(wayBillList);
    		ceb607.getWayBill().add(wayBill);
        } 
        
        String content = XMLUtil.convertToXml(ceb607);
        System.out.println(content);
		return content;
	}
	
	public String getCeb701(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("SummaryApply");  
		
		CEB701Message.BaseTransfer baseTransfer = new CEB701Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
//		baseTransfer.setNote(note);
		
		CEB701Message ceb701 = new CEB701Message();
		ceb701.setGuid(guid);
		ceb701.setVersion(version);
		ceb701.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("SummaryApplyHead");  
            CEB701Message.SummaryApply summaryApply = new CEB701Message.SummaryApply();
            CEB701Message.SummaryApply.SummaryApplyHead summaryApplyHead = new CEB701Message.SummaryApply.SummaryApplyHead();
            summaryApplyHead.setGuid(jh.getString("guid"));
            summaryApplyHead.setAppType(jh.getString("appType"));
            summaryApplyHead.setAppTime(jh.getString("appTime"));
            summaryApplyHead.setAppStatus(jh.getString("appStatus"));
            summaryApplyHead.setCustomsCode(jh.getString("customsCode"));
            summaryApplyHead.setCopNo(jh.getString("copNo"));
            summaryApplyHead.setAgentCode(jh.getString("agentCode"));
            summaryApplyHead.setAgentName(jh.getString("agentName"));
            summaryApplyHead.setEbcCode(jh.getString("ebcCode"));
            summaryApplyHead.setEbcName(jh.getString("ebcName"));
            summaryApplyHead.setDeclAgentCode(jh.getString("declAgentCode"));
            summaryApplyHead.setDeclAgentName(jh.getString("declAgentName"));
            summaryApplyHead.setSummaryFlag(jh.getString("summaryFlag"));
            summaryApplyHead.setItemNameFlag(jh.getString("itemNameFlag"));
            summaryApplyHead.setMsgCount(jh.getInteger("msgCount"));
            summaryApplyHead.setMsgSeqNo(jh.getInteger("msgSeqNo"));
            summaryApply.setSummaryApplyHead(summaryApplyHead);
    		JSONArray jb = ja.getJSONObject(i).getJSONArray("SummaryApplyList");
    		for (int j = 0; j < jb.size(); j++) {
    			JSONObject jl = jb.getJSONObject(j);
    			CEB701Message.SummaryApply.SummaryApplyList summaryApplyList = new CEB701Message.SummaryApply.SummaryApplyList();
    			summaryApplyList.setInvtNo(jl.getString("invtNo"));
    			summaryApply.getSummaryApplyList().add(summaryApplyList);
    		}
    		ceb701.getSummaryApply().add(summaryApply);
        } 
        
        String content = XMLUtil.convertToXml(ceb701);
        System.out.println(content);
		return content;
	}
	
	public String getCeb792(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("SummaryResult");  
		
//		CEB792Message.BaseTransfer baseTransfer = new CEB792Message.BaseTransfer();
//		baseTransfer.setCopCode(copCode);
//		baseTransfer.setCopName(copName);
//		baseTransfer.setDxpMode(dxpMode);
//		baseTransfer.setDxpId(dxpId);
//		baseTransfer.setNote(note);
		
		CEB792Message ceb792 = new CEB792Message();
		ceb792.setGuid(guid);
		ceb792.setVersion(version);
//		ceb792.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("SummaryResultHead");  
            CEB792Message.SummaryResult summaryResult = new CEB792Message.SummaryResult();
            CEB792Message.SummaryResult.SummaryResultHead summaryResultHead = new CEB792Message.SummaryResult.SummaryResultHead();
            summaryResultHead.setGuid(jh.getString("guid"));
            summaryResultHead.setCustomsCode(jh.getString("customsCode"));
            summaryResultHead.setSumNo(jh.getString("sumNo"));
            summaryResultHead.setOpDate(jh.getString("opDate"));
            summaryResultHead.setDeclSeqNo(jh.getString("declSeqNo"));
            summaryResultHead.setDecState(jh.getString("decState"));
            summaryResultHead.setAgentCode(jh.getString("agentCode"));
            summaryResultHead.setAgentName(jh.getString("agentName"));
            summaryResultHead.setEbcCode(jh.getString("ebcCode"));
            summaryResultHead.setEbcName(jh.getString("ebcName"));
            summaryResultHead.setDeclAgentCode(jh.getString("declAgentCode"));
            summaryResultHead.setDeclAgentName(jh.getString("declAgentName"));
            summaryResultHead.setGrossWeight(jh.getDouble("grossWeight"));
            summaryResultHead.setNetWeight(jh.getDouble("netWeight"));
            summaryResultHead.setMsgCount(jh.getInteger("msgCount"));
            summaryResultHead.setMsgSeqNo(jh.getInteger("msgSeqNo"));
            summaryResult.setSummaryResultHead(summaryResultHead);
    		JSONArray jb = ja.getJSONObject(i).getJSONArray("SummaryResultList");
    		for (int j = 0; j < jb.size(); j++) {
    			JSONObject jl = jb.getJSONObject(j);
    			CEB792Message.SummaryResult.SummaryResultList summaryResultList = new CEB792Message.SummaryResult.SummaryResultList();
    			summaryResultList.setInvtNo(jl.getString("invtNo"));
    			summaryResultList.setLogisticsNo(jl.getString("logisticsNo"));
    			summaryResultList.setGnum(jl.getInteger("gnum"));
    			summaryResultList.setGcode(jl.getString("gcode"));
    			summaryResult.getSummaryResultList().add(summaryResultList);
    		}
    		ceb792.getSummaryResult().add(summaryResult);
        } 
        
        String content = XMLUtil.convertToXml(ceb792);
        System.out.println(content);
		return content;
	}
	
	
	public String getCeb311(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Order");  
		
		CEB311Message.BaseTransfer baseTransfer = new CEB311Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB311Message ceb311 = new CEB311Message();
		ceb311.setGuid(guid);
		ceb311.setVersion(version);
		ceb311.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("OrderHead");  
            CEB311Message.Order order = new CEB311Message.Order();
            CEB311Message.Order.OrderHead orderHead = new CEB311Message.Order.OrderHead();
            orderHead.setGuid(jh.getString("guid"));
            orderHead.setAppType(jh.getString("appType"));
            orderHead.setAppTime(jh.getString("appTime"));
            orderHead.setAppStatus(jh.getString("appStatus"));
            orderHead.setOrderType(jh.getString("orderType"));
            orderHead.setOrderNo(jh.getString("orderNo"));
            orderHead.setEbpCode(jh.getString("ebpCode"));
            orderHead.setEbpName(jh.getString("ebpName"));
            orderHead.setEbcCode(jh.getString("ebcCode"));
            orderHead.setEbcName(jh.getString("ebcName"));
            orderHead.setGoodsValue(jh.getDouble("goodsValue"));
            orderHead.setFreight(jh.getDouble("freight"));
            orderHead.setDiscount(jh.getDouble("discount"));
            orderHead.setTaxTotal(jh.getDouble("taxTotal"));
            orderHead.setActuralPaid(jh.getDouble("acturalPaid"));
            orderHead.setCurrency(jh.getString("currency"));
            orderHead.setBuyerRegNo(jh.getString("buyerRegNo"));
            orderHead.setBuyerName(jh.getString("buyerName"));
            orderHead.setBuyerTelephone(jh.getString("buyerTelephone"));
            orderHead.setBuyerIdType(jh.getString("buyerIdType"));
            orderHead.setBuyerIdNumber(jh.getString("buyerIdNumber"));
            orderHead.setPayCode(jh.getString("payCode"));
            orderHead.setPayName(jh.getString("payName"));
            orderHead.setPayTransactionId(jh.getString("payTransactionId"));
            orderHead.setBatchNumbers(jh.getString("batchNumbers"));
            orderHead.setConsignee(jh.getString("consignee"));
            orderHead.setConsigneeTelephone(jh.getString("consigneeTelephone"));
            orderHead.setConsigneeAddress(jh.getString("consigneeAddress"));
            orderHead.setConsigneeDistrict(jh.getString("consigneeDistrict"));
            orderHead.setNote(jh.getString("note"));
            order.setOrderHead(orderHead);
    		JSONArray jb = ja.getJSONObject(i).getJSONArray("OrderList");
    		for (int j = 0; j < jb.size(); j++) {
    			JSONObject jl = jb.getJSONObject(j);
    			CEB311Message.Order.OrderList orderList = new CEB311Message.Order.OrderList();
    			orderList.setGnum(jl.getString("gnum"));
    			orderList.setItemNo(jl.getString("itemNo"));
    			orderList.setItemName(jl.getString("itemName"));
    			orderList.setGmodel(jl.getString("gmodel"));
    			orderList.setItemDescribe(jl.getString("itemDescribe"));
    			orderList.setBarCode(jl.getString("barCode"));
    			orderList.setUnit(jl.getString("unit"));
    			orderList.setQty(jl.getDouble("qty"));
    			orderList.setPrice(jl.getDouble("price"));
    			orderList.setTotalPrice(jl.getDouble("totalPrice"));
    			orderList.setCurrency(jl.getString("currency"));
    			orderList.setCountry(jl.getString("country"));
    			orderList.setNote(jl.getString("note"));
    			order.getOrderList().add(orderList);
    		}
    		ceb311.getOrder().add(order);
        } 
        
        String content = XMLUtil.convertToXml(ceb311);
        System.out.println(content);
		return content;
	}
	
	public String getCeb411(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Payment");  
		
		CEB411Message.BaseTransfer baseTransfer = new CEB411Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB411Message ceb411 = new CEB411Message();
		ceb411.setGuid(guid);
		ceb411.setVersion(version);
		ceb411.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("PaymentHead");  
            CEB411Message.Payment payment = new CEB411Message.Payment();
            CEB411Message.Payment.PaymentHead paymentHead = new CEB411Message.Payment.PaymentHead();
            paymentHead.setGuid(jh.getString("guid"));
            paymentHead.setAppType(jh.getString("appType"));
            paymentHead.setAppTime(jh.getString("appTime"));
            paymentHead.setAppStatus(jh.getString("appStatus"));
            paymentHead.setPayCode(jh.getString("payCode"));
            paymentHead.setPayName(jh.getString("payName"));
            paymentHead.setPayTransactionId(jh.getString("payTransactionId"));
            paymentHead.setOrderNo(jh.getString("orderNo"));
            paymentHead.setEbpCode(jh.getString("ebpCode"));
            paymentHead.setEbpName(jh.getString("ebpName"));
            paymentHead.setPayerIdType(jh.getString("payerIdType"));
            paymentHead.setPayerIdNumber(jh.getString("payerIdNumber"));
            paymentHead.setPayerName(jh.getString("payerName"));
            paymentHead.setTelephone(jh.getString("telephone"));
            paymentHead.setAmountPaid(jh.getDouble("amountPaid"));
            paymentHead.setCurrency(jh.getString("currency"));
            paymentHead.setPayTime(jh.getString("payTime"));
            paymentHead.setNote(jh.getString("note"));
            payment.setPaymentHead(paymentHead);
    		ceb411.getPayment().add(payment);
        } 
        
        String content = XMLUtil.convertToXml(ceb411);
        System.out.println(content);
		return content;
	}
	
	public String getCeb511(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Logistics");  
		
		CEB511Message.BaseTransfer baseTransfer = new CEB511Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB511Message ceb511 = new CEB511Message();
		ceb511.setGuid(guid);
		ceb511.setVersion(version);
		ceb511.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("LogisticsHead");  
            CEB511Message.Logistics logistics = new CEB511Message.Logistics();
            CEB511Message.Logistics.LogisticsHead logisticsHead = new CEB511Message.Logistics.LogisticsHead();
            logisticsHead.setGuid(jh.getString("guid"));
            logisticsHead.setAppType(jh.getString("appType"));
            logisticsHead.setAppTime(jh.getString("appTime"));
            logisticsHead.setAppStatus(jh.getString("appStatus"));
            logisticsHead.setLogisticsCode(jh.getString("logisticsCode"));
            logisticsHead.setLogisticsName(jh.getString("logisticsName"));
            logisticsHead.setLogisticsNo(jh.getString("logisticsNo"));
            logisticsHead.setBillNo(jh.getString("billNo"));
            logisticsHead.setOrderNo(jh.getString("orderNo"));
            logisticsHead.setFreight(jh.getDouble("freight"));
            logisticsHead.setInsuredFee(jh.getDouble("insuredFee"));
            logisticsHead.setCurrency(jh.getString("currency"));
            logisticsHead.setWeight(jh.getDouble("weight"));
            logisticsHead.setPackNo(jh.getDouble("packNo"));
            logisticsHead.setGoodsInfo(jh.getString("goodsInfo"));
            logisticsHead.setConsignee(jh.getString("consignee"));
            logisticsHead.setConsigneeAddress(jh.getString("consigneeAddress"));
            logisticsHead.setConsigneeTelephone(jh.getString("consigneeTelephone"));
            logisticsHead.setNote(jh.getString("note"));
            logistics.setLogisticsHead(logisticsHead);
    		ceb511.getLogistics().add(logistics);
        } 
        
        String content = XMLUtil.convertToXml(ceb511);
        System.out.println(content);
		return content;
	}
	
	public String getCeb513(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("LogisticsStatus");  
		
		CEB513Message.BaseTransfer baseTransfer = new CEB513Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB513Message ceb513 = new CEB513Message();
		ceb513.setGuid(guid);
		ceb513.setVersion(version);
		ceb513.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i);  
            CEB513Message.LogisticsStatus logisticsStatus = new CEB513Message.LogisticsStatus();
            logisticsStatus.setGuid(jh.getString("guid"));
            logisticsStatus.setAppType(jh.getString("appType"));
            logisticsStatus.setAppTime(jh.getString("appTime"));
            logisticsStatus.setAppStatus(jh.getString("appStatus"));
            logisticsStatus.setLogisticsCode(jh.getString("logisticsCode"));
            logisticsStatus.setLogisticsName(jh.getString("logisticsName"));
            logisticsStatus.setLogisticsNo(jh.getString("logisticsNo"));
            logisticsStatus.setLogisticsStatus(jh.getString("logisticsStatus"));
            logisticsStatus.setLogisticsTime(jh.getString("logisticsTime"));
            logisticsStatus.setNote(jh.getString("note"));
    		ceb513.getLogisticsStatus().add(logisticsStatus);
        } 
        
        String content = XMLUtil.convertToXml(ceb513);
        System.out.println(content);
		return content;
	}
	
	public String getCeb621(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("Inventory");  
		
		CEB621Message.BaseTransfer baseTransfer = new CEB621Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB621Message ceb621 = new CEB621Message();
		ceb621.setGuid(guid);
		ceb621.setVersion(version);
		ceb621.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("InventoryHead");  
            CEB621Message.Inventory inventory = new CEB621Message.Inventory();
            CEB621Message.Inventory.InventoryHead inventoryHead = new CEB621Message.Inventory.InventoryHead();
            inventoryHead.setGuid(jh.getString("guid"));
            inventoryHead.setAppType(jh.getString("appType"));
            inventoryHead.setAppTime(jh.getString("appTime"));
            inventoryHead.setAppStatus(jh.getString("appStatus"));
            inventoryHead.setOrderNo(jh.getString("orderNo"));
            inventoryHead.setEbpCode(jh.getString("ebpCode"));
            inventoryHead.setEbpName(jh.getString("ebpName"));
            inventoryHead.setEbcCode(jh.getString("ebcCode"));
            inventoryHead.setEbcName(jh.getString("ebcName"));
            inventoryHead.setLogisticsNo(jh.getString("logisticsNo"));
            inventoryHead.setLogisticsCode(jh.getString("logisticsCode"));
            inventoryHead.setLogisticsName(jh.getString("logisticsName"));
            inventoryHead.setCopNo(jh.getString("copNo"));
            inventoryHead.setPreNo(jh.getString("preNo"));
            inventoryHead.setAssureCode(jh.getString("assureCode"));
            inventoryHead.setEmsNo(jh.getString("emsNo"));
            inventoryHead.setInvtNo(jh.getString("invtNo"));
            inventoryHead.setIeFlag(jh.getString("ieFlag"));
            inventoryHead.setDeclTime(jh.getString("declTime"));
            inventoryHead.setCustomsCode(jh.getString("customsCode"));
            inventoryHead.setPortCode(jh.getString("portCode"));
            inventoryHead.setIeDate(jh.getString("ieDate"));
            inventoryHead.setBuyerIdType(jh.getString("buyerIdType"));
            inventoryHead.setBuyerIdNumber(jh.getString("buyerIdNumber"));
            inventoryHead.setBuyerName(jh.getString("buyerName"));
            inventoryHead.setBuyerTelephone(jh.getString("buyerTelephone"));
            inventoryHead.setConsigneeAddress(jh.getString("consigneeAddress"));
            inventoryHead.setAgentCode(jh.getString("agentCode"));
            inventoryHead.setAgentName(jh.getString("agentName"));
            inventoryHead.setAreaCode(jh.getString("areaCode"));
            inventoryHead.setAreaName(jh.getString("areaName"));
            inventoryHead.setTradeMode(jh.getString("tradeMode"));
            inventoryHead.setTrafMode(jh.getString("trafMode"));
            inventoryHead.setTrafNo(jh.getString("trafNo"));
            inventoryHead.setVoyageNo(jh.getString("voyageNo"));
            inventoryHead.setBillNo(jh.getString("billNo"));
            inventoryHead.setLoctNo(jh.getString("loctNo"));
            inventoryHead.setLicenseNo(jh.getString("licenseNo"));
            inventoryHead.setCountry(jh.getString("country"));
            inventoryHead.setFreight(jh.getDouble("freight"));
            inventoryHead.setInsuredFee(jh.getDouble("insuredFee"));
            inventoryHead.setCurrency(jh.getString("currency"));
            inventoryHead.setWrapType(jh.getString("wrapType"));
            inventoryHead.setPackNo(jh.getInteger("packNo"));
            inventoryHead.setFreight(jh.getDouble("grossWeight"));
            inventoryHead.setNetWeight(jh.getDouble("netWeight"));
            inventoryHead.setNote(jh.getString("note"));
            inventory.setInventoryHead(inventoryHead);
    		JSONArray jb = ja.getJSONObject(i).getJSONArray("InventoryList");
    		for (int j = 0; j < jb.size(); j++) {
    			JSONObject jl = jb.getJSONObject(j);
    			CEB621Message.Inventory.InventoryList inventoryList = new CEB621Message.Inventory.InventoryList();
    			inventoryList.setGnum(jl.getInteger("gnum"));
    			inventoryList.setItemRecordNo(jl.getString("itemRecordNo"));
    			inventoryList.setItemNo(jl.getString("itemNo"));
    			inventoryList.setItemName(jl.getString("itemName"));
    			inventoryList.setGcode(jl.getString("gcode"));
    			inventoryList.setGname(jl.getString("gname"));
    			inventoryList.setGmodel(jl.getString("gmodel"));
    			inventoryList.setBarCode(jl.getString("barCode"));
    			inventoryList.setCountry(jl.getString("country"));
    			inventoryList.setTradeCountry(jl.getDouble("tradeCountry"));
    			inventoryList.setCurrency(jl.getString("currency"));
    			inventoryList.setQty(jl.getDouble("qty"));
    			inventoryList.setUnit(jl.getString("unit"));
    			inventoryList.setQty1(jl.getDouble("qty1"));
    			inventoryList.setUnit1(jl.getString("unit1"));
    			inventoryList.setQty2(jl.getDouble("qty2"));
    			inventoryList.setUnit2(jl.getString("unit2"));
    			inventoryList.setPrice(jl.getDouble("price"));
    			inventoryList.setTotalPrice(jl.getDouble("totalPrice"));
    			inventoryList.setNote(jl.getString("note"));
    			inventory.getInventoryList().add(inventoryList);
    		}
    		ceb621.getInventory().add(inventory);
        } 
        
        String content = XMLUtil.convertToXml(ceb621);
        System.out.println(content);
		return content;
	}
	
	public String getCeb623(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("InvtCancel");  
		
		CEB623Message.BaseTransfer baseTransfer = new CEB623Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB623Message ceb623 = new CEB623Message();
		ceb623.setGuid(guid);
		ceb623.setVersion(version);
		ceb623.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i);  
            CEB623Message.InvtCancel invtCancel = new CEB623Message.InvtCancel();
            invtCancel.setGuid(jh.getString("guid"));
            invtCancel.setAppType(jh.getString("appType"));
            invtCancel.setAppTime(jh.getString("appTime"));
            invtCancel.setAppStatus(jh.getString("appStatus"));
            invtCancel.setCustomsCode(jh.getString("customsCode"));
            invtCancel.setOrderNo(jh.getString("orderNo"));
            invtCancel.setEbpCode(jh.getString("ebpCode"));
            invtCancel.setEbpName(jh.getString("ebpName"));
            invtCancel.setEbcCode(jh.getString("ebcCode"));
            invtCancel.setEbcName(jh.getString("ebcName"));
            invtCancel.setLogisticsNo(jh.getString("logisticsNo"));
            invtCancel.setLogisticsCode(jh.getString("logisticsCode"));
            invtCancel.setLogisticsName(jh.getString("logisticsName"));
            invtCancel.setCopNo(jh.getString("copNo"));
            invtCancel.setPreNo(jh.getString("preNo"));
            invtCancel.setInvtNo(jh.getString("invtNo"));
            invtCancel.setBuyerIdType(jh.getString("buyerIdType"));
            invtCancel.setBuyerIdNumber(jh.getString("buyerIdNumber"));
            invtCancel.setBuyerName(jh.getString("buyerName"));
            invtCancel.setBuyerTelephone(jh.getString("buyerTelephone"));
            invtCancel.setAgentCode(jh.getString("agentCode"));
            invtCancel.setAgentName(jh.getString("agentName"));
            invtCancel.setReason(jh.getString("reason"));
            invtCancel.setNote(jh.getString("note"));
    		ceb623.getInvtCancel().add(invtCancel);
        } 
        
        String content = XMLUtil.convertToXml(ceb623);
        System.out.println(content);
		return content;
	}
	
	public String getCeb625(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONArray ja = jsonObjects.getJSONArray("InvtRefund");  
		
		CEB625Message.BaseTransfer baseTransfer = new CEB625Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB625Message ceb625 = new CEB625Message();
		ceb625.setGuid(guid);
		ceb625.setVersion(version);
		ceb625.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("InvtRefundHead");  
            CEB625Message.InvtRefund invtRefund = new CEB625Message.InvtRefund();
            CEB625Message.InvtRefund.InvtRefundHead invtRefundHead = new CEB625Message.InvtRefund.InvtRefundHead();
            invtRefundHead.setGuid(jh.getString("guid"));
            invtRefundHead.setAppType(jh.getString("appType"));
            invtRefundHead.setAppTime(jh.getString("appTime"));
            invtRefundHead.setAppStatus(jh.getString("appStatus"));
            invtRefundHead.setCustomsCode(jh.getString("customsCode"));
            invtRefundHead.setOrderNo(jh.getString("orderNo"));
            invtRefundHead.setEbpCode(jh.getString("ebpCode"));
            invtRefundHead.setEbpName(jh.getString("ebpName"));
            invtRefundHead.setEbcCode(jh.getString("ebcCode"));
            invtRefundHead.setEbcName(jh.getString("ebcName"));
            invtRefundHead.setLogisticsNo(jh.getString("logisticsNo"));
            invtRefundHead.setLogisticsCode(jh.getString("logisticsCode"));
            invtRefundHead.setLogisticsName(jh.getString("logisticsName"));
            invtRefundHead.setCopNo(jh.getString("copNo"));
            invtRefundHead.setInvtNo(jh.getString("invtNo"));
            invtRefundHead.setBuyerIdType(jh.getString("buyerIdType"));
            invtRefundHead.setBuyerIdNumber(jh.getString("buyerIdNumber"));
            invtRefundHead.setBuyerName(jh.getString("buyerName"));
            invtRefundHead.setBuyerTelephone(jh.getString("buyerTelephone"));
            invtRefundHead.setAgentCode(jh.getString("agentCode"));
            invtRefundHead.setAgentName(jh.getString("agentName"));
            invtRefundHead.setReason(jh.getString("reason"));
            invtRefund.setInvtRefundHead(invtRefundHead);
            JSONObject jb = ja.getJSONObject(i).getJSONObject("InvtRefundList");
			CEB625Message.InvtRefund.InvtRefundList invtRefundList = new CEB625Message.InvtRefund.InvtRefundList();
			invtRefundList.setGnum(jb.getInteger("gnum"));
			invtRefundList.setGcode(jb.getString("gcode"));
			invtRefundList.setGname(jb.getString("gname"));
			invtRefundList.setQty(jb.getDouble("qty"));
			invtRefundList.setUnit(jb.getString("unit"));
			invtRefund.setInvtRefundList(invtRefundList);
    		ceb625.getInvtRefund().add(invtRefund);
        } 
        
        String content = XMLUtil.convertToXml(ceb625);
        System.out.println(content);
		return content;
	}
	
	public String getCeb711(JSONObject obj){
		
		JSONObject jsonObjects = JSON.parseObject(obj.getString("data"));
		JSONObject ja = jsonObjects.getJSONObject("Delivery");  
		
		CEB711Message.BaseTransfer baseTransfer = new CEB711Message.BaseTransfer();
		baseTransfer.setCopCode(copCode);
		baseTransfer.setCopName(copName);
		baseTransfer.setDxpMode(dxpMode);
		baseTransfer.setDxpId(dxpId);
		baseTransfer.setNote(note);
		
		CEB711Message ceb711 = new CEB711Message();
		ceb711.setGuid(guid);
		ceb711.setVersion(version);
		ceb711.setBaseTransfer(baseTransfer);
		
        JSONObject jh = ja.getJSONObject("DeliveryHead");  
        CEB711Message.Delivery delivery = new CEB711Message.Delivery();
        CEB711Message.Delivery.DeliveryHead deliveryHead = new CEB711Message.Delivery.DeliveryHead();
        deliveryHead.setGuid(jh.getString("guid"));
        deliveryHead.setAppType(jh.getString("appType"));
        deliveryHead.setAppTime(jh.getString("appTime"));
        deliveryHead.setAppStatus(jh.getString("appStatus"));
        deliveryHead.setCustomsCode(jh.getString("customsCode"));
        deliveryHead.setCopNo(jh.getString("copNo"));
        deliveryHead.setPreNo(jh.getString("preNo"));
        deliveryHead.setRkdNo(jh.getString("rkdNo"));
        deliveryHead.setOperatorCode(jh.getString("operatorCode"));
        deliveryHead.setOperatorName(jh.getString("operatorName"));
        deliveryHead.setIeFlag(jh.getString("ieFlag"));
        deliveryHead.setTrafMode(jh.getString("trafMode"));
        deliveryHead.setTrafNo(jh.getString("trafNo"));
        deliveryHead.setVoyageNo(jh.getString("voyageNo"));
        deliveryHead.setBillNo(jh.getString("billNo"));
        deliveryHead.setLogisticsCode(jh.getString("logisticsCode"));
        deliveryHead.setLogisticsName(jh.getString("logisticsName"));
        deliveryHead.setUnloadLocation(jh.getString("unloadLocation"));
        deliveryHead.setNote(jh.getString("note"));
        delivery.setDeliveryHead(deliveryHead);
        JSONObject jb = ja.getJSONObject("DeliveryList");
		CEB711Message.Delivery.DeliveryList deliveryList = new CEB711Message.Delivery.DeliveryList();
		deliveryList.setGnum(jb.getInteger("gnum"));
		deliveryList.setLogisticsNo(jb.getString("logisticsNo"));
		deliveryList.setNote(jb.getString("note"));
		delivery.setDeliveryList(deliveryList);
		ceb711.setDelivery(delivery);
        
        String content = XMLUtil.convertToXml(ceb711);
        System.out.println(content);
		return content;
	}
}
