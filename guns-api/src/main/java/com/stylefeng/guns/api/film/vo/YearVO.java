package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/4 17:05
 */
@Data
public class YearVO implements Serializable {
    private static final long serialVersionUID = 2356995334066726722L;

    private String yearId;

    private String yearName;

    private boolean isActive;
}
