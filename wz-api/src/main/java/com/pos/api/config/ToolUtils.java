package com.pos.api.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ToolUtils {
	
	/**
	 * map转化成string
	 */
	public static String transMapToString(Map map){    
		java.util.Map.Entry entry;    
		StringBuffer sb = new StringBuffer();    
		for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)    
		{    
			entry = (java.util.Map.Entry)iterator.next();    
			sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":    
			entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");    
		}    
		return sb.toString();    
	} 
	
	/**
	 * JSON字符串转化为map
	 * @param str
	 * @param spiString
	 * @return
	 */
	public static Map transJsonstringToMap(String str, String spiString)
	{
		String[] strs = str.split("+spiString+");
	    Map<String, String> map = new HashMap<String, String>();
		for(String s:strs){
		String[] ms = s.split(":");
	    map.put(ms[0], ms[1]);
		}
		return map;
	}
}
