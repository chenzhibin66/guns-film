package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/22 14:15
 */
@Data
public class FilmDescVO implements Serializable {

    private static final long serialVersionUID = 3130003098231923897L;
    private String biography;
    private String filmId;
}
