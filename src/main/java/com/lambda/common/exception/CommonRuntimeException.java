package com.lambda.common.exception;


public class CommonRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -4037077320871743741L;
	private Integer code;

	public CommonRuntimeException(String message){
        super(message);
        this.code = 1;
    }
    public CommonRuntimeException(Integer code) {
        this.code = code;
    }

    public CommonRuntimeException(Integer code, String message) {
    	super(message);
        this.code = code;
    }

    public CommonRuntimeException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMessage());
    }

    public Integer getCode() {
        return code;
    }
}
