package com.ronin.base.util.id;

import tk.mybatis.mapper.genid.GenId;

/**
 * uuid主键生成策略
 * 引用方式：@KeySql(genId = UuidGenId.class)
 * @author luc
 * @date 2020/8/710:41
 */
public class UuidGenId implements GenId {
    @Override
    public String genId(String s, String s1) {
        return IdUtil.fastUUID().toString();
    }
}
