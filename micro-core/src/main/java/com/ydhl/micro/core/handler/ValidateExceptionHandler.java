package com.ydhl.micro.core.handler;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.exception.SystemException;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.core.consts.Constants;
import com.ydhl.micro.core.util.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @ClassName ValidateExceptionHandler
 * @Description 全局异常拦截器
 * @Author yangll
 * @Date 2019-9-10 13:44:30
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class ValidateExceptionHandler {

    @Autowired
    private CacheHelper cacheHelper;

    /**
     * @param :
     * @return java.lang.String :
     * @Description //全局异常处理
     * @Author yangll
     * @Date 2019-9-10 13:45:21
     **/
    @ExceptionHandler(value = {Exception.class})
    public HttpResultDTO exceptionHandler(Exception e) {
        log.error("全局异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_COMMON, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_COMMON));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public HttpResultDTO runtimeExceptionHandler(RuntimeException e) {
        log.error("全局异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_COMMON, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_COMMON));
    }

    /**
     * @param :
     * @return java.lang.String :
     * @Description //不支持的调用
     * @Author yangll
     * @Date 2019-9-10 13:45:30
     **/
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public HttpResultDTO methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error("异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_COMMON, "不支持[" + e.getMethod() + "]方式调用");
    }

    // 400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public HttpResultDTO requestNotReadable(HttpMessageNotReadableException ex) {
        log.error("无法解析请求参数400！", ex);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_NOTREADABLE, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_NOTREADABLE));
    }

    // 400错误
    // 参数类型不匹配
    // getPropertyName()获取数据类型不匹配参数名称
    // getRequiredType()实际要求客户端传递的数据类型
    @ExceptionHandler({TypeMismatchException.class})
    public HttpResultDTO requestTypeMismatch(TypeMismatchException ex) {
        log.error("参数类型不匹配400！", ex);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, "参数类型不匹配"));
    }

    // 400错误
    // 缺少参数异常
    // getParameterName() 缺少的参数名称
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public HttpResultDTO requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error("缺少必要参数400！", ex);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_MISSING, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_MISSING, ex.getParameterName()));
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public HttpResultDTO dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_INCOMPLETE, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_INCOMPLETE));
    }

    @ExceptionHandler(value = {TypeMismatchDataAccessException.class})
    public HttpResultDTO typeMismatchDataAccessExceptionHandler(TypeMismatchDataAccessException e) {
        log.error("TypeMismatchDataAccessException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_TYPE_MISMATCH, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_TYPE_MISMATCH));
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public HttpResultDTO unauthorizedExceptionHandler(UnauthorizedException e) {
        log.error("未授权异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_UNAUTHORIZED, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_UNAUTHORIZED));
    }

    @ExceptionHandler(value = {UnauthenticatedException.class})
    public HttpResultDTO unauthenticatedExceptionHandler(UnauthenticatedException e) {
        log.error("未认证异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_UNAUTHENTICATED, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_UNAUTHENTICATED));
    }

    // 大小超过指定大小，抛异常
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    public HttpResultDTO resolveFileUploadException(MaxUploadSizeExceededException e) {
        log.error("上传附件异常：" + e.getMessage());
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_UPLOAD_FILESIZE_MAX, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_UPLOAD_FILESIZE_MAX, Constants.MAX_FILE_SIZE, Constants.MAX_REQUEST_SIZE));
    }

    @ExceptionHandler(value = WebExchangeBindException.class)
    public HttpResultDTO webExchangeBindExceptionHandler(WebExchangeBindException e) {

        List<ObjectError> allErrors = e.getAllErrors();
        String defaultMessage = "";
        for (ObjectError objectError : allErrors) {
            defaultMessage += objectError.getDefaultMessage() + ";";
        }

        log.error("WebExchangeBindException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, defaultMessage));
    }

    @ExceptionHandler(value = ConstraintDeclarationException.class)
    public HttpResultDTO constraintDeclarationExceptionHandler(ConstraintDeclarationException e) {
        log.error("ConstraintDeclarationException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, e.getMessage()));
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public HttpResultDTO bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        log.error("BindException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, message));
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpResultDTO constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        log.error("ConstraintViolationException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, message));
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResultDTO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        log.error("MethodArgumentNotValidException异常日志打印：", e);
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_PARAM_BIND, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_PARAM_BIND, message));
    }

    @ExceptionHandler(value = SystemException.class)
    public HttpResultDTO systemExceptionHandler(SystemException e) {
        log.error("SystemException异常日志打印：", e);
        return HttpResultDTO.fail(e.getCodeEnum(), e.getErrorMsg());
    }

    @ExceptionHandler(value = SystemRuntimeException.class)
    public HttpResultDTO systemRuntimeExceptionHandler(SystemRuntimeException e) {
        log.error("SystemRuntimeException异常日志打印：", e);
        return HttpResultDTO.fail(e.getCodeEnum(), e.getErrorMsg());
    }

}
