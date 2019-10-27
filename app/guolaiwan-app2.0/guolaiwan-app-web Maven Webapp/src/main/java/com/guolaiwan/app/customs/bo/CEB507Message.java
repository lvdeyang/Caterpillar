//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.23 时间 01:55:12 PM CST 
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
 *         &lt;element name="Arrival" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ArrivalHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="domesticTrafNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ArrivalList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "arrival",
    "baseTransfer"
})
@XmlRootElement(name = "CEB507Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB507Message {

    @XmlElement(name = "Arrival", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB507Message.Arrival> arrival;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB507Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the arrival property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrival property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrival().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB507Message.Arrival }
     * 
     * 
     */
    public List<CEB507Message.Arrival> getArrival() {
        if (arrival == null) {
            arrival = new ArrayList<CEB507Message.Arrival>();
        }
        return this.arrival;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB507Message.BaseTransfer }
     *     
     */
    public CEB507Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB507Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB507Message.BaseTransfer value) {
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
     *         &lt;element name="ArrivalHead">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="appType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="appTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="appStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="domesticTrafNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ArrivalList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "arrivalHead",
        "arrivalList"
    })
    public static class Arrival {

        @XmlElement(name = "ArrivalHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB507Message.Arrival.ArrivalHead arrivalHead;
        @XmlElement(name = "ArrivalList", namespace="http://www.chinaport.gov.cn/ceb")
        protected List<CEB507Message.Arrival.ArrivalList> arrivalList;

        /**
         * 获取arrivalHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB507Message.Arrival.ArrivalHead }
         *     
         */
        public CEB507Message.Arrival.ArrivalHead getArrivalHead() {
            return arrivalHead;
        }

        /**
         * 设置arrivalHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB507Message.Arrival.ArrivalHead }
         *     
         */
        public void setArrivalHead(CEB507Message.Arrival.ArrivalHead value) {
            this.arrivalHead = value;
        }

        /**
         * Gets the value of the arrivalList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the arrivalList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArrivalList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CEB507Message.Arrival.ArrivalList }
         * 
         * 
         */
        public List<CEB507Message.Arrival.ArrivalList> getArrivalList() {
            if (arrivalList == null) {
                arrivalList = new ArrayList<CEB507Message.Arrival.ArrivalList>();
            }
            return this.arrivalList;
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
         *         &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="operatorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="loctNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ieFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="trafMode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="domesticTrafNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="logisticsCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="logisticsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
            "copNo",
            "operatorCode",
            "operatorName",
            "loctNo",
            "ieFlag",
            "trafMode",
            "billNo",
            "domesticTrafNo",
            "logisticsCode",
            "logisticsName",
            "msgCount",
            "msgSeqNo",
            "note"
        })
        public static class ArrivalHead {

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
            protected String copNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String operatorCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String operatorName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String loctNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ieFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String trafMode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String billNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String domesticTrafNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double msgCount;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double msgSeqNo;
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
             * 获取operatorName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOperatorName() {
                return operatorName;
            }

            /**
             * 设置operatorName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOperatorName(String value) {
                this.operatorName = value;
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
             * 获取domesticTrafNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDomesticTrafNo() {
                return domesticTrafNo;
            }

            /**
             * 设置domesticTrafNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDomesticTrafNo(String value) {
                this.domesticTrafNo = value;
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
             * 获取msgCount属性的值。
             * 
             */
            public Double getMsgCount() {
                return msgCount;
            }

            /**
             * 设置msgCount属性的值。
             * 
             */
            public void setMsgCount(Double value) {
                this.msgCount = value;
            }

            /**
             * 获取msgSeqNo属性的值。
             * 
             */
            public Double getMsgSeqNo() {
                return msgSeqNo;
            }

            /**
             * 设置msgSeqNo属性的值。
             * 
             */
            public void setMsgSeqNo(Double value) {
                this.msgSeqNo = value;
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
         *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="totalPackageNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "logisticsNo",
            "totalPackageNo",
            "note"
        })
        public static class ArrivalList {
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int gnum;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsNo;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String totalPackageNo;
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

}
