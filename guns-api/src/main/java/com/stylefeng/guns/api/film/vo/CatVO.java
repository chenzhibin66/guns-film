package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/4 17:03
 */
@Data
public class CatVO implements Serializable {

    private static final long serialVersionUID = -6922987844675263296L;

    private String catId;

    private String catName;

    private boolean isActive;
}
