package com.pos.api.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pos.api.user.service.AppDbSession;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.util.MD5;
import com.wz.cashloan.core.common.util.MapUtil;
import com.wz.cashloan.core.common.util.StringUtil;

//import tool.util.StringUtil;

/**
 * Created by lsk on 2017/2/14.
 */
@SuppressWarnings({ "rawtypes" })
public class ApiInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(ApiInterceptor.class);

	@Autowired
	private AppDbSession session;

	public static Map<String,Object> getParams(HttpServletRequest request) {
		Map<String, String[]> rec = request.getParameterMap();
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		for (Map.Entry<String, String[]> entry : rec.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue()[0];
			result.put(name, value);
		}
		return result;
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
		logger.debug("签名验签" + sb.toString());
		return sb.toString();
	}
	
	public static String getBodyString(BufferedReader br) {
		String inputLine;
		String str = "";
		try {
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
//			br.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return str;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		Map< String, Object>  requestMap = getParams(request);
		
		//版本控制
		String check_version=Global.getValue("check_version");
		Map<String,Object> m=JSONObject.parseObject(check_version,Map.class);
		//开关状态
		if(m.get("state")!=null&&m.get("state").equals("10")){
//			String[] sys_vers=(m.get("version")+"").split("\\.");
			String versionNumbe = StringUtil.isNull(requestMap.get("versionNumber"));
			String user_version = StringUtil.isBlank(versionNumbe) ? "1.0.0" : versionNumbe;
			String sys_version=m.get("version")+"";
			int result=StringUtil.compareVersion(sys_version,user_version);
			if(result>0){
				m=new HashMap<String,Object>();
				m.put("code", -1);
				m.put("msg","版本过低，请到官网下载新最新版本！");
				Map<String,String> updateAppUrlMap = new HashMap<String,String>();
				updateAppUrlMap.put("updateAppUrl", Global.getValue("update_app_url"));
				m.put("data", updateAppUrlMap);
				JsonUtil.writeJson(m, response);
				return false;
			}
		}//版本控制结束

		
		//String token = request.getHeader("token");
		String signMsg = requestMap.get("signMsg") == null ? null : requestMap.get("signMsg").toString();
		requestMap.remove("signMsg");
		Map<String, Object> rec = new LinkedHashMap<String, Object>();
		// 登录后的请求地址都带有/act/
		boolean flag = false;
		if (StringUtil.isEmpty(signMsg)) {
			rec.put("code", 400);
			rec.put("msg", "没有signMsg");
			JsonUtil.writeJson(rec, response);
			return false;
		}
		flag =  MD5.verify(paramsString(requestMap),signMsg, "oQIhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ+LdB4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN");
		if (!flag) {
			rec.put("code", 400);
			rec.put("msg", "验签不通过");
			JsonUtil.writeJson(rec, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
