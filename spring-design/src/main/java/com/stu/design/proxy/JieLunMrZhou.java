 package com.stu.design.proxy;

 import org.springframework.stereotype.Service;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.proxy
 * @ClassName: JieLunMrZhou
 * @Author: ZhangSheng
 * @Description: 周杰伦
 * @Date: 2020/1/13 15:01
 * @Version: 1.0
 */
@Service
public class JieLunMrZhou implements HoldConcert{

    @Override
    public void sign() {
        System.out.println("杰伦签约sign...");
    }

    @Override
    public void disseminate() {
        System.out.println("杰伦做宣传...");
    }

    @Override
    public void fixUpMeeting() {
        System.out.println("杰伦进行布置会场...");
    }

    @Override
    public void sing() {
        System.out.println("杰伦唱歌...");
    }
}
