package com.pos.manage.service;

import java.util.List;
import java.util.Map;

public interface SharingService {
    /**--anrong sharing---*/
    /**
     * 借款信息共享
     * @param orderNo
     * @param customerName
     * @param paperNumber
     * @return
     */
    Map<String, Object> getContract(String orderNo, String customerName, String paperNumber);

    /**
     * 新增逾期共享
     * @return
     */
    Map<String, Object> getNewOverdue();

    /**
     * 逾期状态共享
     * @param orderNo
     * @param customerName
     * @param paperNumber
     * @return
     */
    Map<String, Object> getStillOverdue(String orderNo, String customerName, String paperNumber);

    /***
     * 结清状态共享
     * @param orderNo
     * @param customerName
     * @param paperNumber
     * @return
     */
    Map<String, Object> getFinish(String orderNo, String customerName, String paperNumber);
    /**---end---*/
}
