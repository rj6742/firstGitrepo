package com.Bikkadit.Payloads;

public class ApiResponcse {
	
	private String  msg;
	
	private boolean success;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ApiResponcse(String msg, boolean success) {
		super();
		this.msg = msg;
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ApiResponcse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
