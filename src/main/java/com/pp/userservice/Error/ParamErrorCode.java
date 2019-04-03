package com.pp.userservice.Error;

import java.io.Serializable;

/**
 * 
 * @类名：ErrorCode .
 * @描述: *****  .
 * @作者: yakunMeng .
 * @创建时间: 2018年9月20日 下午2:57:30 .
 * @版本号: V1.0 .
 */
public enum ParamErrorCode implements ErrorMethod, Serializable {

    //通用的异常信息
    COMMON_PARAMETER_EMPTY(1001, "参数为空"),
    COMMON_PARAMETER_ILLEGAL(1002, "参数错误"),
    COMMON_PARAMETER_VERSION(1003, "版本号错误")
    ;

    private int errorCode;

    private String errorMessage;

    private String lastMessage;

	ParamErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.lastMessage = errorMessage;
    }
    
    public ParamErrorCode setParam(String param){
    	
    	setLastMessage(getErrorMessage() + " " + param);
    	
    	return this;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}

