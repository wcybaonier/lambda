package com.lambda.vo;

/**
 * 局防房间用途实体
 * @author lcx
 */
public class RoomUse {
    /**房间ID*/
    private String compartmentId;
    /**用途ID*/
    private String useId;
    /**用途名称*/
    private String useName;

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(String compartmentId) {
        this.compartmentId = compartmentId;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

}
