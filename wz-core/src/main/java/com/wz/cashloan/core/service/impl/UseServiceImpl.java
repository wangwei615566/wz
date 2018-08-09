package com.wz.cashloan.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.service.UserService;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UseServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<User> pageList(Map<String, Object> params, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<User> orderList = userMapper.listSelective(params);
        return (Page<User>) orderList;
    }

    @Override
    public int updateUser(Map<String, Object> params) {
        return userMapper.updateBySelective(params);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }
}
