package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ProfitAgentMapper;
import com.czwx.cashloan.core.mapper.ProfitAmountMapper;
import com.czwx.cashloan.core.mapper.ProfitCashLogMapper;
import com.czwx.cashloan.core.mapper.ProfitLogMapper;
import com.czwx.cashloan.core.model.ProfitAmount;
import com.czwx.cashloan.core.model.ProfitCashLog;
import com.rongdu.cashloan.core.service.ProfitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("profitService")
public class ProfitServiceImpl implements ProfitService{
    @Resource
    private ProfitAgentMapper profitAgentMapper;
    @Resource
    private ProfitAmountMapper profitAmountMapper;
    @Resource
    private ProfitCashLogMapper profitCashLogMapper;
    @Resource
    private ProfitLogMapper profitLogMapper;

    /**
     * 推广数及代理数
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> extensionAndGoodsCount(Long userId) {
        return profitAgentMapper.extensionAndGoodsCount(userId);
    }

    @Override
    public List<Map<String, Object>> extensionList(Long userId) {
        return profitAgentMapper.extensionList(userId);
    }

    @Override
    public List<Map<String, Object>> goodsDetaiUser(Long userId) {
        List<Map<String, Object>> data = profitAgentMapper.goodsList(userId);
        for (Map<String, Object> map:data
             ) {
            Long goodsId = (long)map.get("id");
            Map<String, Object> param = new HashMap<>();
            param.put("userId",userId);
            param.put("goodsId",goodsId);
            List<Map<String, Object>> maps = profitAgentMapper.goodsListDetailUser(param);
            map.put("users",maps);
        }
        return data;
    }

    @Override
    public ProfitAmount findToUser(Long userId) {
        return profitAmountMapper.findToUser(userId);
    }

    @Override
    public int getAmount(long id,long userId,String alipayNo,String realName,double amount) {
        //支付接口

        //回写表
        Map<String, Object> param = new HashMap<>();
        param.put("id",id);
        param.put("userId",userId);
        ProfitAmount pAmount = profitAmountMapper.findSelect(param);
        pAmount.setCashed(pAmount.getCashed().add(pAmount.getCanCashed()));
        pAmount.setCanCashed(new BigDecimal("0"));
        int i = profitAmountMapper.updateByPrimaryKey(pAmount);

        ProfitCashLog profitCashLog = new ProfitCashLog();
        profitCashLog.setUserId(userId);
        profitCashLog.setAccountId(0L);
        profitCashLog.setCashWay((byte)2);
        profitCashLog.setAccountNo(alipayNo);
        profitCashLog.setAmount(new BigDecimal(amount));
        profitCashLog.setFee(new BigDecimal("0"));
        profitCashLog.setCreateTime(new Date());
        int j = profitCashLogMapper.insertSelective(profitCashLog);
        if (i>0 && j>0){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public List<ProfitCashLog> findProfitCashLog(Long userId) {
        return profitCashLogMapper.listToUserId(userId);
    }

    @Override
    public Map<String, Object> findIndex(Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        Date startTime = c.getTime();
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        Date endTime = c.getTime();
        param.put("startTime",startTime);//获取今天的0点到23点59分59秒
        param.put("endTime",endTime);
        Double todayAmount = profitLogMapper.fitAmountToTime(param);
        c.set(Calendar.DAY_OF_MONTH,1);
        c.add(Calendar.DAY_OF_MONTH,-1);
        Date endTimeMonth = c.getTime();
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        Date startTimeMonth = c.getTime();
        param.put("startTime",startTimeMonth);//获取上个月的0点到上个月最后一天的23点59分59秒
        param.put("endTime",endTimeMonth);
        Double monthAmount = profitLogMapper.fitAmountToTime(param);
        Map<String, Object> result = new HashMap<>();
        result.put("todayAmount",todayAmount);
        result.put("lastMonthAmount",monthAmount);
        return result;
    }
}
