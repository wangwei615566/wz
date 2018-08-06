package com.wz.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.util.*;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.core.service.SysConfigService;
import com.wz.cashloan.core.service.SysDictService;
import com.wz.cashloan.system.domain.SysConfig;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.model.SysConfigModel;
import com.wz.cashloan.system.permission.annotation.RequiresPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Scope("prototype")
@Controller
public class SysConfigController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysConfigController.class);
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SysDictService sysDictService;
    /**
     * 系统参数表表,插入数据
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
	@RequestMapping("/modules/manage/system/config/save.htm")
//    @RequiresPermission(code = "modules:manage:system:config:save", name = "系统管理-系统参数设置-新增")
    public void saveOrUpdate(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "json" ,required = false)String json,
    	@RequestParam(value = "status" ,required = false)String status  //执行的动作
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;
     
        SysConfig sysConfig = new SysConfig();
        //对json对象进行转换
        if (!StringUtils.isEmpty(json))
            sysConfig = JsonUtil.parse(json, SysConfig.class);
		if("create".equals(status)){
			  SysUser sysUser = this.getLoginUser(request);
			  sysConfig.setStatus(1);//新建时有效
			  sysConfig.setCreator(sysUser.getId());
		//动态插入数据
			flag=sysConfigService.insertSysConfig(sysConfig);
		}else{
		 //修改数据
			flag=sysConfigService.updateSysConfig(sysConfig);
		}
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS+",刷新缓存后生效");
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }

    

    /**
     * 系统参数表,查询数据
     * @param response      页面的response
     * @param currentPage   当前页数
     * @param pageSize      每页限制
     * @param searchParam   查询条件
     * @param whereSql      直接的sql
     * @param fields        排序字段
     * @param rule          排序方式
     * @throws ServiceException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/modules/manage/system/config/list.htm")
    public void listConfigs ( HttpServletRequest request,HttpServletResponse response,
                        @RequestParam(value = "current") Integer current,
                        @RequestParam(value = "pageSize") Integer pageSize,
                        @RequestParam(value = "searchParams",required = false)String searchParams)
    throws Exception{
		Map<String, Object> paramap = new HashMap<String, Object>();
    	if (!StringUtils.isEmpty(searchParams)){
    		paramap = JsonUtil.parse(searchParams, Map.class);
    	}
    	List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
    	//获取系统参数类型数据字典
    	List<Map<String, Object>> dicList = sysDictService.getDictsCache("SYSTEM_TYPE");
		for (Map<String, Object> dic : dicList) {
    		Map<String, Object> types = new HashMap<String, Object>();
    		types.put("systemType", dic.get("value"));
			types.put("systemTypeName", dic.get("text"));
    		dataMap.put((String) dic.get("value"), dic.get("text"));
    		typeList.add(types);
    	}
    	//返回页面的json参数
		Page<SysConfig> page = sysConfigService.getSysConfigPageList(current,pageSize,paramap);
    	
		List<SysConfigModel> sysModel = new ArrayList<SysConfigModel>();
		if (page != null && !page.isEmpty()) {
			for (SysConfig sys : page) {
				SysConfigModel model = new SysConfigModel();
				model = model.getSysModel(sys, dataMap);
				sysModel.add(model);
			}
		}
		Map<String, Object> returnMap = new HashMap<String,Object>();
    	
    	//返回给页面
    	returnMap.put("dicData", typeList);
    	returnMap.put(Constant.RESPONSE_DATA, sysModel);
    	returnMap.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
    	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
    	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

    	ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 系统参数表表,逻辑删除 修改状态
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
    @RequestMapping("/modules/manage/system/config/delete.htm")
    @RequiresPermission(code = "modules:manage:system:config:delete", name = "系统管理-系统参数设置- 修改状态")
    public void deleteSysConfig(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "id" )String id,
    	@RequestParam(value = "status" )int status
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;
        SysConfig sysConfig = new SysConfig();
		 //修改数据
        	sysConfig.setId(Long.valueOf(id));
        	sysConfig.setStatus(status);
			flag=sysConfigService.updateSysConfig(sysConfig);
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS+",刷新缓存后生效");
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 重加载系统配置数据
     * 
     * @throws Exception
     */
    @RequestMapping("/modules/manage/system/config/reload.htm")
    @RequiresPermission(code = "modules:manage:system:config:reload", name = "系统管理-系统参数设置-缓存数据重加载")
    public void reload() throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();

        // 调用缓存辅助类 重加载系统配置数据
        CacheUtil.initSysConfig();

        //前台缓存清理
        String webCleanUrl = Global.getValue("notify_url_host") + "/system/config/reload.htm";
        String webResult = null;
        try {
        	webResult = HttpUtil.getHttpResponse(webCleanUrl);
        	logger.info("刷新api缓存结果:" + webResult);
        } catch (Exception e) {
        	logger.info("刷新api缓存出错");
        	logger.error(e.getMessage(),e);
        }
        
        // 刷新app-api服务缓存
        String appWebCleanUrl = Global.getValue("app_server_host") + "/system/config/reload.htm";
        try {
        	webResult = HttpUtil.getHttpResponse(appWebCleanUrl);
        	logger.info("刷新app-api缓存结果:" + webResult);
        } catch (Exception e) {
        	logger.info("刷新app-api缓存出错");
        	logger.error(e.getMessage(),e);
        }
        
        if(StringUtil.isNotBlank(webResult)){
        	@SuppressWarnings("unchecked")
			Map<String, Object> result = JsonUtil.parse(webResult, Map.class);
			String resultCode = StringUtil.isNull(result.get(Constant.RESPONSE_CODE));
        	if (StringUtil.isNotBlank(resultCode)
        			&& StringUtil.isNull(Constant.SUCCEED_CODE_VALUE).equals(resultCode)) {
        		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        	}else{
        		returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        	}
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, "后台缓存刷新完成,前台缓存刷新失败");
        }

        // 返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
	/**
	 * 返回系统版本号
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/config/findCode.htm")
	@RequiresPermission(code = "modules:manage:system:config:code", name = "系统管理版本号")
	public void findVersionCode(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Map<String,Object> returnMap=new HashMap<String,Object>();
		String versionCode=sysConfigService.findSysVersionCode();

		returnMap.put("versionCode",versionCode);

		//返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

    
}

