package com.ronin.base.advice;

import com.ronin.basemodel.exception.ServiceException;
import com.ronin.basemodel.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @author lizelong
 * @date Created on 2020/6/23 13:06
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 业务异常处理
     *
     * @param ex 异常
     * @return 封装响应
     */
    @ExceptionHandler(ServiceException.class)
    public Result<?> bizException(ServiceException ex) {
        return Result.error(ex.getCode(), ex.getMessage(), null);
    }

    /**
     * 请求参数校验异常处理
     *
     * @param ex 异常
     * @return 封装响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return Optional.of(bindingResult.getAllErrors())
                .map(er -> er.stream().map(ObjectError::getDefaultMessage)
                        .findFirst().map(Result::error)
                        .orElseGet(() -> Result.error("参数错误"))).get();
    }

    /**
     * 请求入参必填验证
     *
     * @param ex 异常
     * @return 封装响应
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> missingRequestParameterException(MissingServletRequestParameterException ex) {
        return Result.error(ex.getMessage());
    }

    /**
     * 服务器内部处理失败异常处理
     *
     * @param ex 异常
     * @return 封装响应
     */
    @ExceptionHandler({Exception.class})
    public Result<?> badRequestException(Exception ex) {
        log.error("服务器处理错误", ex);
        return Result.error("网络不稳定，请稍后再试!");
    }
}
