package com.pos.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.SortedMap;

import com.wz.cashloan.core.common.util.XmlUtils;


public class CorefireHttpPost{
	public static String connect(String reqUrl,SortedMap<String,String> map) throws Exception {
		String res= null; 
		try {
        	//https单向认证
        	HttpURLConnection connection = SSLTrustManager.connect(reqUrl);
            	
        	connection.setRequestProperty("Content-Type", "text/xml");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setReadTimeout(30000);
                  
            byte data[] = XmlUtils.parseXML(map).getBytes("utf-8");
            OutputStream out = connection.getOutputStream();
            out.write(data);
            StringBuffer receivedData = new StringBuffer();
            InputStreamReader inReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader aReader = new BufferedReader(inReader);
            String aLine;
            while ((aLine = aReader.readLine()) != null) {
            	receivedData.append(aLine);
            }
           	//Integer statusCode = connection.getResponseCode();
            res = receivedData.toString();
            aReader.close();
            connection.disconnect();
            
        } catch (Exception e1) {
        	e1.printStackTrace();
        }
		return res;
   }

}