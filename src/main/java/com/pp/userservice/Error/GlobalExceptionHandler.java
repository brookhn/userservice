package com.pp.userservice.Error;

import com.alibaba.druid.util.StringUtils;
import com.pp.userservice.common.HttpObjUtil;
import com.pp.userservice.common.JsonPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @类名：GlobalExceptionHandler .
 * @描述: 统一异常处理  .
 * @作者: yakunMeng .
 * @创建时间: 2018年10月11日 下午6:22:07 .
 * @版本号: V1.0 .
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public void javaExceptionHandler(Exception e) {
		logger.error("捕获到Exception异常", e);
		
		if(e instanceof UnsatisfiedServletRequestParameterException){
			print(ParamErrorCode.COMMON_PARAMETER_VERSION);
		}
		if(e instanceof MissingServletRequestParameterException) {
			print(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(((MissingServletRequestParameterException)e).getParameterName()));
		}
		print(ErrorCode.EXCEPTION);
	}

	@ExceptionHandler(value = GeneralException.class)
	public void generalExceptionHandler(GeneralException e) {
		logger.error("捕获到GeneralException异常", e);
		print(e.getErrorMethod());
	}
	
	private void print(ErrorMethod error){
		HttpServletRequest request = HttpObjUtil.getHttpServletRequest();
		HttpServletResponse response = HttpObjUtil.getHttpServletResponse();
		String format = request.getParameter("format");
		if(StringUtils.isEmpty(format)){
			format = "json";
		}
		if("GET".equals(request.getMethod())){
			JsonPrinter.printObjectSuccess(response, error, null, format, "callback");
		}else{
			JsonPrinter.printObjectAccessSuccess(response, error, null, format, "callback");
		}
	}
}
