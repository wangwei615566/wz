package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ProfitAmount;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;
@RDBatisDao
public interface ProfitAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitAmount record);

    int insertSelective(ProfitAmount record);

    ProfitAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitAmount record);

    int updateByPrimaryKey(ProfitAmount record);

    ProfitAmount findToUser(Long id);

    ProfitAmount findSelect(Map<String,Object> param);

    List<ProfitAmount> selectList(Map<String,Object> param);
}