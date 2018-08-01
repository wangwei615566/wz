package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.ProfitAgent;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;
@RDBatisDao
public interface ProfitAgentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitAgent record);

    int insertSelective(ProfitAgent record);

    ProfitAgent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitAgent record);

    int updateByPrimaryKey(ProfitAgent record);

    /**
     * 代理数及推广数
     * @param userId
     * @return
     */
    Map<String,Object>extensionAndGoodsCount(Long userId);

    /**
     * 代理列表
     * @param userId
     * @return
     */
    List<Map<String,Object>>extensionList(Long userId);

    List<Map<String,Object>>goodsList(Long userId);

    List<Map<String,Object>>goodsListDetailUser(Map<String,Object> param);
}