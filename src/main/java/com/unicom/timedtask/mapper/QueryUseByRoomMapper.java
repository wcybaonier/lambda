package com.unicom.timedtask.mapper;

import com.unicom.timedtask.vo.RoomUse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QueryUseByRoomMapper {
    @Select("select room_id compartmentId,room_use useId from spc_room_ext where room_use is not null")
    List<RoomUse> query();
}
