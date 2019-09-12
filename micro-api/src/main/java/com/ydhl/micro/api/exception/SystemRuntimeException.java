package com.ydhl.micro.api.exception;

import com.ydhl.micro.api.enumcode.CodeEnumClass;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;

/**
 * @ClassName SystemRuntimeException
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:47:07
 * @Version 1.0
 **/
public class SystemRuntimeException extends RuntimeException {

    private CodeEnumClass codeEnum;
    private String errorMsg;

    public SystemRuntimeException(CodeEnumClass codeEnum) {
        super(codeEnum.getEnumCode() + ":" + codeEnum.getEnumMsg());
        this.codeEnum = codeEnum;
        this.errorMsg = codeEnum.getEnumMsg();
    }

    public SystemRuntimeException(CodeEnumClass codeEnum, Throwable cause) {
        super(codeEnum.getEnumCode() + ":" + codeEnum.getEnumMsg(), cause);
        this.codeEnum = codeEnum;
        this.errorMsg = codeEnum.getEnumMsg();
    }

    public SystemRuntimeException(CodeEnumClass codeEnum, String errorMsg) {
        super(codeEnum.getEnumCode() + ":" + errorMsg);
        this.codeEnum = codeEnum;
        this.errorMsg = errorMsg;
    }

    public SystemRuntimeException(CodeEnumClass codeEnum, String errorMsg, Throwable cause) {
        super(codeEnum.getEnumCode() + ":" + errorMsg, cause);
        this.codeEnum = codeEnum;
        this.errorMsg = errorMsg;
    }

    public SystemRuntimeException(String errorMsg) {
        super(errorMsg);
        this.codeEnum = GlobalCodeEnum.ERR_COMMON;
        this.errorMsg = errorMsg;
    }

    public SystemRuntimeException(String errorMsg, Throwable cause) {
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
