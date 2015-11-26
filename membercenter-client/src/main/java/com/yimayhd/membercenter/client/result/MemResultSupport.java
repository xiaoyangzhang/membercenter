package com.yimayhd.membercenter.client.result;

import java.io.Serializable;

import com.yimayhd.membercenter.MemberReturnCode;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class MemResultSupport implements Serializable {
	private static final long serialVersionUID = -2235152751651905167L;
	private boolean success = true;
	private String errorMsg;
	private int errorCode;
	private MemberReturnCode returnCode;

	public MemResultSupport() {

	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public MemberReturnCode getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(MemberReturnCode returnCode) {
		this.returnCode = returnCode;
		this.success = false;
		this.errorCode = returnCode.getCode();
		this.errorMsg = returnCode.getDesc();
	}
}
