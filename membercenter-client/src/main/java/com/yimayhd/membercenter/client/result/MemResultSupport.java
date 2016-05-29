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
	private transient MemberReturnCode returnCode;

	public MemResultSupport() {

	}

	public MemResultSupport(MemberReturnCode returnCode) {
		super();
		this.returnCode = returnCode;
		this.errorCode = returnCode.getCode();
		this.errorMsg = returnCode.getDesc();
		this.success = false;
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

	public void setReturnCode(MemberReturnCode memberReturnCode) {
		this.returnCode = memberReturnCode;
		this.success = false;
		this.errorCode = memberReturnCode.getCode();
		this.errorMsg = memberReturnCode.getDesc();
	}
	
}
