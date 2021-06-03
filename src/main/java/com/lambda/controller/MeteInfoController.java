package com.lambda.controller;

import com.lambda.service.MeteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/mete")
public class MeteInfoController {

    @Resource
    private MeteInfoService meteInfoService;
    private static final Logger logger = LoggerFactory.getLogger(MeteInfoController.class);

    /**
     *  MyBatisPlus 查询数据库
     * @return
     */
    @RequestMapping("queryList")
    public String selectList(){
        logger.info("-----queryList");
        logger.info("-----queryList11");
        return meteInfoService.selectList();
    }


    /**
     *  MyBatisPlus 查询数据库
     * @return
     */
    @RequestMapping("queryListLambda")
    public String selectListLambda(){
        return meteInfoService.queryListLambda();
    }
    /**
     *  导出
     * @return
     */
    @RequestMapping("excel")
    public String excel(HttpServletResponse response){
        return meteInfoService.excel(response);
    }
}
