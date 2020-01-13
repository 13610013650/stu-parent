 package com.java.function;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.function.Consumer;
 import java.util.function.Predicate;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.function
 * @ClassName: TestPredicate
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/13 14:36
 * @Version: 1.0
 */
public class TestPredicate  {

     public static void main(String[] args) {

         List<Student> studentList = Student.getStudentList();
         SocrePredicate socrePredicate = new SocrePredicate();
         studentList.forEach(socrePredicate);
         List<Student> students = socrePredicate.getStudents();
         students.forEach(student -> {
             System.out.println(student.toString());
         });
         System.out.println("===========remove==============");
         studentList.removeIf(new ConditionPredicate());
         for (Student student : studentList) {
             System.out.println(student.toString());
         }
     }

     private static class SocrePredicate implements Consumer<Student> {

         private List<Student> students = new ArrayList<>();

         @Override
         public void accept(Student student) {
             if (new ConditionPredicate().test(student)){
                 students.add(student);
             }
         }
         List<Student> getStudents(){
             return this.students;
         }
     }
     /**
      * @Description 相当于判断条件的进一步封装
      * 作用:判断对象是否符合某个条件  Predicate 断定 断言
      * 使用场景:  ArrayList的removeIf(Predicate)：删除符合条件的元素
      *            如果条件硬编码在ArrayList中，它将提供无数的实现，
      *            但是如果让调用者传入条件，这样ArrayList就可以从复杂和无法猜测的业务中解放出来。
      * 设计思想: 提取条件，让条件从处理逻辑脱离出来，解耦合
      */
     private static class ConditionPredicate implements Predicate<Student>{

         @Override
         public boolean test(Student student) {
             if (student.getSocre() <= 60){
                 return true;
             }
             return false;
         }
     }

 }
