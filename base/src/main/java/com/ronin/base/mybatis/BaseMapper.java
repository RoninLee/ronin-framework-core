package com.ronin.base.mybatis;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author lizelong
 * @date Created on 2020/7/1 15:14
 * @description 基础mapper
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
