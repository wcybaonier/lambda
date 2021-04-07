package com.lambda.common.exception;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = 8987317456040055077L;

	public static interface ResultView {
	};

	@JsonView(ResultView.class)
	private boolean success = true;

	@JsonView(ResultView.class)
	private Integer code;

	@JsonView(ResultView.class)
	private T datas;

	@JsonView(ResultView.class)
	private String message = "";

	public Result() {
		this.success = true;
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = ResultEnum.SUCCESS.getMessage();
	}

	public Result(Integer code) {
		this.success = (this.code = code) == 0;
	}

	public Result(T datas) {
		this.success = true;
		this.code = ResultEnum.SUCCESS.getCode();
		this.message = ResultEnum.SUCCESS.getMessage();
		this.datas = datas;
	}

	public Result(Integer code, String message) {
		if (code != null)
			this.success = (this.code = code) == 0;
		this.code = code;
		this.message = message;
	}

	public Result(ResultEnum resultEnum) {
		this(resultEnum.getCode(), resultEnum.getMessage());
	}

	/**
	 * 是否成功
	 *
	 * @return success
	 */
	public boolean isSuccess() {
		return this.success;
	}

	/**
	 * 设置是否成功
	 *
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;

		if (success) {
			code = 0; // 成功
		}
	}

	/**
	 * 得到数据
	 *
	 * @return
	 */
	public T getDatas() {
		return this.datas;
	}

	/**
	 * 设置数据
	 *
	 * @param datas
	 */
	public void setDatas(T datas) {
		this.datas = datas;
	}

	/**
	 * 得到消息
	 *
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 设置消息
	 *
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return resultCode
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setCode(Integer code) {
		this.success = (code != null && code == 0);
		this.code = code;
	}

}
