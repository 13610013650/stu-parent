package com.stu.execl.test;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelFileUtil;
import cn.hutool.poi.excel.ExcelPicUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.ExcelSaxUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TestExcel {

    public static void main(String[] args) {

    }

    public static void testDateUtil(){
        DateTime date = DateUtil.date();
        System.out.println(date.toString());
        System.out.println("==================");
        System.out.println(date.dayOfMonth());
        System.out.println("==================");
        System.out.println(DateUtil.formatChineseDate(new Date(),true));
    }

    public static void testExcelUtil(){

    }

    /**
     * 导出excel文件
     * @param response：
     * @param fileName：
     * @param projects：
     * @param columnNames：
     * @param keys：
     * @throws IOException：
     */
    public static void export(HttpServletResponse response, String fileName, List<?> projects, String[] columnNames, String[] keys) throws IOException {
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();
        for (int i = 0; i < columnNames.length; i++) {
            bigWriter.addHeaderAlias(columnNames[i], keys[i]);
            bigWriter.setColumnWidth(i, 20);
        }
        // 一次性写出内容，使用默认样式，强制输出标题
        bigWriter.write(projects, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        bigWriter.flush(out, true);
        // 关闭writer，释放内存
        bigWriter.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

}
