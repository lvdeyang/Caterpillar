//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 02:57:23 PM CST 
//


package com.guolaiwan.app.customs.bo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LogisticsReturn" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="returnStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="returnTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="returnInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="guid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "logisticsReturn"
})
@XmlRootElement(name = "CEB512Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB512Message {

    @XmlElement(name = "LogisticsReturn", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB512Message.LogisticsReturn> logisticsReturn;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the logisticsReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logisticsReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogisticsReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB512Message.LogisticsReturn }
     * 
     * 
     */
    public List<CEB512Message.LogisticsReturn> getLogisticsReturn() {
        if (logisticsReturn == null) {
            logisticsReturn = new ArrayList<CEB512Message.LogisticsReturn>();
        }
        return this.logisticsReturn;
    }

    /**
     * 获取guid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 设置guid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="returnStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="returnTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="returnInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "guid",
        "logisticsCode",
        "logisticsNo",
        "returnStatus",
        "returnTime",
        "returnInfo"
    })
    public static class LogisticsReturn {

        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String guid;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnStatus;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnTime;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnInfo;

        /**
         * 获取guid属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGuid() {
            return guid;
        }

        /**
         * 设置guid属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGuid(String value) {
            this.guid = value;
        }

        /**
         * 获取logisticsCode属性的值。
         * 
         */
        public String getLogisticsCode() {
            return logisticsCode;
        }

        /**
         * 设置logisticsCode属性的值。
         * 
         */
        public void setLogisticsCode(String value) {
            this.logisticsCode = value;
        }

        /**
         * 获取logisticsNo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLogisticsNo() {
            return logisticsNo;
        }

        /**
         * 设置logisticsNo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLogisticsNo(String value) {
            this.logisticsNo = value;
        }

        /**
         * 获取returnStatus属性的值。
         * 
         */
        public String getReturnStatus() {
            return returnStatus;
        }

        /**
         * 设置returnStatus属性的值。
         * 
         */
        public void setReturnStatus(String value) {
            this.returnStatus = value;
        }

        /**
         * 获取returnTime属性的值。
         * 
         */
        public String getReturnTime() {
            return returnTime;
        }

        /**
         * 设置returnTime属性的值。
         * 
         */
        public void setReturnTime(String value) {
            this.returnTime = value;
        }

        /**
         * 获取returnInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReturnInfo() {
            return returnInfo;
        }

        /**
         * 设置returnInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReturnInfo(String value) {
            this.returnInfo = value;
        }

    }

}
