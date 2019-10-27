//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 02:56:14 PM CST 
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
 *         &lt;element name="Logistics" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LogisticsHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="goodsInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "logistics",
    "baseTransfer"
})
@XmlRootElement(name = "CEB511Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB511Message {

    @XmlElement(name = "Logistics", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB511Message.Logistics> logistics;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB511Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the logistics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logistics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogistics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB511Message.Logistics }
     * 
     * 
     */
    public List<CEB511Message.Logistics> getLogistics() {
        if (logistics == null) {
            logistics = new ArrayList<CEB511Message.Logistics>();
        }
        return this.logistics;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB511Message.BaseTransfer }
     *     
     */
    public CEB511Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB511Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB511Message.BaseTransfer value) {
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
     *         &lt;element name="LogisticsHead">
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
     *                   &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="goodsInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "logisticsHead"
    })
    public static class Logistics {

        @XmlElement(name = "LogisticsHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB511Message.Logistics.LogisticsHead logisticsHead;

        /**
         * 获取logisticsHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB511Message.Logistics.LogisticsHead }
         *     
         */
        public CEB511Message.Logistics.LogisticsHead getLogisticsHead() {
            return logisticsHead;
        }

        /**
         * 设置logisticsHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB511Message.Logistics.LogisticsHead }
         *     
         */
        public void setLogisticsHead(CEB511Message.Logistics.LogisticsHead value) {
            this.logisticsHead = value;
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
         *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="freight" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="insuredFee" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="packNo" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="goodsInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="consignee" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="consigneeTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
            "billNo",
            "orderNo",
            "freight",
            "insuredFee",
            "currency",
            "weight",
            "packNo",
            "goodsInfo",
            "consignee",
            "consigneeAddress",
            "consigneeTelephone",
            "note"
        })
        public static class LogisticsHead {

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
            protected String billNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String orderNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double freight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double insuredFee;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String currency;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double weight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double packNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String goodsInfo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consignee;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consigneeAddress;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String consigneeTelephone;
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
             * 获取weight属性的值。
             * 
             */
            public Double getWeight() {
                return weight;
            }

            /**
             * 设置weight属性的值。
             * 
             */
            public void setWeight(Double value) {
                this.weight = value;
            }

            /**
             * 获取packNo属性的值。
             * 
             */
            public Double getPackNo() {
                return packNo;
            }

            /**
             * 设置packNo属性的值。
             * 
             */
            public void setPackNo(Double value) {
                this.packNo = value;
            }

            /**
             * 获取goodsInfo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGoodsInfo() {
                return goodsInfo;
            }

            /**
             * 设置goodsInfo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGoodsInfo(String value) {
                this.goodsInfo = value;
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
