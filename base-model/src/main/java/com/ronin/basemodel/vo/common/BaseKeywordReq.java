package com.ronin.basemodel.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lizelong
 * @date Created on 2020/7/17 13:18
 * @description 关键字查询基类
 */
@ApiModel(description = "关键字查询基类")
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseKeywordReq extends PageReq {
    private static final long serialVersionUID = 3133422045529468371L;

    @ApiModelProperty("关键字")
    private String keyword;
}
