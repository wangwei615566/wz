package com.wz.cashloan.core.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.service.SysConfigService;
import com.wz.cashloan.system.domain.SysConfig;

import tool.util.BeanUtil;
import tool.util.StringUtil;



public class CacheUtil {

    private static final Logger logger = Logger.getLogger(CacheUtil.class);

    /**
     * 初始化系统参数配置
     */
	public static void initSysConfig() {

        logger.info("初始化系统配置Config...");

        // 系统配置Config缓存
        SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");

        Map<String, Object> configMap = new HashMap<String, Object>();

        List<SysConfig> sysConfigs = sysConfigService.findAll();
        for (SysConfig sysConfig : sysConfigs) {
            if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode())) {
                configMap.put(sysConfig.getCode(), sysConfig.getValue());
            }
        }

        Global.configMap = new HashMap<String, Object>();
        Global.configMap.putAll(configMap);
    }

 
}
