package com.yimayhd.membercenter.client.result;

import net.pocrd.entity.AbstractReturnCode;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class BaseResult<T> extends ResultSupport {
    private static final long serialVersionUID = 4999091548448313101L;
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BaseResult(T value) {
        this.value = value;
    }

    public BaseResult() {

    }

    public static <U> BaseResult<U> buildFailResult(AbstractReturnCode apiReturnCode, U value) {
        BaseResult<U> baseResult = new BaseResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(String.valueOf(apiReturnCode.getCode()));
        baseResult.setValue(value);
        baseResult.setResultMsg(apiReturnCode.getDesc());

        return baseResult;
    }

    public static <U> BaseResult<U> buildFailResult(String errorCode ,String errorMsg, U value) {
        BaseResult<U> baseResult = new BaseResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(errorCode);
        baseResult.setValue(value);
        baseResult.setResultMsg(errorMsg);

        return baseResult;
    }

    public static <U> BaseResult<U> buildSuccessResult(U value) {
        BaseResult<U> baseResult = new BaseResult<U>();
        baseResult.setSuccess(true);
        baseResult.setValue(value);
        return baseResult;
    }
}
