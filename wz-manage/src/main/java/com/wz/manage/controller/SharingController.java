package com.wz.manage.controller;

import com.wz.manage.service.SharingService;

import com.wz.cashloan.core.common.util.ServletUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class SharingController extends ManageBaseController {
    private static final Logger logger = Logger.getLogger(SharingController.class);

    @Resource
    private SharingService sharingService;

    /**
     * 借款信息共享
     *
     * @param customerName
     * @param paperNumber
     * @param orderNo
     */
    @RequestMapping(value = "/modules/share/borrow/sharingBorrow.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public void sharingBorrow(@RequestParam(value = "customerName", required = true) String customerName,
                              @RequestParam(value = "paperNumber", required = true) String paperNumber,
                              @RequestParam(value = "orderNo", required = true) String orderNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result = sharingService.getContract(orderNo, customerName, paperNumber);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 新增逾期共享
     */
    @RequestMapping(value = "/modules/share/borrow/sharingNewOverdue.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public void sharingNewOverdue() {
        Map<String, Object> result = new HashMap<String, Object>();
        result = sharingService.getNewOverdue();
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 逾期状态共享
     *
     * @param customerName
     * @param paperNumber
     * @param orderNo
     */
    @RequestMapping(value = "/modules/share/borrow/sharingOverdueState.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public void sharingOverdueState(
            @RequestParam(value = "customerName", required = true) String customerName,
            @RequestParam(value = "paperNumber", required = true) String paperNumber,
            @RequestParam(value = "orderNo", required = true) String orderNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result = sharingService.getStillOverdue(orderNo, customerName, paperNumber);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 订单结清状态共享
     *
     * @param customerName
     * @param paperNumber
     * @param orderNo
     */
    @RequestMapping(value = "/modules/share/borrow/sharingFinishBorrow.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public void sharingFinishBorrow(
            @RequestParam(value = "customerName", required = true) String customerName,
            @RequestParam(value = "paperNumber", required = true) String paperNumber,
            @RequestParam(value = "orderNo", required = true) String orderNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result = sharingService.getFinish(orderNo, customerName, paperNumber);
        ServletUtils.writeToResponse(response, result);
    }
}
