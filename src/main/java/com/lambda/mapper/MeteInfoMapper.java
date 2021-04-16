package com.lambda.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lambda.entity.MeteInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeteInfoMapper extends BaseMapper<MeteInfo> {

    /**
     * 查询所有用户信息
     * @return list
     */
    List<MeteInfo> selectAll();
}
