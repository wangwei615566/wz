package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ProfitLevelMapper;
import com.czwx.cashloan.core.model.ProfitLevel;
import com.rongdu.cashloan.core.service.ProfitLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("profitLevelService")
public class ProfitLevelServiceImpl implements ProfitLevelService{
    @Resource
    private ProfitLevelMapper profitLevelMapper;

    @Override
    public List<ProfitLevel> listSelect(Map<String, Object> param) {
        return profitLevelMapper.listSelect(param);
    }
}
