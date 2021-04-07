package com.lambda.service.impl;

import com.lambda.mapper.InsertUseByRoomMapper;
import com.lambda.vo.RoomUse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InsertUseByRoomServiceImpl {

    @Resource
    private InsertUseByRoomMapper insertUseByRoomMapper;

    public void insert(List<RoomUse> roomUses) {
        insertUseByRoomMapper.insert(roomUses);
    }

    public void clear() {
        insertUseByRoomMapper.clear();
    }

    public String queryRestriction(String useId) {
        return insertUseByRoomMapper.queryRestriction(useId);
    }
}
