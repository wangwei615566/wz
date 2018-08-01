package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ProfitAmountMapper;
import com.czwx.cashloan.core.model.ProfitAmount;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.service.ProfitAmountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("profitAmountService")
public class ProfitAmountServiceImpl implements ProfitAmountService {

    @Resource
    private ProfitAmountMapper profitAmountMapper;

    @Override
    public Page<ProfitAmount> selectList(Map<String, Object> map, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ProfitAmount> list  = profitAmountMapper.selectList(map);
        return (Page<ProfitAmount>) list;
    }
}
