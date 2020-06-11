package com.test.stream.stream01;
import org.junit.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Stream api 测试使用案例
 */
public class Test_Stream_02 {


    /**
     *  findFirst()
     */
    @Test
    public void test1(){
        List<Student> data = Test_Stream_01.getData();
        String officeNo = data.stream().findFirst().get().getOfficeNo();
        System.out.println(officeNo);
    }


    /**
     *  peek()
     */
    @Test
    public void test2(){
        List<Student> data = Test_Stream_01.getData();
        List<Student> collect = data.stream().peek(e -> e.setId(e.getId().toUpperCase()+"+")).collect(Collectors.toList());
        collect.forEach(e-> System.out.println(e));
    }

    /**
     *  max()
     */
    @Test
    public  void test3(){
        List<Student> data = Test_Stream_01.getData();

        Student s = data.stream().max(Comparator.comparing(Student::getScore)).orElse(null);

        System.out.println(s);
    }

    /**
     *  min()
     */
    @Test
    public void test4(){
        List<Student> data = Test_Stream_01.getData();
        Student s = data.stream()
                .min(Comparator.comparing(Student::getScore))
                .orElse(null);

        System.out.println(s);
    }


    /**
     * forEach()
     * forEachOrdered()
     */
    @Test
    public void test5(){
        List<Student> data = Test_Stream_01.getData();
        data.stream().forEach(e-> System.out.println(e));
        System.out.println("==============");
        data.stream().forEachOrdered(e -> System.out.println(e));
    }


    /**
     *  limit()
     */
    @Test
    public void test6(){
        List<Student> data = Test_Stream_01.getData();
        data.stream().limit(3).forEachOrdered(e -> System.out.println(e));
    }

    /**
     * skip()
     */
    @Test
    public void test7(){
        List<Student> data = Test_Stream_01.getData();
        data.stream().skip(3).forEachOrdered(e -> System.out.println(e.getName()));
    }

    /**
     * map()
     * distinct()
     * collect()
     */
    @Test
    public void test8(){
        List<Student> data = Test_Stream_01.getData();
        List<String> collect = data.stream()
                .map(Student::getName)
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(e-> System.out.println(e));
        System.out.println("-------------------------------------");
        Map<String, Double> collect1 = data.stream().collect(Collectors.toMap(Student::getId, student -> student.getScore()));
        collect1.entrySet().stream().forEachOrdered(e -> System.out.println(e.getKey() + "-" + e.getValue()));

    }

    /**
     * sorted()
     */
    @Test
    public void test9(){
        List<Student> data = Test_Stream_01.getData();
        List<String> collect = Stream.of("a", "b", "g", "d", "c").collect(Collectors.toList());
        collect.stream().sorted().forEach(e -> System.out.print(e+" "));
        System.out.println();
        System.out.println("------------------------------------------------");
        Stream.of(1,9,3,2,4,8,5)
                .collect(Collectors.toList())
                .stream().sorted()
                .forEach(e-> System.out.print(e+" "));
        System.out.println();
        System.out.println("------------------------------------------------");
        List<Student> newData = data.stream().sorted((x,y) -> x.getScore()<y.getScore()?1:-1).collect(Collectors.toList());
        newData.forEach(e-> System.out.println(e));
    }

    @Test
    public void test10(){
        List<Student> data = Test_Stream_01.getData();
        data.stream().map(e -> e.getId()).forEach(e -> System.out.println(e));
        Map<String, Student> collect = data.stream().collect(Collectors.toMap(Student::getId, student -> student));
        collect.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }

    @Test
    public void test11(){
        List<Student> data = Test_Stream_01.getData();
        List<Student> students = data.stream().filter(e -> e.getScore() > 70).collect(Collectors.toList());
        students.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void test12(){
        List<Student> data = Test_Stream_01.getData();
        Map<String, Long> collect = data.stream().collect(Collectors.groupingBy(e -> e.getOfficeNo(), Collectors.counting()));
        collect.entrySet().stream().forEach(e -> System.out.println(e.getKey()+":"+e.getValue()));
    }


}
