/**
 * Project Name:pay-protocol
 * File Name:Xml.java
 * Package Name:cn.swiftpass.pay.protocol
 * Date:2014-8-10下午10:48:21
 *
*/

package com.wz.cashloan.core.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 * ClassName:Xml
 * Function: XML的工具方法
 * Date:     2014-8-10 下午10:48:21 
 * @author    
 */
public class XmlUtils {
    
    /** <一句话功能简述>
     * <功能详细描述>request转字符串
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String parseRequst(HttpServletRequest request){
        String body = "";
        try {
            ServletInputStream inputStream = request.getInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while(true){
                String info = br.readLine();
                if(info == null){
                    break;
                }
                if(body == null || "".equals(body)){
                    body = info;
                }else{
                    body += info;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }            
        return body;
    }
    
    public static String parseXML(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     * 
     * @param request
     * @return
     */
    public static SortedMap getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        SortedMap returnMap = new TreeMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value.trim());
        }
        return returnMap;
    }
    
    /**
     * 转XMLmap
     * @author  
     * @param xmlBytes
     * @param charset
     * @return
     * @throws Exception
     */
    public static Map<String, String> toMap(byte[] xmlBytes,String charset) throws Exception{
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Document doc = reader.read(source);
        Map<String, String> params = XmlUtils.toMap(doc.getRootElement());
        return params;
    }
    
    /**
     * 转MAP
     * @author  
     * @param element
     * @return
     */
    public static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<Element> els = element.elements();
        for(Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
    
    public static String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }
    /**
     * 根据xml消息体转化为Map
     * 
     * @param xml
     * @param rootElement
     * @return
     * @throws DocumentException
     * @author 蔡政滦 modify by 2015-6-5
     */
    public static Map<String,String> xml2map(String xml, String rootElement) throws DocumentException {
        org.dom4j.Document doc = DocumentHelper.parseText(xml);
        Element body = (Element) doc.selectSingleNode("/" + rootElement);
        Map<String,String> vo = __buildXml2map(body);
        return vo;
    }
     
    private static Map<String,String> __buildXml2map(Element body) {
        Map<String,String> vo = new HashMap();
        if (body != null) {
            List<Element> elements = body.elements();
            for (Element element : elements) {
                String key = element.getName();
                if (key.length()!=0) {
                    String type = element.attributeValue("type", "java.lang.String");
                    String text = element.getText().trim();
                    Object value = null;
                    if (java.lang.String.class.getCanonicalName().equals(type)) {
                        value = text;
                    } else if (java.lang.Character.class.getCanonicalName().equals(type)) {
                        value = new java.lang.Character(text.charAt(0));
                    } else if (java.lang.Boolean.class.getCanonicalName().equals(type)) {
                        value = new java.lang.Boolean(text);
                    } else if (java.lang.Short.class.getCanonicalName().equals(type)) {
                        value = java.lang.Short.parseShort(text);
                    } else if (java.lang.Integer.class.getCanonicalName().equals(type)) {
                        value = java.lang.Integer.parseInt(text);
                    } else if (java.lang.Long.class.getCanonicalName().equals(type)) {
                        value = java.lang.Long.parseLong(text);
                    } else if (java.lang.Float.class.getCanonicalName().equals(type)) {
                        value = java.lang.Float.parseFloat(text);
                    } else if (java.lang.Double.class.getCanonicalName().equals(type)) {
                        value = java.lang.Double.parseDouble(text);
                    } else if (java.math.BigInteger.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigInteger(text);
                    } else if (java.math.BigDecimal.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigDecimal(text);
                    } else if (java.util.Map.class.getCanonicalName().equals(type)) {
                        value = __buildXml2map(element);
                    } else {
                    }
                    vo.put(key, (String) value);
                }
            }
        }
        return vo;
    }


	 public static String parseXML(Map<String, String> parameters) {
	        StringBuffer sb = new StringBuffer();
	        sb.append("<xml>");
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while (it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String k = (String)entry.getKey();
	            String v = (String)entry.getValue();
	            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
	                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
	            }
	        }
	        sb.append("</xml>");
	        return sb.toString();
	    }
}

