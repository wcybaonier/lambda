package com.lambda.service.impl;

import com.alibaba.fastjson.JSON;
import com.lambda.mapper.MeteInfoMapper;
import com.lambda.entity.MeteInfo;
import com.lambda.service.MeteInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MeteInfoServiceImpl implements MeteInfoService {

    @Resource
    private MeteInfoMapper meteInfoMapper;

    @Override
    public String selectList(){
        List<MeteInfo> meteInfos = meteInfoMapper.selectList(null);
        meteInfos.forEach(System.out::println);
        return JSON.toJSONString(meteInfos);
    }

    @Override
    public String queryListLambda() {
        return null;
    }
}
