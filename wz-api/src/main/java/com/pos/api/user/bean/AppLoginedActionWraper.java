package com.pos.api.user.bean;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wz.cashloan.core.common.util.StringUtil;

/**
 * Created by lsk on 2016/7/29.
 */
@SuppressWarnings({ "rawtypes", "unused" })
public abstract class AppLoginedActionWraper extends AppAbsActionWrapper {
	
	public static final Logger logger = LoggerFactory.getLogger(AppLoginedActionWraper.class);

    public AppLoginedActionWraper(HttpServletResponse _resp, HttpServletRequest _req) {
        super(_resp, _req);
    }


    private String getSignParam(String name) {
        String value = _req.getParameter(name);
        if (StringUtil.isEmpty(value)) {
            value = _req.getHeader(name);
        }
        return value;
    }

    @Override
    public Object doAction()  {
        Map userData = (Map) _req.getSession().getAttribute("userData");
        try {
			userData= (Map) userData.get("userData");
		} catch (Exception e) {
			logger.error("session为空");
			logger.error(e.getMessage(),e);
		}
        String userId = _req.getSession().getAttribute("userId").toString();
        return doAction(userData, userId);
    }


    public abstract Object doAction(Map userData, String userId) ;

}
