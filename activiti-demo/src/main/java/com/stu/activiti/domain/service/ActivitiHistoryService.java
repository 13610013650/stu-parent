 package com.stu.activiti.domain.service;

 import org.activiti.engine.history.HistoricTaskInstance;

 import java.io.InputStream;
 import java.util.List;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service
 * @ClassName: ActivitiHistoryService
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 14:11
 * @Version: 1.0
 */
public interface ActivitiHistoryService {

    /**
     * @Author ZhangSheng
     * @param userId: 用户id
     * @Description 查询用户历史审批的流程
     */
    List<HistoricTaskInstance> queryProcessHistoryTask(String userId);
    /**
     * @Author ZhangSheng
     * @param instanceId：流程实例id
     * @Description 查询流程图
     */
    InputStream queryProcessImage(String instanceId);
}
