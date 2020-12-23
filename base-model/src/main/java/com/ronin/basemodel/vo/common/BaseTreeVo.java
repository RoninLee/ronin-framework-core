package com.ronin.basemodel.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizelong
 * @date Created on 2020/8/7 11:17
 * @description 基础树状结构
 */
@ApiModel(description = "基础树状结构对象")
@Data
public class BaseTreeVo<T> implements Serializable {
    private static final long serialVersionUID = -6718998697479561488L;

    @ApiModelProperty("当前节点信息")
    private T parentNode;

    @ApiModelProperty("子节点")
    private List<BaseTreeVo<T>> childNodes;

}
