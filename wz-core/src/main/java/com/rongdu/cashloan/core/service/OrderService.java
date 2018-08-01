package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.Order;
import com.czwx.cashloan.core.model.OrderDetail;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order createOrderMember(Long userId,Long goodsId);

    Order selectByPrimaryKey(Long orderId);

    boolean payOrder(Map<String,Object> param);

    List<Order> listSelective(Map<String,Object> param);

    List<OrderDetail> orderDetail(Long orderId);

    Page<Order> pageList(Map<String,Object> param, int page, int pageSize);

    int updateBySelect(Map<String,Object> map);

    int updateOrderDetail(OrderDetail orderDetail);
}
