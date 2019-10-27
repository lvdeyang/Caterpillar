package com.guolaiwan.app.customs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.customs.bo.CEB303Message;
import com.guolaiwan.app.customs.bo.CEB303Message.BaseTransfer;
import com.guolaiwan.app.customs.bo.CEB303Message.Order;
import com.guolaiwan.app.customs.bo.CEB303Message.Order.OrderHead;
import com.guolaiwan.app.customs.bo.CEB303Message.Order.OrderList;
import com.guolaiwan.app.customs.bo.CEB304Message;
import com.guolaiwan.app.customs.bo.CEB304Message.OrderReturn;
import com.guolaiwan.app.customs.util.XMLUtil;

public class TestClass {

	public static void main(String[] args) {
		
//		OrderHead orderHead = new OrderHead();
//		orderHead.setGuid("704ea0f1-b229-4e89-9f2f-c2e550e95c86");
//		orderHead.setAppType("1");
//		orderHead.setAppTime("20180507153001");
//		orderHead.setAppStatus("2");
//		orderHead.setOrderType("E");
//		orderHead.setOrderNo("order2018050711340001");
//		orderHead.setEbpCode("1105910159");
//		orderHead.setEbpName("东方物通科技(北京)有限公司");
//		orderHead.setEbcCode("1105910159");
//		orderHead.setEbcName("东方物通科技(北京)有限公司");
//		orderHead.setGoodsValue(new Double(12345678912345.12345));
//		orderHead.setFreight(new Double(0));
//		orderHead.setCurrency(new Double(142));
//		orderHead.setNote("test");
//		
//		OrderList orderList = new OrderList();
//		orderList.setGnum("1");
//		orderList.setItemNo("AF001-001");
//		orderList.setItemName("小米盒子");
//		orderList.setItemDescribe("小米盒子");
//		orderList.setBarCode("2345123");
//		orderList.setUnit("aaa");
//		orderList.setCurrency("aaa");
//		orderList.setQty(new Double(100));
//		orderList.setPrice(new Double(20));
//		orderList.setTotalPrice(new Double(2000));
//		orderList.setNote("test");
//		
//		Order order = new Order();
//		order.setOrderHead(orderHead);
//		order.setOrderList(orderList);
//		
//		BaseTransfer baseTransfer = new BaseTransfer();
//		baseTransfer.setCopCode("1105910159");
//		baseTransfer.setCopName("东方物通科技(北京)有限公司");
//		baseTransfer.setDxpMode("DXP");
//		baseTransfer.setDxpId("DXPLGS0000000001");
//		baseTransfer.setNote("test");
//		
//		CEB303Message ceb303 = new CEB303Message();
//		ceb303.setGuid("311af125-6fed-4603-8c5d-49b1fa4b4b9b");
//		ceb303.setVersion("1.0");
//		ceb303.setBaseTransfer(baseTransfer);
//		ceb303.getOrder().add(order);
//		ceb303.getOrder().add(order);
//
//		String content = XMLUtil.convertToXml(ceb303);
		
		String data = "{'Order': [      {        'OrderHead': {          'guid': '704ea0f1-b229-4e89-9f2f-c2e550e95c86',          'appType': '1',          'appTime': '20180507153001',          'appStatus': '2',          'orderType': 'E',          'orderNo': 'order2018050711340001',          'ebpCode': '1105910159',          'ebpName': '东方物通科技(北京)有限公司',          'ebcCode': '1105910159',          'ebcName': '东方物通科技(北京)有限公司',          'goodsValue': '12345678912345.12345',          'freight': '0',          'currency': '142',          'note': 'test'        },        'OrderList': {          'gnum': '1',          'itemNo': 'AF001-001',          'itemName': '小米盒子',          'itemDescribe': '小米盒子',          'barCode': '2345123',          'unit': 'aaa',          'currency': 'aaa',          'qty': '100',          'price': '20',          'totalPrice': '2000',          'note': 'test'        }      },      {        'OrderHead': {          'guid': '704ea0f1-b229-4e89-9f2f-c2e550e95c86',          'appType': '1',          'appTime': '20180507153001',          'appStatus': '2',          'orderType': 'E',          'orderNo': 'order2018050711340001',          'ebpCode': '1105910159',          'ebpName': '东方物通科技(北京)有限公司',          'ebcCode': '1105910159',          'ebcName': '东方物通科技(北京)有限公司',          'goodsValue': '12345678912345.12345',          'freight': '0',          'currency': '142',          'note': 'test'        },        'OrderList': {          'gnum': '1',          'itemNo': 'AF001-001',          'itemName': '小米盒子',          'itemDescribe': '小米盒子',          'barCode': '2345123',          'unit': 'aaa',          'currency': 'aaa',          'qty': '100',          'price': '20',          'totalPrice': '2000',          'note': 'test'        }      }    ]  }";
		JSONObject jsonObjects = JSON.parseObject(data);
		JSONArray ja = jsonObjects.getJSONArray("Order");  
		
		BaseTransfer baseTransfer = new BaseTransfer();
		baseTransfer.setCopCode("1105910159");
		baseTransfer.setCopName("东方物通科技(北京)有限公司");
		baseTransfer.setDxpMode("DXP");
		baseTransfer.setDxpId("DXPLGS0000000001");
		baseTransfer.setNote("test");
		
		CEB303Message ceb303 = new CEB303Message();
		ceb303.setGuid("311af125-6fed-4603-8c5d-49b1fa4b4b9b");
		ceb303.setVersion("1.0");
		ceb303.setBaseTransfer(baseTransfer);
		
        for (int i = 0; i < ja.size(); i++) {  
            JSONObject jh = ja.getJSONObject(i).getJSONObject("OrderHead");  
            Order order = new Order();
            OrderHead orderHead = new OrderHead();
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
            OrderList orderList = new OrderList();
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
	}

}
