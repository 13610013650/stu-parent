 package activiti;

 import com.stu.activiti.domain.service.ActivitiHistoryService;
 import org.activiti.engine.history.HistoricTaskInstance;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.junit4.SpringRunner;

 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.*;
 import java.util.List;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti
 * @ClassName: ActivitiHistoryServiceTest
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 16:37
 * @Version: 1.0
 */
 @RunWith(SpringRunner.class)
 @SpringBootTest
public class ActivitiHistoryServiceTest  {

     @Autowired
     private ActivitiHistoryService activitiHistoryService;


     @Test
     public void testQueryProcessHistoryTask() {
         List<HistoricTaskInstance> historicTaskInstances =
                 activitiHistoryService.queryProcessHistoryTask("user_1");
         historicTaskInstances.stream().forEach(his ->{
             System.out.println(his.getId()+":"+his.getName());
             System.out.println(his.getStartTime());
             System.out.println(his.getEndTime());
         });
     }




     @Test
     public void queryProcessImage() throws IOException {
         InputStream inputStream = activitiHistoryService.queryProcessImage("5001");
         File file = new File("src/main/resources/test06.png");
         if (!file.exists()){
             file.createNewFile();
         }
         OutputStream osm = new FileOutputStream(file);
         BufferedImage bufferedImage = ImageIO.read(inputStream);
         ImageIO.write(bufferedImage,"png",osm);
     }
 }
