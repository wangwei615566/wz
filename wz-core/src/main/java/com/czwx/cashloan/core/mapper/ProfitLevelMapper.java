package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ProfitLevel;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface ProfitLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitLevel record);

    int insertSelective(ProfitLevel record);

    ProfitLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitLevel record);

    int updateByPrimaryKey(ProfitLevel record);
    List<ProfitLevel> listSelect (Map<String,Object> param);
}