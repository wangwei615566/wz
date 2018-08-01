package com.pos.api.controller;

import com.czwx.cashloan.core.model.*;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.OrderService;
import com.rongdu.cashloan.core.service.UserShippingAddrService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;
    @Resource
    private UserShippingAddrService userShippingAddrService;
    /**
     * 订单列表接口
     *
     * @param userId
     */
    @RequestMapping("api/order/list.htm")
    public void listOrder(@RequestParam(value = "userId") Long userId,
                          @RequestParam(value = "state", required = false) Integer state) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("state", state);
        List<Order> orders = orderService.listSelective(param);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, orders);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 订单详情接口
     *
     * @param orderId
     */
    @RequestMapping("api/order/detail.htm")
    public void orderDetail(@RequestParam(value = "orderId") Long orderId) {
        List<OrderDetail> orderDetails = orderService.orderDetail(orderId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, orderDetails);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 订单详情注册码添加
     *
     * @param orderDetailId
     * @param code
     */
    @RequestMapping("api/order/detail/code.htm")
    public void orderDetailCode(@RequestParam(value = "orderDetailId") Long orderDetailId,
                                @RequestParam(value = "code") String code) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailId);
        orderDetail.setCode(code);
        int i = orderService.updateOrderDetail(orderDetail);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, i>0?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, i>0?"添加成功":"添加失败");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 交付押金接口（生成订单及获取收获地址）
     *
     * @param userId
     * @param goodsId
     */
    @RequestMapping("api/order/create/order.htm")
    public void createOrder(@RequestParam(value = "userId") Long userId,
                            @RequestParam(value = "goodsId") Long goodsId) {
        Order order = orderService.createOrderMember(userId, goodsId);
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        List<UserShippingAddr> addrs = userShippingAddrService.listSelective(param);
        Map<String, Object> data = new HashMap<>();
        data.put("order",order);
        data.put("addrs",addrs);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, order!=null?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, order!=null?data:"");
        result.put(Constant.RESPONSE_CODE_MSG, order!=null?"获取成功":"获取失败");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 支付接口(商品支付)
     * @param userId 用户ID
     * @param orderNo 订单号
     * @param amount 支付金额
     * @param levelId 购买的会员id(普通商品则不传)
     * @param payMethod 支付方式
     */
    @RequestMapping("api/order/pay/order.htm")
    public void paymentOrder(@RequestParam(value = "userId")Long userId,
                             @RequestParam(value = "orderNo")Long orderNo,
                             @RequestParam(value = "amount")Long amount,
                             @RequestParam(value = "levelId",required = false)Long levelId,
                             @RequestParam(value = "payMethod")Long payMethod) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("orderNo",orderNo);
        param.put("amount",amount);
        if (levelId!=null) param.put("levelId",levelId);
        param.put("payMethod",payMethod);
        boolean flag = orderService.payOrder(param);
        if (flag){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "支付成功");
        }else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "支付失败");
        }
        ServletUtils.writeToResponse(response, result);
    }
}