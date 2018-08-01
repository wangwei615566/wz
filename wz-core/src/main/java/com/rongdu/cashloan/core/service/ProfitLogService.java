package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.ProfitLog;
import com.github.pagehelper.Page;

import java.util.Map;

public interface ProfitLogService {

    Page<ProfitLog> selectList(Map<String,Object> map, int current, int pageSize);
}
