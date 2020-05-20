package com.stu.moudle.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestParam implements Serializable {


    private String id;

    private String name;

    private int age;

    private String addr;

}
