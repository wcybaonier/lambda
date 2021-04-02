package com.unicom.timedtask.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class MyMetaObjHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasSetter("createTime")){
            setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("updateTime")){
            Object updateTime = getFieldValByName("updateTime", metaObject);
            if(Objects.nonNull(updateTime)){
                setUpdateFieldValByName("updateTime",updateTime,metaObject);
            }else{
                setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
            }
        }
    }
}