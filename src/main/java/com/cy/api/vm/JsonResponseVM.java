package com.cy.api.vm;

import com.cy.api.errors.ErrorVM;

import io.swagger.annotations.ApiModelProperty;

/**
 * 标准接口返回格式
 */
public class JsonResponseVM {

    @ApiModelProperty(value = "响应头对象", required = true)
    private ErrorVM head;

    @ApiModelProperty(value = "响应主体对象")
    private Object body;

    public JsonResponseVM() {
    }

    public JsonResponseVM(ErrorVM head) {
        this.head = head;
    }

    public JsonResponseVM(ErrorVM head, Object body) {
        this.head = head;
        this.body = body;
    }

    public ErrorVM getHead() {
        return head;
    }

    public void setHead(ErrorVM head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "JsonResponseVM{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
