package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ThirdPay;

public interface ThirdPayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThirdPay record);

    int insertSelective(ThirdPay record);

    ThirdPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdPay record);

    int updateByPrimaryKey(ThirdPay record);
}