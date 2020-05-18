package com.stu.valied.bean;


import com.stu.valied.excption.AddGroup;
import com.stu.valied.excption.ListValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestParam {


    @NotBlank(groups = {AddGroup.class})
    private  String id;


    private String name;


    private String email;


    private String url;

    @ListValue(vals = {0,1},groups = {AddGroup.class})
    private Integer status;
}
