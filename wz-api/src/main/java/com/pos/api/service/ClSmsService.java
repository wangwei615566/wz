package com.pos.api.service;


import java.util.Map;

import com.pos.api.bean.Sms;
import com.wz.cashloan.core.common.service.BaseService;


public interface ClSmsService extends BaseService<Sms, Long>{
	
	/**
	 * 查询与最近一条短信的时间差（秒）
	 * @param phone
	 * @param type
	 * @return
	 */
	long findTimeDifference(String phone, String type);
	
	/**
	 * 根据手机号码、短信验证码类型查询今日可获取次数，防短信轰炸
	 * @param phone
	 * @param type
	 * @return
	 */
	int countDayTime(String phone, String type);
	
	/**
	 * 发送短信
	 * @param phone
	 * @param type
	 * @return
	 */
	long sendSms(String phone, String type);

	/**
	 * 验证短信
	 * @param phone
	 * @param type
	 * @param code
	 * @param signMsg
	 * @return
	 */
	int verifySms(String phone, String type, String code);

	/**
	 * 查询用户
	 * @param phone 
	 * @return
	 */
	int findUser(String phone);
	

	/**
	 * 同步短信发送通用接口
	 * @param type 短信类型
	 * @param data	数据
	 * @param phone 手机号
	 * @return
	 */
	int sendSms(String type, Map<String ,Object> data,String phone,Integer ip);

	/**
	 *  异步短信发送通用接口
	 * @param type
	 * @param data
	 * @param phone
	 * @return
	 */
	int sendSmsAsyncron(String type, Map<String ,Object> data,String phone);

	/**
	 * 近一个小时内注册短信的数量
	 * @param ip
	 * @return
	 */
	int listInHourCount(Integer ip,String phone);
}
