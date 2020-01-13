 package com.java.function;

 import javax.swing.tree.TreeNode;
 import java.util.List;
 import java.util.function.Function;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.function
 * @ClassName: TestFunction
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/13 15:13
 * @Version: 1.0
 */
public class TestFunction {

    public static void main(String[] args) {

        List<Employee> empList = Employee.getEmpList();


    }

     public List<List<Integer>> levelOrder(TreeNode root) {
        return null;
     }

    private static class EmplyeeToExpenss implements Function<Employee,Integer>{

        @Override
        public Integer apply(Employee employee) {
            return null;
        }
    }

}
