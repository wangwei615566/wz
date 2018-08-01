package com.pos.manage.controller;

import com.czwx.cashloan.core.model.ProfitAmount;
import com.czwx.cashloan.core.model.ProfitCashLog;
import com.czwx.cashloan.core.model.ProfitLog;
import com.czwx.cashloan.core.model.User;
import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.JsonUtil;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.service.CloanUserService;
import com.rongdu.cashloan.core.service.ProfitAmountService;
import com.rongdu.cashloan.core.service.ProfitCashLogService;
import com.rongdu.cashloan.core.service.ProfitLogService;
import com.rongdu.cashloan.system.domain.SysUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* 代理用户信息Controller
*/
@Scope("prototype")
@Controller
public class ManageProfitController extends ManageBaseController{

	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private ProfitLogService profitLogService;
	@Resource
	private ProfitAmountService profitAmountService;
	@Resource
	private ProfitCashLogService profitCashLogService;

	/**
	 * 会员管理（用户）
	 * @param searchParams 查找条件
	 * @param current 当前页
	 * @param pageSize 每页数量
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/list.htm")
	public void userList(
			@RequestParam(value="searchParams") String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> result = new HashMap<>();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		if(null == user || StringUtil.isBlank(user.getPhone())){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "用户异常");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<User> page = cloanUserService.listUser(params, current, pageSize);
		result.put(Constant.RESPONSE_DATA, page.getResult());
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 分润获得记录查询
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/list.htm")
	public void profitList(
			@RequestParam(value="searchParams") String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ProfitLog> page = profitLogService.selectList(params, current, pageSize);
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 用户资金
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/amount/list.htm")
	public void findAmount(
			@RequestParam(value="searchParams") String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ProfitAmount> page = profitAmountService.selectList(params, current, pageSize);
		result.put(Constant.RESPONSE_DATA, page.getResult());
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 提现记录
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/findCashLog.htm")
	public void findCashLog(
			@RequestParam(value="searchParams") String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ProfitCashLog> page = profitCashLogService.selectList(params, current, pageSize);
		result.put(Constant.RESPONSE_DATA, page.getResult());
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
