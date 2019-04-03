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
public enum ErrorCode implements ErrorMethod, Serializable {

    PPSUCCESS(0, "success"),
    SING_ERROR(1, "签名错误"),
    SUCCESS(1000, "success"),
    EXCEPTION(9999, "服务异常"),
    DEFAULT(500, "网络繁忙"),
    REQUEST_FAIL(501, "请求失败"),

    //用户异常信息
    USER_ERROR(2001, "登陆账户错误，请重新登陆"),
    USER_IP_ERROR(2002, "登陆异常，请重新登陆"),
    USER_HAS_LOGIN(2003, "二维码已登陆，请重新扫码"),
    USER_YK_TOKEN_ERROR(2004, "登陆异常，请重新登陆"),
    USER_NO_EXIST(2005, "用户不存在"),
    
    //商品异常信息
    GOODS_NO_EXIST(3001, "该商品不存在"),
    GOODS_MISMATCHING(3002, "商品信息错误"),
    MERCHANT_NO_EXIST(3003, "商户不存在"),
    GOODS_PRODUCT_NO_EXIST(3004, "商品信息错误"),
    GOODS_STRATEGY_EXCEPTION(3005, "商品价格策略冲突"),

    //支付异常信息
    PAYWAY_NO_EXIST(4001, "请使用微信、支付宝、苏宁金融app扫码"),
    PRE_PAY_ERROR(4002, "预支付失败"),
    PAY_SIGN_CHECK_FAIL(4003, "支付验签失败"),
    //支付通知异常信息
    NOTIFY_CODE_ERROR(5000, "支付回调失败"),
    SIGN_CHECK_FAIL(5002, "支付回调验签失败"),
    PAY_MERCHANT_IS_NULL(4004, "支付商户为空"),
    SIGN_CONTRACT_EROOR(5003, "商户签约失败"),
    UNSIGN_ERROR(5004, "易付宝商户解约失败"),
    UNSIGN_CONTRACT_NO_EXIST(5005, "您不是我们签约用户，无法解约"),
    SN_PAY_TOKEN_NO_EXISTS(5006, "解约失败，请联系客服"),
    SN_UNSIGN_ERROR(5007, "易付宝商户解约失败"),
    
    //订单异常信息
    ORDER_INCOMPLETE(6001, "订单信息错误，请重新扫码"),
    ORDER_SYN_FIND_NO_RECORD(6002, "未找到可用的MAC会员"),
    ORDER_SYN_RESTNUM_NOT_ENOUGH(6003, "MAC会员库存不足"),
    ORDER_NOT_EXISTS(6004, "订单不存在"),
    ORDER_RIGHT_NOT_EXISTS(6005, "没有权益"),
    ORDER_COUPON_IS_USING(6006, "优惠券正在使用中，请稍后重试"),
    ORDER_CARD_IS_USING(6007, "卡密已锁定，请联系客服"),
    ORDER_TICKET_IS_USING(6008, "您已经没有观影券"),
    ORDER_MAC_RESET_NUM_ERROR(6009, "重置MAC会员库存失败"),
    ORDER_CARD_IS_INVALID(6010, "卡密无效"),

    //二维码异常业务
    QRCODE_EXPIRE_ERROR(7001, "二维码失效，请重新扫码"),
    
    //用户已经有单片权益
    QRCODE_USER_HAS_RIGHT(7002, "您已经拥有该影片权益"),
    
    //有观影券可用
    QRCODE_USER_HAS_TICKET(7003, "有观影券可用"),
    
    QRCODE_NOT_EXISTS(7005, "二维码不存在，请重新获取"),
    
    //用户有时长会员包权益错误
    QRCODE_USER_RIGHT(7006, "权益冲突"),

    //观影券异常业务
    TICKET_PACKAGE_NO_EXISTS(11001, "没有找到对应的内容包"),
    
    TICKET_GOODS_ERROR(11002, "观影券商品只能有一个"),
    
    TICKET_NOT_EXISTS(11003, "观影券商品不存在"),

    TICKET_PRODUCT_ERROR(11004, "合作方商品冲突"),

    RIGHT_IS_PROCESSING(11005, "已有权益"),

    //优惠券异常业务
    COUPON_RECEIVE_OVER(12001, "优惠券领取超出限制"),
    ;

    private int errorCode;

    private String errorMessage;

    private String lastMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.lastMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getLastMessage() {
        return lastMessage;
    }


}

