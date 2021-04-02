package com.unicom.timedtask.common;

public enum ResultEnum {
	SUCCESS(0, "成功"),
    ERROR(1, "程序异常")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String mssage) {
        this.code = code;
        this.message = mssage;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
