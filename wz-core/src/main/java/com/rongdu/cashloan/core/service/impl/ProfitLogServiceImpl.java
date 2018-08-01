package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ProfitLogMapper;
import com.czwx.cashloan.core.model.ProfitLog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.service.ProfitLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("profitLogService")
public class ProfitLogServiceImpl implements ProfitLogService {

    @Resource
    ProfitLogMapper profitLogMapper;

    @Override
    public Page<ProfitLog> selectList(Map<String, Object> map, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ProfitLog> profitLogs  = profitLogMapper.selectList(map);
        return (Page<ProfitLog>) profitLogs;
    }
}
