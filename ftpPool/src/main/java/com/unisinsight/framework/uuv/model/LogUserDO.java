/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.model;

import java.util.Date;
import javax.persistence.*;

/**
 * 用户日志实体类
 *
 * @author tangang [tan.gang@h3c.com]
 * @date 2018/9/7 10:05
 * @since 1.0
 */
@Table(name = "log.log_user")
public class LogUserDO {
    /**
     * 日志GUID主键
     */
    @Id
    @Column(name = "log_id")
    private Object logId;

    /**
     * 日志级别: info, warning, error, fault
     */
    @Column(name = "log_level")
    private String logLevel;

    /**
     * 日志简要标题
     */
    @Column(name = "log_title")
    private String logTitle;

    /**
     * 日志产生的日期和时间
     */
    @Column(name = "log_time")
    private Date logTime;

    /**
     * 日志来源, AppName, 如UUV统一用户试图
     */
    private String source;

    /**
     * 操作类别: 如: login, logoff, add, delete, update
     */
    private String action;

    /**
     * 用户唯一标识
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 关键字
     */
    @Column(name = "key_word")
    private String keyWord;

    /**
     * 用户IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 日志详情
     */
    private String details;

    /**
     * 日志追溯码
     */
    @Column(name = "trace_id")
    private Integer traceId;

    /**
     * 获取日志GUID主键
     *
     * @return log_id - 日志GUID主键
     */
    public Object getLogId() {
        return logId;
    }

    /**
     * 设置日志GUID主键
     *
     * @param logId 日志GUID主键
     */
    public void setLogId(Object logId) {
        this.logId = logId;
    }

    /**
     * 获取日志级别: info, warning, error, fault
     *
     * @return log_level - 日志级别: info, warning, error, fault
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * 设置日志级别: info, warning, error, fault
     *
     * @param logLevel 日志级别: info, warning, error, fault
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * 获取日志简要标题
     *
     * @return log_title - 日志简要标题
     */
    public String getLogTitle() {
        return logTitle;
    }

    /**
     * 设置日志简要标题
     *
     * @param logTitle 日志简要标题
     */
    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    /**
     * 获取日志产生的日期和时间
     *
     * @return log_time - 日志产生的日期和时间
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * 设置日志产生的日期和时间
     *
     * @param logTime 日志产生的日期和时间
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * 获取日志来源, AppName, 如UUV统一用户试图
     *
     * @return source - 日志来源, AppName, 如UUV统一用户试图
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置日志来源, AppName, 如UUV统一用户试图
     *
     * @param source 日志来源, AppName, 如UUV统一用户试图
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取操作类别: 如: login, logoff, add, delete, update
     *
     * @return action - 操作类别: 如: login, logoff, add, delete, update
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置操作类别: 如: login, logoff, add, delete, update
     *
     * @param action 操作类别: 如: login, logoff, add, delete, update
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取用户唯一标识
     *
     * @return user_code - 用户唯一标识
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户唯一标识
     *
     * @param userCode 用户唯一标识
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取关键字
     *
     * @return key_word - 关键字
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 设置关键字
     *
     * @param keyWord 关键字
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * 获取用户IP地址
     *
     * @return ip_address - 用户IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置用户IP地址
     *
     * @param ipAddress 用户IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取日志详情
     *
     * @return details - 日志详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置日志详情
     *
     * @param details 日志详情
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取日志追溯码
     *
     * @return trace_id - 日志追溯码
     */
    public Integer getTraceId() {
        return traceId;
    }

    /**
     * 设置日志追溯码
     *
     * @param traceId 日志追溯码
     */
    public void setTraceId(Integer traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "LogUserDO{" +
                "logId=" + logId +
                ", logLevel='" + logLevel + '\'' +
                ", logTitle='" + logTitle + '\'' +
                ", logTime=" + logTime +
                ", source='" + source + '\'' +
                ", action='" + action + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", details='" + details + '\'' +
                ", traceId=" + traceId +
                '}';
    }
}