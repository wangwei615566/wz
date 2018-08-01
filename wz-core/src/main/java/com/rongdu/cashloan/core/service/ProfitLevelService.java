package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.ProfitLevel;

import java.util.List;
import java.util.Map;

public interface ProfitLevelService {
    List<ProfitLevel> listSelect (Map<String,Object> param);
}
