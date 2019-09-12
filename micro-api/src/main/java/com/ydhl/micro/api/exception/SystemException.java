package com.ydhl.micro.api.exception;

import com.ydhl.micro.api.enumcode.CodeEnumClass;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;

/**
 * @ClassName SystemException
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:47:21
 * @Version 1.0
 **/
public class SystemException extends Exception {

    private CodeEnumClass codeEnum;
    private String errorMsg;

    public SystemException(CodeEnumClass codeEnum) {
        super(codeEnum.getEnumCode() + ":" + codeEnum.getEnumMsg());
        this.codeEnum = codeEnum;
        this.errorMsg = codeEnum.getEnumMsg();
    }

    public SystemException(CodeEnumClass codeEnum, Throwable cause) {
        super(codeEnum.getEnumCode() + ":" + codeEnum.getEnumMsg(), cause);
        this.codeEnum = codeEnum;
        this.errorMsg = codeEnum.getEnumMsg();
    }

    public SystemException(CodeEnumClass codeEnum, String errorMsg) {
        super(codeEnum.getEnumCode() + ":" + errorMsg);
        this.codeEnum = codeEnum;
        this.errorMsg = errorMsg;
    }

    public SystemException(CodeEnumClass codeEnum, String errorMsg, Throwable cause) {
        super(codeEnum.getEnumCode() + ":" + errorMsg, cause);
        this.codeEnum = codeEnum;
        this.errorMsg = errorMsg;
    }

    public SystemException(String errorMsg) {
        super(errorMsg);
        this.codeEnum = GlobalCodeEnum.ERR_COMMON;
        this.errorMsg = errorMsg;
    }

    public SystemException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.codeEnum = GlobalCodeEnum.ERR_COMMON;
        this.errorMsg = errorMsg;
    }

    public CodeEnumClass getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(CodeEnumClass codeEnum) {
        this.codeEnum = codeEnum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
