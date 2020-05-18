package com.stu.module.test;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhangSheng
 */
@Data
@ToString
public class MyUser implements Serializable {

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}