package com.cy.common.constants;

/**
 * 错误代码表
 */
public final class ErrorConstants {

	private ErrorConstants() {
	}

	// 系统错误 10开头
	public static final String ERR_INTERNAL_SERVER_ERROR = "服务器内部错误";
	public static final String ERR_INTERNAL_SERVER_ERROR_CODE = "1000";
	public static final String ERR_CONCURRENCY_FAILURE = "并发错误";
	public static final String ERR_CONCURRENCY_FAILURE_CODE = "1001";
	public static final String ERR_ACCESS_DENIED = "请求被拒绝";
	public static final String ERR_ACCESS_DENIED_CODE = "1002";
	public static final String ERR_CONTENT_TYPE_NOT_SUPPORTED = "内容类型不支持";
	public static final String ERR_CONTENT_TYPE_NOT_SUPPORTED_CODE = "1003";
	public static final String ERR_VALIDATION = "校验错误";
	public static final String ERR_VALIDATION_CODE = "1004";
	public static final String ERR_PARAMETER_VALIDATION = "参数校验错误";
	public static final String ERR_PARAMETER_VALIDATION_CODE = "1005";
	public static final String ERR_METHOD_NOT_SUPPORTED = "http请求方法不支持";
	public static final String ERR_METHOD_NOT_SUPPORTED_CODE = "1006";
	public static final String ERR_INTERNAL_SYSTEM_INTERFACE_CALL_FAILURE = "内部系统接口调用失败";
	public static final String ERR_INTERNAL_SYSTEM_INTERFACE_CALL_FAILURE_CODE = "1007";
	public static final String ERR_JSON_SYNTAX_NOT_VALID = "Json数据不合法";
	public static final String ERR_JSON_SYNTAX_NOT_VALID_CODE = "1008";
	public static final String ERR_REQUEST_NOT_VALID = "非法请求,签名验证失败";
	public static final String ERR_REQUEST_NOT_VALID_CODE = "1009";
	public static final String ERR_VERSION_VAILD_FAIL = "app当前版本不存在或无法匹配";
	public static final String ERR_VERSION_VAILD_FAIL_CODE = "1010";
	public static final String ERR_FILE_UPLOAD_FAIL = "文件上传失败";
	public static final String ERR_FILE_UPLOAD_FAIL_CODE = "1011";
	public static final String ERR_FILE_UPLOAD_SIZE_FAIL = "文件大小超过限制(1M)";
	public static final String ERR_FILE_UPLOAD_SIZE_FAIL_CODE = "1012";
	public static final String ERR_GEOCOD_FAIL = "获取定位信息失败";
	public static final String ERR_GEOCOD_FAIL_CODE = "1013";
	public static final String ERR_PARAM_SPECIAL_FAIL = "请求中包含非法字符";
	public static final String ERR_PARAM_SPECIAL_FAIL_CODE = "1014";
	public static final String ERR_NET_FAIL = "网络异常";
	public static final String ERR_NET_FAIL_CODE = "1015";
	public static final String ERR_LOGIN_TOKEN_VAILD = "登录已过期，请重新登录";
	public static final String ERR_LOGIN_TOKEN_VAILD_CODE = "1016";
	public static final String ERR_CAMPAIGN_NOT_EXIST = "活动不存在";
	public static final String ERR_CAMPAIGN_NOT_EXIST_CODE = "1017";
	public static final String ERR_CAMPAIGN_EXIPRY = "当前活动已下架";
	public static final String ERR_CAMPAIGN_EXIPRY_CODE = "1018";
	public static final String ERR_MONITOR_VALIDATE_FAIL = "mongo存储数据无效";
	public static final String ERR_MONITOR_VALIDATE_FAIL_CODE = "1019";

}
