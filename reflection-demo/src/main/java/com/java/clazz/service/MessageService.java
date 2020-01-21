 package com.java.clazz.service;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;
 import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.UUID;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.service
 * @ClassName: Message
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 0:51
 * @Version: 1.0
 */

@Service
public class MessageService {


    public List<User> getUserList(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            User user =new User(UUID.randomUUID().toString(),""+i);
            users.add(user);
        }
        return users;
    }

     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     public class User{
         private String id;
         private String name;
     }

}
