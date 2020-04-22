package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhibin
 * @time 2020/4/22 14:17
 */
@Data
public class ActorVO implements Serializable {
    private static final long serialVersionUID = -1554316414549101851L;
    
    private String imgAddress;
    private String directorName;
    private String roleName;
}
