package com.lambda.service.impl;

import com.alibaba.fastjson.JSON;
import com.lambda.mapper.MeteInfoMapper;
import com.lambda.entity.MeteInfo;
import com.lambda.service.MeteInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Override
    public String excel(HttpServletResponse response) throws RuntimeException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<MeteInfo> classmateList = meteInfoMapper.selectAll();

        String fileName = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "id", "华为ID", "华为名称"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (MeteInfo mateInfo : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(mateInfo.getId());
            row1.createCell(1).setCellValue(mateInfo.getMeteId());
            row1.createCell(2).setCellValue(mateInfo.getMeteName());
            rowNum++;
        }

        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
