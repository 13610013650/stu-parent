package com.stu.valied.bean;


import com.stu.valied.excption.ListValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestParam2 {


    @NotBlank
    private  String id;


    private String name;


    private String email;


    private String url;

    @ListValue(vals = {0,1})
    private Integer status;


}
