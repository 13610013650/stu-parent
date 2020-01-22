 package com.java.function;

 import org.junit.Test;

 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.function.Consumer;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.function
 * @ClassName: TestConsumer
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/13 10:33
 * @Version: 1.0
 */
public class TestConsumer {

     public static void main(String[] args) {
         List<Student> studentList = Student.getStudentList();
         SocreConsumer socreConsumer = new SocreConsumer();
         studentList.forEach(socreConsumer);
         Map<Integer ,Student> acceptStudents = socreConsumer.getAcceptStudents();
         for(Map.Entry<Integer,Student> entry:acceptStudents.entrySet()){
             System.out.println("id:"+entry.getKey()+",office:"+entry.getValue().toString());
         }
     }


    /**
     * @Description  找出及格的学生 并且offcie最后两位数子 为偶数的学生
     * Consumer<T>  消费某个对象
     * 使用场景: Iterable接口的forEach方法需要传入Consumer，大部分集合类都实现了该接口，用于返回Iterator对象进行迭代。
     * 设计思想: 多变的逻辑能够封装成一个类（实现Consumer接口），将逻辑提取出来-----> 解耦
     */
    public static class SocreConsumer implements Consumer<Student>{

        private Map<Integer ,Student> acceptStudents = new HashMap<>();

        @Override
        public void accept(Student student) {
            String office = student.getOffice();
            String[] index= office.split("office");
            String result= index[1];
            int serializable = (result == null || result == "") ? 0 : Integer.valueOf(result);
            if (student.getSocre()>=60 && Math.floorMod(serializable,2) == 0 ){
                acceptStudents.put(serializable,student);
            }
        }

        public  Map<Integer ,Student> getAcceptStudents() {
            return acceptStudents;
        }
    }

    @Test
    public void test(){
        String aa = "office19";
        String[] index= aa.split("office");
        for (int i = 0; i < index.length; i++) {
            System.out.println(index[i]);
        }
        String result= index[1];
        System.out.println("result:"+result);
        int serializable = (result == null || result == "") ? 0 : Integer.valueOf(result);
        System.out.println("serializable:" + serializable);
        int i = Math.floorMod(serializable, 2);
        System.out.println("i:"+i);
    }

}

