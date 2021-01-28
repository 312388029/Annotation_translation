package com.cy.domain;

import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_sequence")
public class Sequence implements Serializable {

    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable = false)
    private Integer id;

    /**
     * 键名
     */
    private String key;

    /**
     * 值
     */
    private String val;

    /**
     * 应用于何处
     */
    @Column(name = "use_for")
    private String useFor;

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
     * 获取键名
     *
     * @return key - 键名
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置键名
     *
     * @param key 键名
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取值
     *
     * @return val - 值
     */
    public String getVal() {
        return val;
    }

    /**
     * 设置值
     *
     * @param val 值
     */
    public void setVal(String val) {
        this.val = val;
    }

    /**
     * 获取应用于何处
     *
     * @return use_for - 应用于何处
     */
    public String getUseFor() {
        return useFor;
    }

    /**
     * 设置应用于何处
     *
     * @param useFor 应用于何处
     */
    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", key=").append(key);
        sb.append(", val=").append(val);
        sb.append(", useFor=").append(useFor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}