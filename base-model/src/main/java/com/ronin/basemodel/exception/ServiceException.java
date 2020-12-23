package com.ronin.basemodel.exception;

import com.ronin.basemodel.enums.BaseEnum;

/**
 * @author lizelong
 * @date Created on 2020/7/10 11:19
 * @description 业务异常
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -6873174195938670725L;
    private int code;

    public int getCode() {
        return code;
    }

    public ServiceException(String message) {
        this(-1, message);
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(BaseEnum err) {
        super(err.getName());
        this.code = err.getCode();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}