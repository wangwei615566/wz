
package com.wz.cashloan.core.common.context;



/**
 * 公用常量类定义
 * 
 * @version 1.0
 * @author 吴国成f
 * @created 2014年9月23日 下午2:17:20
 */
public class Constant {

	/** session用户 **/
	public static final String SESSION_OPERATOR = "session_operator";

	/**  角色session 暂用*/
	public static final String ROLEID = "roleId";
	
	public static final String INSERT = "create";
	
	public static final String UPDATE = "update";
	
	public static final String ACCESSCODE = "accessCode";
	
	public static final String RESPONSE_CODE = "code";

	public static final String RESPONSE_CODE_MSG = "msg";
	
	public static final String RESPONSE_DATA = "data";
	
	public static final String RESPONSE_DATA_TOTAL = "total";
	
	public static final String RESPONSE_DATA_TOTALCOUNT = "totalCount";
	
	public static final String RESPONSE_DATA_CURRENTPAGE = "currentPage";
	
	public static final String RESPONSE_DATA_PAGE = "page";
	
	
	public static final String OPERATION_SUCCESS = "操作成功";
	
	public static final String OPERATION_FAIL = "操作失败";
	
	public static final int SIGN_FAIL = 99; // 验签失败

	public static final int SUCCEED_CODE_VALUE = 200; // 成功
	
	public static final int FAIL_CODE_PARAM_INSUFFICIENT = 300;	//业务异常
	
	public static final int FAIL_CODE_VALUE = 400; // 缺少参数

	public static final int OTHER_CODE_VALUE = 500; // 系统异常
	
	public static final int FAIL_CODE_PWD = 401; // 交易密码错误
	
	public static final int PERM_CODE_VALUE = 403; // 无权限访问
	
	public static final int NOSESSION_CODE_VALUE = 800; // session失效
	
	public static final int CLIENT_EXCEPTION_CODE_VALUE = 998; // 连接异常（除请求超时）
	
	public static final int TIMEOUT_CODE_VALUE = 999; // 请求超时
	



	
	/**武汉成长无限网络科技有限公司相关参数 START*/
	public static final String CZWX_LENDER = "武汉成长无限网络科技有限公司";
	
	public static final String RESPONSE_RETCODE = "retCode";//返回状态码
	
	public static final String RESPONSE_RETMSG = "retMsg";//返回消息
	
	public static final String RESPONSE_INFO = "info";//返回数据
	
	public static final String SUCCEED_KNCODE_VALUE = "00000"; // 成功
	
	public static final String FAIL_KNCODE_PARAM = "20001"; // 参数错误
	
	public static final String FAIL_KNCODE_EXP = "10001"; // 服务器异常
	
	public static final String FAIL_BSCODE_EXP = "10002"; // 业务异常
	
	public static final String FAIL_KNCODE_SIGN = "99999"; //验签错误
	
	public static final String FAIL_KNCODE_USER = "30013"; //用户信息不存在
	
	public static final String FAIL_KNCODE_IDNO = "30002"; //身份证号码已经存在
	
	public static final String FAIL_KNCODE_PHONE = "30003"; //手机号码已经存在
	
	public static final String FAIL_KNCODE_BANKCARD = "30004"; //银行卡号已经存在
	
	public static final String FAIL_KNCODE_VERIFYCODE= "20008"; //验证码错误
	
	
}

