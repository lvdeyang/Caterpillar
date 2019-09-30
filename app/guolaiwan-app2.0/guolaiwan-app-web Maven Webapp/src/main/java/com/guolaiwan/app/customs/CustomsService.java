package com.guolaiwan.app.customs;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
	
	private void sendMessage(CustomBo customBo){
		try {
			String content = RSA.encrypt(RSA.getPublicKey(secret), customBo.getContent(), "utf8");
			customBo.setContent(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String result=HttpClient.postJson(SendURL,JSON.parseObject(JSONObject.toJSONString(customBo)));
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrder(){
		CustomBo customBo=new CustomBo();
		customBo.setSigned(signed);
		customBo.setEntCode(entCode);
		customBo.setDxpId(dxpId);
		customBo.setMessageType(MsgTypeEnums.ADD_ORDER);
		//此处生成报文内容xml
		/*<ceb:CEB303Message xmlns:ceb="http://www.chinaport.gov.cn/ceb" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" guid="311af125-6fed-4603-8c5d-49b1fa4b4b9b" version="1.0">
		<ceb:Order>
		<ceb:OrderHead>
		<ceb:guid>704ea0f1-b229-4e89-9f2f-c2e550e95c86</ceb:guid>
		<ceb:appType>1</ceb:appType>
		<ceb:appTime>20190731153001</ceb:appTime>
		<ceb:appStatus>1</ceb:appStatus>
		<ceb:orderType>E</ceb:orderType>
		<ceb:orderNo>order2018050711340001</ceb:orderNo>
		<ceb:ebpCode>13029609B5</ceb:ebpCode>
		<ceb:ebpName>遵化市博客旅游集散中心有限公司</ceb:ebpName>
		<ceb:ebcCode>13029609B5</ceb:ebcCode>
		<ceb:ebcName>遵化市博客旅游集散中心有限公司</ceb:ebcName>
		<ceb:goodsValue>10</ceb:goodsValue>
		<ceb:freight>0</ceb:freight>
		<ceb:currency>142</ceb:currency>
		</ceb:OrderHead>
		<ceb:OrderList>
		<ceb:gnum>1</ceb:gnum>
		<ceb:itemNo>AF001-001</ceb:itemNo>
		<ceb:itemName>手机</ceb:itemName>
		<ceb:itemDescribe>手机</ceb:itemDescribe>
		<ceb:barCode>001</ceb:barCode>
		<ceb:unit>1</ceb:unit>
		<ceb:currency>142</ceb:currency>
		<ceb:qty>20</ceb:qty>
		<ceb:price>20</ceb:price>
		<ceb:totalPrice>2000</ceb:totalPrice>
		</ceb:OrderList>
		</ceb:Order>
		<ceb:BaseTransfer>
		<ceb:copCode>13029609B5</ceb:copCode>
		<ceb:copName>遵化市博客旅游集散中心有限公司</ceb:copName>
		<ceb:dxpMode>DXP</ceb:dxpMode>
		<ceb:dxpId>DXPLGS0000000001</ceb:dxpId>
		</ceb:BaseTransfer>
		</ceb:CEB303Message>*/
		
		/*系统唯一序号 guid C36 是 企业系统生成36位唯一序号 （英文字母大写） 
		报送类型 appType C1 是 企业报送类型。1-新增 2-变 更 3-删除。 
		报送时间 appTime C14 是 企业报送时间。 格式:YYYYMMDDhhmmss。 
		报送状态 appStatus C1 是 企业报送状态。1-暂存,2-申 报。填写2时,Signature节点必须 填写。 
		订单类型 orderType C1 是 电商平台的订单类型 I-进口 商品订单；E-出口商品订单 
		订单编号 orderNo C..60 是 电商平台的交易订单编号，同 一平台的订单编号唯一不重 复 
		电商平台代码 ebpCode C..50 是 电商平台的海关注册登记编 号或统一社会信用代码。 
		电商平台名称 ebpName C..100 是 电商平台的登记名称。 
		电商企业代码 ebcCode C..18 是 电商企业的海关注册登记编号或统一社会信用代码，对应 清单的收发货人。
                      电商企业名称 ebcName C..100 是 电商企业的登记名称，对应清 单的收发货人。 
                      商品金额 goodsValue N19,5 是 商品实际成交价FOB(不含运 杂费)，含非现金抵扣金额 
                      运杂费 freight N19,5 是 运杂费，无则填写"0"。 
                      币制 currency C3 是 海 关 标 准 的 参 数 代 码 《JGS-20 海关业务代码集》- 货币代码 
                      备注 note C..1000 否 电子订单商品表体 
                      序号 gnum N5 是 从1开始的递增序号。（与清 单的商品项关联对应） 
                      企业商品货号 itemNo C..30 是 企业自定义的商品货号。 
                      企业商品名称 itemName C..250 是 同一类商品的中文名称。任何 一种具体商品可以并只能归 入表中的一个条目。 
                      企业商品描述 itemDescribe C..1000 否 电商平台上架的商品描述宣 传信息。 
                      条形码 barCode C..50 否 商品条形码一般由前缀部分、 制造厂商代码、商品代码和校 验码组成。 
                      计量单位 unit C3 是 海关标准的参数代码 海关标 准的参数代码《JGS-20 海关 业务代码集》，计量单位代码 
                      币制 currency C3 是 海关标准的参数代码 海关标 准的参数代码《JGS-20 海关 业务代码集》- 货币代码 
                      数量 qty N19,5 是 
                      单价 price N19,5 是 
                      总价 totalPrice N19,5 是 
                      备注 note C..1000*/
		String content="";//开发人员注意，这里生成xml字符串
		//		try {
		//			File f = new File("D:\\yuebacode\\app\\guolaiwan-app2.0\\guolaiwan-app-web Maven Webapp\\src\\main\\resources\\addorder.xml");  
		//			SAXReader reader = new SAXReader();   
		//			Document doc = reader.read(f);
		//			content=doc.getRootElement().asXML();
		//		} catch (Exception e) {
		//			// TODO: handle exception
		//			System.out.println(e.getMessage());
		//		}
		customBo.setContent(content);
		//下发命令
		sendMessage(customBo);
	}
	
	
}
