package com.wz.cashloan.core.service.impl;

import com.wz.cashloan.core.mapper.UserExtensionLogMapper;
import com.wz.cashloan.core.mapper.UserInviteMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.service.UserInviteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userInviteService")
public class UserInviteServiceImpl implements UserInviteService {
    @Resource
    private UserExtensionLogMapper userExtensionLogMapper;
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean getFreeInvite(Long userId) {
        int countIp = userExtensionLogMapper.countIp(userId);
        int countInvite = userInviteMapper.countInvite(userId);
        if (countIp>=500 || countInvite>=50){
            //开通成功把用户改为禁用
            User user = new User();
            user.setId(userId);
            user.setState((byte)2);
            userMapper.updateByPrimaryKeySelective(user);
            return true;
        }else {
            return false;
        }
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
}
