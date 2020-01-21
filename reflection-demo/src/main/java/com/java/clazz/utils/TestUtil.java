 package com.java.clazz.utils;

 import com.java.clazz.aware.ApplicationContextUtil;
 import com.java.clazz.service.MessageService;

 import java.util.List;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.utils
 * @ClassName: TestUtil
 * @Author: ZhangSheng
 * @Description: 测试Util List<MessageService.User></MessageService.User>
 * @Date: 2020/1/19 0:49
 * @Version: 1.0
 */
public class TestUtil {

    public static List<MessageService.User> getMessages(){

        MessageService messageService

                = ApplicationContextUtil.getBean(MessageService.class);

        return messageService.getUserList();
    }

}
