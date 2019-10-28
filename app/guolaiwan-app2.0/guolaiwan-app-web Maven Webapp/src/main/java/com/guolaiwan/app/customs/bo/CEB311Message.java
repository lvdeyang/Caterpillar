//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 02:25:47 PM CST 
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
 *         &lt;element name="Order" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OrderHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="taxTotal" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="acturalPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="buyerRegNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="buyerTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="buyerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="buyerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="batchNumbers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="consigneeDistrict" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="OrderList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
    "order",
    "baseTransfer"
})
@XmlRootElement(name = "CEB311Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB311Message {

    @XmlElement(name = "Order", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB311Message.Order> order;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB311Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the order property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the order property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB311Message.Order }
     * 
     * 
     */
    public List<CEB311Message.Order> getOrder() {
        if (order == null) {
            order = new ArrayList<CEB311Message.Order>();
        }
        return this.order;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB311Message.BaseTransfer }
     *     
     */
    public CEB311Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB311Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB311Message.BaseTransfer value) {
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
     *         &lt;element name="OrderHead">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="taxTotal" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="acturalPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="buyerRegNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="buyerTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="buyerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="buyerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="batchNumbers" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="consigneeDistrict" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="OrderList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
        "orderHead",
        "orderList"
    })
    public static class Order {

        @XmlElement(name = "OrderHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB311Message.Order.OrderHead orderHead;
        @XmlElement(name = "OrderList", namespace="http://www.chinaport.gov.cn/ceb")
        protected List<CEB311Message.Order.OrderList> orderList;

        /**
         * 获取orderHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB311Message.Order.OrderHead }
         *     
         */
        public CEB311Message.Order.OrderHead getOrderHead() {
            return orderHead;
        }

        /**
         * 设置orderHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB311Message.Order.OrderHead }
         *     
         */
        public void setOrderHead(CEB311Message.Order.OrderHead value) {
            this.orderHead = value;
        }

        /**
         * Gets the value of the orderList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orderList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrderList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CEB311Message.Order.OrderList }
         * 
         * 
         */
        public List<CEB311Message.Order.OrderList> getOrderList() {
            if (orderList == null) {
                orderList = new ArrayList<CEB311Message.Order.OrderList>();
            }
            return this.orderList;
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
         *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="taxTotal" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="acturalPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="buyerRegNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="buyerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="buyerTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="buyerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="buyerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="batchNumbers" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="consigneeDistrict" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            "orderType",
            "orderNo",
            "ebpCode",
            "ebpName",
            "ebcCode",
            "ebcName",
            "goodsValue",
            "freight",
            "discount",
            "taxTotal",
            "acturalPaid",
            "currency",
            "buyerRegNo",
            "buyerName",
            "buyerTelephone",
            "buyerIdType",
            "buyerIdNumber",
            "payCode",
            "payName",
            "payTransactionId",
            "batchNumbers",
            "consignee",
            "consigneeTelephone",
            "consigneeAddress",
            "consigneeDistrict",
            "note"
        })
        public static class OrderHead {

            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String guid;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appType;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appTime;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appStatus;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String orderType;
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
            protected Double goodsValue;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double freight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double discount;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double taxTotal;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double acturalPaid;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String buyerRegNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String buyerName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String buyerTelephone;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String buyerIdType;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String buyerIdNumber;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payTransactionId;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String batchNumbers;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consignee;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consigneeTelephone;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consigneeAddress;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consigneeDistrict;
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
             * 获取orderType属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrderType() {
                return orderType;
            }

            /**
             * 设置orderType属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrderType(String value) {
                this.orderType = value;
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
             * 获取goodsValue属性的值。
             * 
             */
            public Double getGoodsValue() {
                return goodsValue;
            }

            /**
             * 设置goodsValue属性的值。
             * 
             */
            public void setGoodsValue(Double value) {
                this.goodsValue = value;
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
             * 获取discount属性的值。
             * 
             */
            public Double getDiscount() {
                return discount;
            }

            /**
             * 设置discount属性的值。
             * 
             */
            public void setDiscount(Double value) {
                this.discount = value;
            }

            /**
             * 获取taxTotal属性的值。
             * 
             */
            public Double getTaxTotal() {
                return taxTotal;
            }

            /**
             * 设置taxTotal属性的值。
             * 
             */
            public void setTaxTotal(Double value) {
                this.taxTotal = value;
            }

            /**
             * 获取acturalPaid属性的值。
             * 
             */
            public Double getActuralPaid() {
                return acturalPaid;
            }

            /**
             * 设置acturalPaid属性的值。
             * 
             */
            public void setActuralPaid(Double value) {
                this.acturalPaid = value;
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
             * 获取buyerRegNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBuyerRegNo() {
                return buyerRegNo;
            }

            /**
             * 设置buyerRegNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBuyerRegNo(String value) {
                this.buyerRegNo = value;
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
             * 获取payCode属性的值。
             * 
             */
            public String getPayCode() {
                return payCode;
            }

            /**
             * 设置payCode属性的值。
             * 
             */
            public void setPayCode(String value) {
                this.payCode = value;
            }

            /**
             * 获取payName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPayName() {
                return payName;
            }

            /**
             * 设置payName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPayName(String value) {
                this.payName = value;
            }

            /**
             * 获取payTransactionId属性的值。
             * 
             */
            public String getPayTransactionId() {
                return payTransactionId;
            }

            /**
             * 设置payTransactionId属性的值。
             * 
             */
            public void setPayTransactionId(String value) {
                this.payTransactionId = value;
            }

            /**
             * 获取batchNumbers属性的值。
             * 
             */
            public String getBatchNumbers() {
                return batchNumbers;
            }

            /**
             * 设置batchNumbers属性的值。
             * 
             */
            public void setBatchNumbers(String value) {
                this.batchNumbers = value;
            }

            /**
             * 获取consignee属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConsignee() {
                return consignee;
            }

            /**
             * 设置consignee属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConsignee(String value) {
                this.consignee = value;
            }

            /**
             * 获取consigneeTelephone属性的值。
             * 
             */
            public String getConsigneeTelephone() {
                return consigneeTelephone;
            }

            /**
             * 设置consigneeTelephone属性的值。
             * 
             */
            public void setConsigneeTelephone(String value) {
                this.consigneeTelephone = value;
            }

            /**
             * 获取consigneeAddress属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConsigneeAddress() {
                return consigneeAddress;
            }

            /**
             * 设置consigneeAddress属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConsigneeAddress(String value) {
                this.consigneeAddress = value;
            }

            /**
             * 获取consigneeDistrict属性的值。
             * 
             */
            public String getConsigneeDistrict() {
                return consigneeDistrict;
            }

            /**
             * 设置consigneeDistrict属性的值。
             * 
             */
            public void setConsigneeDistrict(String value) {
                this.consigneeDistrict = value;
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
         *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="gmodel" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
            "itemName",
            "gmodel",
            "itemDescribe",
            "barCode",
            "unit",
            "qty",
            "price",
            "totalPrice",
            "currency",
            "country",
            "note"
        })
        public static class OrderList {
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gnum;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gmodel;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemDescribe;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String barCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String unit;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double qty;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double price;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double totalPrice;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String country;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String note;

            /**
             * 获取gnum属性的值。
             * 
             */
            public String getGnum() {
                return gnum;
            }

            /**
             * 设置gnum属性的值。
             * 
             */
            public void setGnum(String value) {
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
             * 获取itemDescribe属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getItemDescribe() {
                return itemDescribe;
            }

            /**
             * 设置itemDescribe属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setItemDescribe(String value) {
                this.itemDescribe = value;
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
