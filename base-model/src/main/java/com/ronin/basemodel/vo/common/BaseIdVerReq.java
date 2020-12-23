package com.ronin.basemodel.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lizelong
 * @date Created on 2020/8/5 14:00
 * @description 基础ID、Version请求对象
 */
@ApiModel(description = "基础ID、Version请求对象")
@Data
public class BaseIdVerReq<T> implements Serializable {
    private static final long serialVersionUID = -3833602743601448391L;

    @NotNull(message = "未知主键")
    @ApiModelProperty("唯一标识")
    private T id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本号")
    private Integer version;
}
