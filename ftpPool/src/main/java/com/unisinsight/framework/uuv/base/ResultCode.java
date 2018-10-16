package com.unisinsight.framework.uuv.base;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author  tanjiquan [KF.tanjiquan@h3c.com]
 * @date    2018/9/6 12:42
 * @since   1.0
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS(1,"成功"),
    /** 失败 */
    FAIL(0,"失败"),
    /** 接口不存在或地址错误 */
    NOT_FOUND(-1,"接口不存在或地址错误"),
    /** 入参为空 */
    REQISNULL(-2,"入参为空"),
    /** 返回值为空 */
    RESPISNULL(-3,"返回值为空"),
    /** 空指针异常 */
    NULL_ERROR(-10,"空指针异常"),
    /** IO异常 */
    IO_ERROR(-11,"IO异常"),
    /** 数据库操作异常 */
    DB_ERROR(-13,"数据库操作异常"),
    /** 文件流异常 */
    FILE_ERROR(-14,"文件流异常"),
    /** 参数校验异常 */
    PARA_ERROR(-15,"参数校验异常"),
    /** 服务器内部错误 */
    INTERNAL_SERVER_ERROR(50,"服务器内部错误")
    ;

    private Integer errorCode;
    private String errorMsg;

    ResultCode(int errorCode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ResultCode getByCode(Integer errorCode){
        for(ResultCode rc:values()){
            if(rc.getErrorCode().equals(errorCode)){
                return rc;
            }
        }
        return null;
    }

    public static String getMsgByCode(Integer errorCode){
        if( errorCode == null ){
            return "";
        }
        ResultCode resultCode = getByCode( errorCode );
        return resultCode == null ? "" : resultCode.getErrorMsg();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
