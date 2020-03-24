package com.stylefeng.guns.rest.modular.vo;

/**
 * @author chenzhibin
 * @time 2020/3/24 15:09
 */
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

    private ResponseVO() {
    }

    public static <T> ResponseVO success(T t) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
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


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
