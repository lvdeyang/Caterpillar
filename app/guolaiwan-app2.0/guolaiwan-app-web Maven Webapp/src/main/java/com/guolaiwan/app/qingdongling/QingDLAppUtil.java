package com.guolaiwan.app.qingdongling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.alibaba.fastjson.JSON;


/*
 * 账号 ：100019
密钥 ： a36c415c112c749aba38efd7c5abe755
测试接口地址（代码http://open.12301dev.com/openService/MXSE_beta.wsdl
测试产品：景区id：89130 门票id：213536 供应商id：113 正常票种
测试信息不对外开放，请密保。
 * 
 * 
 * */
public class QingDLAppUtil {

	private final static String URL = "http://open.12301dev.com/openService/pftMX.php";
	private final static String ac = "100019";
	private final static String pw = "a36c415c112c749aba38efd7c5abe755";
	private final static String m = "113";
	private final static String aid="113";
	private final static String tid="213536";
	private final static String lid="89130";
	public static void main(String[] args) {
		

		
		
		//查询
		//String temp =getScenicSpotList();
		//测试判断能否下单
		//String temp = orderPreCheck(tid, "1", new Date(), "18632546210", "何靖", "2", "130281198610130051");
		
		Map<String, Object> map;
		try {
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//开始下单
		try {
			String temp=orderSubmit(lid, tid, "glw-13142021", "40",
					"1", new Date(), "", "18632546210", "何靖", 
					"18632546210", "0", "0",  "0", 
					"", "", "0", "0", "130281198610130051", "干啥去", 
					"");
			System.out.println(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询订单 如果没有村第三方订单号用自己订单号能查询到第三方订单号
		//String temp =orderQuery("", "glw-13142020");
		//退单
		//String temp =orderChangePro("100014630","0", "18632546210");
	}
	
	public static String getScenicSpotList(){
		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " + "xmlns:urn=\"urn:PFTMX\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<urn:Get_ScenicSpot_List soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		sb.append("<ac xsi:type=\"xsd:string\">" + ac + "</ac>");
		sb.append("<pw xsi:type=\"xsd:string\">" + pw + "</pw>");
		sb.append("<n xsi:type=\"xsd:string\">0</n>");
		sb.append(" <m xsi:type=\"xsd:string\">1</m>");
		sb.append("</urn:Get_ScenicSpot_List>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		//return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8").replace("&lt;", "<").replace("&gt;",">");
		return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8");
	}
	
	/**
	 * 预判下单方法名：OrderPreCheck
	 * @param ac 账号 string Y 
	 * @param pw 密码 string Y 
	 * @param tid 门票 id string Y 
	 * @param tnum 购买数量 string Y
	 * @param playtime 游玩日期 string Y 格式：Y-m-d 备注：为分时预约产品 时，playtime 格式为： Y-m-d hh:mm，hh:mm 值为分时价格 库 存 方 法 返 回 的 
	 * @param ordertel 游客手机号 string Y 
	 * @param ordername 游客姓名 string Y 多个用英文逗号隔开， 不支持特殊符号：/|[] 等 
	 * @param m 供应商 id string Y 
	 * @param paymode 支付方式 string Y 0-账户余额，2-供应商 授信额度，4-现场支付 
	 * @param personid 游客身份证 string Y 多个用英文逗号隔开， 与游客姓名数量一致
	 */
	// 预判下单方法名：OrderPreCheck
	public static String orderPreCheck(String tid, String tnum, Date playtime, String ordertel, String ordername,
			String paymode, String personid) throws Exception {

		String playTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(playtime);

		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:PFTMX\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<urn:OrderPreCheck soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		sb.append("<ac xsi:type=\"xsd:string\">" + ac + "</ac>");
		sb.append("<pw xsi:type=\"xsd:string\">" + pw + "</pw>");
		sb.append("<tid xsi:type=\"xsd:string\">" + tid + "</tid>");
		sb.append("<tnum xsi:type=\"xsd:string\">" + tnum + "</tnum>");
		sb.append("<playtime xsi:type=\"xsd:string\">" + playTimeStr + "</playtime>");
		sb.append("<ordertel xsi:type=\"xsd:string\">" + ordertel + "</ordertel>");
		sb.append("<ordername xsi:type=\"xsd:string\">" + ordername + "</ordername>");
		sb.append("<m xsi:type=\"xsd:string\">" + m + "</m>");
		sb.append("<paymode xsi:type=\"xsd:string\">" + paymode + "</paymode>");
		sb.append("<personid xsi:type=\"xsd:string\">" + personid + "</personid>");
		sb.append("</urn:OrderPreCheck>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
System.out.println(sb.toString());
		//return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8").replace("&lt;", "<").replace("&gt;",">");
		return JSON.toJSONString(SOAPUtil.paseMap(HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8")));
	}

	/**
	 *  提交订单方法名：PFT_Order_Submit
	 * @param ac 账号 string Y 
	 * @param pw 密码 string Y 
	 * @param lid 产品 id string Y 
	 * @param tid 门票 id string Y
	 * @param remotenum 远端订单号 string Y 贵方订单号,请确保唯 一
	 * @param tprice 结算价 string Y 供应商配置的结算单价，单位：分 
	 * @param tnum 购买数量 string Y 
	 * @param playtime 游玩日期 string Y 格式：Y-m-d 备注：为分时预约产 品时，playtime 格式 为：Y-m-d hh:mm， hh:mm 值为分时价格 库 存 方 法 返 回 的 
	 * @param start_date 参数。
	 * @param ordername 游客姓名 string Y 多 个 用 英 文 逗 号 隔 开，不支持特殊符号： /|[]等 
	 * @param ordertel 游客手机号string Y 
	 * @param contactTEL 联系人手机号 string Y 
	 * @param smsSend 是否选择票付通 发送短信 string Y 0- 票 付通 发 送 短 信 （下单成功只返回双 票付通-API 分销接口 方订单号） 1-票付通不发送短信 （ 凭 证 信 息 一 起 返 回）
	 * @param paymode 支付方式 string Y 0-账户余额，2-供应商 授信额度，4-现场支 付
	 * @param ordermode 下单方式 string Y默认传输 0-正常下单 
	 * @param assembly 集合地点 string Y 线路时需要，参数必 传，值可传输空 
	 * @param series 团号 string Y线路，演出时需要， 参数必传，值可传输 空；演出需要时传输 格 式 ： 
	 * @param json_encode(array(int) 场 馆 id,(int) 场次 id,(string)分区 id)); 
	 * @param concatID 联票 id string Y （未开放，请填 0） 
	 * @param pCode 套票 idstring Y （未开放，请填 0） 
	 * @param m 供应商 id string Y 
	 * @param personID 身份证号 string Y 
	 * @param memo 备注string Y 参数必传，值可为空 传输 
	 * @param callbackUrl 回调地址 string Y 参数必传，值可为空 传输
	 */
	// 提交订单方法名：PFT_Order_Submit
	public static String orderSubmit(String lid, String tid, String remotenum, String tprice, String tnum,
			Date playtime, String start_date, String ordertel, String ordername, String contactTEL, String smsSend,
			String paymode,  String ordermode, String assembly, String series, String concatID,
			String pCode, String personID, String memo, String callbackUrl) throws Exception {
		String playTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(playtime);

		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " + "xmlns:urn=\"urn:PFTMX\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<urn:PFT_Order_Submit soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		sb.append("<ac xsi:type=\"xsd:string\">"+ac+"</ac>");
		sb.append("<pw xsi:type=\"xsd:string\">"+pw+"</pw>");
		sb.append("<lid xsi:type=\"xsd:string\">"+lid+"</lid>");
		sb.append("<tid xsi:type=\"xsd:string\">"+tid+"</tid>");
		sb.append("<remotenum xsi:type=\"xsd:string\">"+remotenum+"</remotenum>");
		sb.append("<tprice xsi:type=\"xsd:string\">"+tprice+"</tprice>");
		sb.append("<tnum xsi:type=\"xsd:string\">"+tnum+"</tnum>");
		sb.append("<playtime xsi:type=\"xsd:string\">"+playTimeStr+"</playtime>");
		sb.append("<ordername xsi:type=\"xsd:string\">"+ordername+"</ordername>");
		sb.append("<ordertel xsi:type=\"xsd:string\">"+ordertel+"</ordertel>");
		sb.append("<contactTEL xsi:type=\"xsd:string\">"+contactTEL+"</contactTEL>");
		sb.append("<smsSend xsi:type=\"xsd:string\">"+smsSend+"</smsSend>");
		sb.append("<paymode xsi:type=\"xsd:string\">"+paymode+"</paymode>");
		sb.append("<ordermode xsi:type=\"xsd:string\">"+ordermode+"</ordermode>");
		sb.append("<assembly xsi:type=\"xsd:string\">"+assembly+"</assembly>");
		sb.append("<series xsi:type=\"xsd:string\">"+series+"</series>");
		sb.append("<concatID xsi:type=\"xsd:string\">"+concatID+"</concatID>");
		sb.append("<pCode xsi:type=\"xsd:string\">"+pCode+"</pCode>");
		sb.append("<m xsi:type=\"xsd:string\">"+m+"</m>");
		sb.append("<personID xsi:type=\"xsd:string\">"+personID+"</personID>");
		sb.append("<memo xsi:type=\"xsd:string\">"+memo+"</memo>");
		sb.append("<callbackUrl xsi:type=\"xsd:string\">"+callbackUrl+"</callbackUrl>");
		sb.append("</urn:PFT_Order_Submit>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		System.out.println(sb.toString());
		//return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8").replace("&lt;", "<").replace("&gt;",">");
		return JSON.toJSONString(SOAPUtil.paseMap(HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8")));
	}
	
	
	/**
	 * 查询订单orderQuery 查询订单 如果没有村第三方订单号用自己订单号能查询到第三方订单号
	 *  @param ac 账号 string Y 
	 *  @param pw 密码 string Y 
	 *  @param pftOrdernum 票付通订单号 string Y 可 为 空 ， 为 空 时 remoteOrdernum 不能 为空可为空 
	 *  @param remoteOrdernum 远端订单号 string Y 为 空 时 pftOrdernum 不能为空
	 * 
	 */
	//查询订单orderQuery
	public static String orderQuery(String pftOrdernum,String remoteOrdernum) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:PFTMX\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<urn:OrderQuery soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		sb.append("<ac xsi:type=\"xsd:string\">"+ac+"</ac>");
		sb.append("<pw xsi:type=\"xsd:string\">"+pw+"</pw>");
		sb.append("<pftOrdernum xsi:type=\"xsd:string\">"+pftOrdernum+"</pftOrdernum>");
		sb.append("<remoteOrdernum xsi:type=\"xsd:string\">"+remoteOrdernum+"</remoteOrdernum>");
		sb.append("</urn:OrderQuery>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		//return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8").replace("&lt;", "<").replace("&gt;",">");
		return JSON.toJSONString(SOAPUtil.paseMap(HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8")));
	}
	
	
	
	
	/**
	 * 取消修改订单Order_Change_Pro
	 * @param ordern 票付通订单号 string Y 
	 * @param  num 剩余数量 string Y -1-订 单 数量 不 做 修 改，指要修改游玩人 手机号 0-取消订单 >0-订单剩余数量 
	 * 
	 * 
	 * */
	//取消修改订单Order_Change_Pro
	public static String orderChangePro(String ordern,String num,String ordertel) throws Exception{
			StringBuffer sb = new StringBuffer();
			sb.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:PFTMX\">");
			sb.append("<soapenv:Header/>");
			sb.append("<soapenv:Body>");
			sb.append("<urn:Order_Change_Pro soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
			sb.append("<ac xsi:type=\"xsd:string\">"+ac+"</ac>");
			sb.append("<pw xsi:type=\"xsd:string\">"+pw+"</pw>");
			sb.append("<ordern xsi:type=\"xsd:string\">"+ordern+"</ordern>");
			sb.append("<num xsi:type=\"xsd:string\">"+num+"</num>");
			sb.append("<ordertel xsi:type=\"xsd:string\">"+ordertel+"</ordertel>");
			sb.append("<m xsi:type=\"xsd:string\">"+m+"</m>");
			sb.append("</urn:Order_Change_Pro>");
			sb.append("</soapenv:Body>");
			sb.append("</soapenv:Envelope>");
			System.out.println(sb.toString());
	//return HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8").replace("&lt;", "<").replace("&gt;",">");
			return JSON.toJSONString(SOAPUtil.paseMap(HttpUtil.post(URL, sb.toString(), 60000, 60000,"UTF-8")));
	}
}
