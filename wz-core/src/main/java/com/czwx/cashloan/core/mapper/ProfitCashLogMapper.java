package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ProfitCashLog;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface ProfitCashLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitCashLog record);

    int insertSelective(ProfitCashLog record);

    ProfitCashLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitCashLog record);

    int updateByPrimaryKey(ProfitCashLog record);

    List<ProfitCashLog> listToUserId(Long userId);

    List<ProfitCashLog> selectList(Map<String,Object> map);
}