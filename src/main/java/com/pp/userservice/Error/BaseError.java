package com.pp.userservice.Error;

/**
 * @Author: lalety
 * @Description：
 * @Date：Created in 15:19 2018/8/30
 * @Modify by
 */
public class BaseError {
    private Boolean success;
    private int code;
    private String msg;

    public BaseError() {
    }

    public BaseError(Boolean success, int code, String msg) {
        this.setSuccess(success);
        this.setCode(code);
        this.setMsg(msg);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
