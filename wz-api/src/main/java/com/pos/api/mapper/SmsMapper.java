package com.pos.api.mapper;


import java.util.Map;

import com.pos.api.bean.Sms;
import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.mapper.RDBatisDao;


@RDBatisDao
public interface SmsMapper extends BaseMapper<Sms,Long> {

	/**
	 * 查询最新一条短信记录
	 * @param data
	 * @return
	 */
	Sms findTimeMsg(Map<String, Object> data);

    /**
     * 查询某号码某种类型当天已发送次数
     * @param data
     * @return
     */
    int countDayTime(Map<String, Object> data);

	/**
	 * 同一个ip或电话一小时内发送的短信量
	 * @param data
	 * @return
	 */
	int hourOrPhoneCount(Map<String, Object> data);

}
