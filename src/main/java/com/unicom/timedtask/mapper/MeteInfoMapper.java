package com.unicom.timedtask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unicom.timedtask.entity.MeteInfo;

import java.util.List;

public interface MeteInfoMapper extends BaseMapper<MeteInfo> {

    /**
     * 查询所有用户信息
     * @return list
     */
    List<MeteInfo> selectAll();
}
