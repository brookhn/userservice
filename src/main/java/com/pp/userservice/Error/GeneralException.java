package com.pp.userservice.Error;


/**
 * 
 * @类名：GeneralException .
 * @描述: 真麻煩  .
 * @作者: yakunMeng .
 * @创建时间: 2018年9月18日 上午10:39:22 .
 * @版本号: V1.0 .
 */
public class GeneralException extends RuntimeGlobalException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GeneralException(ErrorMethod errorCode) {
        super(errorCode);
    }


    public GeneralException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }


}
