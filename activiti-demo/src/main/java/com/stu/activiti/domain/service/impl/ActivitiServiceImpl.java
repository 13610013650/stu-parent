 package com.stu.activiti.domain.service.impl;

 import com.stu.activiti.domain.service.ActivitiService;
 import org.activiti.engine.RepositoryService;
 import org.activiti.engine.repository.Deployment;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.io.InputStream;
 import java.util.List;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service.impl
 * @ClassName: ActivitiServiceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 16:23
 * @Version: 1.0
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

     @Autowired private RepositoryService repositoryService;

     @Override
     public void processDeploymentFile(String pngFile,String bpmnFile,String category) {
         repositoryService.createDeployment()
                 .category(category)
                 .addClasspathResource(pngFile)
                 .addClasspathResource(bpmnFile)
                 .deploy();
     }

     @Override
     public void processDeploymentInputStream(InputStream inputStream, String processDeploymentName, String key, String category) {

     }

     @Override
     public List<Deployment> queryProcessDeploymentList() {
         return repositoryService.createDeploymentQuery().list();
     }

 }
