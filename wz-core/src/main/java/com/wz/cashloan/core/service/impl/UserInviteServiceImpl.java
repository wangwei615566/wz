package com.wz.cashloan.core.service.impl;

import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.mapper.UserExtensionLogMapper;
import com.wz.cashloan.core.mapper.UserInviteMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.service.UserInviteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userInviteService")
public class UserInviteServiceImpl implements UserInviteService {
    @Resource
    private UserExtensionLogMapper userExtensionLogMapper;
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public Map<String, Object> getFreeInvite(Long userId) {
        int countIp = userExtensionLogMapper.countIp(userId);
        int countInvite = userInviteMapper.countInvite(userId);
        Map<String, Object> result = new HashMap<>();
        if (countIp>=500 || countInvite>=50){
            //开通成功把用户改为禁用
            User user = new User();
            user.setId(userId);
            user.setState((byte)2);
            userMapper.updateByPrimaryKeySelective(user);
            result.put("state",1);
        }else {
            result.put("state",-1);
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
        List<String> nameList = userInviteMapper.listInviteName(userId);
        Map<String, Object> map;
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (String name:nameList
             ) {
            map = new HashMap<>();
            map.put("name",name);
            map.put("amount",recommendAward);
            list.add(map);
        }
        return list;
    }
}
