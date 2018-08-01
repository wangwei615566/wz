package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.ProfitCashLog;
import com.github.pagehelper.Page;

import java.util.Map;

public interface ProfitCashLogService {

    Page<ProfitCashLog> selectList(Map<String,Object> map, int current, int pageSize);
}
