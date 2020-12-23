package com.ronin.basemodel.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author lizelong
 * @date Created on 2020/6/23 10:44
 * @description 基础响应对象
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1704223636052161204L;

    protected Integer code = 200;
    protected String msg = "success";
    protected T data;

    public Result() {
        super();
    }

    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(-1, msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<T>(-1, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result<T>(code, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null, null);
    }

    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<T>();
        if (StringUtils.isNotBlank(msg)) {
            result.setMsg(msg);
            result.setCode(200);
        }
        result.setData(data);
        return result;
    }

    /**
     * 判断结果集是否返回成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return this.code == 200;
    }

}
