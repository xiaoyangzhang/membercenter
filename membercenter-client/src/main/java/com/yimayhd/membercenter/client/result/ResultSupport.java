package com.yimayhd.membercenter.client.result;


import java.io.Serializable;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class ResultSupport implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
    private boolean success = true;
    private String resultMsg;
    private String errorCode;

    public ResultSupport() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
