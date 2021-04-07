package com.lambda.common.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	/**
	 * 自定义异常捕捉
	 * 
	 * @param commonRuntimeException
	 * @return
	 */
	@ExceptionHandler(value = CommonRuntimeException.class)
	public Result CommonExcceptionHandler(CommonRuntimeException commonRuntimeException) {
		logger.error("CommonExcceptionHandler自定义异常捕捉打印：{}",commonRuntimeException);
		return new Result(commonRuntimeException.getCode(), commonRuntimeException.getMessage());
	}

	/**
	 * 所有异常报错
	 *
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public Result allExceptionHandler(Exception exception){
		logger.error("全局异常捕获打印：{}",exception);
		Result r = new Result();
		r.setMessage(exception.getMessage());
		r.setCode(-1);
		r.setSuccess(false);
		JSON.toJSONString(r);
		return r;
	}
}
