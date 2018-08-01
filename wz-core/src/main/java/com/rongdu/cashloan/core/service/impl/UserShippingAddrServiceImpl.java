package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.UserShippingAddrMapper;
import com.czwx.cashloan.core.model.UserShippingAddr;
import com.rongdu.cashloan.core.service.UserShippingAddrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userShippingAddrService")
public class UserShippingAddrServiceImpl implements UserShippingAddrService {
    @Resource
    private UserShippingAddrMapper userShippingAddrMapper;
    @Override
    public List<UserShippingAddr> listSelective(Map<String, Object> param) {
        return userShippingAddrMapper.listSelective(param);
    }

    @Override
    public int saveOrUpdate(UserShippingAddr userShippingAddr) {
        int code = 0;
        if (userShippingAddr.getState()==1){
            Map<String, Object> param = new HashMap<>();
            param.put("state",userShippingAddr.getState());
            UserShippingAddr sAddr = userShippingAddrMapper.findSelective(param);
            if (sAddr!=null){
                sAddr.setState((byte)0);
                userShippingAddrMapper.updateByPrimaryKey(sAddr);
            }
        }
        if (userShippingAddr.getId() == null){
            userShippingAddr.setCreateTime(new Date());
            userShippingAddr.setUpdateTime(new Date());
            code = userShippingAddrMapper.insertSelective(userShippingAddr);
        }else {
            userShippingAddr.setUpdateTime(new Date());
            code = userShippingAddrMapper.updateByPrimaryKey(userShippingAddr);
        }
        return code;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userShippingAddrMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserShippingAddr findDefaultToUserId(Long userId) {
        return userShippingAddrMapper.findDefaultToUserId(userId);
    }
}
