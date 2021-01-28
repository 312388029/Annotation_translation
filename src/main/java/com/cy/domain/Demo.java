package com.cy.domain;

import com.cy.common.annotation.Translate;

public class Demo {

    @Translate(targetTab = "t_sequence",targetCode = "id",targetValue = "use_for" , targetField = "targetField")
    private Integer testField;

    private String targetField;

    public Integer getTestField() {
        return testField;
    }

    public void setTestField(Integer testField) {
        this.testField = testField;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "testField=" + testField +
                ", targetField='" + targetField + '\'' +
                '}';
    }
}
