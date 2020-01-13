 package com.stu.design.observer;

 import lombok.Data;
 import org.springframework.context.ApplicationEvent;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.observer
 * @ClassName: ProjectObservable
 * @Author: ZhangSheng
 * @Description: 项目的被观察者
 * @Date: 2019/12/29 1:23
 * @Version: 1.0
 */
@Data
public class ProjectObservable extends ApplicationEvent{

     private String projectName;

     public ProjectObservable(Object source,String projectNmae) {
         super(source);
         this.projectName = projectNmae;
     }

     @Override
     public Object getSource() {
         return super.getSource();
     }
 }
