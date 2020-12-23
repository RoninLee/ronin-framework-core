package com.ronin.basemodel.vo.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lizelong
 * @date Created on 2020/7/3 11:05
 * @description 基础<key, value>格式对象
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "键值对对象")
@Data
public class BaseVo<K, V> implements Serializable {
    private static final long serialVersionUID = -1184540443496599642L;

    private K key;

    private V value;

}
