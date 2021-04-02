package com.unicom.timedtask.service.impl;

import com.unicom.timedtask.mapper.InsertUseByRoomMapper;
import com.unicom.timedtask.vo.RoomUse;
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
