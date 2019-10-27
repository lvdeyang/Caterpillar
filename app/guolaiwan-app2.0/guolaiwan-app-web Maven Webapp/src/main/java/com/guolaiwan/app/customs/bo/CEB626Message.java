//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.10.25 时间 03:07:24 PM CST 
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
 *         &lt;element name="InvtRefundReturn" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="returnStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="returnTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="returnInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "invtRefundReturn"
})
@XmlRootElement(name = "CEB626Message", namespace="http://www.chinaport.gov.cn/ceb")
public class CEB626Message {

    @XmlElement(name = "InvtRefundReturn", namespace="http://www.chinaport.gov.cn/ceb")
    protected List<CEB626Message.InvtRefundReturn> invtRefundReturn;
    @XmlAttribute(name = "guid")
    protected String guid;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the invtRefundReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invtRefundReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvtRefundReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CEB626Message.InvtRefundReturn }
     * 
     * 
     */
    public List<CEB626Message.InvtRefundReturn> getInvtRefundReturn() {
        if (invtRefundReturn == null) {
            invtRefundReturn = new ArrayList<CEB626Message.InvtRefundReturn>();
        }
        return this.invtRefundReturn;
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
     *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="customsCode" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="agentCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="ebpCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="ebcCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="copNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="preNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="invtNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="returnStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="returnTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="returnInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "agentCode",
        "ebpCode",
        "ebcCode",
        "copNo",
        "preNo",
        "invtNo",
        "returnStatus",
        "returnTime",
        "returnInfo"
    })
    public static class InvtRefundReturn {

        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String guid;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String customsCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String agentCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebpCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String ebcCode;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String copNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String preNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String invtNo;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnStatus;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnTime;
        @XmlElement(required = true, namespace="http://www.chinaport.gov.cn/ceb")
        protected String returnInfo;

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
         * 获取returnStatus属性的值。
         * 
         */
        public String getReturnStatus() {
            return returnStatus;
        }

        /**
         * 设置returnStatus属性的值。
         * 
         */
        public void setReturnStatus(String value) {
            this.returnStatus = value;
        }

        /**
         * 获取returnTime属性的值。
         * 
         */
        public String getReturnTime() {
            return returnTime;
        }

        /**
         * 设置returnTime属性的值。
         * 
         */
        public void setReturnTime(String value) {
            this.returnTime = value;
        }

        /**
         * 获取returnInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReturnInfo() {
            return returnInfo;
        }

        /**
         * 设置returnInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReturnInfo(String value) {
            this.returnInfo = value;
        }

    }

}
