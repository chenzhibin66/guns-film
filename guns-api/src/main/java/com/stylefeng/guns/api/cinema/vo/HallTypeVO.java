package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:06
 */
@Data
public class HallTypeVO implements Serializable {
    private static final long serialVersionUID = 6276116391803263882L;
    /**
     * 影厅id
     */
    private String hallTypeId;
    /**
     * 影厅类型名称
     */
    private String hallTypeName;
    /**
     * 是否有效
     */
    private boolean isActive;
}
