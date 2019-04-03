package com.pp.userservice.Error;

/**
 * 
 * @类名：ErrorMethod .
 * @描述: *****  .
 * @作者: yakunMeng .
 * @创建时间: 2018年9月25日 下午2:05:29 .
 * @版本号: V1.0 .
 */
public interface ErrorMethod {


    public int getErrorCode();

    public String getErrorMessage();

    public String getLastMessage();
}
