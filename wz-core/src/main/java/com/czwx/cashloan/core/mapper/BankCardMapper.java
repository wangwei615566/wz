package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.BankCard;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface BankCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
}