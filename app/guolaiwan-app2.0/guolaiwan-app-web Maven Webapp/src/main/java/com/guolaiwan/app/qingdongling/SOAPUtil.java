package com.guolaiwan.app.qingdongling;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

public class SOAPUtil {

    /**
     * 解析soapXML
     * @param soapXML
     * @return
     */
    public static WebserviceResultBean parseSoapMessage(String soapXML) {
        WebserviceResultBean resultBean = new WebserviceResultBean();
        try {
            SOAPMessage msg = formatSoapString(soapXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            parse(iterator, resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    public static Map<String,Object> paseMap(String requstSaopStr) throws Exception{
    	 WebserviceResultBean ret = parseSoapMessage(requstSaopStr);
    	 SOAPMessage msg = formatSoapString(requstSaopStr);
    	 SOAPBody body = msg.getSOAPBody();
         Iterator<SOAPElement> iterator = body.getChildElements();
         WebserviceResultBean bean = new WebserviceResultBean();
         return new ParseXml().parse(PrintBody(iterator, null));
    }
    
    
    public static void main(String[] args) {
        System.out.println("开始解析soap...");
        String temp =QingDLAppUtil.getScenicSpotList();
        String deptXML = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP:Header/><SOAP:Body><ns:OP_SDMS_Consume_Material_SynResponse xmlns:ns=\"http://toSDMS.material.service.ffcs.com\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><ns:return><ns:BASEINFO><MSGID>?</MSGID><PMSGID>?</PMSGID><SENDTIME>20140212094609</SENDTIME><S_PROVINCE>?</S_PROVINCE><S_SYSTEM>?</S_SYSTEM><SERVICENAME>?</SERVICENAME><T_PROVINCE>?</T_PROVINCE><T_SYSTEM>?</T_SYSTEM><RETRY>?</RETRY></ns:BASEINFO><ns:MESSAGE><RESULT>E</RESULT><REMARK/><XMLDATA><![CDATA[<response><RESULT>E</RESULT><MESSAGE>平台系统处理时发生异常!保存接口接收数据出错!</MESSAGE></response>]]></XMLDATA></ns:MESSAGE></ns:return></ns:OP_SDMS_Consume_Material_SynResponse></SOAP:Body></SOAP:Envelope>";
        WebserviceResultBean ret = parseSoapMessage(temp);
        try {
            SOAPMessage msg = formatSoapString(temp);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();

            WebserviceResultBean bean = new WebserviceResultBean();
            //parse(iterator,bean);
            
           String a=PrintBody(iterator, null);
           System.out.println(a+"1111111111111");
           Map<String,String> map = new ParseXml().parse(a);
           System.out.println("----------------------------");
           for (String value : map.keySet()) {
        	   
               System.out.println(value+"====="+map.get(value));
           }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("解析soap结束...");
    }

    /**
     * 把soap字符串格式化为SOAPMessage
     *
     * @param soapString
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static SOAPMessage formatSoapString(String soapString) {
        MessageFactory msgFactory;
        try {
            msgFactory = MessageFactory.newInstance();
            SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(),
                    new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析soap xml
     * @param iterator
     * @param resultBean
     */
    private static void parse(Iterator<SOAPElement> iterator, WebserviceResultBean resultBean) {
        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
//            System.out.println("Local Name:" + element.getLocalName());
//            System.out.println("Node Name:" + element.getNodeName());
//            System.out.println("Tag Name:" + element.getTagName());
//            System.out.println("Value:" + element.getValue());
            if ("ns:BASEINFO".equals(element.getNodeName())) {
                continue;
            } else if ("ns:MESSAGE".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                SOAPElement el = null;
                while (it.hasNext()) {
                    el = it.next();
                    if ("RESULT".equals(el.getLocalName())) {
                        resultBean.setResult(el.getValue());
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    } else if ("REMARK".equals(el.getLocalName())) {
                        resultBean.setRemark(null != el.getValue() ? el.getValue() : "");
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    } else if ("XMLDATA".equals(el.getLocalName())) {
                        resultBean.setXmlData(el.getValue());
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    }
                }
            } else if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
                parse(element.getChildElements(), resultBean);
            }
        }
    }
    
    private static String  PrintBody(Iterator<SOAPElement> iterator, String side) {
    	
        while (iterator.hasNext()) {
            SOAPElement element = (SOAPElement) iterator.next();          
/*            System.out.println("Local Name:" + element.getLocalName());
            System.out.println("Node Name:" + element.getNodeName());
            System.out.println("Tag Name:" + element.getTagName());            
            System.out.println("Value:" + element.getValue());
*/            side=element.getValue();
            if(element.getValue()!=null){
            	return side;
            }
            
            if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
            	
            	side=PrintBody(element.getChildElements(), side + "-----");
            }
        }
        return side;
    }
}