package com.guolaiwan.app.web.publicnum.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import pub.caterpillar.commons.util.binary.Md5Utils;

public class CommonUtil {
	 /**
     * 获取当前时间 yyyyMMddHHmmss
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }
    
    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
    /**
     * 获取32位随机字符串
     *
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return Md5Utils.MD5(String.valueOf(random.nextInt(10000)));
    }
    
    /**
     * 微信支付sign签名
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters,String api_key) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            /** 如果参数为key或者sign，则不参与加密签名 */
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        System.out.println("传过来的支付密钥api_key="+api_key);
        /** 支付密钥必须参与加密，放在字符串最后面 */
        sb.append("key=" + api_key);
        /** 记得最后一定要转换为大写 */
        String sign = Md5Utils.MD5(sb.toString());
        return sign;
    }
    
    /**
     * 将请求参数转换为xml格式的string
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = entry.getValue() + "";
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    
    /**
     * 获取系统前一天的信息（amount=-1）,当前时间（amount=0），后一天（amount=1）
     * @param date
     * @return
     */
    public static String getPreDay(Date date,Integer amount) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        date = calendar.getTime();
        String value = sdf.format(date);
        return value.split(" ")[0];
    }
}
