//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.23 时间 02:32:33 PM CST 
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
 *         &lt;element name="Inventory" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="InventoryHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="portCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="ieDate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="statisticsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ownerCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="iacCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="iacName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="emsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="tradeMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="trafName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="voyageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="licenseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="POD" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="fCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="fFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="iCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="iFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="wrapType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="InventoryList">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="itemRecordNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="gname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="qty1" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="qty2" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="unit1" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="unit2" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "inventory",
    "baseTransfer"
})
@XmlRootElement(name = "CEB603Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB603Message {

    @XmlElement(name = "Inventory", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB603Message.Inventory> inventory;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB603Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the inventory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inventory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInventory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB603Message.Inventory }
     * 
     * 
     */
    public List<CEB603Message.Inventory> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<CEB603Message.Inventory>();
        }
        return this.inventory;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB603Message.BaseTransfer }
     *     
     */
    public CEB603Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB603Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB603Message.BaseTransfer value) {
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
     *         &lt;element name="InventoryHead">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="portCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="ieDate" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="statisticsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ownerCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="iacCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="iacName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="emsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="tradeMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="trafName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="voyageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="licenseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="POD" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="fCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="fFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="iCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="iFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="wrapType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="InventoryList">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="itemRecordNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="gname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="qty1" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="qty2" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="unit1" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="unit2" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "inventoryHead",
        "inventoryList"
    })
    public static class Inventory {

        @XmlElement(name = "InventoryHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB603Message.Inventory.InventoryHead inventoryHead;
        @XmlElement(name = "InventoryList", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB603Message.Inventory.InventoryList inventoryList;

        /**
         * 获取inventoryHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB603Message.Inventory.InventoryHead }
         *     
         */
        public CEB603Message.Inventory.InventoryHead getInventoryHead() {
            return inventoryHead;
        }

        /**
         * 设置inventoryHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB603Message.Inventory.InventoryHead }
         *     
         */
        public void setInventoryHead(CEB603Message.Inventory.InventoryHead value) {
            this.inventoryHead = value;
        }

        /**
         * 获取inventoryList属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB603Message.Inventory.InventoryList }
         *     
         */
        public CEB603Message.Inventory.InventoryList getInventoryList() {
            return inventoryList;
        }

        /**
         * 设置inventoryList属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB603Message.Inventory.InventoryList }
         *     
         */
        public void setInventoryList(CEB603Message.Inventory.InventoryList value) {
            this.inventoryList = value;
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
         *         &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="portCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="ieDate" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="statisticsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ownerCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ownerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="iacCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="iacName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="emsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="tradeMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="trafName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="voyageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="licenseNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="POD" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="fCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="fFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="iCurrency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="iFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="wrapType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
            "ebpCode",
            "ebpName",
            "orderNo",
            "logisticsCode",
            "logisticsName",
            "logisticsNo",
            "copNo",
            "ieFlag",
            "portCode",
            "ieDate",
            "statisticsFlag",
            "agentCode",
            "agentName",
            "ebcCode",
            "ebcName",
            "ownerCode",
            "ownerName",
            "iacCode",
            "iacName",
            "emsNo",
            "tradeMode",
            "trafMode",
            "trafName",
            "voyageNo",
            "billNo",
            "totalPackageNo",
            "loctNo",
            "licenseNo",
            "country",
            "pod",
            "freight",
            "fCurrency",
            "fFlag",
            "insuredFee",
            "iCurrency",
            "iFlag",
            "wrapType",
            "packNo",
            "grossWeight",
            "netWeight",
            "note"
        })
        public static class InventoryHead {

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
            protected String ebpCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebpName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String orderNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String copNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ieFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String portCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ieDate;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String statisticsFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String agentCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String agentName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebcCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebcName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ownerCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ownerName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String iacCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String iacName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String emsNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String tradeMode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String trafMode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String trafName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String voyageNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String billNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String totalPackageNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String loctNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String licenseNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String country;
            @XmlElement(name = "POD", namespace="http://www.chinaport.gov.cn/ceb")
            protected String pod;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double freight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String fCurrency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String fFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double insuredFee;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String iCurrency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String iFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String wrapType;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int packNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double grossWeight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double netWeight;
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
             * 获取ieFlag属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIeFlag() {
                return ieFlag;
            }

            /**
             * 设置ieFlag属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIeFlag(String value) {
                this.ieFlag = value;
            }

            /**
             * 获取portCode属性的值。
             * 
             */
            public String getPortCode() {
                return portCode;
            }

            /**
             * 设置portCode属性的值。
             * 
             */
            public void setPortCode(String value) {
                this.portCode = value;
            }

            /**
             * 获取ieDate属性的值。
             * 
             */
            public String getIeDate() {
                return ieDate;
            }

            /**
             * 设置ieDate属性的值。
             * 
             */
            public void setIeDate(String value) {
                this.ieDate = value;
            }

            /**
             * 获取statisticsFlag属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatisticsFlag() {
                return statisticsFlag;
            }

            /**
             * 设置statisticsFlag属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatisticsFlag(String value) {
                this.statisticsFlag = value;
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
             * 获取ownerCode属性的值。
             * 
             */
            public String getOwnerCode() {
                return ownerCode;
            }

            /**
             * 设置ownerCode属性的值。
             * 
             */
            public void setOwnerCode(String value) {
                this.ownerCode = value;
            }

            /**
             * 获取ownerName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOwnerName() {
                return ownerName;
            }

            /**
             * 设置ownerName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOwnerName(String value) {
                this.ownerName = value;
            }

            /**
             * 获取iacCode属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIacCode() {
                return iacCode;
            }

            /**
             * 设置iacCode属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIacCode(String value) {
                this.iacCode = value;
            }

            /**
             * 获取iacName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIacName() {
                return iacName;
            }

            /**
             * 设置iacName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIacName(String value) {
                this.iacName = value;
            }

            /**
             * 获取emsNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmsNo() {
                return emsNo;
            }

            /**
             * 设置emsNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmsNo(String value) {
                this.emsNo = value;
            }

            /**
             * 获取tradeMode属性的值。
             * 
             */
            public String getTradeMode() {
                return tradeMode;
            }

            /**
             * 设置tradeMode属性的值。
             * 
             */
            public void setTradeMode(String value) {
                this.tradeMode = value;
            }

            /**
             * 获取trafMode属性的值。
             * 
             */
            public String getTrafMode() {
                return trafMode;
            }

            /**
             * 设置trafMode属性的值。
             * 
             */
            public void setTrafMode(String value) {
                this.trafMode = value;
            }

            /**
             * 获取trafName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTrafName() {
                return trafName;
            }

            /**
             * 设置trafName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTrafName(String value) {
                this.trafName = value;
            }

            /**
             * 获取voyageNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVoyageNo() {
                return voyageNo;
            }

            /**
             * 设置voyageNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVoyageNo(String value) {
                this.voyageNo = value;
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
             * 获取totalPackageNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTotalPackageNo() {
                return totalPackageNo;
            }

            /**
             * 设置totalPackageNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTotalPackageNo(String value) {
                this.totalPackageNo = value;
            }

            /**
             * 获取loctNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLoctNo() {
                return loctNo;
            }

            /**
             * 设置loctNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLoctNo(String value) {
                this.loctNo = value;
            }

            /**
             * 获取licenseNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLicenseNo() {
                return licenseNo;
            }

            /**
             * 设置licenseNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLicenseNo(String value) {
                this.licenseNo = value;
            }

            /**
             * 获取country属性的值。
             * 
             */
            public String getCountry() {
                return country;
            }

            /**
             * 设置country属性的值。
             * 
             */
            public void setCountry(String value) {
                this.country = value;
            }

            /**
             * 获取pod属性的值。
             * 
             */
            public String getPOD() {
                return pod;
            }

            /**
             * 设置pod属性的值。
             * 
             */
            public void setPOD(String value) {
                this.pod = value;
            }

            /**
             * 获取freight属性的值。
             * 
             */
            public Double getFreight() {
                return freight;
            }

            /**
             * 设置freight属性的值。
             * 
             */
            public void setFreight(Double value) {
                this.freight = value;
            }

            /**
             * 获取fCurrency属性的值。
             * 
             */
            public String getFCurrency() {
                return fCurrency;
            }

            /**
             * 设置fCurrency属性的值。
             * 
             */
            public void setFCurrency(String value) {
                this.fCurrency = value;
            }

            /**
             * 获取fFlag属性的值。
             * 
             */
            public String getFFlag() {
                return fFlag;
            }

            /**
             * 设置fFlag属性的值。
             * 
             */
            public void setFFlag(String value) {
                this.fFlag = value;
            }

            /**
             * 获取insuredFee属性的值。
             * 
             */
            public Double getInsuredFee() {
                return insuredFee;
            }

            /**
             * 设置insuredFee属性的值。
             * 
             */
            public void setInsuredFee(Double value) {
                this.insuredFee = value;
            }

            /**
             * 获取iCurrency属性的值。
             * 
             */
            public String getICurrency() {
                return iCurrency;
            }

            /**
             * 设置iCurrency属性的值。
             * 
             */
            public void setICurrency(String value) {
                this.iCurrency = value;
            }

            /**
             * 获取iFlag属性的值。
             * 
             */
            public String getIFlag() {
                return iFlag;
            }

            /**
             * 设置iFlag属性的值。
             * 
             */
            public void setIFlag(String value) {
                this.iFlag = value;
            }

            /**
             * 获取wrapType属性的值。
             * 
             */
            public String getWrapType() {
                return wrapType;
            }

            /**
             * 设置wrapType属性的值。
             * 
             */
            public void setWrapType(String value) {
                this.wrapType = value;
            }

            /**
             * 获取packNo属性的值。
             * 
             */
            public int getPackNo() {
                return packNo;
            }

            /**
             * 设置packNo属性的值。
             * 
             */
            public void setPackNo(int value) {
                this.packNo = value;
            }

            /**
             * 获取grossWeight属性的值。
             * 
             */
            public Double getGrossWeight() {
                return grossWeight;
            }

            /**
             * 设置grossWeight属性的值。
             * 
             */
            public void setGrossWeight(Double value) {
                this.grossWeight = value;
            }

            /**
             * 获取netWeight属性的值。
             * 
             */
            public Double getNetWeight() {
                return netWeight;
            }

            /**
             * 设置netWeight属性的值。
             * 
             */
            public void setNetWeight(Double value) {
                this.netWeight = value;
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
         *         &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="itemRecordNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="gname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="qty1" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="qty2" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="unit1" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="unit2" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
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
            "gnum",
            "itemNo",
            "itemRecordNo",
            "itemName",
            "gcode",
            "gname",
            "gmodel",
            "barCode",
            "country",
            "currency",
            "qty",
            "qty1",
            "qty2",
            "unit",
            "unit1",
            "unit2",
            "price",
            "totalPrice",
            "note"
        })
        public static class InventoryList {
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int gnum;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemRecordNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gcode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gname;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gmodel;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String barCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String country;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double qty;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double qty1;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double qty2;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String unit;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String unit1;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String unit2;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double price;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double totalPrice;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String note;

            /**
             * 获取gnum属性的值。
             * 
             */
            public int getGnum() {
                return gnum;
            }

            /**
             * 设置gnum属性的值。
             * 
             */
            public void setGnum(int value) {
                this.gnum = value;
            }

            /**
             * 获取itemNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getItemNo() {
                return itemNo;
            }

            /**
             * 设置itemNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setItemNo(String value) {
                this.itemNo = value;
            }

            /**
             * 获取itemRecordNo属性的值。
             * 
             */
            public String getItemRecordNo() {
                return itemRecordNo;
            }

            /**
             * 设置itemRecordNo属性的值。
             * 
             */
            public void setItemRecordNo(String value) {
                this.itemRecordNo = value;
            }

            /**
             * 获取itemName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getItemName() {
                return itemName;
            }

            /**
             * 设置itemName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setItemName(String value) {
                this.itemName = value;
            }

            /**
             * 获取gcode属性的值。
             * 
             */
            public String getGcode() {
                return gcode;
            }

            /**
             * 设置gcode属性的值。
             * 
             */
            public void setGcode(String value) {
                this.gcode = value;
            }

            /**
             * 获取gname属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGname() {
                return gname;
            }

            /**
             * 设置gname属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGname(String value) {
                this.gname = value;
            }

            /**
             * 获取gmodel属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGmodel() {
                return gmodel;
            }

            /**
             * 设置gmodel属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGmodel(String value) {
                this.gmodel = value;
            }

            /**
             * 获取barCode属性的值。
             * 
             */
            public String getBarCode() {
                return barCode;
            }

            /**
             * 设置barCode属性的值。
             * 
             */
            public void setBarCode(String value) {
                this.barCode = value;
            }

            /**
             * 获取country属性的值。
             * 
             */
            public String getCountry() {
                return country;
            }

            /**
             * 设置country属性的值。
             * 
             */
            public void setCountry(String value) {
                this.country = value;
            }

            /**
             * 获取currency属性的值。
             * 
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * 设置currency属性的值。
             * 
             */
            public void setCurrency(String value) {
                this.currency = value;
            }

            /**
             * 获取qty属性的值。
             * 
             */
            public Double getQty() {
                return qty;
            }

            /**
             * 设置qty属性的值。
             * 
             */
            public void setQty(Double value) {
                this.qty = value;
            }

            /**
             * 获取qty1属性的值。
             * 
             */
            public Double getQty1() {
                return qty1;
            }

            /**
             * 设置qty1属性的值。
             * 
             */
            public void setQty1(Double value) {
                this.qty1 = value;
            }

            /**
             * 获取qty2属性的值。
             * 
             */
            public Double getQty2() {
                return qty2;
            }

            /**
             * 设置qty2属性的值。
             * 
             */
            public void setQty2(Double value) {
                this.qty2 = value;
            }

            /**
             * 获取unit属性的值。
             * 
             */
            public String getUnit() {
                return unit;
            }

            /**
             * 设置unit属性的值。
             * 
             */
            public void setUnit(String value) {
                this.unit = value;
            }

            /**
             * 获取unit1属性的值。
             * 
             */
            public String getUnit1() {
                return unit1;
            }

            /**
             * 设置unit1属性的值。
             * 
             */
            public void setUnit1(String value) {
                this.unit1 = value;
            }

            /**
             * 获取unit2属性的值。
             * 
             */
            public String getUnit2() {
                return unit2;
            }

            /**
             * 设置unit2属性的值。
             * 
             */
            public void setUnit2(String value) {
                this.unit2 = value;
            }

            /**
             * 获取price属性的值。
             * 
             */
            public Double getPrice() {
                return price;
            }

            /**
             * 设置price属性的值。
             * 
             */
            public void setPrice(Double value) {
                this.price = value;
            }

            /**
             * 获取totalPrice属性的值。
             * 
             */
            public Double getTotalPrice() {
                return totalPrice;
            }

            /**
             * 设置totalPrice属性的值。
             * 
             */
            public void setTotalPrice(Double value) {
                this.totalPrice = value;
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

}
