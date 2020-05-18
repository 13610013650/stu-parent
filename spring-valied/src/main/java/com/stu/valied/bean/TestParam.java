package com.stu.valied.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestParam {


    @NotNull
    private  String id;


    private String name;


    private String email;


    private String url;


    private String status;
}
