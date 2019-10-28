//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.23 时间 02:02:51 PM CST 
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
 *         &lt;element name="ArrivalReturn" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
    "arrivalReturn"
})
@XmlRootElement(name = "CEB508Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB508Message {

    @XmlElement(name = "ArrivalReturn", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB508Message.ArrivalReturn> arrivalReturn;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the arrivalReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrivalReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrivalReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB508Message.ArrivalReturn }
     * 
     * 
     */
    public List<CEB508Message.ArrivalReturn> getArrivalReturn() {
        if (arrivalReturn == null) {
            arrivalReturn = new ArrayList<CEB508Message.ArrivalReturn>();
        }
        return this.arrivalReturn;
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
     *         &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
        "operatorCode",
        "logisticsCode",
        "preNo",
        "copNo",
        "billNo",
        "msgSeqNo",
        "returnStatus",
        "returnTime",
        "returnInfo"
    })
    public static class ArrivalReturn {

        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String guid;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String operatorCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String preNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String copNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String billNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected int msgSeqNo;
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
         * 获取operatorCode属性的值。
         * 
         */
        public String getOperatorCode() {
            return operatorCode;
        }

        /**
         * 设置operatorCode属性的值。
         * 
         */
        public void setOperatorCode(String value) {
            this.operatorCode = value;
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
         * 获取preNo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPreNo() {
            return preNo;
        }

        /**
         * 设置preNo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPreNo(String value) {
            this.preNo = value;
        }

        /**
         * 获取copNo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCopNo() {
            return copNo;
        }

        /**
         * 设置copNo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCopNo(String value) {
            this.copNo = value;
        }

        /**
         * 获取billNo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillNo() {
            return billNo;
        }

        /**
         * 设置billNo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillNo(String value) {
            this.billNo = value;
        }

        /**
         * 获取msgSeqNo属性的值。
         * 
         */
        public int getMsgSeqNo() {
            return msgSeqNo;
        }

        /**
         * 设置msgSeqNo属性的值。
         * 
         */
        public void setMsgSeqNo(int value) {
            this.msgSeqNo = value;
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
