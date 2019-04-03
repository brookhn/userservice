package com.pp.userservice.common;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pp.userservice.Error.BaseError;
import com.pp.userservice.Error.ErrorMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lalety @Description：
 * @Date：Created in 15:24 2018/8/30
 * @Modify by
 */
public class JsonPrinter {

	public static void notifyAnswer(HttpServletResponse response, String answer) {
		if (response != null && answer != null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			try {
				response.getWriter().println(answer);
				response.getWriter().flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void printJson(HttpServletResponse response, String json) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter write = response.getWriter();
			write.print(json);
			write.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printJsonFormatAndCallBack(HttpServletResponse response, String json, String format, String cb) {
		try {
			if ("jsonp".equalsIgnoreCase(format)) {
				json = cb + "(" + json + ")";
			}
			printJson(response, json);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printError(HttpServletResponse response, BaseError baseError, String format, String cb) {
		JSONObject obj = new JSONObject();
		obj.put("code", baseError.getCode());
		obj.put("msg", baseError.getMsg());
		obj.put("success", false);
		String json = obj.toString();
		printJsonFormatAndCallBack(response, json, format, cb);
	}

	public static void printError(HttpServletResponse response, ErrorMethod error, String format, String cb) {
		JSONObject obj = new JSONObject();
		obj.put("code", error.getErrorCode());
		obj.put("msg", error.getLastMessage());
		String json = obj.toString();
		printJsonFormatAndCallBack(response, json, format, cb);
	}

	public static void printError(HttpServletResponse response, ErrorMethod error) {
		JSONObject obj = new JSONObject();
		obj.put("code", error.getErrorCode());
		obj.put("msg", error.getLastMessage());
		String json = obj.toString();
		printJsonFormatAndCallBack(response, json, "json", "");
	}

	public static void printSuccess(HttpServletResponse response, BaseError baseError, String data, String format,
                                    String cb) {
		// data为json字符串的情况处理
		if (!StringUtils.isEmpty(data)) {
			JSONObject obj = new JSONObject();
			obj.put("code", baseError.getCode());
			obj.put("msg", baseError.getMsg());
			obj.put("success", true);
			obj.put("data", data);
			printJsonFormatAndCallBack(response, obj.toString(), format, cb);
		}
		else {
			JSONObject obj = new JSONObject();
			obj.put("code", baseError.getCode());
			obj.put("msg", baseError.getMsg());
			obj.put("success", true);
			obj.put("data", null);
			String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
			printJsonFormatAndCallBack(response, json, format, cb);
		}

	}

	public static void printObjectSuccess(HttpServletResponse response, BaseError baseError, Object data, String format,
                                          String cb) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", baseError.getCode());
		obj.put("msg", baseError.getMsg());
		obj.put("success", true);
		obj.put("data", data);
		String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		printJsonFormatAndCallBack(response, json, format, cb);
	}

	public static void printObjectSuccess(HttpServletResponse response, ErrorMethod error, Object data, String format,
                                          String cb) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", error.getErrorCode());
		obj.put("msg", error.getLastMessage());
		obj.put("data", data);
		String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		printJsonFormatAndCallBack(response, json, format, cb);
	}

	public static void printPpObjectSuccess(HttpServletResponse response, String errorCode, String message, String format,
                                            String cb)
	{
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("errorCode", errorCode);
		obj.put("message", message);
		String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		printJsonFormatAndCallBack(response, json, format, cb);
	}

	/**
	 * POST跨域接口调用
	 * @param response
	 * @param error
	 * @param data
	 * @param format
	 * @param cb
	 */
	public static void printObjectAccessSuccess(HttpServletResponse response, ErrorMethod error, Object data, String format,
                                                String cb) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", error.getErrorCode());
		obj.put("msg", error.getLastMessage());
		obj.put("data", data);
		String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		printJsonFormatAndCallBack(response, json, format, cb);
	}

//	public static void printObjectPropertyFilterSuccess(HttpServletResponse response, BaseError baseError, Object data,
//			PropertyFilter propertyFilter, String format, String cb) {
//		Map<String, Object> obj = new HashMap<String, Object>();
//		obj.put("code", baseError.getCode());
//		obj.put("msg", baseError.getMsg());
//		obj.put("success", true);
//		obj.put("data", data);
//		String json = JSON.toJSONString(obj, propertyFilter, SerializerFeature.WriteMapNullValue);
//		printJsonFormatAndCallBack(response, json, format, cb);
//	}
//
//	public static String toJSONString(Object obj) {
//		// 禁用fastJson的‘循环引用检测’机制
//		return JSON.toJSONString(obj, new NameFilter() {
//			@Override
//			public String process(Object object, String name, Object value) {
//				return name;
//			}
//		}, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
//				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect);
//	}

}
