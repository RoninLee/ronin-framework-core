package com.ronin.basemodel.enums;

/**
 * @author lizelong
 * @date Created on 2020/8/10 14:06
 * @description 启用禁用状态
 */
public enum StatusEnum implements BaseEnum {

    /**
     * 启用禁用状态
     */
    DISABLE(0, "禁用"),
    ENABLE(1, "启用"),
    ;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    /**
     * 获取code
     *
     * @return code
     */
    @Override
    public Integer getCode() {
        return code;
    }

    /**
     * 获取枚举名称
     *
     * @return 枚举名称
     */
    @Override
    public String getName() {
        return name;
    }
}
