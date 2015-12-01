package com.yimayhd.membercenter;

public class Response {

	private static final String OK = "ok";
	private static final String ERROR = "error";
	

	private Meta meta;
	private Object data;

	public Response success() {
		this.meta = new Meta(true, OK);
		return this;
	}

	public Response success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}

	public Response failure() {
		this.meta = new Meta(false, ERROR);
		return this;
	}

	public Response failure(String message) {
		this.meta = new Meta(false, message,null);
		return this;
	}
	
	public Response failure(String message,int code) {
		this.meta = new Meta(false, message,code);
		return this;
	}

	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {

		private boolean success;
		private String message;
		private int code;

		public Meta(boolean success) {
			this(success,"",null);
		}
		
		public Meta(boolean success, String message) {
			this(success,message,null);
		}
		
		public Meta(boolean success, String message,Integer code) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}
		
		public int getCode(){
			return code;
		}
	}
}