 package com.stu.activiti.domain.service.impl;
 import com.stu.activiti.domain.service.ActivitiRuntimeService;
 import org.activiti.engine.RuntimeService;
 import org.activiti.engine.TaskService;
 import org.activiti.engine.runtime.ProcessInstance;
 import org.activiti.engine.task.Task;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import java.util.List;
 import java.util.Map;

 /**
* @ProjectName: ativiti-demo
* @Package: com.stu.activiti.domain.service.impl
* @ClassName: ProcessRuntimeService
* @Author: ZhangSheng
* @Description:
* @Date: 2020/1/8 14:29
* @Version: 1.0
*/
@Service
public class ActivitiRuntimeServiceImpl implements ActivitiRuntimeService{

     @Autowired private RuntimeService runtimeService;

     @Autowired private TaskService taskService;

     @Override
     public ProcessInstance startProcess(String instancekey,Map<String,Object> variable) {
         return runtimeService.startProcessInstanceByKey(instancekey,variable);
     }

     @Override
     public void completeProcess(String instanceId,Map<String,Object> variable) {
         taskService.complete(instanceId,variable);
     }

     @Override
     public List<Task> queryRuntimeTask(String userid) {
         return taskService.createTaskQuery().taskAssignee(userid).list();
     }

}
