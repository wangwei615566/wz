package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ProfitCashLogMapper;
import com.czwx.cashloan.core.model.ProfitAmount;
import com.czwx.cashloan.core.model.ProfitCashLog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.service.ProfitCashLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("profitCashLogService")
public class ProfitCashLogServiceImpl implements ProfitCashLogService{

    @Resource
    private ProfitCashLogMapper profitCashLogMapper;
    @Override
    public Page<ProfitCashLog> selectList(Map<String, Object> map, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ProfitCashLog> list  = profitCashLogMapper.selectList(map);
        return (Page<ProfitCashLog>) list;
    }
}
