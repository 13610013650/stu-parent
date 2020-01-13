 package com.stu.wapper;

 import com.stu.wapper.bean.User;
 import com.stu.wapper.util.UserCacheUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cache.CacheManager;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper
 * @ClassName: TestController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 16:48
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    private CacheManager cacheManager;

     @GetMapping("/success")
    public String success(String param){
        System.out.println("================TestController==================");
        return "SUCCESS";
    }

    @GetMapping("/getUser")
    public User getUser() {
        return UserCacheUtils.get();
    }
}
