package com.wz.manage.service;

import com.aliyun.oss.model.PutObjectResult;

public interface QuartzPsaiceCreditSyncService {

    public PutObjectResult uploadPlanTask() throws Exception;
}
