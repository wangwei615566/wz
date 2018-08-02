package com.wz.cashloan.core.service;

public interface UserInviteService {
    boolean getFreeInvite(Long userId);
    int getExtensionCount(Long userId);
    int registerCount(Long userId);
}
