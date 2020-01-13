 package com.stu.activiti.domain.service;

 import org.activiti.engine.runtime.ProcessInstance;
 import org.activiti.engine.task.Task;

 import java.util.List;
 import java.util.Map;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service
 * @ClassName: ActivitiRuntimeService
 * @Author: ZhangSheng
 * @Description: 运行时接口
 * @Date: 2020/1/8 14:14
 * @Version: 1.0
 */
public interface ActivitiRuntimeService {
    /**
     * @Author ZhangSheng
     * @param instancekey 流程实例key
     * @param variable 参数集合
     * @Description 启动一个流程实例
     */
    ProcessInstance startProcess(String instancekey, Map<String, Object> variable);
    /**
     * @Author ZhangSheng
     * @param instanceId 流程实例
     * @param variable 参数
     * @Description 提交流程
     */
    void completeProcess(String instanceId, Map<String, Object> variable);
    /**
     * @Author ZhangSheng
     * @param
     * @Description 查询用户代办任务
     */
    List<Task> queryRuntimeTask(String assignee);
}
