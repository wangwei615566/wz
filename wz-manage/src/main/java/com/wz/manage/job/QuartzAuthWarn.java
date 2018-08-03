package com.wz.manage.job;

import java.util.HashMap;
import java.util.Map;

import com.wz.cashloan.core.common.exception.ServiceException;
import com.wz.manage.domain.QuartzLog;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.wz.manage.domain.QuartzInfo;
import com.wz.manage.service.QuartzInfoService;
import com.wz.manage.service.QuartzLogService;

import tool.util.BeanUtil;
import tool.util.DateUtil;

@Component
@Lazy(value = false)
public class QuartzAuthWarn implements Job {
    private static final Logger logger = Logger.getLogger(QuartzAuthWarn.class);


    public String authWarnSms() throws ServiceException {
        String quartzRemark = "";
        int succeed = 0;
        int fail = 0;
        int total = 0;

        quartzRemark = "执行总次数" + total + ",成功" + succeed + "次,失败" + fail + "次";
        return quartzRemark;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        QuartzLog ql = new QuartzLog();
        Map<String, Object> qiData = new HashMap<>();
        QuartzInfo qi = quartzInfoService.findByCode("doAuthWarnSms");
        try {
            qiData.put("id", qi.getId());
            ql.setQuartzId(qi.getId());
            ql.setStartTime(DateUtil.getNow());

            String remark = authWarnSms();

            ql.setTime(DateUtil.getNow().getTime() - ql.getStartTime().getTime());
            ql.setResult("10");
            ql.setRemark(remark);
            qiData.put("succeed", qi.getSucceed() + 1);

        } catch (Exception e) {
            ql.setResult("20");
            qiData.put("fail", qi.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存定时任务日志");
            quartzLogService.save(ql);
            quartzInfoService.update(qiData);
        }
    }
}
