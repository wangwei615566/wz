package com.wz.cashloan.core.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/** 
* 功能：MD5签名
* 版本：3.3
* 修改日期：2012-08-17
* */
public class MD5 {
    public static void main(String[] args) throws Exception {
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("loginName", "wangwei");
    	params.put("loginPwd", "123");
    	//app生成签名
    	String sign = MD5.sign(params, "e1cf0ddcf6b47b59c351565d8ad717af");
    	/**
    	 * 在项目开发中，这里的signMsg需要放置http的header头中
    	 * 这样以便于后台直接通过String signMsg = request.getHeader("signMsg")获取
    	 * 无需对参数进行剔除signMsg操作
    	 */
    	params.put("signMsg", sign);
        System.out.println("签名："+sign);
        //服务端验证签名
        String signMsg = params.get("signMsg").toString();
        params.remove("signMsg");
        boolean flag = MD5.verify(paramsString(params), signMsg, "e1cf0ddcf6b47b59c351565d8ad717af");
        System.out.println("验证签名："+flag);
        
    }

    /**
     * md加密
     * @param text 需要签名的字符串
     * @return 加密结果
     * @throws Exception 
     */
    public static String md5(String text){
    	return md5(text,null);
    }
    
    /**
     * md加密
     * @param text 需要签名的字符串
     * @param chartset 字符编码
     * @return 加密结果
     */
    public static String md5(String text,String key){
    	text = StringUtils.isBlank(key) ? text : text+key;
    	byte[] bytes = getContentBytes(text,"utf-8");
        MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("获取加密方式出错:",e);
		}
        messageDigest.update(bytes);
        bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 0x10) {
                sb.append("0");
            }

            sb.append(Long.toString(bytes[i] & 0xff, 16));
        }

        return sb.toString().toLowerCase();
    }
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @return 签名结果
     * @throws Exception 
     */
    public static String sign(Map<String,Object> map, String key){
    	return md5(paramsString(map),key);
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @return 签名结果
     * @throws Exception 
     */
    public static boolean verify(String text, String sign, String key){
    	String mysign = md5(text,key);
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 按照key的升序排序
     * @param map
     * @return
     */
    private static String paramsString(Map<String,Object> map) {
		Map<String, Object> rec = MapUtil.simpleSort(map);
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Object> entry : rec.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			sb.append(name + "=" + value).append("|");
		}

		if (sb.length() > 1)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}