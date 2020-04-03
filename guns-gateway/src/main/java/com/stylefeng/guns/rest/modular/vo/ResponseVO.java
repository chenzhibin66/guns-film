package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

/**
 * @author chenzhibin
 * @time 2020/3/24 15:09
 */
@Data
public class ResponseVO<T> {
    /**
     * 返回状态
     **/
    private int status;
    /**
     * 返回信息【0-成功，1-业务失败，999-系统异常】
     **/
    private String msg;
    /**
     * 返回数据实体
     **/
    private T data;

    //图片前缀
    private String  imgPre;

    private ResponseVO() {
    }

    public static <T> ResponseVO success(T t) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
        return responseVO;
    }

    public static <T> ResponseVO success(String imgPre,T t) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
        responseVO.setImgPre(imgPre);
        return responseVO;
    }

    public static <T> ResponseVO success(String message) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(message);
        return responseVO;
    }

    /**
     * 业务异常
     **/
    public static <T> ResponseVO serviceFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }

    /**
     * 系统异常
     **/
    public static <T> ResponseVO appFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);
        return responseVO;
    }
}
