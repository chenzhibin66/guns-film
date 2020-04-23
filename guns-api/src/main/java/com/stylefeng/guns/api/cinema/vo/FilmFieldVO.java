package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/23 17:12
 */
@Data
public class FilmFieldVO implements Serializable {
    private static final long serialVersionUID = 7096651194348820410L;

    private String fieldId;
    private String beginTime;
    private String endTime;
    private String language;

}
