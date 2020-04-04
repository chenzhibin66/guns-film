package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/4 17:05
 */
@Data
public class SourceVO implements Serializable {
    private static final long serialVersionUID = 3897165352169003996L;

    private String sourceId;

    private String sourceName;

    private boolean isActive;
}
