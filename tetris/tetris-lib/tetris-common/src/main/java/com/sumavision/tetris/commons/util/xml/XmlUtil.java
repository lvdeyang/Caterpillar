package com.sumavision.tetris.commons.util.xml;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

public class XmlUtil{
	
	public static String toXML(Object obj) {
		try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息

            StringWriter out = new StringWriter();
            OutputFormat format = new OutputFormat();
            format.setIndent(false);
            format.setNewlines(false);
            format.setNewLineAfterDeclaration(false);
            XMLWriter writer = new XMLWriter(out, format);

            XMLFilterImpl nsfFilter = new XMLFilterImpl() {
                private boolean ignoreNamespace = false;
                private String rootNamespace = null;
                private boolean isRootElement = true;

                @Override
                public void startDocument() throws SAXException {
                    super.startDocument();
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                    if (this.ignoreNamespace) 
                    	uri = "";
                    if (this.isRootElement) 
                    	this.isRootElement = false;
                    else if (!uri.equals("") && !localName.contains("xmlns")) 
                    	localName = localName + " xmlns=\"" + uri + "\"";

                    if(atts.getLength() != 0){
                    	atts = new AttributesImpl();
                    }
                    
                    super.startElement(uri, localName, localName, atts);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (this.ignoreNamespace) 
                    	uri = "";
                    super.endElement(uri, localName, localName);
                }

                @Override
                public void startPrefixMapping(String prefix, String url) throws SAXException {
                    if (this.rootNamespace != null) 
                    	url = this.rootNamespace;
                    if (!this.ignoreNamespace && "http://www.w3.org/2001/XMLSchema".equals(url)) 
                    	super.startPrefixMapping("xs", url);

                }
            };
            nsfFilter.setContentHandler(writer);
            marshaller.marshal(obj, nsfFilter);
            return out.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}
	
	public static <T> String toEasyXml(T obj,Class<T> clazz){
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Marshaller mar = context.createMarshaller();
//			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			mar.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头声明信息
			
			StringWriter writer = new StringWriter();
			
			mar.marshal(obj, writer);
			
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
        
	/**
	 * 将XML转化为对象(重载)
	 * @author 史明
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws IDSException 
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T XML2Obj(String xml, Class<T> clazz){

        Object obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmar = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            obj = unmar.unmarshal(reader);        	
            return (T)obj;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return (T)obj;
    }
	
	/**
	 * 将XML转化为对象(重载)
	 * @author 史明
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws IDSException 
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T XML2ObjIgnoreNamespace(String xml, Class<T> clazz){

        Object obj = null;
        try {           
        	JAXBContext jaxbContext = JAXBContext.newInstance(clazz);  
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();  
            StringReader reader = new StringReader(xml);  
            SAXParserFactory sax = SAXParserFactory.newInstance();  
            sax.setNamespaceAware(false);  
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();  
            Source source = new SAXSource(xmlReader, new InputSource(reader));  
             
            obj = unmarshaller.unmarshal(source); 
            return (T)obj;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return (T)obj;
    }

}


