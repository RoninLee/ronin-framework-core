package com.ronin.base.util.id;

import tk.mybatis.mapper.genid.GenId;

/**
 * @author lizelong
 * @date Created on 2020/8/7 10:20
 * @description 雪花算法主键生成策略
 * 引用方式：@KeySql(genId = SnowflakeIdGenId.class)
 */
public class SnowflakeIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return String.valueOf(IdWorker.getIdWorker().nextId());
    }
}
