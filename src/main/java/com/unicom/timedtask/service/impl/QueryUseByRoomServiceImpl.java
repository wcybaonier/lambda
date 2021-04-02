package com.unicom.timedtask.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.unicom.timedtask.vo.RoomUse;
import com.unicom.timedtask.mapper.QueryUseByRoomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QueryUseByRoomServiceImpl {

    @Resource
    private QueryUseByRoomMapper queryUseByRoomMapper;

    @DS("oracle")
    public List<RoomUse> query() {
        return queryUseByRoomMapper.query();
    }
}
