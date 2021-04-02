package com.unicom.timedtask.controller;

import com.unicom.timedtask.task.QueryUseByRoomTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 手动执行 定时任务
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private QueryUseByRoomTask queryUseByRoomTask;

    @RequestMapping("/queryUseByRoomTask")
    public void queryUseByRoomTask(){
        queryUseByRoomTask.getUseByRoom();
    }
}
