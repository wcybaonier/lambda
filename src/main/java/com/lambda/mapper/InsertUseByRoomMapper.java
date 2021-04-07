package com.lambda.mapper;

import com.lambda.vo.RoomUse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InsertUseByRoomMapper {
    void insert(List<RoomUse> roomUses);

    @Delete("delete from spc_room_use")
    void clear();

    @Select("select desc_china from pub_restriction where serial_no=#{useId}")
    String queryRestriction(@Param("useId") String useId);
}
