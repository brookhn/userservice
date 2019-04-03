package com.pp.userservice.Error;


import java.io.Serializable;

/**
 * 
 * @类名：RuntimeGlobalException .
 * @描述: 全局的runtimeException的基类  .
 * @作者: yakunMeng .
 * @创建时间: 2018年9月18日 上午10:38:45 .
 * @版本号: V1.0 .
 */
public abstract class RuntimeGlobalException extends RuntimeException implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorMethod errorMethod;

    protected Throwable throwable;

    public ErrorMethod getErrorMethod() {
        return this.errorMethod;
    }

    public RuntimeGlobalException(ErrorMethod errorMethod) {
        this(errorMethod, null);
    }


    public RuntimeGlobalException(ErrorMethod errorMethod, Throwable throwable) {
        this.errorMethod = errorMethod;
        this.throwable = throwable;
    }


}
