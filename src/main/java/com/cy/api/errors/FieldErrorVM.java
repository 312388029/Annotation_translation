package com.cy.api.errors;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 字段错误描述View Model
 */
public class FieldErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "表单提交对象")
	private String objectName;

	@ApiModelProperty(value = "字段名")
	private String field;

	@ApiModelProperty(value = "错误消息")
	private String message;

	public FieldErrorVM(String dto, String field, String message) {
		this.objectName = dto;
		this.field = field;
		this.message = message;
	}

	// TODO 由于json格式转对象时该类没有默认构造函数，导致无法转换，暂先添加
	public FieldErrorVM() {
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    @Override
    public String toString() {
        return "FieldErrorVM{" +
                "objectName='" + objectName + '\'' +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
