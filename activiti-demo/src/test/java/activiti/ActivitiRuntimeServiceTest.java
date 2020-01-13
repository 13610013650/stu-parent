 package activiti;

 import com.stu.activiti.domain.service.ActivitiRuntimeService;
 import org.activiti.engine.runtime.ProcessInstance;
 import org.activiti.engine.task.Task;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.junit4.SpringRunner;

 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti
 * @ClassName: ActivitiRuntimeServiceTest
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 15:16
 * @Version: 1.0
 */
 @RunWith(SpringRunner.class)
 @SpringBootTest
public class ActivitiRuntimeServiceTest {

    @Autowired
    private ActivitiRuntimeService activitiRuntimeService;

    @Test
    public void startProcess(){
        Map<String,Object> variable = new HashMap<>();
        variable.put("assignee","user0");
        variable.put("day",5);
        ProcessInstance processInstance = activitiRuntimeService
                .startProcess("My_HolidayProcess", variable);
        System.out.println("DeploymentId:"+processInstance.getDeploymentId());
        System.out.println("Name:"+processInstance.getName());
        System.out.println("startTime:"+processInstance.getStartTime());
        System.out.println("ProcessDefinitionId:"+processInstance.getProcessDefinitionId());
        System.out.println("ProcessDefinitionKey:"+processInstance.getProcessDefinitionKey());
    }

    @Test
    public void testCompleteTask(){
        Map<String,Object> variable = new HashMap<>();
        activitiRuntimeService.completeProcess("12502",variable);
    }

    @Test
    public void testQueryRuntimeTask(){
        List<Task> tasks =
                activitiRuntimeService.queryRuntimeTask("user_01");
        tasks.stream().forEach(task ->{
            System.out.println("任务id:==>"+task.getId());
            System.out.println("任务名称:==>"+task.getName());
            System.out.println("任务办理人:==>"+task.getAssignee());
            System.out.println("任务种类:==>"+task.getCategory());
            System.out.println("任务执行id:==>"+task.getExecutionId());
            System.out.println("任务描述:==>"+task.getDescription());
            System.out.println("任务的拥有者:==>"+task.getOwner());
            System.out.println("流程定义id:==>"+task.getProcessDefinitionId());
            System.out.println("流程实例:==>"+task.getProcessInstanceId());
            System.out.println("流程定义key:==>"+task.getTaskDefinitionKey());
            System.out.println("流程创建时间:==>"+task.getCreateTime());
            System.out.println("过期时间:==>"+task.getDueDate());
            System.out.println("任务表单key:==>"+task.getFormKey());
        });
    }

}
