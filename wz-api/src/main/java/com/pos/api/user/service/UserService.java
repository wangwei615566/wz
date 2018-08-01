package com.pos.api.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.czwx.cashloan.core.mapper.UserMapper;
import com.czwx.cashloan.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.api.model.SmsModel;
import com.pos.api.service.ClSmsService;
import com.pos.api.user.bean.AppSessionBean;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.MapUtil;
import com.rongdu.cashloan.core.common.util.SqlUtil;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.service.CloanUserService;

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
     * @param vcode
     * @param invitationCode
     * @param registerCoordinate
     * @param registerAddr
     * @param regClient
     * @param signMsg
     * @param channelCode
     * @param markChannel
     * @param drainageId
     * @param registration 注册方式
     * @param passWord 登陆密码（未加密）
     * @return
     */
	@Transactional
    public Map registerUser(HttpServletRequest request, String phone, String pwd, String vcode, String invitationCode,
                            String registerCoordinate,String registerAddr,String regClient, String signMsg, String channelCode,
                            String markChannel,String registration,String passWord) {
        try {
            if (StringUtil.isEmpty(phone) || !StringUtil.isPhone(phone) || StringUtil.isEmpty(pwd) || StringUtil.isEmpty(vcode) || pwd.length() < 32) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "参数有误");
                return ret;
            }

            CloanUserService cloanUserService = (CloanUserService) BeanUtil.getBean("cloanUserService");
            long todayCount = cloanUserService.todayCount();
            String dayRegisterMax_ = Global.getValue("day_register_max");
            if(StringUtil.isNotBlank(dayRegisterMax_)){
            	int dayRegisterMax = Integer.parseInt(dayRegisterMax_);
            	if(dayRegisterMax > 0 && todayCount >= dayRegisterMax){
            		 Map ret = new LinkedHashMap();
                     ret.put("success", false);
                     ret.put("msg", "今日注册用户数已达上限，请明日再来");
                     return ret;
            	}
            }

            ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");
            int results = clSmsService.verifySms(phone, SmsModel.SMS_TYPE_REGISTER, vcode);
            String vmsg;
            if (results == 1) {
            	vmsg = null;
    		}else if(results == -1){
    			vmsg="验证码已过期";
    		}else {
    			vmsg="手机号码或验证码错误";
    		}
            if (vmsg != null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", vmsg);
                return ret;
            }
            Map invitor = null;
            if (!StringUtil.isEmpty(invitationCode)) {
                invitor = mybatisService.queryRec("usr.queryUserByInvitation", invitationCode);
                if (invitor == null) {
                    Map ret = new LinkedHashMap();
                    ret.put("success", false);
                    ret.put("msg", "邀请人不存在");
                    return ret;
                }
            }
//
            Map old = mybatisService.queryRec("usr.queryUserByLoginName", phone);
            if (old != null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "该手机号码已被注册");
                return ret;
            }

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long userId = dbService.insert(SqlUtil.buildInsertSqlMap("cl_user", new Object[][]{
                {"login_name", phone},
                {"login_pwd", pwd},
                {"invitation_code", randomInvitationCode(6)},
                {"regist_time", new Date()},
                {"uuid", uuid},
                {"level", 3},
                {"register_client", regClient},
                {"mark_channel", markChannel}
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_base_info", new Object[][]{
                {"user_id", userId},
                {"phone", phone},
                {"register_coordinate", registerCoordinate},
                {"register_addr", registerAddr},
                {"third_auth", "0"},
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("arc_credit", new Object[][]{
            		{"user_id", userId},
            		{"consumer_no", userId},
                {"total", Global.getDouble("init_credit")},
                {"unuse", Global.getDouble("init_credit")},
                {"state", 10}
            }));
            dbService.insert(SqlUtil.buildInsertSqlMap("cl_profit_amount", new Object[][]{
                {"user_id", userId},
                {"state", "10"}
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_auth", new Object[][]{
                {"user_id", userId},
                {"id_state", 10},
                {"zhima_state", 10},
                {"alipay_state", 30},
                {"phone_state", 10},
                {"phone_consistent", "true"},
                {"alipay_consistent", "true"},
                {"contact_state", 10},
                {"bank_card_state", 10},
                {"work_info_state", 10},
                {"other_info_state", 10},
            }));

            if (invitor != null) {
                dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_invite", new Object[][]{
                    {"invite_time", new Date()},
                    {"invite_id", userId},
                    {"invite_name", phone},
                    {"user_id", invitor.get("id")},
                    {"user_name", invitor.get("login_name")},
                }));
            }

            //2017.5.6 仅用于demo演示环境
//            demoUser(userId);

            Map result = new LinkedHashMap();
            result.put("success", true);
            result.put("msg", "注册成功");
            if ("sms".equals(registration)){//是否短信注册，即自动生成密码   是则发短信通知用户密码
                try{
                    //注册成功发送密码
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("registerPass",passWord);
                    clSmsService.sendSmsAsyncron("registerPassword", data, phone);
                }catch (Exception e){
                    logger.error("初始化密码发送失败", e);
                }
            }
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


    public Object forgetPwd(String oldPwd, String newPwd, String newPwd2,Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null || !newPwd.equals(newPwd2)) {
            Map ret = new LinkedHashMap();
            ret.put("success", false);
            ret.put("msg", "用户不存在或两次密码不一致");
            return ret;
        }
        user.setLoginPwd(newPwd);
        int i = userMapper.updateByPrimaryKeySelective(user);

        if (i>0) {
                Map ret = new LinkedHashMap();
                ret.put("success", true);
                ret.put("msg", "密码已修改");
                return ret;
            } else {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "密码修改失败");
                return ret;
            }

    }

    public Map login(final HttpServletRequest request, final String loginName, final String loginPwd) {
        try {
            Map user = mybatisService.queryRec("usr.queryUserByLoginName", loginName);
            if (user == null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "账户不存在");
                return ret;
            }

            String dbPwd = (String) user.get("login_pwd");
            if (dbPwd.equalsIgnoreCase(loginPwd)) {
                AppSessionBean session = appDbSession.create(request, loginName);
                Date loginTime = new Date();
                dbService.update("update cl_user set login_time=? where login_name=?", loginTime, loginName);//设置当前时间为登录时间
                Map ret = new LinkedHashMap();
                ret.put("success", true);
                ret.put("msg", "登录成功");
                ret.put("data", session.getFront());
                return ret;
            }
            Map ret = new LinkedHashMap();
            ret.put("success", false);
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