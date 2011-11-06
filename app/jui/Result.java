package jui;

public class Result {
	
	public Integer statusCode;
	public String message;
	public String navTabId;
	public String rel;
	public String callbackType;
	public String forwardUrl;
	
	public Result() {
		this.statusCode = 200;
		this.message = "操作成功";
		this.callbackType = "closeCurrent";
	}
	
	public Result(Integer statusCode,String message){
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public Result(String navTabId) {
		this.statusCode = 200;
		this.message = "操作成功";
		this.callbackType = "closeCurrent";
		this.navTabId = navTabId;
	}
	
	public Result(String navTabId,boolean close) {
		this.statusCode = 200;
		this.message = "操作成功";
		if(close) this.callbackType = "closeCurrent";
		this.navTabId = navTabId;
	}
	
}
