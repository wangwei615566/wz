package com.wz.cashloan.core.service;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.model.UserCashLog;

import java.util.List;
import java.util.Map;

public interface UserCashLogService {
    Map<String, Object> save(UserCashLog userCashLog);

    List<UserCashLog> listUserCashLog(Long userId);

    int listToUserIdState(Long userId,int state);

    Page<Map<String,Object>> pageList(Map<String, Object> params, int current, int pageSize);

    int updateOrder(Map<String, Object> params);
    
    public boolean selectInviteId(Long inviteId);

}
