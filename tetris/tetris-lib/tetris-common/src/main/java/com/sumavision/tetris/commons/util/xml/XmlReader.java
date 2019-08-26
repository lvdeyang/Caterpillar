package com.sumavision.tetris.commons.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.aspectj.weaver.ast.Var;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

	private Document document;
	
	public XmlReader(String xml) throws Exception{
		 DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		 this.document = builder.parse(xml);
	}
	
	
	public XmlReader(InputStream xml) throws Exception{
		 DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		 this.document = builder.parse(xml);
	}
	
	/**
	 * 根据key路径读取节点的内容<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月3日 下午3:30:40
	 * @param String keyPath key.key.key
	 * @return String xml节点内容
	 */
	public String readString(String keyPath) throws Exception{
		String[] keys = keyPath.split("\\.");
		NodeList nodeList = document.getChildNodes();
		Node currentNode = null;
		for(int j=0; j<nodeList.getLength(); j++){
			Node scopeNode = nodeList.item(j);
			if(scopeNode.getNodeName().equals(keys[0])){
				currentNode = scopeNode;
				break;
			}
		}
		
		if(keys.length > 1){
			for(int i=1; i<keys.length; i++){
				String scopeKey = keys[i];
				for(int j=0; j<currentNode.getChildNodes().getLength(); j++){
					Node scopeNode = currentNode.getChildNodes().item(j);
					if(scopeNode.getNodeName().equals(scopeKey)){
						if(i == keys.length-1){
							return scopeNode.getTextContent();
						}else{
							currentNode = scopeNode;
						}
						break;
					}
				}
			}
		}else{
			return currentNode.getTextContent();
		}

		return null;
	}
	
	/**
	 * 根据key路径读取节点的内容<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年4月3日 下午3:30:40
	 * @param String keyPath key.key.key
	 * @return Long xml节点内容
	 */
	public Long readLong(String keyPath) throws Exception{
		return Long.valueOf(readString(keyPath));
	}
	
	public static void main(String[] args) throws Exception{
		InputStream xmlStream = null;
		try{
			File xmlFile = new File("D:\\code\\2.0会议原型-改\\EBDT_10415222400000001030101010000000000130290\\EBDB_10415222400000001030101010000000000130290.xml");
			xmlStream = new FileInputStream(xmlFile);
			XmlReader reader = new XmlReader(xmlStream);
			String senderName = reader.readString("EBD.EBM.MsgBasicInfo.SenderName");
			String senderTime = reader.readString("EBD.EBM.MsgBasicInfo.SendTime");
			String eventType = reader.readString("EBD.EBM.MsgBasicInfo.EventType");
			String msgTitle = reader.readString("EBD.EBM.MsgContent.MsgTitle");
			String msgDesc = reader.readString("EBD.EBM.MsgContent.MsgDesc");
			String areaCode = reader.readString("EBD.EBM.MsgContent.AreaCode");
			System.out.println(senderName);
			System.out.println(senderTime);
			System.out.println(eventType);
			System.out.println(msgTitle);
			System.out.println(msgDesc);
			System.out.println(areaCode);
		}finally{
			xmlStream.close();
		}
		
	}
	
}
