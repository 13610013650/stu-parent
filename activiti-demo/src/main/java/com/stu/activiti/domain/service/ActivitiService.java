 package com.stu.activiti.domain.service;

 import org.activiti.engine.repository.Deployment;

 import java.io.InputStream;
 import java.util.List;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service
 * @ClassName: ActivitiService
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 14:03
 * @Version: 1.0
 */
public interface ActivitiService {
    /**
     * @Author ZhangSheng
     * @param pngFile : png图片文件路径
     * @param bpmnFile : bpmn文件路径
     * @param category：流程部署的种类
     * @Description 流程部署
     */
    void processDeploymentFile(String pngFile, String bpmnFile, String category);
     /**
      * @Author ZhangSheng
      * @param inputStream : 部署的文件流
      * @param processDeploymentName ：流程部署名称
      * @param key：流程部署的Key
      * @param category：流程部署的种类
      * @Description 流程部署
      */
    void processDeploymentInputStream(InputStream inputStream, String processDeploymentName, String key, String category);
    /**
     * @Author ZhangSheng
     * @param null
     * @Description 查询流程部署列表
     */
    List<Deployment> queryProcessDeploymentList();
}
