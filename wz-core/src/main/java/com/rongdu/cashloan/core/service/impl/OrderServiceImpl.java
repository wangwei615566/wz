package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.*;
import com.czwx.cashloan.core.model.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.common.exception.BussinessException;
import com.rongdu.cashloan.core.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ProfitAgentMapper profitAgentMapper;
    @Resource
    private UserInviteMapper userInviteMapper;
    @Resource
    private ProfitLogMapper profitLogMapper;
    @Resource
    private ProfitAmountMapper profitAmountMapper;
    @Override
    public Order createOrderMember(Long userId,Long goodsId) {
        Date date = new Date();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        String orderNo = getOrderNo();
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNo(orderNo);
        order.setGoodsNum(1);
        order.setAmount(goods.getPrice());
        order.setState((byte)1);
        order.setUpdateTime(date);
        order.setCreateTime(date);
        orderMapper.insertSelective(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGoodsId(goodsId);
        orderDetail.setGoodsNum(1);
        orderDetail.setOrderId(order.getId());
        orderDetail.setCreateTime(date);
        orderDetail.setUpdateTime(date);
        orderDetailMapper.insertSelective(orderDetail);
        return order;
    }

    @Override
    public Order selectByPrimaryKey(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return order;
    }

    @Override
    public int updateBySelect(Map<String,Object> map) {
        return orderMapper.updateBySelect(map);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {

        return orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
    }

    /**
     * 支付订单接口
     * @param param
     * @return
     */
    @Override
    public boolean payOrder(Map<String, Object> param) {
        long userId = (long)param.get("userId");
        String orderNo = param.get("orderNo").toString();
        Map<String, Object> paramOrder = new HashMap<>();
        paramOrder.put("userId",userId);
        paramOrder.put("orderNo",orderNo);
        Order order = orderMapper.findSelective(paramOrder);
        long levelId = param.get("levelId") == null?0:(long)param.get("levelId");
        User user = userMapper.selectByPrimaryKey(userId);
        if (order!=null && (order.getState() !=1 || order.getState() != 0)){
            throw  new BussinessException("订单不存在或状态有误");
        }
        //支付

        boolean state = true;
        if (state){//支付成功
            order.setState((byte)4);
            if (levelId != 0)user.setLevelId(levelId);
            return tradeSuc(order,user);
        }else {
            order.setState((byte)0);
            return tradeFail(order);
        }

    }

    /**
     * 支付成功处理
     * @param order
     * @param user
     * @return
     */
    private boolean tradeSuc(Order order,User user){
        int i = orderMapper.updateByPrimaryKey(order);
        int j = userMapper.updateByPrimaryKeySelective(user);
        Date date = new Date();

        ProfitAgent profitAgent = new ProfitAgent();
        UserInvite userInvite = userInviteMapper.selectByUserId(user.getId());
        if (userInvite !=null){//有邀请记录
            Long userId = userInvite.getUserId();//分润人id
            profitAgent.setLeaderId(userId);

            Map<String, Object> levelRate = userMapper.findLevelToUserId(userId);
            BigDecimal rate = new BigDecimal(levelRate.get("rate").toString());

            //回写分润记录表
            double profitAmount = orderMapper.profitAmount(order.getId());//推广总费用查询
            BigDecimal amount = rate.multiply(new BigDecimal(profitAmount));//分润资金
            ProfitLog profitLog = new ProfitLog();
            profitLog.setOrderId(order.getId());
            profitLog.setAgentId(userId);
            profitLog.setUserId(user.getId());
            profitLog.setAmount(amount);
            profitLog.setRate(rate);
            profitLog.setCreateTime(date);
            profitLogMapper.insertSelective(profitLog);
            //回写分润资金表
            Map<String, Object> param = new HashMap<>();
            param.put("user_id",userId);
            ProfitAmount profitA = profitAmountMapper.findSelect(param);
            if (profitA == null){
                profitA = new ProfitAmount();
                profitA.setUserId(userId);
                profitA.setTotal(amount);
                profitA.setCanCashed(amount);
                profitA.setCashed(new BigDecimal(0));
                profitA.setFrozen(new BigDecimal(0));
                profitA.setCreateTime(date);
                profitA.setUpdateTime(date);
                profitAmountMapper.insert(profitA);
            }else {
                profitA.setTotal(profitA.getTotal().add(amount));
                profitA.setCanCashed(profitA.getCanCashed().add(amount));
                profitA.setUpdateTime(date);
                profitAmountMapper.updateByPrimaryKeySelective(profitA);
            }

        }
            //回写用户会员信息表
        profitAgent.setUserId(user.getId());
        profitAgent.setLevelId(user.getLevelId());
        profitAgent.setUseState((byte)1);
        profitAgent.setCreateTime(date);
        profitAgent.setUpdateTime(date);
        profitAgentMapper.insert(profitAgent);

        if (i>0 && j>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 支付失败处理
     * @param order
     * @return
     */
    private boolean tradeFail(Order order){
        int i = orderMapper.updateByPrimaryKey(order);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Order> listSelective(Map<String, Object> param) {
        return orderMapper.listSelective(param);
    }

    @Override
    public List<OrderDetail> orderDetail(Long orderId) {
        Map<String, Object> param = new HashMap<>();
        param.put("orderId",orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.listSelective(param);
        for (OrderDetail d:orderDetails
             ) {
            Goods goods = goodsMapper.selectByPrimaryKey(d.getGoodsId());
            d.setGoods(goods);
        }
        return orderDetails;
    }

    @Override
    public Page<Order> pageList(Map<String, Object> param, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Order> orderList = orderMapper.listSelective(param);
        return (Page<Order>) orderList;
    }

    /**
     * 生成订单号
     * @return
     */
    public static String getOrderNo() {
        Random random = new Random();
        String reqOrderNo = "";
        for (int i = 0; i < 9; i++) {
            int a = random.nextInt(10);
            reqOrderNo += a;
        }
        return reqOrderNo;
    }
}
