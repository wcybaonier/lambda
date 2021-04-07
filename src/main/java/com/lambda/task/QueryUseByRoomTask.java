package com.lambda.task;

import com.lambda.service.impl.InsertUseByRoomServiceImpl;
import com.lambda.service.impl.QueryUseByRoomServiceImpl;
import com.lambda.vo.RoomUse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *定时查询并同步房间用途
 * @author lcx
 */
@Slf4j
@Component
public class QueryUseByRoomTask {

    @Resource
    private QueryUseByRoomServiceImpl queryUseByRoomService;
    @Resource
    private InsertUseByRoomServiceImpl insertUseByRoomService;

    /**
     * 测试五秒执行一次
     * 生产每天凌晨两点执行一次
     */
    //@Scheduled(cron = "0 1/2 * * * ? ")
    @Scheduled(cron = "0 0 2 * * ? ")
    public void getUseByRoom() {
        log.info("查询房间用途开始!");
        //查询房间用途
        List<RoomUse> roomUses=queryUseByRoomService.query();
        //查询字典表
        List<RoomUse> useList = roomUses.stream().peek((roomUs) -> {
            String useName = insertUseByRoomService.queryRestriction(roomUs.getUseId());
            roomUs.setUseName(useName);
        }).collect(Collectors.toList());
        //清空数据库
        insertUseByRoomService.clear();
        //同步到本地库
        log.info("同步数据开始");
        insertUseByRoomService.insert(useList);
        log.info("数据同步成功");
    }
}
