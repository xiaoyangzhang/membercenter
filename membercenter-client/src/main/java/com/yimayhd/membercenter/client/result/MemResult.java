package com.yimayhd.membercenter.client.result;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class MemResult<T> extends MemResultSupport {
    private static final long serialVersionUID = 4999091548448313101L;
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MemResult(T value) {
        this.value = value;
    }

    public MemResult() {

    }


    public static <U> MemResult<U> buildFailResult(int errorCode ,String errorMsg, U value) {
        MemResult<U> baseResult = new MemResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(errorCode);
        baseResult.setValue(value);
        baseResult.setErrorMsg(errorMsg);

        return baseResult;
    }

    public static <U> MemResult<U> buildSuccessResult(U value) {
        MemResult<U> baseResult = new MemResult<U>();
        baseResult.setSuccess(true);
        baseResult.setValue(value);
        return baseResult;
    }
}
