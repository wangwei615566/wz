package com.wz.cashloan.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.mapper.UserExtensionLogMapper;
import com.wz.cashloan.core.mapper.UserInviteMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.model.UserExtensionLog;
import com.wz.cashloan.core.service.UserAmountService;
import com.wz.cashloan.core.service.UserInviteService;

@Service("userInviteService")
public class UserInviteServiceImpl implements UserInviteService {
    @Resource
    private UserExtensionLogMapper userExtensionLogMapper;
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAmountService userAmountService;
    @Override
    public Map<String, Object> getFreeInvite(Long userId) {
        int countIp = userExtensionLogMapper.countIp(userId);
        int countInvite = userInviteMapper.countInvite(userId);
        Map<String, Object> result = new HashMap<>();
        Integer extensionCount = Integer.parseInt(Global.getValue("extension_count"));
        Integer inviteCount = Integer.parseInt(Global.getValue("invite_count"));
        if (countIp>=extensionCount || countInvite>=inviteCount){
            //开通成功把用户改为禁用
            User user = new User();
            user.setId(userId);
            user.setVipState((byte) 1);
            user.setState((byte)2);
            userMapper.updateByPrimaryKeySelective(user);
            result.put("state",1);
//            UserInvite toInvite = userInviteMapper.findToInvite(userId);
//            if (toInvite!=null){
//                String invite_amount = Global.getValue("invite_amount");
//                Double amount = userAmountService.getAmount(toInvite.getUserId(), Double.parseDouble(invite_amount));
//            }
        }else {
            result.put("state",-1);
            result.put("msg",countIp < extensionCount ? "您的未结算推荐人数"+countIp+"没有达到升级要求"+extensionCount+"请继续努力" : "您的未结算推荐注册人数"+countInvite+"没有达到升级要求"+inviteCount+"请继续努力");
        }
        return result;
    }

    /**
     * 推广人数统计
     * @param userId
     * @return
     */
    @Override
    public int getExtensionCount(Long userId){
        return userExtensionLogMapper.countIp(userId);
    }

    /**
     * 注册人数统计
     * @param userId
     * @return
     */
    @Override
    public int registerCount(Long userId) {
        return userInviteMapper.countInvite(userId);
    }

    /**
     * 奖励查询接口
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> listReward(Long userId) {
        String recommendAward = Global.getValue("recommend_ award");
        List<Map<String,Object>> list = userInviteMapper.listInviteName(userId);
        
        for (Map<String,Object> map:list
             ) {
            map.put("amount",recommendAward);
        }
        return list;
    }

    @Override
    public void saveExtension(String id, String ip) {
        Map<String, Object> reqMap = new HashMap<>();
        User user = null;
        if(StringUtils.isNotBlank(id)){
        	reqMap.put("id",Long.parseLong(id));
        	user = userMapper.findSelective(reqMap);
        }
        UserExtensionLog userExtensionLog = new UserExtensionLog();
        if (user!=null)userExtensionLog.setInvitationCode(user.getInvitationCode());
        userExtensionLog.setIp(ip);
        userExtensionLog.setCreateTime(new Date());
        userExtensionLogMapper.insertSelective(userExtensionLog);
    }
}
