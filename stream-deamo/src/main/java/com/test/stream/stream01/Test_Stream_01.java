package com.test.stream.stream01;

import com.sun.org.apache.xpath.internal.SourceTree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import javax.swing.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;

/**
 * @program: stu-parent
 * @description: Jdk8 Stream api 操作实例
 * @author: Mr.Zhang
 * @create: 2019-11-28 16:21
 **/

public class Test_Stream_01 {

    /**
     * @description:  获取 List<Student> 中Student 对象的某个属性集合
     *  stream() 将集合转换成流对象
     *  map(Student::getOfficeNo) 获取对象集合的某个属性集合
     *  collect(Collectors.toList()) 收集 ->  Collectors.toList() 收集到一个list集合;
     *  forEach(System.out::println) 遍历结合打印到控制台
     **/
    @Test
    public void testMap(){
        List<Student> students = getData();
        students.stream()
                .map(Student::getOfficeNo)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * @description: 删选出 List<Student> 中 officeNo 为"N002"的对象集合
     **/
    @Test
    public void  testFilter(){
        List<Student> students = getData();
        students.stream()
                .filter(student -> student.getOfficeNo().equals("N002"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * @description: 按照name分组统计 List<Student> 统计分数
     * Collectors.groupingBy(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)
     * @author Mr.Zhang
     * @date 2019/11/29 11:30
     **/
    @Test
    public void testCount(){
        List<Student> students = getData();
        Map<String, DoubleSummaryStatistics> collect =
                students.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Student::getName,
                                        Collectors.summarizingDouble(Student::getScore)
                                )
                        );

        for (Map.Entry<String,DoubleSummaryStatistics> entry : collect.entrySet()){
            System.out.println(entry.getKey().toString()+ "->" +
                            "  count:" + entry.getValue().getCount()+
                             "  avg:" + entry.getValue().getAverage()+
                             "  max:" +entry.getValue().getMax()+
                             "  min:" +entry.getValue().getMin()+
                              "  sum:" + entry.getValue().getSum());
        }

        Long counting = students.stream().count();
        System.out.println("counting:"+counting);
    }

    /**
    　* @description: 获取list中最大的值
    　* @param
    　* @return
    　* @author Mr.Zhang
    　* @date 2019/11/29 17:00
    　**/
    @Test
    public void testGetMax(){
        List<Student> students = getData();
        Optional<Student> collection = students.stream().max(Comparator.comparing(Student::getScore));
        System.out.println(collection.get());
    }

    /**
     * @description: 分组统计后获取每个分组中最大的值
     * @param
     * @return
     * @author Mr.Zhang
     * @date 2019/11/29 16:18
     **/
    @Test
    public void testGroupMax(){
        List<Student> students = getData();
        Map<String, Optional<Student>> collect =
                students.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Student::getName,
                                        Collectors.maxBy(Comparator.comparing(Student::getScore))
                                )
                        );

        for (Map.Entry<String,  Optional<Student>> entry : collect.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue().get());
        }
    }
    /**
    　* @description: 将list对象转换成map对象
    　* @param
    　* @return
    　* @author Mr.Zhang
    　* @date 2019/12/1 2:12
    　**/
    public void testToMap(){
        List<Student> students = getData();
        Map<String, Student> collect = students.stream().collect(Collectors.toMap(Student::getId,student -> student));
        collect.entrySet().stream().forEach(t->{
            System.out.println(t.getKey()+":"+t.getValue().getName());
        });
    }
    
    


    public static List<Student> getData(){
        List<Student> students = new ArrayList<>();
        Student student1 = new Student(UUID.randomUUID().toString(),"张三","N001",50.0);
        Student student2 = new Student(UUID.randomUUID().toString(),"李四","N002",66.0);
        Student student3 = new Student(UUID.randomUUID().toString(),"王五","N003",79.0);
        Student student4 = new Student(UUID.randomUUID().toString(),"赵六","N004",100.0);
        Student student5 = new Student(UUID.randomUUID().toString(),"钱七","N005",89.0);
        Student student6 = new Student(UUID.randomUUID().toString(),"王六","N005",90.0);
        Student student7 = new Student(UUID.randomUUID().toString(),"李五","N005",98.0);
        Student student8 = new Student(UUID.randomUUID().toString(),"张七","N002",99.0);
        Student student9 = new Student(UUID.randomUUID().toString(),"李五","N005",92.0);
        Student student10 = new Student(UUID.randomUUID().toString(),"张七","N002",94.0);
        Student student11 = new Student(UUID.randomUUID().toString(),"李四","N002",69.1);
        Student student12 = new Student(UUID.randomUUID().toString(),"张三","N001",90.1);
        students.add(student1);students.add(student2);students.add(student3);students.add(student4);
        students.add(student5);students.add(student6);students.add(student7);students.add(student8);
        students.add(student9);students.add(student10);students.add(student11);students.add(student12);
        return students;
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Student{
    private String id;
    private String name;
    private String officeNo;
    private Double score;
}
