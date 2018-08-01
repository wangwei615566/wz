package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.BankList;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface BankListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankList record);

    int insertSelective(BankList record);

    BankList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankList record);

    int updateByPrimaryKey(BankList record);
}