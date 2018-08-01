package com.wz.cashloan.core.common.context;

/**
 * 
 * TODO 后台列表数据导出 常量
 * @author lyang
 * @date 2017年4月13日 下午14:40:52
 */
public final class ExportConstant {
	
	/** 导出暂定为5000条，如有需求，酌情更改*/
	/*** 分页起始*/
	public static final int STRAT_LIMIT = 0;
	
	/*** 分页结束*/
	public static final int END_LIMIT = 5000;
	
	/** 还款记录导出 表头*/
	public static final String[] EXPORT_REPAYLOG_LIST_HEARDERS = {"真实姓名","手机号码","订单号", "借款金额(元)", "应还逾期罚金(元)", "已还逾期罚金(元)", "逾期天数","逾期管理费(元)", "应还本息(元)","减免金额(元)", 
		"已还款金额(元)", "还款账号", "流水号", "还款方式", "应还款日期", "实际还款日期","操作日期"};
	/** 还款记录导出 属性数组*/
	public static final String[] EXPORT_REPAYLOG_LIST_FIELDS = {"realName","phone","orderNo", "borrowAmount", "repayPenalty", "penaltyAmout", "penaltyDay", "penaltyManageAmt","repayAmount",
			"derateAmount","repayLogAmount", "repayAccount", "serialNumber", "repayWay", "repayPlanTime", "repayTime","createTime"};
	
	/** 还款记录结算导出 表头*/
	public static final String[] EXPORT_REPAYLOG_SETTLE_LIST_HEARDERS = {"姓名","手机号","流水号", "借款天数", "本金", "服务费", "利息", "逾期罚息", 
		"逾期管理费","减免金额","减免备注", "还款金额", "借款时间", "还款时间","渠道","还款方式"};
	/** 还款结算记录导出 属性数组*/
	public static final String[] EXPORT_REPAYLOG__SETTLE_LIST_FIELDS = {"realName","phone","serialNumber", "timeLimit", "borrowAmount", "serviceFee", "interest", "repayPenalty",
			"penaltyManageAmt", "derateAmount","derateRemark", "repayLogAmount", "createTime", "repayTime","channelId","repayWay"};
	
	/** 借款订单导出 表头*/
	public static final String[] EXPORT_BORROW_LIST_HEARDERS = {"真实姓名","手机号","订单号","借款金额(元)","借款期限","订单生成时间","综合费用","应还时间","应还金额(元)",
		"实际到账金额","实际还款金额(元)","注册客户端","渠道","订单状态"};
	/** 借款订单导出 属性数组*/
	public static final String[] EXPORT_BORROW_LIST_FIELDS = {"realName", "phone", "orderNo", "amount","timeLimit","createTime", "fee",
		"sRepay","penaltyAmoutCount","realAmount","repayAmount", "registerClient", "channelName", "stateStr"};
	
	/** 支付记录导出 表头*/
	public static final String[] EXPORT_PAYLOG_LIST_HEARDERS = {"收款人姓名","手机号码","金额","收款银行卡","借款时间","打款时间","业务","渠道","状态"};
	/** 支付记录导出 属性数组*/
	public static final String[] EXPORT_PAYLOG_LIST_FIELDS = {"realName","loginName","amount","cardNo","loanTime","updateTime","scenesStr","channelName","stateStr"};
	
	/** 支付对账记录导出 表头*/
	public static final String[] EXPORT_PAYCHECK_LIST_HEARDERS = {"订单号","支付方式","订单金额","支付方订单金额","错误类型","对账记录添加时间","支付业务","处理方式","处理结果"};
	/** 支付对账记录导出 属性*/
	public static final String[] EXPORT_PAYCHECK_LIST_FIELDS = {"orderNo","payTypeStr","orderAmount","realPayAmount","typeStr","processTime",
		"scenesStr","processWayStr","processResultStr",};
	
	/** 已逾期订单导出 表头*/
	public static final String[] EXPORT_OVERDUE_LIST_HEARDERS = {"真实姓名","手机号","订单号","借款金额(元)","借款期限","订单生成时间","综合费用","居间服务费","信息认证费","利息",
		"实际到账金额","订单状态","借款地址","放款时间","逾期天数","逾期罚金(元)","逾期等级"};
	/** 已逾期订单导出 属性数组*/
	public static final String[] EXPORT_OVERDUE_LIST_FIELDS = {"realName", "phone", "orderNo", "amount","timeLimit","createTime", "fee",
		"serviceFee","infoAuthFee","interest","realAmount", "state", "address", "loanTime","penaltyDay","penaltyAmout","level"};
	
	/** 已坏账订单导出 表头*/
	public static final String[] EXPORT_BADDEBT_LIST_HEARDERS = {"真实姓名","手机号","订单号","借款金额(元)","借款期限","订单生成时间","综合费用","居间服务费","信息认证费","利息",
		"实际到账金额","订单状态","借款地址","放款时间","实际还款时间","实际还款金额(元)","逾期天数","逾期罚金(元)"};
	/** 已坏账订单导出 属性数组*/
	public static final String[] EXPORT_BADDEBT_LIST_FIELDS = {"realName", "phone", "orderNo", "amount","timeLimit","createTime", "fee",
		"serviceFee","infoAuthFee","interest","realAmount", "state", "address", "loanTime", "repayTime", "repayAmount","penaltyDay","penaltyAmout"};
	
	/** 催收订单导出 表头*/
	public static final String[] EXPORT_REPAYORDER_LIST_HEARDERS = {"真实姓名","手机号码","金额","借款时间","预计还款时间","逾期天数","逾期等级","罚息","催收人","订单状态"};
	/** 催收订单导出 属性*/
	public static final String[] EXPORT_REPAYORDER_LIST_FIELDS = {"borrowName","phone","amount","borrowTime","repayTime","penaltyDay","level",
		"penaltyAmout","userName","state"};

	/** 已还统计导出 表头*/
	public static final String[] EXPORT_REPAYMENTSTATISTICS_LIST_HEARDERS = {"应还款日期","应还款笔数","应还款金额","未还款笔数","未还款金额"};
	/** 已还统计导出 属性*/
	public static final String[] EXPORT_REPAYMENTSTATISTICS_LIST_FIELDS = {"repayTime","repayCount","repayAmount","noRepayCount","noRepayAmount",};

	/** 导出风控数据统计（动态） 表头*/
	public static final String[] EXPORT_RISKCONTROLDYNAMICDATA_LIST_HEARDERS = {"日期","当日应还笔数","当日应还总本金","当日逾期笔数","当日逾期总本金","当日已还款笔数","当日已还款总本金","当日提前还款总笔数","当日提前还款总金额","当日逾期笔数比率","当日逾期本金比率"};
	/** 风控数据统计（动态）导出 属性*/
	public static final String[] EXPORT_RISKCONTROLDYNAMICDATA_LIST_FIELDS = {"createTime","dateShouldPayCount","dateShouldPayMoney","dateOverPayCount","dateOverPayMoney","dateHadPayCount","dateHadPayMoney","dateAdvancePayCount","dateAdvancePayMoney","dateOverPayCountRatio","dateOverPayMoneyRatio"};

	/** 催收反馈导出 表头*/
	public static final String[] EXPORT_URGELOG_LIST_HEARDERS = {"真实姓名","手机号码","金额","借款时间","预计还款时间","逾期天数","逾期等级","罚息","催收人","订单状态",
		"承诺还款时间","催收反馈","催收时间"};
	/** 催收反馈导出 属性*/
	public static final String[] EXPORT_URGELOG_LIST_FIELDS = {"borrowName","phone","amount","borrowTime","repayTime","penaltyDay","level",
		"penaltyAmout","userName","state","promiseTime","remark","createTime"};
	
	/** 同盾审核记录导出 表头*/
	public static final String[] EXPORT_TONGDUNLOG_LIST_HEARDERS = {"真实姓名","手机号码","借款订单号","借款金额","风险报告编码","申请状态",
		"提交审核报告结果编码","提交审核返回信息","提交审核报告时间","查询审核报告结果编码","查询审核报告信息","风险结果","风险分数","查询审核报告时间"};
	/** 同盾审核记录导出 属性*/
	public static final String[] EXPORT_TONGDUNLOG_LIST_FIELDS = {"realName","phone","borrowNo","amount","reportId","stateStr",
		"submitCode","submitParams","createTime","queryCode","queryParams","rsState","rsScore","updateTime"};
	
	/** 催收订单导出 表头*/
	public static final String[] EXPORT_COLLECTION_SUCCESS_COUNT_LIST_HEARDERS = {"日期","逾期总订单数","当前逾期订单数","当日回款成功数","截止当前回款成功数","坏账数","当日回款率(%)","截止当前回款率(%)","催收记录次数"};
	/** 催收订单导出 属性*/
	public static final String[] EXPORT_COLLECTION_SUCCESS_COUNT_LIST_FIELDS = {"createTimeStr","orderCount","newOrderCount","orSuccessCount","successCount","failCount","orBackRate",
		"backRate","urgeRecordCount"};
	/** 逾期订单报表导出 表头*/
	public static final String[] EXPORT_COLLECTION_OVERDUE_ORDER_LIST_HEARDERS = {"身份证号","姓名","手机号","本金","逾期管理费","逾期罚金","欠款总额","借款时间","借款周期","约定还款时间","逾期天数","银行卡号",
			"开户行","紧急联系人","手机联系人","借款所在地","渠道(1.卡牛 2.APP)"};
	/** 逾期订单报表导出 属性*/
	public static final String[] EXPORT_COLLECTION_OVERDUE_ORDER_LIST_FIELDS = {"idNo","realName","phone","realAmount","penaltyManageAmt","penaltyAmount","countAmount","createTime","timeLimit",
		"repayTime","penaltyDay","cardNo","bank","urgentPhoneName","phoneName","address","channelId"};
	/** 风控数据统计导出 表头*/
	public static final String[] EXPORT_RCDATA_STATISTICS_LIST_HEARDERS = {"日期","渠道","当日应还笔数","当日应还总本金", "当日逾期笔数", "当日逾期总本金", "当日已还款笔数", "当日已还款总本金", "当日提前还款笔数", 
		"当日提前还款总金额", "当日逾期笔数比率", "当日逾期本金比率", "应还总笔数","应还总本金","已还款总笔数","已还款总本金","提前还款总笔数","提前还款总金额","逾期总笔数","逾期总本金","坏账总笔数","坏账总本金","逾期笔数比率",
		"逾期本金比率","D7逾期笔数","D7逾期比率","D15逾期笔数","D15逾期比率","M1逾期笔数","M1逾期笔数比率","M2逾期笔数","M2逾期笔数比率","M3逾期笔数","M3逾期笔数比率"};
	/** 风控数据统计导出 属性数组*/
	public static final String[] EXPORT_RCDATA__STATISTICS_LIST_FIELDS = {"createTime","channelName","dateShouldPayCount","dateShouldPayMoney", "dateOverPayCount", "dateOverPayMoney", "dateHadPayCount", "dateHadPayMoney", "dateAdvancePayCount", 
			"dateAdvancePayMoney", "dateOverPayCountRatio", "dateOverPayMoneyRatio", "shouldPayCount","shouldPayMoney","hadPayCount","hadPayMoney","advancePayCount","advancePayMoney","overPayCount","overPayMoney","noPayCount","noPayMoney","overPayCountRatio",
			"overPayMoneyRatio","dsevenOverPayCount","dsevenOverPayRatio","dfifteenOverPayCount","dfifteenOverPayRatio","moneOverPayCount","moneOverPayCountRatio","mtwoOverPayCount","mtwoOverPayCountRatio","mthreeOverPayCount","mthreeOverPayCountRatio"};
}
