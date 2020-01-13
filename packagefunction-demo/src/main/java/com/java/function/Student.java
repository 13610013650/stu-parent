 package com.java.function;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.UUID;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.function
 * @ClassName: Student
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/13 10:33
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private  String id;

     private String name;

     private double socre;

     private String office;

     @Override
     public String toString(){
        return new StringBuilder()
                .append("id:").append(id)
                .append(",name:").append(name)
                .append(",socre:").append(socre)
                .append(",office:").append(office).toString();
     }

    public static  List<Student>  getStudentList(){
        List<Student> students =new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            double random = Math.random()*100;
            Student student = new Student(UUID.randomUUID().toString(),"student"+i, random,"office"+i);
            students.add(student);
        }
        return  students;
    }
}
