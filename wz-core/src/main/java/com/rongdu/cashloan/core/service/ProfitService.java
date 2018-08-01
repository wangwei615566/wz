package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.ProfitAmount;
import com.czwx.cashloan.core.model.ProfitCashLog;

import java.util.List;
import java.util.Map;

public interface ProfitService {

    Map<String,Object> extensionAndGoodsCount(Long userId);

    List<Map<String,Object>> extensionList(Long userId);

    List<Map<String,Object>>goodsDetaiUser(Long userId);

    ProfitAmount findToUser(Long userId);

    int getAmount(long id,long userId,String alipayNo,String realName,double amount);

    List<ProfitCashLog> findProfitCashLog(Long userId);

    Map<String,Object> findIndex(Long userId);
}
