//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.23 时间 03:04:25 PM CST 
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
 *         &lt;element name="SummaryApply" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SummaryApplyHead">
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
 *                             &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="summaryFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="itemNameFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SummaryApplyList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "summaryApply",
    "baseTransfer"
})
@XmlRootElement(name = "CEB701Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB701Message {

    @XmlElement(name = "SummaryApply", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB701Message.SummaryApply> summaryApply;
    @XmlElement(name = "BaseTransfer", required = true, namespace="http://www.chinaport.gov.cn/ceb")
    protected CEB701Message.BaseTransfer baseTransfer;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the summaryApply property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the summaryApply property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSummaryApply().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB701Message.SummaryApply }
     * 
     * 
     */
    public List<CEB701Message.SummaryApply> getSummaryApply() {
        if (summaryApply == null) {
            summaryApply = new ArrayList<CEB701Message.SummaryApply>();
        }
        return this.summaryApply;
    }

    /**
     * 获取baseTransfer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CEB701Message.BaseTransfer }
     *     
     */
    public CEB701Message.BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    /**
     * 设置baseTransfer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CEB701Message.BaseTransfer }
     *     
     */
    public void setBaseTransfer(CEB701Message.BaseTransfer value) {
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
        "dxpId"
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
     *         &lt;element name="SummaryApplyHead">
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
     *                   &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="summaryFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="itemNameFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SummaryApplyList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "summaryApplyHead",
        "summaryApplyList"
    })
    public static class SummaryApply {

        @XmlElement(name = "SummaryApplyHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB701Message.SummaryApply.SummaryApplyHead summaryApplyHead;
        @XmlElement(name = "SummaryApplyList", namespace="http://www.chinaport.gov.cn/ceb")
        protected List<CEB701Message.SummaryApply.SummaryApplyList> summaryApplyList;

        /**
         * 获取summaryApplyHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB701Message.SummaryApply.SummaryApplyHead }
         *     
         */
        public CEB701Message.SummaryApply.SummaryApplyHead getSummaryApplyHead() {
            return summaryApplyHead;
        }

        /**
         * 设置summaryApplyHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB701Message.SummaryApply.SummaryApplyHead }
         *     
         */
        public void setSummaryApplyHead(CEB701Message.SummaryApply.SummaryApplyHead value) {
            this.summaryApplyHead = value;
        }

        /**
         * Gets the value of the summaryApplyList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the summaryApplyList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSummaryApplyList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CEB701Message.SummaryApply.SummaryApplyList }
         * 
         * 
         */
        public List<CEB701Message.SummaryApply.SummaryApplyList> getSummaryApplyList() {
            if (summaryApplyList == null) {
                summaryApplyList = new ArrayList<CEB701Message.SummaryApply.SummaryApplyList>();
            }
            return this.summaryApplyList;
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
         *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="summaryFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="itemNameFlag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
            "agentCode",
            "agentName",
            "ebcCode",
            "ebcName",
            "declAgentCode",
            "declAgentName",
            "summaryFlag",
            "itemNameFlag",
            "msgCount",
            "msgSeqNo"
        })
        public static class SummaryApplyHead {

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
            protected String agentCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String agentName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebcCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String ebcName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String declAgentCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String declAgentName;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String summaryFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String itemNameFlag;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int msgCount;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int msgSeqNo;

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
             * 获取declAgentCode属性的值。
             * 
             */
            public String getDeclAgentCode() {
                return declAgentCode;
            }

            /**
             * 设置declAgentCode属性的值。
             * 
             */
            public void setDeclAgentCode(String value) {
                this.declAgentCode = value;
            }

            /**
             * 获取declAgentName属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDeclAgentName() {
                return declAgentName;
            }

            /**
             * 设置declAgentName属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDeclAgentName(String value) {
                this.declAgentName = value;
            }

            /**
             * 获取summaryFlag属性的值。
             * 
             */
            public String getSummaryFlag() {
                return summaryFlag;
            }

            /**
             * 设置summaryFlag属性的值。
             * 
             */
            public void setSummaryFlag(String value) {
                this.summaryFlag = value;
            }

            /**
             * 获取itemNameFlag属性的值。
             * 
             */
            public String getItemNameFlag() {
                return itemNameFlag;
            }

            /**
             * 设置itemNameFlag属性的值。
             * 
             */
            public void setItemNameFlag(String value) {
                this.itemNameFlag = value;
            }

            /**
             * 获取msgCount属性的值。
             * 
             */
            public int getMsgCount() {
                return msgCount;
            }

            /**
             * 设置msgCount属性的值。
             * 
             */
            public void setMsgCount(int value) {
                this.msgCount = value;
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
         *         &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "invtNo"
        })
        public static class SummaryApplyList {

            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String invtNo;

            /**
             * 获取invtNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInvtNo() {
                return invtNo;
            }

            /**
             * 设置invtNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInvtNo(String value) {
                this.invtNo = value;
            }

        }

    }

}
