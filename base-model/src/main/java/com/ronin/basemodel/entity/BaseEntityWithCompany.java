package com.ronin.basemodel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author lizelong
 * @date Created on 2020/8/13 10:04
 * @description 带有公司ID的业务表基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntityWithCompany extends BaseEntity {
    private static final long serialVersionUID = -8013910101051817130L;

    /**
     * 公司ID
     */
    private String companyId;

    public BaseEntityWithCompany() {
    }

    public BaseEntityWithCompany(String companyId) {
        this.companyId = companyId;
    }

    public BaseEntityWithCompany(LocalDateTime createTime, LocalDateTime editTime, Boolean deleteFlag) {
        super(createTime, editTime, deleteFlag);
    }

    public BaseEntityWithCompany(LocalDateTime createTime, LocalDateTime editTime, Boolean deleteFlag, String companyId) {
        super(createTime, editTime, deleteFlag);
        this.companyId = companyId;
    }
}
