package com.wz.manage.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.exception.ServiceException;
import com.wz.cashloan.core.common.exception.SysAccessCodeException;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.system.domain.SysAccessCode;
import com.wz.cashloan.system.domain.SysRole;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.domain.SysUserRole;
import com.wz.cashloan.system.security.authentication.encoding.PasswordEncoder;
import com.wz.cashloan.system.service.SysAccessCodeService;
import com.wz.cashloan.system.service.SysRoleService;
import com.wz.cashloan.system.service.SysUserRoleService;
import com.wz.cashloan.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 登陆处理Action, 实际登陆处理交由Spring Security框架, 该Action的作用仅仅为辅助
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午2:15:42
 */
@Scope("prototype")
@Controller
public class SysLoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private PasswordEncoder passwordEncoder;// 密码加密
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysAccessCodeService sysAccessCodeService;

	/**
	 * 登陆处理
	 *
	 */
	@RequestMapping("/login.htm")
	public void login(HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.sendRedirect("/dev/index.html");
	}

	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/system/user/login.htm")
	public void loginAjax(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
			SysUser sysUser = (SysUser) request.getSession().getAttribute("SysUser");
			if(sysUser != null){
				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			} else {
				password = passwordEncoder.encodePassword(String.valueOf(password));
				Map<String,Object> params = new HashMap<>();
				params.put("userName",username);
				params.put("password",password);
				SysUser sysUserBySql = sysUserService.loginByUserName(params);
				if(sysUserBySql == null){
					res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
					res.put(Constant.RESPONSE_CODE_MSG, "密码错误请重新输入");
				} else {
					session.setAttribute("SysUser",sysUserBySql);
					res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				}
			}
			ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping(value = "/system/user/loginOut.htm")
	public void loginOut() throws Exception {
		Map<String, Object> res = new HashMap<>();
		try {
			Subject user = SecurityUtils.getSubject();
			user.logout();
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG,"退出成功");
		} catch (Exception e) {
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "系统错误");
		}
		ServletUtils.writeToResponse(response, res);

	}
	

	/**
	 * 切换角色
	 * @description
	 * @param role
	 * @param response
	 * @param request
	 * @author wgc
	 * @return void
	 * @throws IOException
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/system/user/switch/role.htm")
	public void changeLoginajax(@RequestParam(value = "role", required = false) String role,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.ROLEID, Long.valueOf(role.trim()));
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		ServletUtils.writeToResponse(response, res);
	}

	public void validateRole(SysUser user, Long roleid) throws ServiceException {
		List<SysUserRole> list = sysUserRoleService.getSysUserRoleList(user.getId());
		for (SysUserRole role : list) {
			if (role.getRoleId().equals(roleid))
				return;
		}
		SysRole role = sysRoleService.getRoleById(roleid);
		throw new ServiceException(user.getName() + "不包含[" + role.getName() + "]这个角色");
	}

	@RequestMapping(value = "/login2.htm")
	public void sessionout(HttpServletResponse response) {

		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "登录失败");

		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 获取头部信息 可以js缓存优化 后期处理
	 * @param response
	 * @param request
	 */
	@RequestMapping("/system/user/find.htm")
	public void findUser(HttpServletResponse response, HttpServletRequest request) throws Exception{
		Map<String, Object> responsemap = new HashMap<String, Object>();
		SysUser sysUser = this.getLoginUser(request);
		if (null==sysUser) {
			responsemap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responsemap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
			ServletUtils.writeToResponse(response, responsemap);
			return;
		}
		responsemap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responsemap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responsemap);
	}
	
}
