 package com.stu.design;

 import com.stu.design.chain.*;
 import com.stu.design.mediator.ConcreteMediator;
 import com.stu.design.observer.ProjectObservable;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.context.ApplicationContext;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RestController;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design
 * @ClassName: test
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/29 1:03
 * @Version: 1.0
 */
@RestController
public class TestCtroller {

     private ApplicationContext applicationContext;

     public TestCtroller(ApplicationContext applicationContext){
       this.applicationContext = applicationContext;
     }

     /**
      * @Author ZhangSheng
      * @param name 名称
      * @Description 测试观察者模式
      */
     @GetMapping("createProject/{name}")
     public String createProject(@PathVariable String name){
          ProjectObservable observable = new ProjectObservable(this,name);
          applicationContext.publishEvent(observable);
          return "SUCCESS";
     }

     /**
      * @Author ZhangSheng
      * @param ins 指令
      * @Description 测试中介者模式
      */
     @GetMapping("testMediator/{ins}")
     public String testMediator(@PathVariable("ins") String ins){
         ConcreteMediator bean = applicationContext.getBean(ConcreteMediator.class);
         bean.bath(ins);
         return "SUCCESS";
     }

     /**
      * @Author ZhangSheng
      * @param
      * @Description 测试责任链模式
      */
     @GetMapping("testChain/{money}")
     public String testChain(@PathVariable("money") String money){
         int mon = Integer.valueOf(money);
         Person person = new Person();
         person.setMoney(mon);
         LeaderInfo leader1 = new Leader500();
         LeaderInfo leader2 = new Leader1000();
         LeaderInfo leader3 = new Leader1500();
         leader1.setCanAuditMoney(500);
         leader2.setCanAuditMoney(1000);
         leader3.setCanAuditMoney(1500);
         leader1.setLeaderInfo(leader2);
         leader2.setLeaderInfo(leader3);
         leader1.dealInfo(person);
         return  "SUCCESS";
     }

}
