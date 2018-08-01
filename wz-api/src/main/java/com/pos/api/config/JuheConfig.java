package com.pos.api.config;

import java.util.Properties;

import com.wz.cashloan.core.common.context.Global;

public class JuheConfig {

	private static JuheConfig instance;
	private Properties properties;
	
	private JuheConfig() {
		properties = new Properties();
		
		properties.put("mch_id", Global.getValue("juhe_pay_mch_id"));
		properties.put("appid", Global.getValue("juhe_pay_appid"));
		properties.put("key", Global.getValue("juhe_pay_key"));
		properties.put("version", "2.0.1");
		properties.put("common_url", "https://api.tectopper.com/pay/gateway");
		properties.put("notify_url", "/api/act/borrow/juheRepay/alipay/repaymentNotify.htm");
	}
	
	public static JuheConfig getInstance() {
		if (instance == null) {
			instance = new JuheConfig();
		}
		return instance;
	}
    
    public String getMch_id() {
		return properties.getProperty("mch_id");
    }
    
    public String getAppid() {
    	return properties.getProperty("appid");
    }
    
    public String getKey() {
    	return properties.getProperty("key");
    }
    
    public String getVersion() {
    	return properties.getProperty("version");
    }
    
    public String getCommon_url() {
    	return properties.getProperty("common_url");
    }
    
    public String getNotify_url() {
		return Global.getValue("app_server_host") + properties.getProperty("notify_url");
    }
}
