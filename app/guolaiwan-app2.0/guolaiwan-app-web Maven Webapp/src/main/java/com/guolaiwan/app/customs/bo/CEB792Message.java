//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.23 时间 03:24:34 PM CST 
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
 *         &lt;element name="SummaryResult" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SummaryResultHead">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="sumNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="opDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                             &lt;element name="declSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="decState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SummaryResultList" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "summaryResult"
})
@XmlRootElement(name = "CEB792Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB792Message {

    @XmlElement(name = "SummaryResult", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB792Message.SummaryResult> summaryResult;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the summaryResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the summaryResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSummaryResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB792Message.SummaryResult }
     * 
     * 
     */
    public List<CEB792Message.SummaryResult> getSummaryResult() {
        if (summaryResult == null) {
            summaryResult = new ArrayList<CEB792Message.SummaryResult>();
        }
        return this.summaryResult;
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
     *         &lt;element name="SummaryResultHead">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *                   &lt;element name="sumNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="opDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="declSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="decState" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="msgCount" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="msgSeqNo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SummaryResultList" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                   &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "summaryResultHead",
        "summaryResultList"
    })
    public static class SummaryResult {

        @XmlElement(name = "SummaryResultHead", required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected CEB792Message.SummaryResult.SummaryResultHead summaryResultHead;
        @XmlElement(name = "SummaryResultList", namespace="http://www.chinaport.gov.cn/ceb")
        protected List<CEB792Message.SummaryResult.SummaryResultList> summaryResultList;

        /**
         * 获取summaryResultHead属性的值。
         * 
         * @return
         *     possible object is
         *     {@link CEB792Message.SummaryResult.SummaryResultHead }
         *     
         */
        public CEB792Message.SummaryResult.SummaryResultHead getSummaryResultHead() {
            return summaryResultHead;
        }

        /**
         * 设置summaryResultHead属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link CEB792Message.SummaryResult.SummaryResultHead }
         *     
         */
        public void setSummaryResultHead(CEB792Message.SummaryResult.SummaryResultHead value) {
            this.summaryResultHead = value;
        }

        /**
         * Gets the value of the summaryResultList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the summaryResultList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSummaryResultList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CEB792Message.SummaryResult.SummaryResultList }
         * 
         * 
         */
        public List<CEB792Message.SummaryResult.SummaryResultList> getSummaryResultList() {
            if (summaryResultList == null) {
                summaryResultList = new ArrayList<CEB792Message.SummaryResult.SummaryResultList>();
            }
            return this.summaryResultList;
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
         *         &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
         *         &lt;element name="sumNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="opDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="declSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="decState" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="agentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ebcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="declAgentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="declAgentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
            "customsCode",
            "sumNo",
            "opDate",
            "declSeqNo",
            "decState",
            "agentCode",
            "agentName",
            "ebcCode",
            "ebcName",
            "declAgentCode",
            "declAgentName",
            "grossWeight",
            "netWeight",
            "msgCount",
            "msgSeqNo"
        })
        public static class SummaryResultHead {

            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String guid;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String customsCode;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String sumNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String opDate;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String declSeqNo;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String decState;
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
            protected Double grossWeight;
            @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected Double netWeight;
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
             * 获取sumNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSumNo() {
                return sumNo;
            }

            /**
             * 设置sumNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSumNo(String value) {
                this.sumNo = value;
            }

            /**
             * 获取opDate属性的值。
             * 
             */
            public String getOpDate() {
                return opDate;
            }

            /**
             * 设置opDate属性的值。
             * 
             */
            public void setOpDate(String value) {
                this.opDate = value;
            }

            /**
             * 获取declSeqNo属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDeclSeqNo() {
                return declSeqNo;
            }

            /**
             * 设置declSeqNo属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDeclSeqNo(String value) {
                this.declSeqNo = value;
            }

            /**
             * 获取decState属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDecState() {
                return decState;
            }

            /**
             * 设置decState属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDecState(String value) {
                this.decState = value;
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
         *         &lt;element name="logisticsNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="gnum" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *         &lt;element name="gcode" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
            "invtNo",
            "logisticsNo",
            "gnum",
            "gcode"
        })
        public static class SummaryResultList {

        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String invtNo;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String logisticsNo;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected int gnum;
        	@XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
            protected String gcode;

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

        }

    }

}
