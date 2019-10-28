//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 02:58:44 PM CST 
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
 *         &lt;element name="LogisticsStatus" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BaseTransfer">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="copCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="copName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dxpMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dxpId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "logisticsStatus",
    "baseTransfer"
})
@XmlRootElement(name = "CEB513Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB513Message {

    @XmlElement(name = "LogisticsStatus", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB513Message.LogisticsStatus> logisticsStatus;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB513Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the logisticsStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logisticsStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogisticsStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB513Message.LogisticsStatus }
     * 
     * 
     */
    public List<CEB513Message.LogisticsStatus> getLogisticsStatus() {
        if (logisticsStatus == null) {
            logisticsStatus = new ArrayList<CEB513Message.LogisticsStatus>();
        }
        return this.logisticsStatus;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB513Message.BaseTransfer }
     *     
     */
    public CEB513Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB513Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB513Message.BaseTransfer value) {
        this.baseTransfer = value;
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
     *         &lt;element name="copCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="copName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dxpMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dxpId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "copCode",
        "copName",
        "dxpMode",
        "dxpId",
        "note"
    })
    public static class BaseTransfer {
    	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String copCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String copName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String dxpMode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String dxpId;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String note;

        /**
         * 获取copCode属性的值。
         * 
         */
        public String getCopCode() {
            return copCode;
        }

        /**
         * 设置copCode属性的值。
         * 
         */
        public void setCopCode(String value) {
            this.copCode = value;
        }

        /**
         * 获取copName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCopName() {
            return copName;
        }

        /**
         * 设置copName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCopName(String value) {
            this.copName = value;
        }

        /**
         * 获取dxpMode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDxpMode() {
            return dxpMode;
        }

        /**
         * 设置dxpMode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDxpMode(String value) {
            this.dxpMode = value;
        }

        /**
         * 获取dxpId属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDxpId() {
            return dxpId;
        }

        /**
         * 设置dxpId属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDxpId(String value) {
            this.dxpId = value;
        }

        /**
         * 获取note属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNote() {
            return note;
        }

        /**
         * 设置note属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNote(String value) {
            this.note = value;
        }

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
     *         &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "appType",
        "appTime",
        "appStatus",
        "logisticsCode",
        "logisticsName",
        "logisticsNo",
        "logisticsStatus",
        "logisticsTime",
        "note"
    })
    public static class LogisticsStatus {

        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String guid;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appType;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appTime;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appStatus;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsStatus;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsTime;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String note;

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
         * 获取appType属性的值。
         * 
         */
        public String getAppType() {
            return appType;
        }

        /**
         * 设置appType属性的值。
         * 
         */
        public void setAppType(String value) {
            this.appType = value;
        }

        /**
         * 获取appTime属性的值。
         * 
         */
        public String getAppTime() {
            return appTime;
        }

        /**
         * 设置appTime属性的值。
         * 
         */
        public void setAppTime(String value) {
            this.appTime = value;
        }

        /**
         * 获取appStatus属性的值。
         * 
         */
        public String getAppStatus() {
            return appStatus;
        }

        /**
         * 设置appStatus属性的值。
         * 
         */
        public void setAppStatus(String value) {
            this.appStatus = value;
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
         * 获取logisticsName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLogisticsName() {
            return logisticsName;
        }

        /**
         * 设置logisticsName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLogisticsName(String value) {
            this.logisticsName = value;
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
         * 获取logisticsStatus属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLogisticsStatus() {
            return logisticsStatus;
        }

        /**
         * 设置logisticsStatus属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLogisticsStatus(String value) {
            this.logisticsStatus = value;
        }

        /**
         * 获取logisticsTime属性的值。
         * 
         */
        public String getLogisticsTime() {
            return logisticsTime;
        }

        /**
         * 设置logisticsTime属性的值。
         * 
         */
        public void setLogisticsTime(String value) {
            this.logisticsTime = value;
        }

        /**
         * 获取note属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNote() {
            return note;
        }

        /**
         * 设置note属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNote(String value) {
            this.note = value;
        }

    }

}
