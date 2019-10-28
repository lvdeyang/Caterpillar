//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 02:53:15 PM CST 
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
 *         &lt;element name="Payment" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PaymentHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="payerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="payerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="payerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="payTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "payment",
    "baseTransfer"
})
@XmlRootElement(name = "CEB411Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB411Message {

    @XmlElement(name = "Payment", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB411Message.Payment> payment;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB411Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the payment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB411Message.Payment }
     * 
     * 
     */
    public List<CEB411Message.Payment> getPayment() {
        if (payment == null) {
            payment = new ArrayList<CEB411Message.Payment>();
        }
        return this.payment;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB411Message.BaseTransfer }
     *     
     */
    public CEB411Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB411Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB411Message.BaseTransfer value) {
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
     *         &lt;element name="PaymentHead">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="payerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="payerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="payerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="payTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "paymentHead"
    })
    public static class Payment {

        @XmlElement(name = "PaymentHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB411Message.Payment.PaymentHead paymentHead;

        /**
         * 获取paymentHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB411Message.Payment.PaymentHead }
         *     
         */
        public CEB411Message.Payment.PaymentHead getPaymentHead() {
            return paymentHead;
        }

        /**
         * 设置paymentHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB411Message.Payment.PaymentHead }
         *     
         */
        public void setPaymentHead(CEB411Message.Payment.PaymentHead value) {
            this.paymentHead = value;
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
         *         &lt;element name="payCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="payName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="payTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="payerIdType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="payerIdNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="payerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="payTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
            "payCode",
            "payName",
            "payTransactionId",
            "orderNo",
            "ebpCode",
            "ebpName",
            "payerIdType",
            "payerIdNumber",
            "payerName",
            "telephone",
            "amountPaid",
            "currency",
            "payTime",
            "note"
        })
        public static class PaymentHead {

            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String guid;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appType;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appTime;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String appStatus;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payTransactionId;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String orderNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebpCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebpName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payerIdType;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payerIdNumber;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payerName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String telephone;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double amountPaid;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String payTime;
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
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPayTransactionId() {
                return payTransactionId;
            }

            /**
             * 设置payTransactionId属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPayTransactionId(String value) {
                this.payTransactionId = value;
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
             * 获取payerIdType属性的值。
             * 
             */
            public String getPayerIdType() {
                return payerIdType;
            }

            /**
             * 设置payerIdType属性的值。
             * 
             */
            public void setPayerIdType(String value) {
                this.payerIdType = value;
            }

            /**
             * 获取payerIdNumber属性的值。
             * 
             */
            public String getPayerIdNumber() {
                return payerIdNumber;
            }

            /**
             * 设置payerIdNumber属性的值。
             * 
             */
            public void setPayerIdNumber(String value) {
                this.payerIdNumber = value;
            }

            /**
             * 获取payerName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPayerName() {
                return payerName;
            }

            /**
             * 设置payerName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPayerName(String value) {
                this.payerName = value;
            }

            /**
             * 获取telephone属性的值。
             * 
             */
            public String getTelephone() {
                return telephone;
            }

            /**
             * 设置telephone属性的值。
             * 
             */
            public void setTelephone(String value) {
                this.telephone = value;
            }

            /**
             * 获取amountPaid属性的值。
             * 
             */
            public Double getAmountPaid() {
                return amountPaid;
            }

            /**
             * 设置amountPaid属性的值。
             * 
             */
            public void setAmountPaid(Double value) {
                this.amountPaid = value;
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
             * 获取payTime属性的值。
             * 
             */
            public String getPayTime() {
                return payTime;
            }

            /**
             * 设置payTime属性的值。
             * 
             */
            public void setPayTime(String value) {
                this.payTime = value;
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
