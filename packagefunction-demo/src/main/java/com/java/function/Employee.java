 package com.java.function;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.util.ArrayList;
 import java.util.List;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.function
 * @ClassName: Employee
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/13 15:07
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private Double salary;

     public static List<Employee> getEmpList(){
         List<Employee> employees = new ArrayList<>();
         for (int i = 1; i <= 20; i++) {
             employees.add(new Employee("emp"+i,Math.random() * 1000));
         }
         return  employees;
     }

     @Override
     public String toString(){
         return new StringBuilder()
                 .append("name:").append(name)
                 .append("salary:").append(salary).toString();
     }

}
