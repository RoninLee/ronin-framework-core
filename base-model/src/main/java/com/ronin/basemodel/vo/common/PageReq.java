package com.ronin.basemodel.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lizelong
 * @date Created on 2020/7/17 13:07
 * @description 分页查询对象
 */
@ApiModel(description = "分页查询对象")
@Data
public class PageReq implements Serializable {
    private static final long serialVersionUID = 9004710577675951684L;

    @NotNull(message = "未知页码")
    @Min(value = 1, message = "页码最小为第1页")
    @ApiModelProperty("页码")
    private Integer pageNum;

    @NotNull(message = "未知页面数据大小")
    @Min(value = 1, message = "条数至少为1条")
    @Max(value = 500, message = "每页最大500条数据")
    @ApiModelProperty("页面数据条数")
    private Integer pageSize;
}
