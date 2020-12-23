package com.ronin.basemodel.enums;

/**
 * @author lizelong
 * @date Created on 2020/7/16 18:09
 * @description 删除标志
 */
public enum DeleteFlagEnum implements BaseEnum {
    /**
     * 删除标志
     */
    DELETED(0, "已删除"),
    NORMAL(1, "正常"),
    ;

    private final Integer code;
    private final String name;

    DeleteFlagEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

}
