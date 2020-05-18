 package com.stu.wapper.bean;

 import lombok.Data;

 import java.io.Serializable;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper.bean
 * @ClassName: User
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/25 11:05
 * @Version: 1.0
 */
@Data
public class User implements Serializable{

    private String userId;

     private String userName;

     private String password;

     private int age;

}
