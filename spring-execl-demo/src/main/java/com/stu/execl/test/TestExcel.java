package com.stu.execl.test;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
public class TestExcel {

    public static void main(String[] args) {
        DateTime date = DateUtil.date();
        System.out.println(date.dayOfMonth());

    }
}
