package com.pos.api.user.service;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.api.model.SmsModel;
import com.pos.api.service.ClSmsService;
import com.pos.api.user.bean.AppSessionBean;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.util.MapUtil;
import com.wz.cashloan.core.common.util.SqlUtil;
import com.wz.cashloan.core.common.util.StringUtil;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.mapper.UserMapper;

import tool.util.BeanUtil;

/**
 * Created by lsk on 2016/7/27.
 */
@Service("clUserService_")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AppDbSession appDbSession;

    @Resource
    protected DBService dbService;

    @Resource
    protected MybatisService mybatisService;

    @Resource
    protected SmsService smsService;

    @Resource
    private UserMapper userMapper;



    public UserService() {
        super();
    }

    /**
     *
     * @param request
     * @param phone
     * @param pwd 登陆密码（加密）
     * @param regClient
     * @param passWord 登陆密码（未加密）
     * @return
     */
	@Transactional
    public Map registerUser(HttpServletRequest request, String phone, String pwd, String InvitationName,String regClient,String registerIp,String deviceId,
                            String passWord) {
        try {
            if (StringUtil.isAnyBlank(phone,pwd,regClient,registerIp,deviceId) ||pwd.length() < 6) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "参数有误");
                return ret;
            }
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("deviceId",deviceId);
            List<User> users = userMapper.listSelective(reqMap);
            if (users!=null && users.size()>0){
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "该设备已经注册,请更换设备");
                return ret;
            }


            Map invitor = null;
            if (!StringUtil.isEmpty(InvitationName)) {
                invitor = mybatisService.queryRec("usr.queryUserByLoginName", InvitationName);
            }
//
            Map old = mybatisService.queryRec("usr.queryUserByLoginName", phone);
            if (old != null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "该账号已被注册");
                return ret;
            }

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long userId = dbService.insert(SqlUtil.buildInsertSqlMap("user", new Object[][]{
                {"login_name", phone},
                {"login_pwd", pwd},
                {"invitation_code", randomInvitationCode(6)},
                {"vip_state", 0},
                {"register_ip", registerIp},
                {"device_id", deviceId},
                {"register_client", regClient},
                {"create_time", new Date()},
            }));

            if (invitor != null) {
                dbService.insert(SqlUtil.buildInsertSqlMap("user_invite", new Object[][]{
                    {"invite_time", new Date()},
                    {"invite_id", userId},
                    {"invite_name", phone},
                    {"user_id", invitor.get("id")},
                    {"user_name", invitor.get("login_name")},
                }));
            }
            dbService.insert(SqlUtil.buildInsertSqlMap("user_amount", new Object[][]{
                    {"user_id", userId},
                    {"total", 0.0},
                    {"create_time", new Date()},
            }));
            //2017.5.6 仅用于demo演示环境
//            demoUser(userId);

            Map result = new LinkedHashMap();
            result.put("success", true);
            result.put("msg", "注册成功");
            return result;
        } catch (Exception e) {
            logger.error("注册失败", e);
            Map ret = new LinkedHashMap();
            ret.put("success", false);
            ret.put("msg", "注册失败");
            return ret;
        }
    }


    private String randomInvitationCode(int len) {
        while (true) {
            String str = randomNumAlph(len);
            if (mybatisService.queryRec("usr.queryUserByInvitation", str) == null) {
                return str;
            }
        }
    }

    private static String randomNumAlph(int len) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        byte[][] list = {
            {48, 57},
            {97, 122},
            {65, 90}
        };
        for (int i = 0; i < len; i++) {
            byte[] o = list[random.nextInt(list.length)];
            byte value = (byte) (random.nextInt(o[1] - o[0] + 1) + o[0]);
            sb.append((char) value);
        }
        return sb.toString();
    }


    public Object forgetPwd(String oldPwd, String newPwd, String newPwd2,String loginName) {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName",loginName);
        map.put("loginPwd",oldPwd);
        User user = userMapper.findSelective(map);
        if (user == null || !newPwd.equals(newPwd2)) {
            Map ret = new LinkedHashMap();
            ret.put("code", 300);
            ret.put("msg", "用户不存在或两次密码不一致");
            return ret;
        }
        user.setLoginPwd(newPwd);
        int i = userMapper.updateByPrimaryKeySelective(user);

        if (i>0) {
                Map ret = new LinkedHashMap();
                ret.put("code", 200);
                ret.put("msg", "密码已修改");
                return ret;
            } else {
                Map ret = new LinkedHashMap();
                ret.put("code", 300);
                ret.put("msg", "密码修改失败");
                return ret;
            }

    }

    public Map login(final HttpServletRequest request, final String loginName, final String loginPwd) {
        try {
            Map user = mybatisService.queryRec("usr.queryUserByLoginName", loginName);
            if (user == null) {
                Map ret = new LinkedHashMap();
                ret.put("code", 300);
                ret.put("msg", "账户不存在");
                return ret;
            }

            if("2".equals(user.get("state").toString())){
                Map ret = new LinkedHashMap();
                ret.put("code", 99);
                //ret.put("msg", "账户已冻结");
                return ret;
            }
            String dbPwd = (String) user.get("login_pwd");
            if (dbPwd.equalsIgnoreCase(loginPwd)) {
                AppSessionBean session = appDbSession.create(request, loginName);
                Map ret = new LinkedHashMap();
                ret.put("code", 200);
                ret.put("msg", "登录成功");
                ret.put("data", session.getFront());
                return ret;
            }
            Map ret = new LinkedHashMap();
            ret.put("code", 300);
            ret.put("msg", "密码错误");
            return ret;
        } catch (Exception e) {
            logger.error("登录异常", e);
            Map ret = new LinkedHashMap();
            ret.put("code", 500);
            ret.put("msg", "系统异常");
            return ret;
        }
    }
}