 package com.stu.wapper.util;

 import com.stu.wapper.bean.User;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper.util
 * @ClassName: UserCacheUtils
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/25 11:30
 * @Version: 1.0
 */
public class UserCacheUtils {

     private final Logger logger = LoggerFactory.getLogger(UserCacheUtils.class);

     private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

     public static User get(){
         return userThreadLocal.get();
     }
     public static void set(User user){
         userThreadLocal.set(user);
     }

     public static void clear(){
         userThreadLocal.remove();
     }

}
