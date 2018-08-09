package com.wz.cashloan.core.model;

import java.util.Date;

public class User {
    private Long id;

    private String loginName;

    private String loginPwd;

    private Date loginpwdModifyTime;

    private String registerClient;

    private String invitationCode;

    private String deviceId;

    private Long levelId;

    private String registerIp;

    private String loginIp;

    private byte state;

    private byte vipState;

    private Date updateTime;

    private Date createTime;

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public Date getLoginpwdModifyTime() {
        return loginpwdModifyTime;
    }

    public void setLoginpwdModifyTime(Date loginpwdModifyTime) {
        this.loginpwdModifyTime = loginpwdModifyTime;
    }

    public String getRegisterClient() {
        return registerClient;
    }

    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient == null ? null : registerClient.trim();
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte getVipState() {
        return vipState;
    }

    public void setVipState(byte vipState) {
        this.vipState = vipState;
    }
}