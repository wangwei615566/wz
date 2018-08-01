/*package com.pos.api.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import com.czwx.cashloan.core.mapper.UserMapper;
import com.czwx.cashloan.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pos.api.bean.Sms;
import com.pos.api.bean.SmsTpl;
import com.pos.api.mapper.SmsMapper;
import com.pos.api.mapper.SmsTplMapper;
import com.pos.api.service.ClSmsService;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.service.impl.BaseServiceImpl;
import com.wz.cashloan.core.common.util.DateUtil;
import com.wz.cashloan.core.common.util.HttpUtil;
import com.wz.cashloan.core.common.util.StringUtil;

import credit.Header;
import smscredit.BatchSmsCreditRequest;
import smscredit.SmsCreditRequest;


@Service("clSmsService")
public class ClSmsServiceImpl extends BaseServiceImpl<Sms, Long> implements ClSmsService {
	
	public static final Logger logger = LoggerFactory.getLogger(ClSmsServiceImpl.class);
	private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    @Resource
    private SmsMapper smsMapper;
    @Resource
    private SmsTplMapper smsTplMapper;
    @Resource
    private UserMapper userMapper;

	private int msg;

	@Override
	public BaseMapper<Sms, Long> getMapper() {
		return smsMapper;
	}
	

	@Override
	public long sendSms(String phone, String type) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		search.put("state", "10");
		SmsTpl tpl = smsTplMapper.findSelective(search);
		if (tpl!=null) {
			Map<String, Object> payload = new HashMap<>();
			int vcode = (int) (Math.random() * 9000) + 1000;
	        payload.put("mobile", phone);
	        payload.put("message", change(type)+vcode);
	        String result = sendCode(payload,tpl.getNumber());
	        return result(result, phone, type,null);
		}
        return 0;
	}

	
    
	
	@Override
	public int verifySms(String phone, String type, String code) {
		if ("dev".equals(Global.getValue("app_environment")) && "0000".equals(code)) {
			return 1;
		}
		
		if(StringUtil.isBlank(phone) || StringUtil.isBlank(type) || StringUtil.isBlank(code)){
			return 0;
		}
		
		
		if (!StringUtil.isPhone(phone)) {
			return 0;
		}
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		if (sms != null) {
			String mostTimes = Global.getValue("sms_day_most_times");
			int mostTime = JSONObject.parseObject(mostTimes).getIntValue("verifyTime");
			
			data = new HashMap<>();
			data.put("verifyTime", sms.getVerifyTime()+1);
			data.put("id", sms.getId());
			smsMapper.updateSelective(data);
			
			if (StringUtil.equals("00", sms.getState()) || StringUtil.equals("20", sms.getState()) || sms.getVerifyTime()>mostTime) {
				return 0;
			}
			
			long timeLimit = Long.parseLong(Global.getValue("sms_time_limit"));
			
			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff > timeLimit * 60 * 1000) {
				return -1;
			}
			if (sms.getCode().equals(code)) {
				Map<String,Object> map = new HashMap<>();
				map.put("id", sms.getId());
				map.put("state", "20");
				smsMapper.updateSelective(map);
				return 1;
			}
		}
		return 0;
	}
	
	protected String changeMessage(String message, Map<String,Object> map) {
		String message_ = "";
		if ("overdue".equals(message)) {
			message_ = ret(message);
			message_ = message
					.replace("{$name}", StringUtil.isNull(map.get("realName")))
					.replace("{$phone}",StringUtil.isNull(map.get("phone")));
		}
		if ("loanInform".equals(message)) {
			message_ = ret(message);
			message_ = message.replace("{$time}",
					DateUtil.dateStr(DateUtil.parse(map.get("time")
							.toString(), DateUtil.DATEFORMAT_STR_001),
							"M月dd日"));
		}
		if ("repayInform".equals(message)) {
			message_ = ret(message);
			message_ = message.replace("{$time}",
					DateUtil.dateStr(DateUtil.parse(map.get("time")
							.toString(), DateUtil.DATEFORMAT_STR_001),
							"M月dd日"))
							.replace("{$loan}", StringUtil.isNull(map.get("loan")));
		}
		return message_;
	}
	
	public String change(String code){
		String message = null;
		if ("register".equals(code)) {
			message = ret("register");
		}else if ("findReg".equals(code)) {
			message = ret("findReg");
		}else if ("bindCard".equals(code)) {
			message = ret("bindCard");
		}else if ("findPay".equals(code)) {
			message = ret("findPay");
		}else if ("overdue".equals(code)) {
			message = ret("overdue");
		}else if ("loanInform".equals(code)) {
			message = ret("loanInform");
		}else if ("repayInform".equals(code)) {
			message = ret("repayInform");
		}
		return message;
	}
	
	public String ret(String type){
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		SmsTpl tpl = smsTplMapper.findSelective(search);
		return tpl.getTpl();
	}
	
	private int result(String result,String phone,String type,Integer ip){
		JSONObject resultJson = JSONObject.parseObject(result);

        Integer code;
		if (StringUtil.isNotBlank(resultJson)) {
			code = resultJson.getInteger("code");
			Sms sms = new Sms();
			if (code == 200) {
				JSONObject resJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("res")));
				JSONObject tempJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("tempParame")));
				String orderNo = StringUtil.isNull(resultJson.get("orderNo"));
				Integer tempCode = tempJson.getInteger("code");
                JSONObject data = resultJson.getJSONObject("data");
                String orderNo = StringUtil.isNull(data.getString("orderNo"));
                JSONObject params = data.getJSONObject("params");
				String tempCode = params.getString("code");

                sms.setPhone(phone);
				sms.setSendTime(DateUtil.getNow());
				sms.setContent(resultJson.toString());
				sms.setRespTime(DateUtil.getNow());
				sms.setResp("短信已发送");
				sms.setSmsType(type);
				sms.setCode(StringUtil.isNull(tempCode));
				sms.setOrderNo(orderNo);
				sms.setState("10");
				sms.setVerifyTime(0);
				if (ip!=null) sms.setRegisterIp(ip);
				msg = smsMapper.save(sms);
			} else {
				sms.setPhone(phone);
				sms.setSendTime(DateUtil.getNow());
				sms.setContent(resultJson.getString("message"));
				sms.setRespTime(DateUtil.getNow());
				sms.setResp("短信发送失败");
				sms.setSmsType(type);
				sms.setCode("");
				sms.setOrderNo("");
				sms.setState("00"); //发送失败是00  发送成功是10 和 20  其中未使用验证码是10  已经使用验证过是20
				sms.setVerifyTime(0);
				smsMapper.save(sms);
				msg = 0;
			}
		}
		return msg;

	}

    private int result(String result,String phone,String type){
        JSONObject resultJson = JSONObject.parseObject(result);

        Integer code;
        if (StringUtil.isNotBlank(resultJson)) {
            code = resultJson.getInteger("code");
            if (code == 200) {
                msg = 1;
            } else {
                msg = 0;
            }
        }
        return msg;

    }


	
	private String sendCode(Map<String, Object> payload, String smsNo){
		final String APIKEY = Global.getValue("apikey");
		final String SECRETKEY = Global.getValue("secretkey");
		final String APIHOST = Global.getValue("sms_apihost");//发送地址
        final String channelNo = Global.getValue("sms_channelNo");//渠道编号
        final String interfaceName = Global.getValue("sms_interfaceName");//接口名称
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        SmsCreditRequest creditRequest = new SmsCreditRequest(APIHOST, header, smsNo);


        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String result = creditRequest.request();

        return result;
	}

	@Override
	public int findUser(String phone) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", phone);
		User user =  userMapper.findSelective(paramMap);
		if (StringUtil.isNotBlank(user)) {
			return 1;
		}
		return 0;
	}

	//同步发短信的方法
	@Override
	public int sendSms(String type, Map<String, Object> data, String phone,Integer ip) {

		if ("dev".equals(Global.getValue("app_environment"))){
			return 1;
		}

		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		search.put("state", "10");
		SmsTpl tpl = smsTplMapper.findSelective(search);
		if (tpl!=null) {
			Map<String, Object> payload = new HashMap<>();
			payload.put("mobile", phone);
			if (createMessage(type,data)!=null){
				payload.put("message", createMessage(type,data));
				String result = sendCode(payload,tpl.getNumber());
				logger.info(result);
				return result(result, phone, type);
			}else {
				return 0;
			}

		}
		return 0;

		boolean isSendVcode =  "overdue".equals(type) || "repayInform".equals(type) || "checkPass".equals(type) || "advanceFiveDayInform".equals(type) || "registerPassword".equals(type);
		if (!isSendVcode) {
			int code = (int) (Math.random() * 9000) + 1000;
			if (data == null) {
				data = new HashMap<String,Object>();
			}
			data.put("code", code);
		}

		String result = sendMsg(type, data, phone);
		logger.info(result);
		return result(result, phone, type,ip);
	}



	//异步发短信方法
	@Override
	public int sendSmsAsyncron(String type, Map<String, Object> data, String phone) {
		if (data == null) {
			data = new HashMap<String,Object>();
		}
		final String type_clone = type;
		final Map<String, Object> data_clone = data;
		final String phone_clone = phone;

		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				if ("dev".equals(Global.getValue("app_environment"))){
					return;
				}
				boolean isSendVcode =  "overdue".equals(type_clone) || "repayInform".equals(type_clone) || "checkPass".equals(type_clone) || "advanceFiveDayInform".equals(type_clone) || "registerPassword".equals(type_clone) || "authWarn".equals(type_clone);
				if (!isSendVcode) {
					int code = (int) (Math.random() * 9000) + 1000;
					data_clone.put("code", code);
				}

				String result = sendMsg(type_clone, data_clone, phone_clone);
				logger.info(result);
				result(result, phone_clone, type_clone,null);
			}
		});

		return 1;
	}

	@Override
	public int listInHourCount(Integer ip,String phone) {
		Map<String, Object> data = new HashMap<>();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY,-1);
		if (ip!=null){
			data.put("ip",ip);
		}else {
			data.put("phone",phone);
		}
		data.put("respTime",c.getTime());
		return smsMapper.hourOrPhoneCount(data);
	}

	private String sendMsg(String type, Map<String, Object> data, String mobile) {
		StringBuilder tplValue = new StringBuilder();
		if (data != null && data.size() > 0) {
			tplValue.append("{");
			
			Set<String> keySet = data.keySet();
			Iterator<String> it = keySet.iterator();
			String key = "";
			while (it.hasNext()) {
				key = it.next();
				tplValue.append(key).append(":").append(data.get(key));
				if (it.hasNext()) {
					tplValue.append(",");
				}
			}
			tplValue.append("}");
		}
        
        Map<String, String> param = new HashMap<String, String>();
        param.put("busi_id","100002");   //"manage":100001  "app":100002
        param.put("busi_key","c33367701511b4f6020ec61ded352059");  //busKey:654321
        param.put("sms_type", type);
        param.put("mobile", mobile);
        param.put("tpl_value", tplValue.toString());
        
        String url = Global.getValue("sms_apiService");
        String result = HttpUtil.doPost(url,param);
        return result;
	}

	private String createMessage(String type,Map<String, Object> map){
		String message  = null;
		if ("overdue".equals(type)) {
			message= ret(type);
			if(map.containsKey("time")&&map.containsKey("platform")&&map.containsKey("loan")&&map.containsKey("overdueDay")&&map.containsKey("amercement")){
				message = message
						.replace("{$time}",StringUtil.isNull(map.get("time")))
						.replace("{$platform}", StringUtil.isNull(map.get("platform")))
						.replace("{$loan}", StringUtil.isNull(map.get("loan")))
						.replace("{$overdueDay}", StringUtil.isNull(map.get("overdueDay")))
						.replace("{$amercement}",StringUtil.isNull(map.get("amercement")));
			}

		}else if ("repayInform".equals(type)) {
			message= ret(type);
			if(map.containsKey("time")&&map.containsKey("platform")&&map.containsKey("loan")) {
				message = message.replace("{$time}", StringUtil.isNull(map.get("time")))
						.replace("{$platform}", StringUtil.isNull(map.get("platform")))
						.replace("{$loan}", StringUtil.isNull(map.get("loan")));
			}
		}else if ("checkPass".equals(type)||"failLoanInform".equals(type)||"loanInform".equals(type)|"lastDayInform".equals(type)) {
			message= ret(type);
			if(map.containsKey("platform")&&map.containsKey("loan")) {
				message = message
						.replace("{$platform}", StringUtil.isNull(map.get("platform")))
						.replace("{$loan}", StringUtil.isNull(map.get("loan")));
			}
		}else if ("advanceFiveDayInform".equals(type)) {
			message= ret(type);
			if(map.containsKey("time")&&map.containsKey("platform")&&map.containsKey("loan")&&map.containsKey("leftDay")) {
				message = message
						.replace("{$platform}", StringUtil.isNull(map.get("platform")))
						.replace("{$leftDay}", StringUtil.isNull(map.get("leftDay")))
						.replace("{$loan}", StringUtil.isNull(map.get("loan")))
						.replace("{$time}", StringUtil.isNull(map.get("time")));
			}
		}else {
			message= ret(type);
			int code = (int) (Math.random() * 9000) + 1000;
			message = message
					.replace("{$code}",code+"");
		}
		return message;
	}

	public void overdue(long borrowId){
		
	}


	public static void main(String[] args) {
		String string =	"15237982970,13893442817,13590367858,13712118023,13736751605,15808508940,18226005378,15715590300,15227985120";
		String[] strings = string.split(",");

//		System.out.println(strings.length);

		ClSmsServiceImpl clSmsService = new ClSmsServiceImpl();
		String APIKEY = "5d3477460c94b7e9a74331eb058d94c7";
		String SECRETKEY = "f7bd58c93d5978670bf72c5013733e5d23716ec1f52352236eba5e3054d8c922";
//		String APIHOST = "https://api.dsdatas.com/sms/api/getSmsParameSend";//发送地址
		String APIHOST = "https://api.dsdatas.com/sms/api/batchSmsSend";
		String channelNo = "CH1945487800";//渠道编号
//		String interfaceName = "movekSimpleInfo";//接口名称
//		String smsNo = "SMS1223706486";
		String interfaceName = "movekSmsSend";
		String smsNo = "SMS0782852771";
		long timestamp = new Date().getTime();
		Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

//		SmsCreditRequest creditRequest = new SmsCreditRequest(APIHOST, header, smsNo);
		BatchSmsCreditRequest batchSmsCreditRequest = new BatchSmsCreditRequest(APIHOST,header,smsNo);
		List<Map<String, Object>> mapList = new ArrayList<>();
//		int vcode = (int) (Math.random() * 9000) + 1000;
		for (int i = 0; i < strings.length; i++) {
			Map<String, Object> payload1 = new HashMap<>();
			payload1.put("mobile", strings[i]);
			payload1.put("message", "亲爱的用户，您在卡牛平台-成长钱包的身份资质已通过审核，可以重新提交借款申请。");
			mapList.add(payload1);
		}



//		Map<String, Object> payload2 = new HashMap<>();
//		payload2.put("mobile", "15827044178");
//		payload2.put("message", "尊敬的用户，您在成长钱包借款申请已放款成功，点击查看详情：www.gov.cn/");
//		mapList.add(payload2);
//		Map<String, Object> payload3 = new HashMap<>();
//		payload3.put("mobile", "15172366265");
//		payload3.put("message", "尊敬的用户，您在成长钱包借款申请已放款成功，点击查看详情：www.gov.cn/");
//		mapList.add(payload3);
//		Map<String, Object> payload4 = new HashMap<>();
//		payload4.put("mobile", "15327732322");
//		payload4.put("message", "尊敬的用户，您在成长钱包借款申请已放款成功，点击查看详情：www.gov.cn/");
//		mapList.add(payload4);
//		Map<String, Object> payload5 = new HashMap<>();
//		payload5.put("mobile", "18162580679");
//		payload5.put("message", "尊敬的用户，您在成长钱包借款申请已放款成功，点击查看详情：www.gov.cn/");
//		mapList.add(payload5);
//		creditRequest.setPayload(payload);
//		creditRequest.signByKey(SECRETKEY);

//		String result = creditRequest.request();
		batchSmsCreditRequest.setPayloadList(mapList);
		batchSmsCreditRequest.signByKey(SECRETKEY);
		String result = batchSmsCreditRequest.request();
		System.out.println(result);
	}


	@Override
	public int insert(Sms record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateById(Sms record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long findTimeDifference(String phone, String type) {
		int countdown = Global.getInt("sms_countdown");
		Map<String,Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		long times = 0;
		if (sms != null) {
			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff < countdown*1000) {
				times = countdown-(diff/1000);
			}else {
				times = 0;
			}
		}
		return times;
	}


	@Override
	public int countDayTime(String phone, String type) {
		String mostTimes = Global.getValue("sms_day_most_times");
		int mostTime = JSONObject.parseObject(mostTimes).getIntValue(type);
		
		Map<String,Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		int times = smsMapper.countDayTime(data);
		
		return mostTime - times;
	}
}*/