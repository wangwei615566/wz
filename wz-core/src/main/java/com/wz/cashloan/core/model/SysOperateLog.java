package com.wz.cashloan.core.model;

import java.io.Serializable;

/**
 * Created by czwx on 2017/9/5.
 */
public class SysOperateLog implements Serializable {
    private Long id;
    private Long OperId;
    private String OperName;
    private String params;
    private String createTime;
    private String actionName;
    private String actionUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperId() {
        return OperId;
    }

    public void setOperId(Long operId) {
        OperId = operId;
    }

    public String getOperName() {
        return OperName;
    }

    public void setOperName(String operName) {
        OperName = operName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
}
