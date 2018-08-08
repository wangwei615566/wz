package com.wz.cashloan.core.service;

import java.util.List;
import java.util.Map;

public interface UserInviteService {
    Map<String, Object> getFreeInvite(Long userId);
    int getExtensionCount(Long userId);
    int registerCount(Long userId);
    List<Map<String,Object>> listReward(Long userId);

    void saveExtension(String userId,String ip);
}
