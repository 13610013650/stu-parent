 package activiti;

 import com.stu.activiti.domain.service.ActivitiService;
 import com.stu.activiti.domain.service.ActivitiUserGroupService;
 import org.activiti.engine.IdentityService;
 import org.activiti.engine.ManagementService;
 import org.activiti.engine.ProcessEngine;
 import org.activiti.engine.identity.Group;
 import org.activiti.engine.identity.User;
 import org.activiti.engine.repository.ProcessDefinition;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.junit4.SpringRunner;

 import java.io.InputStream;
 import java.util.Map;
 import java.util.UUID;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti
 * @ClassName: ActivitiServiceTest
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 16:38
 * @Version: 1.0
 */
 @RunWith(SpringRunner.class)
 @SpringBootTest
public class ActivitiServiceTest{

     @Autowired
     private ActivitiService activitiService;

     @Autowired
     private ProcessEngine processEngine;

     @Autowired
     private ActivitiUserGroupService userGroupService;

     @Autowired
     private IdentityService identityService;

     @Test
     public void addUserAndGroup(){
         Group group = identityService.newGroup(UUID.randomUUID().toString());
         group.setType("HR_CLASS");
         group.setName("人事组");
         for (int i = 0; i <=5; i++) {
             User user = identityService.newUser(UUID.randomUUID().toString());
             user.setEmail("123@qq.com");user.setFirstName("user");
             user.setLastName(i+"");user.setPassword("123");
             userGroupService.newGroupAndUser(user,group);
         }
     }

     @Test
     public void processDeployment() throws Exception {
         String pngFile = "processes/apply_holiday.png";
         String bpmnFile = "processes/apply_holiday.bpmn";
         activitiService.processDeploymentFile(pngFile,bpmnFile,"人事流程");
     }

     @Test
     public void getFileInputStream() throws  Exception{
         ProcessDefinition processDefinition = processEngine.getRepositoryService()
                 .createProcessDefinitionQuery()
                 .deploymentId("22504").singleResult();
         String deploymentId = processDefinition.getDeploymentId();
         InputStream processModel = processEngine
                 .getRepositoryService()
                 .getProcessModel(deploymentId);
         if (processModel.available()!=0){
             StringBuffer builder = new StringBuffer();
             int available = processModel.available();
             byte [] content = new byte[available];
             processModel.read(content);
             builder.append(content.toString());
             System.out.println(builder.toString());
         }
     }

     @Test
     public void queryProcessDeploymentList() {
         ManagementService managementService = processEngine.getManagementService();
         Map<String, Long> tableCount = managementService.getTableCount();
         for (Map.Entry<String,Long> entry:tableCount.entrySet()){
             if (entry.getValue()>0){
                 System.out.println(entry.getKey()+":"+entry.getValue());
             }
         }
     }
 }
