package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.Order;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;
@RDBatisDao
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateBySelect(Map<String,Object> param);

    Order findSelective(Map<String,Object> param);

    List<Order> listSelective(Map<String,Object> param);

    /**
     * 该订单的推广总费用（计算前）
     * @param orderId
     * @return
     */
    double profitAmount(Long orderId);
}