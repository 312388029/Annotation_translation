package com.cy.domain.vo;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "section")
public class SectionVo implements Serializable {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable = false)
    private Integer id;

    /**
     * 状态(1：启用，0：禁用)
     */
    private String status;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 岗位细则
     */
    private String rules;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 部门人数
     */
    @Column(name = "number")
    private Integer number;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取状态(1：启用，0：禁用)
     *
     * @return status - 状态(1：启用，0：禁用)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态(1：启用，0：禁用)
     *
     * @param status 状态(1：启用，0：禁用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取岗位名称
     *
     * @return name - 岗位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置岗位名称
     *
     * @param name 岗位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取岗位细则
     *
     * @return rules - 岗位细则
     */
    public String getRules() {
        return rules;
    }

    /**
     * 设置岗位细则
     *
     * @param rules 岗位细则
     */
    public void setRules(String rules) {
        this.rules = rules;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SectionVo{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", rules='" + rules + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", number=" + number +
                '}';
    }
}