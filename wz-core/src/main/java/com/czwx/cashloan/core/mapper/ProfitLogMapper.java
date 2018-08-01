package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ProfitLog;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface ProfitLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProfitLog record);

    int insertSelective(ProfitLog record);

    ProfitLog selectByPrimaryKey(Long id);

    List<ProfitLog> selectList(Map<String,Object> map);

    int updateByPrimaryKeySelective(ProfitLog record);

    int updateByPrimaryKey(ProfitLog record);

    Double fitAmountToTime(Map<String,Object> map);
}