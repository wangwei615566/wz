package test.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.wz.cashloan.core.common.util.MD5;
import com.wz.cashloan.core.common.util.MapUtil;


public class TestApi {


    public static void main(String[] args){
        String url = "http://localhost:8080/api/act/user/login.htm";
//        String str = "{\"mobile\":\"13666666666\"}";
//        JSONObject  jsonObject = JSONObject.parseObject(str);
        Map<String, Object> hashMap = new HashMap<>();
        //hashMap.put("loginPwd","111111");
//        hashMap.put("versionNumber", "1.0.0");
//        hashMap.put("client", "Android");
//        hashMap.put("registerIp", "e80%3A%3Ad82d%3A30ff%3Afef4%3A1afe%25dummy0");

        hashMap.put("loginName", "wangwei");
        hashMap.put("loginPwd", "8886465");
        //hashMap.put("versionNumber", "1.0.0");
        String s = TestApi.doPost(url, hashMap);
        System.out.println(s);
    }

     /**
     * 发送 POST 请求（HTTP），JSON形式
     * @param url
     * @param map
     *            json对象
     * @return*/


    public static String doPost(String url, Map<String,Object> map) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
//            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
//            stringEntity.setContentEncoding("UTF-8");
//            stringEntity.setContentType("application/json");
			List<NameValuePair> reqPair = new ArrayList<NameValuePair>();
        	for(Map.Entry<String, Object> entry : map.entrySet()){
				reqPair.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
        	String sign = MD5.md5(paramsString(map), "oQIhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ+LdB4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN");
        	reqPair.add(new BasicNameValuePair("signMsg", sign));
        	UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(reqPair,"utf-8");
        	httpPost.setEntity(urlEncodedFormEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result ;
    }
    
    public static String paramsString(Map<String,Object> map) {
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
}
