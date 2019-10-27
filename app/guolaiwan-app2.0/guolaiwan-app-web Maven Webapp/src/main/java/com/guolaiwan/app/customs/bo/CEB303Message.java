//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.20 时间 09:49:27 AM CST 
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
 *                             &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="OrderList">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
    "order",
    "baseTransfer"
})
@XmlRootElement(name = "CEB303Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB303Message {

    @XmlElement(name = "Order", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB303Message.Order> order;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB303Message.BaseTransfer baseTransfer;
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
     * {@link CEB303Message.Order }
     * 
     * 
     */
    public List<CEB303Message.Order> getOrder() {
        if (order == null) {
            order = new ArrayList<CEB303Message.Order>();
        }
        return this.order;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB303Message.BaseTransfer }
     *     
     */
    public CEB303Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB303Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB303Message.BaseTransfer value) {
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
     *                   &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="OrderList">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="itemNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
        "orderHead",
        "orderList"
    })
    public static class Order {
    	
        @XmlElement(name = "OrderHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB303Message.Order.OrderHead orderHead;
        @XmlElement(name = "OrderList", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB303Message.Order.OrderList orderList;

        /**
         * 获取orderHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB303Message.Order.OrderHead }
         *     
         */
        public CEB303Message.Order.OrderHead getOrderHead() {
            return orderHead;
        }

        /**
         * 设置orderHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB303Message.Order.OrderHead }
         *     
         */
        public void setOrderHead(CEB303Message.Order.OrderHead value) {
            this.orderHead = value;
        }

        /**
         * 获取orderList属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB303Message.Order.OrderList }
         *     
         */
        public CEB303Message.Order.OrderList getOrderList() {
            return orderList;
        }

        /**
         * 设置orderList属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB303Message.Order.OrderList }
         *     
         */
        public void setOrderList(CEB303Message.Order.OrderList value) {
            this.orderList = value;
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
         *         &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
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
            "currency",
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
            protected Double currency;
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
             * 获取currency属性的值。
             * 
             */
            public Double getCurrency() {
                return currency;
            }

            /**
             * 设置currency属性的值。
             * 
             */
            public void setCurrency(Double value) {
                this.currency = value;
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
         *         &lt;element name="itemDescribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="barCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
            "itemName",
            "itemDescribe",
            "barCode",
            "unit",
            "currency",
            "qty",
            "price",
            "totalPrice",
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
            protected String itemDescribe;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String barCode;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String unit;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double qty;
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
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnit() {
                return unit;
            }

            /**
             * 设置unit属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnit(String value) {
                this.unit = value;
            }

            /**
             * 获取currency属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * 设置currency属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
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
