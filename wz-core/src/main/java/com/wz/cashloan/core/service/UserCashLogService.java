package com.wz.cashloan.core.service;

import com.wz.cashloan.core.model.UserCashLog;

import java.util.List;

public interface UserCashLogService {
    int save(UserCashLog userCashLog);
    List<UserCashLog> listUserCashLog(Long userId);
}
