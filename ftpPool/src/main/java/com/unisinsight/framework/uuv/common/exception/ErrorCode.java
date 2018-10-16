/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.exception;

/**
 * description 错误码
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public enum ErrorCode {

    /**
     * 基础错误码00开头
     * */
    BASE_RESOURCE_NOT_EXIST("000001", "资源不存在"),
    BASE_RESOURCE_EXISED("000002", "资源已经存在"),
    BASE_DB_CONNECTION_ERROR("000003", "数据库连接异常"),
    BASE_FILE_NOT_EXIST("000004", "文件不存在"),
    BASE_PASSWORD_ERROR("000005", "账号密码错误"),
    BASE_PARAM_VALID_ERROR("000006", "参数校验异常"),

    /**
     * 业务自定义错误码
     * */
    USER_NOT_EXIST("010101", "用户不存在"),
    USER_GENDER_ERROR("010102", "用户性别不合法"),
    USER_TITLE_NOT_EXIST("010103", "职级不存在"),
    USER_POSITION_NOT_EXIST("010104", "岗位不存在"),
    USER_STATUS_ERROR("010105", "用户状态不合法"),
    USER_EXIST("010106", "用户已存在"),

    APP_EXIST("010201","应用已存在"),
    APP_NOT_EXIST("010202", "应用不存在"),
    APP_CATEGORY_NOT_EXIST("010203", "应用分类不存在"),

    ORG_ROOT_EXIST("010301", "组织根节点已经存在"),
    ORG_PARENT_NOT_EXIST("010302", "组织父节点不存在"),
    ORG_NOT_EXIST("010303", "组织不存在"),
    ORG_HAVE_SUBNODE("010304", "组织中存在子节点"),
    ORG_MOVE_ERROR("010307", "移动组织节点异常"),
    ORG_NAME_EXIST("010307", "组织名已经存在"),

    PRIVILEGE_CODE_EXIST("010401", "权限CODE已经存在"),
    PRIVILEGE_NOT_EXIST("010402", "权限不存在"),
    PRIVILEGE_EXIST("010403", "权限已经存在"),

    ROLE_NOT_EXIST("010501", "角色不存在"),
    ROLE_EXIST("010502", "角色已存在"),


    PREFERENCE_NOT_EXIST("010601", "扩展字段不存在"),

    DICTIONARY_EXIST("010701","字典已经存在"),
    DICTIONARY_NOT_EXIST("010702","字典不存在"),

    ;

    ErrorCode(String errorCode, String message){
        this.message = message;
        this.errorCode = BASE + "-" + PROJECT + "-" + errorCode;
    }

    private static final String BASE = "ERROR";
    private static final String PROJECT = "UUV";

    private String message;
    private String errorCode;

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
