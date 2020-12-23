package com.ronin.basemodel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lizelong
 * @date Created on 2020/8/13 9:53
 * @description 实体表基类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1427766406512024884L;

    /**
     * 创建时间 default CURRENT_TIMESTAMP
     */
    private LocalDateTime createTime;

    /**
     * 更新时间 default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
     */
    private LocalDateTime editTime;

    /**
     * 是否删除 正常:true:1 删除:false:0
     */
    private Boolean deleteFlag;
}
