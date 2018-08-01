package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.ProfitAmount;
import com.github.pagehelper.Page;

import java.util.Map;

public interface ProfitAmountService {

    Page<ProfitAmount> selectList(Map<String,Object> map, int current, int pageSize);

}
