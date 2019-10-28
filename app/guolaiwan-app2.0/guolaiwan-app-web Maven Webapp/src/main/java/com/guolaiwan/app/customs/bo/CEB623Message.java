//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 03:04:00 PM CST 
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
 *         &lt;element name="InvtCancel" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="buyerIdType" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="buyerIdNumber" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="buyerTelephone" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *                   &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                   &lt;element name="copCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
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
    "invtCancel",
    "baseTransfer"
})
@XmlRootElement(name = "CEB623Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB623Message {

    @XmlElement(name = "InvtCancel", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB623Message.InvtCancel> invtCancel;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB623Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the invtCancel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invtCancel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvtCancel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB623Message.InvtCancel }
     * 
     * 
     */
    public List<CEB623Message.InvtCancel> getInvtCancel() {
        if (invtCancel == null) {
            invtCancel = new ArrayList<CEB623Message.InvtCancel>();
        }
        return this.invtCancel;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB623Message.BaseTransfer }
     *     
     */
    public CEB623Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB623Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB623Message.BaseTransfer value) {
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
     *         &lt;element name="copCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
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
     *         &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="buyerIdType" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="buyerIdNumber" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="buyerTelephone" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}String"/>
     *         &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "customsCode",
        "orderNo",
        "ebpCode",
        "ebpName",
        "ebcCode",
        "ebcName",
        "logisticsNo",
        "logisticsCode",
        "logisticsName",
        "copNo",
        "preNo",
        "invtNo",
        "buyerIdType",
        "buyerIdNumber",
        "buyerName",
        "buyerTelephone",
        "agentCode",
        "agentName",
        "reason",
        "note"
    })
    public static class InvtCancel {

        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String guid;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appType;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appTime;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String appStatus;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String customsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String orderNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebpCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebpName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebcCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebcName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String logisticsName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String copNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String preNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String invtNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String buyerIdType;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String buyerIdNumber;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String buyerName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String buyerTelephone;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String agentCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String agentName;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String reason;
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
         * 获取customsCode属性的值。
         * 
         */
        public String getCustomsCode() {
            return customsCode;
        }

        /**
         * 设置customsCode属性的值。
         * 
         */
        public void setCustomsCode(String value) {
            this.customsCode = value;
        }

        /**
         * 获取orderNo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrderNo() {
            return orderNo;
        }

        /**
         * 设置orderNo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderNo(String value) {
            this.orderNo = value;
        }

        /**
         * 获取ebpCode属性的值。
         * 
         */
        public String getEbpCode() {
            return ebpCode;
        }

        /**
         * 设置ebpCode属性的值。
         * 
         */
        public void setEbpCode(String value) {
            this.ebpCode = value;
        }

        /**
         * 获取ebpName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEbpName() {
            return ebpName;
        }

        /**
         * 设置ebpName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEbpName(String value) {
            this.ebpName = value;
        }

        /**
         * 获取ebcCode属性的值。
         * 
         */
        public String getEbcCode() {
            return ebcCode;
        }

        /**
         * 设置ebcCode属性的值。
         * 
         */
        public void setEbcCode(String value) {
            this.ebcCode = value;
        }

        /**
         * 获取ebcName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEbcName() {
            return ebcName;
        }

        /**
         * 设置ebcName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEbcName(String value) {
            this.ebcName = value;
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
         * 获取invtNo属性的值。
         * 
         */
        public String getInvtNo() {
            return invtNo;
        }

        /**
         * 设置invtNo属性的值。
         * 
         */
        public void setInvtNo(String value) {
            this.invtNo = value;
        }

        /**
         * 获取buyerIdType属性的值。
         * 
         */
        public String getBuyerIdType() {
            return buyerIdType;
        }

        /**
         * 设置buyerIdType属性的值。
         * 
         */
        public void setBuyerIdType(String value) {
            this.buyerIdType = value;
        }

        /**
         * 获取buyerIdNumber属性的值。
         * 
         */
        public String getBuyerIdNumber() {
            return buyerIdNumber;
        }

        /**
         * 设置buyerIdNumber属性的值。
         * 
         */
        public void setBuyerIdNumber(String value) {
            this.buyerIdNumber = value;
        }

        /**
         * 获取buyerName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBuyerName() {
            return buyerName;
        }

        /**
         * 设置buyerName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBuyerName(String value) {
            this.buyerName = value;
        }

        /**
         * 获取buyerTelephone属性的值。
         * 
         */
        public String getBuyerTelephone() {
            return buyerTelephone;
        }

        /**
         * 设置buyerTelephone属性的值。
         * 
         */
        public void setBuyerTelephone(String value) {
            this.buyerTelephone = value;
        }

        /**
         * 获取agentCode属性的值。
         * 
         */
        public String getAgentCode() {
            return agentCode;
        }

        /**
         * 设置agentCode属性的值。
         * 
         */
        public void setAgentCode(String value) {
            this.agentCode = value;
        }

        /**
         * 获取agentName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAgentName() {
            return agentName;
        }

        /**
         * 设置agentName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAgentName(String value) {
            this.agentName = value;
        }

        /**
         * 获取reason属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReason() {
            return reason;
        }

        /**
         * 设置reason属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReason(String value) {
            this.reason = value;
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
