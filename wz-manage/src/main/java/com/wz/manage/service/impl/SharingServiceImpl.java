package com.wz.manage.service.impl;

import com.wz.manage.service.SharingService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sharingService")
public class SharingServiceImpl implements SharingService {

    @Override
    public Map<String, Object> getContract(String orderNo, String customerName, String paperNumber) {
        return null;
    }

    @Override
    public Map<String, Object> getNewOverdue() {
        return null;
    }

    @Override
    public Map<String, Object> getStillOverdue(String orderNo, String customerName, String paperNumber) {
        return null;
    }

    @Override
    public Map<String, Object> getFinish(String orderNo, String customerName, String paperNumber) {
        return null;
    }
}
