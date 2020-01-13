 package com.stu.activiti.domain.listener;

 import lombok.extern.slf4j.Slf4j;
 import org.activiti.engine.delegate.DelegateTask;
 import org.activiti.engine.delegate.TaskListener;

 import java.util.Random;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.listener
 * @ClassName: MangerTaskHandlerCandidateUsers
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/10 10:55
 * @Version: 1.0
 */
@Slf4j
public  class MangerTaskHandlerAssignee implements TaskListener{


     @Override
     public void notify(DelegateTask delegateTask) {
         log.info("====================进入handler====================");
         String[] empLoyees = {"user1","user2","user3"};
         Random random = new Random();
         int i = random.nextInt(3);
         delegateTask.setAssignee(empLoyees[i]);
         log.info("====================setAssignee====================:"+empLoyees[i]);
         log.info("====================离开handler====================");
     }

 }
