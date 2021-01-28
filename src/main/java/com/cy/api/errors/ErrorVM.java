package com.cy.api.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 错误描述View Model
 */
public class ErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "错误代码")
	private String errCode;
	@ApiModelProperty(value = "返回消息")
	private String msg;
	@ApiModelProperty(value = "返回状态", required = true)
	private String retCode;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ApiModelProperty(value = "表单元素验证错误列表")
	private List<FieldErrorVM> fieldErrors;

	public ErrorVM() {
	}

	public ErrorVM(String retCode) {
		this.retCode = retCode;
	}

	public ErrorVM(String errCode, String msg, String retCode) {
		this.errCode = errCode;
		this.msg = msg;
		this.retCode = retCode;
	}

	public ErrorVM(String errCode, String msg, String retCode, List<FieldErrorVM> fieldErrors) {
		this.errCode = errCode;
		this.msg = msg;
		this.retCode = retCode;
		this.fieldErrors = fieldErrors;
	}

	public void add(String objectName, String field, String message) {
		if (fieldErrors == null) {
			fieldErrors = new ArrayList<>();
		}
		fieldErrors.add(new FieldErrorVM(objectName, field, message));
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public List<FieldErrorVM> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorVM> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

    @Override
    public String toString() {
        return "ErrorVM{" +
                "errCode='" + errCode + '\'' +
                ", msg='" + msg + '\'' +
                ", retCode='" + retCode + '\'' +
                ", fieldErrors=" + fieldErrors +
                '}';
    }
}
